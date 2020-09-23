package com.apktool.protobuf.gservice;

import java.util.concurrent.TimeUnit;

import com.apktool.protobuf.grpc.RpcProtos;
import com.apktool.protobuf.grpc.RpcServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author apktool
 * @package com.apktool.protobuf.service
 * @class RpcServiceClient
 * @date 2020-09-23 22:59:31
 */

@Slf4j
public class RpcServiceClient {

    private final ManagedChannel channel;
    private final RpcServiceGrpc.RpcServiceBlockingStub blockingStub;

    public RpcServiceClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
            .usePlaintext()
            .build());
    }

    public RpcServiceClient(ManagedChannel channel) {
        this.channel = channel;
        this.blockingStub = RpcServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * Say hello to server.
     */
    public void greet(String name) {
        log.info("client request ==============" + name + " ...");
        RpcProtos.Request request = RpcProtos.Request.newBuilder()
            .setServiceName("demo service")
            .setMethodName("method")
            .addParams(RpcProtos.Params.newBuilder().setKey("key").setValue("value").build())
            .build();
        RpcProtos.Response response;
        try {
            response = blockingStub.call(request);
        } catch (StatusRuntimeException e) {
            log.warn("RPC failed: {0}", e.getStatus());
            return;
        }
        log.info("response: ===============" + response.getData());
        System.out.println(response.toString());
    }

    public static void main(String[] args) throws Exception {
        RpcServiceClient client = new RpcServiceClient("localhost", 50051);
        try {
            /* Access a service running on the local machine on port 50051 */
            String user = "world===============";
            if (args.length > 0) {
                user = args[0]; /* Use the arg as the name to greet if provided */
            }
            client.greet(user);
        } finally {
            client.shutdown();
        }
    }
}
