package com.simplekv.locator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class TokenRing {

    public static TokenRing instance;
    public static ReentrantLock lock = new ReentrantLock();

    public Map<Token, Endpoint> tokenToEndpointMap = new HashMap<>();
    public Map<Endpoint, Token> endpointToTokenMap = new HashMap<>();


    public static TokenRing loadInstance() {
        if(instance == null) {
            try {
                lock.lock();
                if(instance == null) {
                    instance = new TokenRing();
                    Token token = TokenManager.getSelfNodeToken();
                    Endpoint endpoint = new Endpoint.EndpointBuilder().getForSelfFromConfig().build();
                    instance.tokenToEndpointMap.put(token, endpoint);
                    instance.endpointToTokenMap.put(endpoint, token);
                }

            } finally {
                lock.unlock();
            }
        }
        return instance;
    }
}
