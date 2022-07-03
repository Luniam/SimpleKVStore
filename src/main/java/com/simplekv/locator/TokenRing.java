package com.simplekv.locator;

import com.simplekv.config.Config;
import com.simplekv.config.DatabaseDescriptor;
import com.simplekv.utils.KeyRecord;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class TokenRing {

    public static TokenRing instance;
    public static ReentrantLock lock = new ReentrantLock();

    public Map<Token, Endpoint> tokenToEndpointMap = new TreeMap<>(Comparator.comparingInt(t -> t.tokenIdentifier));
    public Map<Endpoint, Token> endpointToTokenMap = new HashMap<>();


    public static TokenRing loadInstance() {
        if(instance == null) {
            try {
                lock.lock();
                if(instance == null) {
                    instance = new TokenRing();
                    Config config = DatabaseDescriptor.getConfig();
                    Token token = TokenManager.getRandomToken();
                    token.isVNode = false;
                    Endpoint endpoint = new Endpoint.EndpointBuilder().getForSelfFromConfig().build();
                    instance.placeTokenToRing(token, endpoint);
                    int vNodes = config.num_tokens;
                    for(int i = 1; i < vNodes; i++) {
                        Token vToken = TokenManager.getRandomToken();
                        vToken.parentToken = token;
                        instance.placeTokenToRing(vToken, endpoint);
                    }
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private void placeTokenToRing(Token token, Endpoint endpoint) {
        tokenToEndpointMap.put(token, endpoint);
        endpointToTokenMap.put(endpoint, token);
    }

    public Collection<Token> getTokensFromKey(KeyRecord keyRecord) {
        ReplicationStrategy replicationStrategy = ReplicationStrategyFactory.getReplicationStrategy();
        return replicationStrategy.getTokensFromRing(tokenToEndpointMap, keyRecord);
    }
}
