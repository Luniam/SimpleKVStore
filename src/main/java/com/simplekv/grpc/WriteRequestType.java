// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MessagingService.proto

package com.simplekv.grpc;

/**
 * Protobuf enum {@code com.simplekv.grpc.WriteRequestType}
 */
public enum WriteRequestType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>CLIENT_WRITE = 0;</code>
   */
  CLIENT_WRITE(0),
  /**
   * <code>WRITE = 1;</code>
   */
  WRITE(1),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>CLIENT_WRITE = 0;</code>
   */
  public static final int CLIENT_WRITE_VALUE = 0;
  /**
   * <code>WRITE = 1;</code>
   */
  public static final int WRITE_VALUE = 1;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static WriteRequestType valueOf(int value) {
    return forNumber(value);
  }

  public static WriteRequestType forNumber(int value) {
    switch (value) {
      case 0: return CLIENT_WRITE;
      case 1: return WRITE;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<WriteRequestType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      WriteRequestType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<WriteRequestType>() {
          public WriteRequestType findValueByNumber(int number) {
            return WriteRequestType.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return com.simplekv.grpc.MessagingService.getDescriptor().getEnumTypes().get(1);
  }

  private static final WriteRequestType[] VALUES = values();

  public static WriteRequestType valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private WriteRequestType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:com.simplekv.grpc.WriteRequestType)
}
