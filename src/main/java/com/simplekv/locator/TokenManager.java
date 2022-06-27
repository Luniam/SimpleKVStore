package com.simplekv.locator;

import com.simplekv.utils.RandomUtils;

public class TokenManager {

    public static Token getSelfNodeToken() {
        Token token = new Token();
        token.tokenIdentifier = RandomUtils.getNodeTokenIdentifier();
        return token;
    }
}
