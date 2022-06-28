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
The read and write path take the following sequence

#### Read path
<ul>
    <li>A node receives a get request through the API service. </li>
    <li>The API service then hands over the request to the StorageService which is responsible for finding the replicas and sending the request to the appropriate node.</li>
    <li>The replica nodes for the search key are located based on the replication strategy and replication factor. Currently, there is only one replication strategy(SimpleStrategy) and it finds the replica nodes by walking clockwise on the token ring "replication factor" number of times.</li>
    <li>After the nodes are found, one read request is sent to the first node and read digest request is sent to the n-1 nodes.</li>
    <li>Once the results are found, the read request response is matched with the digest responses. The digest responses are simple hashes of the original data. If they match, the actual data from the read response is sent to the API service to send back to the client. If they do not match then a not found result will be returned to the client. Read repair for the corrupted digest values has not been implemented yet.</li>
    <li>The node that receives the data read request invokes the StorageProxy that does the actual disk access. StorageProxy searches for the key in the following sequence</li>
    <li>First, the in memory memtable is checked for the availability of the data.</li>
    <li>If the data is not found then the in memory bloom filters are checked. There is one bloom filter for each SSTable on disk. The bloom filters will provide the appropriate SSTable name</li>
    <li>If a bloom filter signals that the key has been found then the in memory block index of the corresponding SSTable is searched. Data is stored in blocks(128 keys in each block) in the SSTables and the block index contains the starting index of each block along with the starting key of that block</li>
    <li>A binary search is performed on the block indices to find the starting key of the block where the data may reside</li>
    <li>Once the starting position of the block is found, disk access is done on the SSTable. Starting from the starting location of the block, a linear scan is performed for the next 128 keys until the actual key is found. When the key is found, it's corresponding data is returned.</li>
</ul>



### Failure handling
Temporary node outage will be handled by hinted handoffs in the future, it is not implemented yet. Merkle tree based data comparison will also be implemented for data integrity among nodes.

### Node discovery
Basic gossip protocol is used for node discovery. The gossip starts from the seed nodes spedicied in the config file.

### Conflict resolution
Last write wins strategy is used for write conflict resolution, like Cassandra.