package com.simplekv.config;

public class Config {

    public Config() {}

    public String cluster_name;
    public String node_host;
    public Integer node_port;
    public String data_directory;
    public Integer num_tokens;
    public Integer local_replication_factor;
    public String endpoint_snitch;
    public String replication_strategy;
}
