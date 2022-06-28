package com.simplekv.locator;

import com.simplekv.config.Config;
import com.simplekv.config.DatabaseDescriptor;

import java.net.InetSocketAddress;

public class Endpoint {

    public InetSocketAddress address;

    private Endpoint(EndpointBuilder endpointBuilder) {
        this.address = endpointBuilder.address;
    }

    public static class EndpointBuilder {
        public InetSocketAddress address;

        public EndpointBuilder() {}
        public EndpointBuilder getForSelfFromConfig() {
            Config config = DatabaseDescriptor.getConfig();
            this.address = new InetSocketAddress(config.node_host, config.node_port);
            return this;
        }

        public Endpoint build() {
            return new Endpoint(this);
        }
    }
}
