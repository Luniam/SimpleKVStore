// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MessagingService.proto

package com.simplekv.grpc;

public interface GenerationClockOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.simplekv.grpc.GenerationClock)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>uint64 timestamp = 1;</code>
   */
  long getTimestamp();

  /**
   * <code>uint64 generation = 2;</code>
   */
  long getGeneration();
}