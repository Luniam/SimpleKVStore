package com.simplekv.disk;

/**
 * SSTable format
 * |...4 byte key size...|...key...|...8 byte timestamp...|...1 byte tombstone...|...4 byte data size...|...data...|
 */
public class SSTableTemplate extends AbstractSSTableTemplate {


    @Override
    protected void writeData() {

    }

    @Override
    protected void writeDataHeader() {

    }

    @Override
    protected void writeKey() {

    }

    @Override
    protected void writeKeyHeader() {

    }
}
