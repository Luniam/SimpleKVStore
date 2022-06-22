package com.simplekv.disk;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static abstract class AbstractReader implements FileReader {
        protected String filename;

        public AbstractReader(String filename) {
            this.filename = filename;
        }
    }

    public static class Reader extends AbstractReader {
        private RandomAccessFile file;

        public Reader(String filename) throws IOException {
            super(filename);
            init();
        }

        private void init() throws IOException {
            File tempFile = new File(filename);
            if(!tempFile.exists()) tempFile.createNewFile();
            file = new RandomAccessFile(tempFile, "r");
        }

        public byte readByte() throws IOException {
            return file.readByte();
        }

        public byte readByte(long position) throws IOException {
            file.seek(position);
            return file.readByte();
        }

        public byte[] readBytes(long position, int len) throws IOException {
            byte[] b = new byte[len];
            file.seek(position);
            file.read(b, 0, len);
            return b;
        }
    }

    public static abstract class AbstractWriter implements FileWriter {
        protected String filename;

        private AbstractWriter(String filename) {
            this.filename = filename;
        }
    }

    public static class Writer extends AbstractWriter {
        private RandomAccessFile file;

        public Writer(String filename) throws IOException {
            super(filename);
            init();
        }

        private void init() throws IOException {
            File tempFile = new File(filename);
            if(!tempFile.exists()) tempFile.createNewFile();
            file = new RandomAccessFile(tempFile, "rw");
        }

        public void appendBytes(byte[] bytes) throws IOException {
            Long filePointer = file.getFilePointer();
            writeBytes(bytes, filePointer);
        }

        public void writeBytes(byte[] bytes, long position) throws IOException {
            file.seek(position);
            file.write(bytes, 0, bytes.length);
        }

        public void closeWriter() throws IOException {
            this.file.close();
        }

        public long getFilePointer() throws IOException {
            return this.file.getFilePointer();
        }
    }

    public static class Serializer implements ObjectSerializer {

        private final ObjectOutputStream objectOutputStream;
        private Serializable serializableObject;

        public Serializer(String filename) throws IOException {
            FileOutputStream fileOutputStream = new FileOutputStream(filename, true);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
        }

        public Serializer(Serializable serializable, String filename) throws IOException {
            this(filename);
            this.serializableObject = serializable;
        }

        @Override
        public void write() throws IOException {
            this.objectOutputStream.writeObject(this.serializableObject);
            this.objectOutputStream.close();
        }

        @Override
        public void write(Serializable serializable) throws IOException {
            this.objectOutputStream.writeObject(serializable);
            this.objectOutputStream.close();
        }
    }

    public static class SerializerAppender implements ObjectSerializerAppender {
        private static class AppendingObjectOutputStream extends ObjectOutputStream {

            public AppendingObjectOutputStream(OutputStream out) throws IOException {
                super(out);
            }

            @Override
            protected void writeStreamHeader() throws IOException {
                // do not write a header, but reset:
                // this line added after another question
                // showed a problem with the original
                reset();
            }
        }

        private AppendingObjectOutputStream appendingObjectOutputStream;
        private ObjectOutputStream objectOutputStream;

        public SerializerAppender(String filename) throws IOException {
            FileOutputStream fileOutputStream = new FileOutputStream(filename, true);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            appendingObjectOutputStream = new AppendingObjectOutputStream(objectOutputStream);
        }

        @Override
        public void append(Serializable serializable) throws IOException {
            this.appendingObjectOutputStream.writeObject(serializable);
        }
    }

    public static class DeSerializer implements ObjectDeSerializer {

        private final ObjectInputStream objectInputStream;

        public DeSerializer(String filename) throws IOException {
            FileInputStream fileInputStream = new FileInputStream(filename);
            objectInputStream = new ObjectInputStream(fileInputStream);
        }

        @Override
        public Object read() throws IOException, ClassNotFoundException {
            return objectInputStream.readObject();
        }

        @Override
        public List<Object> readAll() throws IOException, ClassNotFoundException {
            List<Object> returnObjects = new ArrayList<>();
            try {
                for(;;) {
                    Object readObject = objectInputStream.readObject();
                    returnObjects.add(readObject);
                }
            } catch (EOFException eofException) {}
            return returnObjects;
        }
    }

    public static FileWriter getFileWriter(String fileName) throws IOException {
        return new Writer(fileName);
    }

    public static ObjectSerializer getObjectSerializer(Serializable serializable, String filename)
            throws IOException {
        return new Serializer(serializable, filename);
    }

    public static ObjectSerializer getObjectSerializer(String filename)
            throws IOException {
        return new Serializer(filename);
    }

    public static ObjectSerializerAppender getObjectSerializerAppender(String filename)
            throws IOException {
        return new SerializerAppender(filename);
    }

    public static ObjectDeSerializer getObjectDeSerializer(String filename)
            throws IOException {
        return new DeSerializer(filename);
    }
}
