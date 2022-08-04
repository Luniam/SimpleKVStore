package com.simplekv.locator;

import com.simplekv.utils.RandomUtils;

public class TokenManager {

    public static Token getRandomToken() {
        Token token = new Token();
        token.tokenIdentifier = RandomUtils.getNodeTokenIdentifier();
        return token;
    }

    public static Token getVirtualTokenOfParent(Token token) {
        Integer tokenIdentifier = token.tokenIdentifier;
        int newIdentifier = RandomUtils.getNodeTokenIdentifier();
        if(newIdentifier <= tokenIdentifier) newIdentifier+=tokenIdentifier;
        Token vToken = new Token();
        vToken.tokenIdentifier = newIdentifier;
        vToken.parentToken = token;
        vToken.isVNode = true;
        return vToken;
    }
}
