# SimpleKVStore


[![Luniam](https://circleci.com/gh/Luniam/SimpleKVStore.svg?style=svg)](https://app.circleci.com/pipelines/github/Luniam/SimpleKVStore)

### SimpleKVStore is a distributed and highly available key value database based on LSM trees. The architecture is inspired from Dynamo and Cassandra.

### Architecture

This project follows the SEDA (Staged Event Driven Architecture) model which typically gives higher throughput than regular concurrent models. To know more : https://en.wikipedia.org/wiki/Staged_event-driven_architecture

The system has the following four components:
1. Cluster service
2. Storage Service
3. API service
4. Messaging service

The Stages or services do not talk to each other directly but communicate through asynchronous task queues. Each stage has a variable number of workers to process the tasks assigned to the stage.

### Config
The config file is located in the conf/ directory as simplekv.yaml. The options are commented for better understanding.

### Storage Engine
LSM tree based storage is used for the key value pairs. Data is first written into the in memory memtable and write ahead log. After the memtable reaches a certain size it is flushed in SStables in disk.

### Storage path
The read and write path takes the following sequence

#### Read path
<ul>
    <li>A node receives a get request through the API service. </li>
    <li>The API service then hands over the request to the StorageService which is responsible for finding the replicas and sending the request to the appropriate node.</li>
    <li>The replica nodes for the search key are located based on the replication strategy and replication factor. Currently, there is only one replication strategy(SimpleStrategy) and it finds the replica nodes by walking clockwise on the token ring "replication factor" number of times.</li>
    <li>After the nodes are found, one read request is sent to the first node and read digest request is sent to the n-1 nodes.</li>
    <li>Once the results are found, the read request response is matched with the digest responses. The digest responses are simple hashes of the original data. If they match the actual data from the read response is sent to the API service to send back to the client. If they do not match then a not found result will be returned to the client. Read repair for the corrupted digest values has not been implemented yet.</li>
</ul>



### Failure handling
Temporary node outage will be handled by hinted handoffs in the future, it is not implemented yet. Merkle tree based data comparison will also be implemented for data integrity among nodes.

### Node discovery
Basic gossip protocol is used for node discovery. The gossip starts from the seed nodes spedicied in the config file.

### Conflict resolution
Last write wins strategy is used for write conflict resolution, like Cassandra.