package com.simplekv.locator;

import java.util.HashMap;
import java.util.Map;

public class TokenRing {

    public Map<Token, Endpoint> tokenToEndpointMap = new HashMap<>();
    public Map<Endpoint, Token> endpointToTokenMap = new HashMap<>();


}
