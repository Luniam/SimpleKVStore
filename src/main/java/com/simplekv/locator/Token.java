package com.simplekv.locator;

public class Token {

    public Integer tokenIdentifier;
    public boolean isVNode = true;
    public Token parentToken;

    @Override
    public boolean equals(Object that) {
        return that instanceof Token &&
                this.tokenIdentifier.equals(((Token) that).tokenIdentifier);
    }
}
