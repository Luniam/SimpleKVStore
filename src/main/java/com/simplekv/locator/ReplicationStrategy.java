package com.simplekv.locator;

import com.simplekv.utils.KeyRecord;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public interface ReplicationStrategy {
    Collection<Token> getTokensFromRing(Map<Token, Endpoint> tokenEndpointMap, KeyRecord keyRecord);
}
