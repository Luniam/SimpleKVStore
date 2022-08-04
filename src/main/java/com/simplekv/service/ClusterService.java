package com.simplekv.service;

import com.simplekv.locator.TokenRing;

public class ClusterService {

    public static TokenRing tokenRing;

    public static void createSelfTokenForRing() {
        tokenRing = TokenRing.loadInstance();
    }

    public static void startGossip() {
        Thread gossipThread = new Thread(() -> {

        });
    }
}
