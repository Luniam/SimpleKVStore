// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MessagingService.proto

package com.simplekv.grpc;

/**
 * Protobuf type {@code com.simplekv.grpc.ReadResponse}
 */
public  final class ReadResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.simplekv.grpc.ReadResponse)
    ReadResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ReadResponse.newBuilder() to construct.
  private ReadResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ReadResponse() {
    key_ = "";
    vale_ = com.google.protobuf.ByteString.EMPTY;
    digest_ = com.google.protobuf.ByteString.EMPTY;
    executionTime_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ReadResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            key_ = s;
            break;
          }
          case 18: {

            vale_ = input.readBytes();
            break;
          }
          case 26: {

            digest_ = input.readBytes();
            break;
          }
          case 32: {

            executionTime_ = input.readUInt64();
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.simplekv.grpc.MessagingService.internal_static_com_simplekv_grpc_ReadResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.simplekv.grpc.MessagingService.internal_static_com_simplekv_grpc_ReadResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.simplekv.grpc.ReadResponse.class, com.simplekv.grpc.ReadResponse.Builder.class);
  }

  public static final int KEY_FIELD_NUMBER = 1;
  private volatile java.lang.Object key_;
  /**
   * <code>string key = 1;</code>
   */
  public java.lang.String getKey() {
    java.lang.Object ref = key_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      key_ = s;
      return s;
    }
  }
  /**
   * <code>string key = 1;</code>
   */
  public com.google.protobuf.ByteString
      getKeyBytes() {
    java.lang.Object ref = key_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      key_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int VALE_FIELD_NUMBER = 2;
  private com.google.protobuf.ByteString vale_;
  /**
   * <code>bytes vale = 2;</code>
   */
  public com.google.protobuf.ByteString getVale() {
    return vale_;
  }

  public static final int DIGEST_FIELD_NUMBER = 3;
  private com.google.protobuf.ByteString digest_;
  /**
   * <code>bytes digest = 3;</code>
   */
  public com.google.protobuf.ByteString getDigest() {
    return digest_;
  }

  public static final int EXECUTIONTIME_FIELD_NUMBER = 4;
  private long executionTime_;
  /**
   * <code>uint64 executionTime = 4;</code>
   */
  public long getExecutionTime() {
    return executionTime_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getKeyBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, key_);
    }
    if (!vale_.isEmpty()) {
      output.writeBytes(2, vale_);
    }
    if (!digest_.isEmpty()) {
      output.writeBytes(3, digest_);
    }
    if (executionTime_ != 0L) {
      output.writeUInt64(4, executionTime_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getKeyBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, key_);
    }
    if (!vale_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, vale_);
    }
    if (!digest_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(3, digest_);
    }
    if (executionTime_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(4, executionTime_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.simplekv.grpc.ReadResponse)) {
      return super.equals(obj);
    }
    com.simplekv.grpc.ReadResponse other = (com.simplekv.grpc.ReadResponse) obj;

    boolean result = true;
    result = result && getKey()
        .equals(other.getKey());
    result = result && getVale()
        .equals(other.getVale());
    result = result && getDigest()
        .equals(other.getDigest());
    result = result && (getExecutionTime()
        == other.getExecutionTime());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + KEY_FIELD_NUMBER;
    hash = (53 * hash) + getKey().hashCode();
    hash = (37 * hash) + VALE_FIELD_NUMBER;
    hash = (53 * hash) + getVale().hashCode();
    hash = (37 * hash) + DIGEST_FIELD_NUMBER;
    hash = (53 * hash) + getDigest().hashCode();
    hash = (37 * hash) + EXECUTIONTIME_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getExecutionTime());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.simplekv.grpc.ReadResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.simplekv.grpc.ReadResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.simplekv.grpc.ReadResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.simplekv.grpc.ReadResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.simplekv.grpc.ReadResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.simplekv.grpc.ReadResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.simplekv.grpc.ReadResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.simplekv.grpc.ReadResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.simplekv.grpc.ReadResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.simplekv.grpc.ReadResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.simplekv.grpc.ReadResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.simplekv.grpc.ReadResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.simplekv.grpc.ReadResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.simplekv.grpc.ReadResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.simplekv.grpc.ReadResponse)
      com.simplekv.grpc.ReadResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.simplekv.grpc.MessagingService.internal_static_com_simplekv_grpc_ReadResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.simplekv.grpc.MessagingService.internal_static_com_simplekv_grpc_ReadResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.simplekv.grpc.ReadResponse.class, com.simplekv.grpc.ReadResponse.Builder.class);
    }

    // Construct using com.simplekv.grpc.ReadResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      key_ = "";

      vale_ = com.google.protobuf.ByteString.EMPTY;

      digest_ = com.google.protobuf.ByteString.EMPTY;

      executionTime_ = 0L;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.simplekv.grpc.MessagingService.internal_static_com_simplekv_grpc_ReadResponse_descriptor;
    }

    @java.lang.Override
    public com.simplekv.grpc.ReadResponse getDefaultInstanceForType() {
      return com.simplekv.grpc.ReadResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.simplekv.grpc.ReadResponse build() {
      com.simplekv.grpc.ReadResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.simplekv.grpc.ReadResponse buildPartial() {
      com.simplekv.grpc.ReadResponse result = new com.simplekv.grpc.ReadResponse(this);
      result.key_ = key_;
      result.vale_ = vale_;
      result.digest_ = digest_;
      result.executionTime_ = executionTime_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.simplekv.grpc.ReadResponse) {
        return mergeFrom((com.simplekv.grpc.ReadResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.simplekv.grpc.ReadResponse other) {
      if (other == com.simplekv.grpc.ReadResponse.getDefaultInstance()) return this;
      if (!other.getKey().isEmpty()) {
        key_ = other.key_;
        onChanged();
      }
      if (other.getVale() != com.google.protobuf.ByteString.EMPTY) {
        setVale(other.getVale());
      }
      if (other.getDigest() != com.google.protobuf.ByteString.EMPTY) {
        setDigest(other.getDigest());
      }
      if (other.getExecutionTime() != 0L) {
        setExecutionTime(other.getExecutionTime());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.simplekv.grpc.ReadResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.simplekv.grpc.ReadResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object key_ = "";
    /**
     * <code>string key = 1;</code>
     */
    public java.lang.String getKey() {
      java.lang.Object ref = key_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        key_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string key = 1;</code>
     */
    public com.google.protobuf.ByteString
        getKeyBytes() {
      java.lang.Object ref = key_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        key_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string key = 1;</code>
     */
    public Builder setKey(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      key_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string key = 1;</code>
     */
    public Builder clearKey() {
      
      key_ = getDefaultInstance().getKey();
      onChanged();
      return this;
    }
    /**
     * <code>string key = 1;</code>
     */
    public Builder setKeyBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      key_ = value;
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString vale_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes vale = 2;</code>
     */
    public com.google.protobuf.ByteString getVale() {
      return vale_;
    }
    /**
     * <code>bytes vale = 2;</code>
     */
    public Builder setVale(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      vale_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes vale = 2;</code>
     */
    public Builder clearVale() {
      
      vale_ = getDefaultInstance().getVale();
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString digest_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes digest = 3;</code>
     */
    public com.google.protobuf.ByteString getDigest() {
      return digest_;
    }
    /**
     * <code>bytes digest = 3;</code>
     */
    public Builder setDigest(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      digest_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes digest = 3;</code>
     */
    public Builder clearDigest() {
      
      digest_ = getDefaultInstance().getDigest();
      onChanged();
      return this;
    }

    private long executionTime_ ;
    /**
     * <code>uint64 executionTime = 4;</code>
     */
    public long getExecutionTime() {
      return executionTime_;
    }
    /**
     * <code>uint64 executionTime = 4;</code>
     */
    public Builder setExecutionTime(long value) {
      
      executionTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>uint64 executionTime = 4;</code>
     */
    public Builder clearExecutionTime() {
      
      executionTime_ = 0L;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.simplekv.grpc.ReadResponse)
  }

  // @@protoc_insertion_point(class_scope:com.simplekv.grpc.ReadResponse)
  private static final com.simplekv.grpc.ReadResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.simplekv.grpc.ReadResponse();
  }

  public static com.simplekv.grpc.ReadResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ReadResponse>
      PARSER = new com.google.protobuf.AbstractParser<ReadResponse>() {
    @java.lang.Override
    public ReadResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ReadResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ReadResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ReadResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.simplekv.grpc.ReadResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

