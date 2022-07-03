package com.simplekv.locator;

import com.simplekv.config.Config;
import com.simplekv.config.DatabaseDescriptor;
import com.simplekv.utils.Constants;
import com.simplekv.utils.Hash;
import com.simplekv.utils.KeyRecord;
import com.simplekv.utils.MurmurHash;

import java.util.*;

public class SimpleStrategy implements ReplicationStrategy {

    public Collection<Token> getTokensFromRing(Map<Token, Endpoint> tokenToEndpointMap, KeyRecord keyRecord) {
        Hash hasher = new MurmurHash();
        Config config = DatabaseDescriptor.getConfig();
        String key = keyRecord.getKey();
        Integer tokenIdentifier = hasher.hash(key.getBytes(), key.length(), 0) %
                                                Constants.TOTAL_TOKENS_IN_RING;
        Set<Token> allTokens = tokenToEndpointMap.keySet();
        Set<Token> returnTokens = new HashSet<>();
        for(Token token : allTokens) {
            if(token.tokenIdentifier > tokenIdentifier) {
                returnTokens.add(token);
            }
            if(returnTokens.size() == config.local_replication_factor) break;
        }
        return returnTokens;
    }
}
