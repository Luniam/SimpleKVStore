package com.simplekv.service;

import com.simplekv.config.Config;
import com.simplekv.config.DatabaseDescriptor;
import com.simplekv.locator.Token;
import com.simplekv.locator.TokenRing;
import com.simplekv.storage.Command;
import com.simplekv.storage.ReadCommand;
import com.simplekv.storage.StorageService;
import com.simplekv.utils.DataRecord;
import com.simplekv.utils.DataReturnRecord;
import com.simplekv.utils.KeyRecord;

import java.util.Collection;

public class StorageProxy {

    private static final StorageService storageService = StorageService.loadInstance();

    public static DataReturnRecord get(ReadCommand command) {
        DataRecord dataRecord = command.dataRecord;
        KeyRecord keyRecord = dataRecord.getKey();

        if(command.command == Command.CommandType.READ) {
            return storageService.get(keyRecord, false);
        }

        Config config = DatabaseDescriptor.getConfig();
        TokenRing tokenRing = TokenRing.loadInstance();
        Collection<Token> tokens = tokenRing.getTokensFromKey(keyRecord);
        //Not enough nodes to satisfy the replication factor
        if(tokens.size() < config.local_replication_factor)
            return null;
        return null;
    }
}
