# SimpleKVStore

### This is a distributed and highly available key value database based on LSM trees. The architecture is inspired from Dynamo and Cassandra.

## Architecture

This project follows the SEDA (Staged Event Driven Architecture) model which typically gives higher throughput on high volume workloads than typical concurrent models. To know more : https://en.wikipedia.org/wiki/Staged_event-driven_architecture

The system has the following three components:
1. Cluster service
2. Storage Service
3. API service
4. Messaging service