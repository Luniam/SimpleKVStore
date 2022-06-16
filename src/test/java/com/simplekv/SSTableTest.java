package com.simplekv;

import com.simplekv.utils.DataRecord;
import com.simplekv.utils.KeyRecord;
import com.simplekv.utils.ValueRecord;
import org.junit.Test;

public class SSTableTest {

    @Test
    public void testSSTableCreation() {
        for(int i = 0; i < 128; i++) {
            KeyRecord key = new KeyRecord("Mahi" + i);
            ValueRecord value = new ValueRecord("test-key" + i);
            DataRecord dataRecord = new DataRecord(key, value);

        }
    }
}
