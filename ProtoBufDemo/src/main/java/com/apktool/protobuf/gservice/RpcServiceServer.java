package com.apktool.protobuf.gservice;

import com.apktool.protobuf.grpc.RpcProtos;
import com.apktool.protobuf.grpc.RpcServiceGrpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author apktool
 * @package com.apktool.protobuf.service
 * @class RpcServiceServer
 * @date 2020-09-23 22:53:57
 */

@Slf4j
public class RpcServiceServer {
    private Server server;

    private void start() throws IOException {
        /* The port on which the server should run */
        int port = 50051;
        server = ServerBuilder.forPort(port)
            .addService(new GreeterImpl())
            .build()
            .start();
        log.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(
            () -> {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                if (server != null) {
                    server.shutdown();
                }
                System.err.println("*** server shut down");
            }));
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final RpcServiceServer server = new RpcServiceServer();
        server.start();
        server.blockUntilShutdown();
    }

    static class GreeterImpl extends RpcServiceGrpc.RpcServiceImplBase {
        @Override
        public void call(RpcProtos.Request request, StreamObserver<RpcProtos.Response> responseObserver) {
            RpcProtos.Response reply = RpcProtos.Response.newBuilder()
                .setCode(RpcProtos.Response.CodeType.SUCCESS)
                .setData("data")
                .setMsg("Hello world")
                .build();

            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}

