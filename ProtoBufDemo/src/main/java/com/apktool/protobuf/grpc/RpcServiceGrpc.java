package com.apktool.protobuf.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.32.1)",
    comments = "Source: Rpc.proto")
public final class RpcServiceGrpc {

  private RpcServiceGrpc() {}

  public static final String SERVICE_NAME = "grpc.RpcService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.apktool.protobuf.grpc.RpcProtos.Request,
      com.apktool.protobuf.grpc.RpcProtos.Response> getCallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "call",
      requestType = com.apktool.protobuf.grpc.RpcProtos.Request.class,
      responseType = com.apktool.protobuf.grpc.RpcProtos.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.apktool.protobuf.grpc.RpcProtos.Request,
      com.apktool.protobuf.grpc.RpcProtos.Response> getCallMethod() {
    io.grpc.MethodDescriptor<com.apktool.protobuf.grpc.RpcProtos.Request, com.apktool.protobuf.grpc.RpcProtos.Response> getCallMethod;
    if ((getCallMethod = RpcServiceGrpc.getCallMethod) == null) {
      synchronized (RpcServiceGrpc.class) {
        if ((getCallMethod = RpcServiceGrpc.getCallMethod) == null) {
          RpcServiceGrpc.getCallMethod = getCallMethod =
              io.grpc.MethodDescriptor.<com.apktool.protobuf.grpc.RpcProtos.Request, com.apktool.protobuf.grpc.RpcProtos.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "call"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.apktool.protobuf.grpc.RpcProtos.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.apktool.protobuf.grpc.RpcProtos.Response.getDefaultInstance()))
              .setSchemaDescriptor(new RpcServiceMethodDescriptorSupplier("call"))
              .build();
        }
      }
    }
    return getCallMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RpcServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RpcServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RpcServiceStub>() {
        @java.lang.Override
        public RpcServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RpcServiceStub(channel, callOptions);
        }
      };
    return RpcServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RpcServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RpcServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RpcServiceBlockingStub>() {
        @java.lang.Override
        public RpcServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RpcServiceBlockingStub(channel, callOptions);
        }
      };
    return RpcServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RpcServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RpcServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RpcServiceFutureStub>() {
        @java.lang.Override
        public RpcServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RpcServiceFutureStub(channel, callOptions);
        }
      };
    return RpcServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class RpcServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void call(com.apktool.protobuf.grpc.RpcProtos.Request request,
        io.grpc.stub.StreamObserver<com.apktool.protobuf.grpc.RpcProtos.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getCallMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCallMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.apktool.protobuf.grpc.RpcProtos.Request,
                com.apktool.protobuf.grpc.RpcProtos.Response>(
                  this, METHODID_CALL)))
          .build();
    }
  }

  /**
   */
  public static final class RpcServiceStub extends io.grpc.stub.AbstractAsyncStub<RpcServiceStub> {
    private RpcServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RpcServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RpcServiceStub(channel, callOptions);
    }

    /**
     */
    public void call(com.apktool.protobuf.grpc.RpcProtos.Request request,
        io.grpc.stub.StreamObserver<com.apktool.protobuf.grpc.RpcProtos.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCallMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class RpcServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<RpcServiceBlockingStub> {
    private RpcServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RpcServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RpcServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.apktool.protobuf.grpc.RpcProtos.Response call(com.apktool.protobuf.grpc.RpcProtos.Request request) {
      return blockingUnaryCall(
          getChannel(), getCallMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RpcServiceFutureStub extends io.grpc.stub.AbstractFutureStub<RpcServiceFutureStub> {
    private RpcServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RpcServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RpcServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.apktool.protobuf.grpc.RpcProtos.Response> call(
        com.apktool.protobuf.grpc.RpcProtos.Request request) {
      return futureUnaryCall(
          getChannel().newCall(getCallMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CALL = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RpcServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RpcServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CALL:
          serviceImpl.call((com.apktool.protobuf.grpc.RpcProtos.Request) request,
              (io.grpc.stub.StreamObserver<com.apktool.protobuf.grpc.RpcProtos.Response>) responseObserver);
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

  private static abstract class RpcServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RpcServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.apktool.protobuf.grpc.RpcProtos.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RpcService");
    }
  }

  private static final class RpcServiceFileDescriptorSupplier
      extends RpcServiceBaseDescriptorSupplier {
    RpcServiceFileDescriptorSupplier() {}
  }

  private static final class RpcServiceMethodDescriptorSupplier
      extends RpcServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RpcServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (RpcServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RpcServiceFileDescriptorSupplier())
              .addMethod(getCallMethod())
              .build();
        }
      }
    }
    return result;
  }
}
