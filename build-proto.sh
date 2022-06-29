sudo protoc --proto_path=src/main/proto/ --plugin=protoc-gen-grpc-java=protoc-gen-grpc-java-1.46.0-linux-x86_64.exe --java_out=src/main/java/ --grpc-java_out=src/main/java/ MessagingService.proto
