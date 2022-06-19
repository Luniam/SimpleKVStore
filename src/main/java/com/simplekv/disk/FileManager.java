package com.simplekv.disk;

import java.io.*;

public class FileManager {

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

        public void writeBytes(byte[] bytes, Long position) throws IOException {
            file.seek(position);
            file.write(bytes, 0, bytes.length);
        }
    }

    public static class Serializer implements ObjectSerializer {

        private final ObjectOutputStream objectOutputStream;
        private final Serializable serializableObject;

        public Serializer(Serializable serializable, String filename) throws IOException {
            this.serializableObject = serializable;
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
        }

        @Override
        public void write() throws IOException {
            this.objectOutputStream.writeObject(this.serializableObject);
        }
    }

    public static FileWriter getFileWriter(String fileName) throws IOException {
        return new Writer(fileName);
    }

    public static ObjectSerializer getObjectSerializer(Serializable serializable, String filename)
            throws IOException {
        return new Serializer(serializable, filename);
    }
}
