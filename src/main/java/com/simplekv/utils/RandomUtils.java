package com.simplekv.utils;

import java.util.Random;

public class RandomUtils {

    public static int getNodeTokenIdentifier() {
        Random rand = new Random(System.currentTimeMillis());
        return rand.nextInt(Constants.TOTAL_TOKENS_IN_RING);
    }
}
