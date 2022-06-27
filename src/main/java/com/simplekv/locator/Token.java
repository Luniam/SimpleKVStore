package com.simplekv.locator;

public class Token {

    public Integer tokenIdentifier;

    @Override
    public boolean equals(Object that) {
        return that instanceof Token &&
                this.tokenIdentifier.equals(((Token) that).tokenIdentifier);
    }
}
