# SimpleKVStore


[![Luniam](https://circleci.com/gh/Luniam/SimpleKVStore.svg?style=svg)](https://app.circleci.com/pipelines/github/Luniam/SimpleKVStore?filter=all)

### SimpleKVStore is a distributed and highly available key value database based on LSM trees. The architecture is inspired from Dynamo and Cassandra.

### Architecture

This project follows the SEDA (Staged Event Driven Architecture) model which typically gives higher throughput than regular concurrent models. To know more : https://en.wikipedia.org/wiki/Staged_event-driven_architecture

The system has the following four components:
1. Cluster service
2. Storage Service
3. API service
4. Messaging service

### Config
The config file is located in the conf/ directory as simplekv.yaml. The options are commented for better understanding.

### Storage
LSM tree based storage is used for the key value pairs. Data is first written into the in memory memtable and write ahead log. After the memtable reaches a certain size it is flushed in SStables in disk.

### Failure handling
Temporary node outage will be handled by hinted handoffs in the future, it is not implemented yet. Merkle tree based data comparison will also be implemented for data integrity among nodes.

### Node discovery
Basic gossip protocol is used for node discovery. The gossip starts from the seed nodes spedicied in the config file.

### Conflict resolution
Last write wins strategy is used for write conflict resolution, like Cassandra.