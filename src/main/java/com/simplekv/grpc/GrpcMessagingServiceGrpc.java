package com.simplekv.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.46.0)",
    comments = "Source: MessagingService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class GrpcMessagingServiceGrpc {

  private GrpcMessagingServiceGrpc() {}

  public static final String SERVICE_NAME = "com.simplekv.grpc.GrpcMessagingService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.simplekv.grpc.ReadRequest,
      com.simplekv.grpc.ReadResponse> getGetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "get",
      requestType = com.simplekv.grpc.ReadRequest.class,
      responseType = com.simplekv.grpc.ReadResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.simplekv.grpc.ReadRequest,
      com.simplekv.grpc.ReadResponse> getGetMethod() {
    io.grpc.MethodDescriptor<com.simplekv.grpc.ReadRequest, com.simplekv.grpc.ReadResponse> getGetMethod;
    if ((getGetMethod = GrpcMessagingServiceGrpc.getGetMethod) == null) {
      synchronized (GrpcMessagingServiceGrpc.class) {
        if ((getGetMethod = GrpcMessagingServiceGrpc.getGetMethod) == null) {
          GrpcMessagingServiceGrpc.getGetMethod = getGetMethod =
              io.grpc.MethodDescriptor.<com.simplekv.grpc.ReadRequest, com.simplekv.grpc.ReadResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "get"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.simplekv.grpc.ReadRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.simplekv.grpc.ReadResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GrpcMessagingServiceMethodDescriptorSupplier("get"))
              .build();
        }
      }
    }
    return getGetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.simplekv.grpc.WriteRequest,
      com.simplekv.grpc.WriteResponse> getPutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "put",
      requestType = com.simplekv.grpc.WriteRequest.class,
      responseType = com.simplekv.grpc.WriteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.simplekv.grpc.WriteRequest,
      com.simplekv.grpc.WriteResponse> getPutMethod() {
    io.grpc.MethodDescriptor<com.simplekv.grpc.WriteRequest, com.simplekv.grpc.WriteResponse> getPutMethod;
    if ((getPutMethod = GrpcMessagingServiceGrpc.getPutMethod) == null) {
      synchronized (GrpcMessagingServiceGrpc.class) {
        if ((getPutMethod = GrpcMessagingServiceGrpc.getPutMethod) == null) {
          GrpcMessagingServiceGrpc.getPutMethod = getPutMethod =
              io.grpc.MethodDescriptor.<com.simplekv.grpc.WriteRequest, com.simplekv.grpc.WriteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "put"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.simplekv.grpc.WriteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.simplekv.grpc.WriteResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GrpcMessagingServiceMethodDescriptorSupplier("put"))
              .build();
        }
      }
    }
    return getPutMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.simplekv.grpc.GossipMessage,
      com.simplekv.grpc.GossipResponse> getGossipMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "gossip",
      requestType = com.simplekv.grpc.GossipMessage.class,
      responseType = com.simplekv.grpc.GossipResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.simplekv.grpc.GossipMessage,
      com.simplekv.grpc.GossipResponse> getGossipMethod() {
    io.grpc.MethodDescriptor<com.simplekv.grpc.GossipMessage, com.simplekv.grpc.GossipResponse> getGossipMethod;
    if ((getGossipMethod = GrpcMessagingServiceGrpc.getGossipMethod) == null) {
      synchronized (GrpcMessagingServiceGrpc.class) {
        if ((getGossipMethod = GrpcMessagingServiceGrpc.getGossipMethod) == null) {
          GrpcMessagingServiceGrpc.getGossipMethod = getGossipMethod =
              io.grpc.MethodDescriptor.<com.simplekv.grpc.GossipMessage, com.simplekv.grpc.GossipResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "gossip"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.simplekv.grpc.GossipMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.simplekv.grpc.GossipResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GrpcMessagingServiceMethodDescriptorSupplier("gossip"))
              .build();
        }
      }
    }
    return getGossipMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GrpcMessagingServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GrpcMessagingServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GrpcMessagingServiceStub>() {
        @java.lang.Override
        public GrpcMessagingServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GrpcMessagingServiceStub(channel, callOptions);
        }
      };
    return GrpcMessagingServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GrpcMessagingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GrpcMessagingServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GrpcMessagingServiceBlockingStub>() {
        @java.lang.Override
        public GrpcMessagingServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GrpcMessagingServiceBlockingStub(channel, callOptions);
        }
      };
    return GrpcMessagingServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GrpcMessagingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GrpcMessagingServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GrpcMessagingServiceFutureStub>() {
        @java.lang.Override
        public GrpcMessagingServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GrpcMessagingServiceFutureStub(channel, callOptions);
        }
      };
    return GrpcMessagingServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class GrpcMessagingServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void get(com.simplekv.grpc.ReadRequest request,
        io.grpc.stub.StreamObserver<com.simplekv.grpc.ReadResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetMethod(), responseObserver);
    }

    /**
     */
    public void put(com.simplekv.grpc.WriteRequest request,
        io.grpc.stub.StreamObserver<com.simplekv.grpc.WriteResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPutMethod(), responseObserver);
    }

    /**
     */
    public void gossip(com.simplekv.grpc.GossipMessage request,
        io.grpc.stub.StreamObserver<com.simplekv.grpc.GossipResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGossipMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.simplekv.grpc.ReadRequest,
                com.simplekv.grpc.ReadResponse>(
                  this, METHODID_GET)))
          .addMethod(
            getPutMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.simplekv.grpc.WriteRequest,
                com.simplekv.grpc.WriteResponse>(
                  this, METHODID_PUT)))
          .addMethod(
            getGossipMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.simplekv.grpc.GossipMessage,
                com.simplekv.grpc.GossipResponse>(
                  this, METHODID_GOSSIP)))
          .build();
    }
  }

  /**
   */
  public static final class GrpcMessagingServiceStub extends io.grpc.stub.AbstractAsyncStub<GrpcMessagingServiceStub> {
    private GrpcMessagingServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcMessagingServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GrpcMessagingServiceStub(channel, callOptions);
    }

    /**
     */
    public void get(com.simplekv.grpc.ReadRequest request,
        io.grpc.stub.StreamObserver<com.simplekv.grpc.ReadResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void put(com.simplekv.grpc.WriteRequest request,
        io.grpc.stub.StreamObserver<com.simplekv.grpc.WriteResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPutMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void gossip(com.simplekv.grpc.GossipMessage request,
        io.grpc.stub.StreamObserver<com.simplekv.grpc.GossipResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGossipMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GrpcMessagingServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<GrpcMessagingServiceBlockingStub> {
    private GrpcMessagingServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcMessagingServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GrpcMessagingServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.simplekv.grpc.ReadResponse get(com.simplekv.grpc.ReadRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.simplekv.grpc.WriteResponse put(com.simplekv.grpc.WriteRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPutMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.simplekv.grpc.GossipResponse gossip(com.simplekv.grpc.GossipMessage request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGossipMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GrpcMessagingServiceFutureStub extends io.grpc.stub.AbstractFutureStub<GrpcMessagingServiceFutureStub> {
    private GrpcMessagingServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcMessagingServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GrpcMessagingServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.simplekv.grpc.ReadResponse> get(
        com.simplekv.grpc.ReadRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.simplekv.grpc.WriteResponse> put(
        com.simplekv.grpc.WriteRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPutMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.simplekv.grpc.GossipResponse> gossip(
        com.simplekv.grpc.GossipMessage request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGossipMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET = 0;
  private static final int METHODID_PUT = 1;
  private static final int METHODID_GOSSIP = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GrpcMessagingServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GrpcMessagingServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET:
          serviceImpl.get((com.simplekv.grpc.ReadRequest) request,
              (io.grpc.stub.StreamObserver<com.simplekv.grpc.ReadResponse>) responseObserver);
          break;
        case METHODID_PUT:
          serviceImpl.put((com.simplekv.grpc.WriteRequest) request,
              (io.grpc.stub.StreamObserver<com.simplekv.grpc.WriteResponse>) responseObserver);
          break;
        case METHODID_GOSSIP:
          serviceImpl.gossip((com.simplekv.grpc.GossipMessage) request,
              (io.grpc.stub.StreamObserver<com.simplekv.grpc.GossipResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GrpcMessagingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GrpcMessagingServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.simplekv.grpc.MessagingService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GrpcMessagingService");
    }
  }

  private static final class GrpcMessagingServiceFileDescriptorSupplier
      extends GrpcMessagingServiceBaseDescriptorSupplier {
    GrpcMessagingServiceFileDescriptorSupplier() {}
  }

  private static final class GrpcMessagingServiceMethodDescriptorSupplier
      extends GrpcMessagingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GrpcMessagingServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GrpcMessagingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GrpcMessagingServiceFileDescriptorSupplier())
              .addMethod(getGetMethod())
              .addMethod(getPutMethod())
              .addMethod(getGossipMethod())
              .build();
        }
      }
    }
    return result;
  }
}
