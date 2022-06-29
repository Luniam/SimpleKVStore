// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MessagingService.proto

package com.simplekv.grpc;

/**
 * Protobuf type {@code com.simplekv.grpc.WriteRequest}
 */
public  final class WriteRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.simplekv.grpc.WriteRequest)
    WriteRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use WriteRequest.newBuilder() to construct.
  private WriteRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private WriteRequest() {
    key_ = "";
    value_ = com.google.protobuf.ByteString.EMPTY;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private WriteRequest(
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

            value_ = input.readBytes();
            break;
          }
          case 26: {
            com.simplekv.grpc.GenerationClock.Builder subBuilder = null;
            if (generationClock_ != null) {
              subBuilder = generationClock_.toBuilder();
            }
            generationClock_ = input.readMessage(com.simplekv.grpc.GenerationClock.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(generationClock_);
              generationClock_ = subBuilder.buildPartial();
            }

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
    return com.simplekv.grpc.MessagingService.internal_static_com_simplekv_grpc_WriteRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.simplekv.grpc.MessagingService.internal_static_com_simplekv_grpc_WriteRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.simplekv.grpc.WriteRequest.class, com.simplekv.grpc.WriteRequest.Builder.class);
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

  public static final int VALUE_FIELD_NUMBER = 2;
  private com.google.protobuf.ByteString value_;
  /**
   * <code>bytes value = 2;</code>
   */
  public com.google.protobuf.ByteString getValue() {
    return value_;
  }

  public static final int GENERATIONCLOCK_FIELD_NUMBER = 3;
  private com.simplekv.grpc.GenerationClock generationClock_;
  /**
   * <code>.com.simplekv.grpc.GenerationClock generationClock = 3;</code>
   */
  public boolean hasGenerationClock() {
    return generationClock_ != null;
  }
  /**
   * <code>.com.simplekv.grpc.GenerationClock generationClock = 3;</code>
   */
  public com.simplekv.grpc.GenerationClock getGenerationClock() {
    return generationClock_ == null ? com.simplekv.grpc.GenerationClock.getDefaultInstance() : generationClock_;
  }
  /**
   * <code>.com.simplekv.grpc.GenerationClock generationClock = 3;</code>
   */
  public com.simplekv.grpc.GenerationClockOrBuilder getGenerationClockOrBuilder() {
    return getGenerationClock();
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
    if (!value_.isEmpty()) {
      output.writeBytes(2, value_);
    }
    if (generationClock_ != null) {
      output.writeMessage(3, getGenerationClock());
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
    if (!value_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, value_);
    }
    if (generationClock_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getGenerationClock());
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
    if (!(obj instanceof com.simplekv.grpc.WriteRequest)) {
      return super.equals(obj);
    }
    com.simplekv.grpc.WriteRequest other = (com.simplekv.grpc.WriteRequest) obj;

    boolean result = true;
    result = result && getKey()
        .equals(other.getKey());
    result = result && getValue()
        .equals(other.getValue());
    result = result && (hasGenerationClock() == other.hasGenerationClock());
    if (hasGenerationClock()) {
      result = result && getGenerationClock()
          .equals(other.getGenerationClock());
    }
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
    hash = (37 * hash) + VALUE_FIELD_NUMBER;
    hash = (53 * hash) + getValue().hashCode();
    if (hasGenerationClock()) {
      hash = (37 * hash) + GENERATIONCLOCK_FIELD_NUMBER;
      hash = (53 * hash) + getGenerationClock().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.simplekv.grpc.WriteRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.simplekv.grpc.WriteRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.simplekv.grpc.WriteRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.simplekv.grpc.WriteRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.simplekv.grpc.WriteRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.simplekv.grpc.WriteRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.simplekv.grpc.WriteRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.simplekv.grpc.WriteRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.simplekv.grpc.WriteRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.simplekv.grpc.WriteRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.simplekv.grpc.WriteRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.simplekv.grpc.WriteRequest parseFrom(
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
  public static Builder newBuilder(com.simplekv.grpc.WriteRequest prototype) {
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
   * Protobuf type {@code com.simplekv.grpc.WriteRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.simplekv.grpc.WriteRequest)
      com.simplekv.grpc.WriteRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.simplekv.grpc.MessagingService.internal_static_com_simplekv_grpc_WriteRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.simplekv.grpc.MessagingService.internal_static_com_simplekv_grpc_WriteRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.simplekv.grpc.WriteRequest.class, com.simplekv.grpc.WriteRequest.Builder.class);
    }

    // Construct using com.simplekv.grpc.WriteRequest.newBuilder()
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

      value_ = com.google.protobuf.ByteString.EMPTY;

      if (generationClockBuilder_ == null) {
        generationClock_ = null;
      } else {
        generationClock_ = null;
        generationClockBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.simplekv.grpc.MessagingService.internal_static_com_simplekv_grpc_WriteRequest_descriptor;
    }

    @java.lang.Override
    public com.simplekv.grpc.WriteRequest getDefaultInstanceForType() {
      return com.simplekv.grpc.WriteRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.simplekv.grpc.WriteRequest build() {
      com.simplekv.grpc.WriteRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.simplekv.grpc.WriteRequest buildPartial() {
      com.simplekv.grpc.WriteRequest result = new com.simplekv.grpc.WriteRequest(this);
      result.key_ = key_;
      result.value_ = value_;
      if (generationClockBuilder_ == null) {
        result.generationClock_ = generationClock_;
      } else {
        result.generationClock_ = generationClockBuilder_.build();
      }
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
      if (other instanceof com.simplekv.grpc.WriteRequest) {
        return mergeFrom((com.simplekv.grpc.WriteRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.simplekv.grpc.WriteRequest other) {
      if (other == com.simplekv.grpc.WriteRequest.getDefaultInstance()) return this;
      if (!other.getKey().isEmpty()) {
        key_ = other.key_;
        onChanged();
      }
      if (other.getValue() != com.google.protobuf.ByteString.EMPTY) {
        setValue(other.getValue());
      }
      if (other.hasGenerationClock()) {
        mergeGenerationClock(other.getGenerationClock());
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
      com.simplekv.grpc.WriteRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.simplekv.grpc.WriteRequest) e.getUnfinishedMessage();
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

    private com.google.protobuf.ByteString value_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes value = 2;</code>
     */
    public com.google.protobuf.ByteString getValue() {
      return value_;
    }
    /**
     * <code>bytes value = 2;</code>
     */
    public Builder setValue(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      value_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes value = 2;</code>
     */
    public Builder clearValue() {
      
      value_ = getDefaultInstance().getValue();
      onChanged();
      return this;
    }

    private com.simplekv.grpc.GenerationClock generationClock_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.simplekv.grpc.GenerationClock, com.simplekv.grpc.GenerationClock.Builder, com.simplekv.grpc.GenerationClockOrBuilder> generationClockBuilder_;
    /**
     * <code>.com.simplekv.grpc.GenerationClock generationClock = 3;</code>
     */
    public boolean hasGenerationClock() {
      return generationClockBuilder_ != null || generationClock_ != null;
    }
    /**
     * <code>.com.simplekv.grpc.GenerationClock generationClock = 3;</code>
     */
    public com.simplekv.grpc.GenerationClock getGenerationClock() {
      if (generationClockBuilder_ == null) {
        return generationClock_ == null ? com.simplekv.grpc.GenerationClock.getDefaultInstance() : generationClock_;
      } else {
        return generationClockBuilder_.getMessage();
      }
    }
    /**
     * <code>.com.simplekv.grpc.GenerationClock generationClock = 3;</code>
     */
    public Builder setGenerationClock(com.simplekv.grpc.GenerationClock value) {
      if (generationClockBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        generationClock_ = value;
        onChanged();
      } else {
        generationClockBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.com.simplekv.grpc.GenerationClock generationClock = 3;</code>
     */
    public Builder setGenerationClock(
        com.simplekv.grpc.GenerationClock.Builder builderForValue) {
      if (generationClockBuilder_ == null) {
        generationClock_ = builderForValue.build();
        onChanged();
      } else {
        generationClockBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.com.simplekv.grpc.GenerationClock generationClock = 3;</code>
     */
    public Builder mergeGenerationClock(com.simplekv.grpc.GenerationClock value) {
      if (generationClockBuilder_ == null) {
        if (generationClock_ != null) {
          generationClock_ =
            com.simplekv.grpc.GenerationClock.newBuilder(generationClock_).mergeFrom(value).buildPartial();
        } else {
          generationClock_ = value;
        }
        onChanged();
      } else {
        generationClockBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.com.simplekv.grpc.GenerationClock generationClock = 3;</code>
     */
    public Builder clearGenerationClock() {
      if (generationClockBuilder_ == null) {
        generationClock_ = null;
        onChanged();
      } else {
        generationClock_ = null;
        generationClockBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.com.simplekv.grpc.GenerationClock generationClock = 3;</code>
     */
    public com.simplekv.grpc.GenerationClock.Builder getGenerationClockBuilder() {
      
      onChanged();
      return getGenerationClockFieldBuilder().getBuilder();
    }
    /**
     * <code>.com.simplekv.grpc.GenerationClock generationClock = 3;</code>
     */
    public com.simplekv.grpc.GenerationClockOrBuilder getGenerationClockOrBuilder() {
      if (generationClockBuilder_ != null) {
        return generationClockBuilder_.getMessageOrBuilder();
      } else {
        return generationClock_ == null ?
            com.simplekv.grpc.GenerationClock.getDefaultInstance() : generationClock_;
      }
    }
    /**
     * <code>.com.simplekv.grpc.GenerationClock generationClock = 3;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.simplekv.grpc.GenerationClock, com.simplekv.grpc.GenerationClock.Builder, com.simplekv.grpc.GenerationClockOrBuilder> 
        getGenerationClockFieldBuilder() {
      if (generationClockBuilder_ == null) {
        generationClockBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.simplekv.grpc.GenerationClock, com.simplekv.grpc.GenerationClock.Builder, com.simplekv.grpc.GenerationClockOrBuilder>(
                getGenerationClock(),
                getParentForChildren(),
                isClean());
        generationClock_ = null;
      }
      return generationClockBuilder_;
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


    // @@protoc_insertion_point(builder_scope:com.simplekv.grpc.WriteRequest)
  }

  // @@protoc_insertion_point(class_scope:com.simplekv.grpc.WriteRequest)
  private static final com.simplekv.grpc.WriteRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.simplekv.grpc.WriteRequest();
  }

  public static com.simplekv.grpc.WriteRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<WriteRequest>
      PARSER = new com.google.protobuf.AbstractParser<WriteRequest>() {
    @java.lang.Override
    public WriteRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new WriteRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<WriteRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<WriteRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.simplekv.grpc.WriteRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

