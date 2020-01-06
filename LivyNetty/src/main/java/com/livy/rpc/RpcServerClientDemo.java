package com.livy.rpc;

import com.livy.rpc.rsc.RSCConf;
import com.livy.rpc.rsc.rpc.Rpc;
import com.livy.rpc.rsc.rpc.RpcDispatcher;
import com.livy.rpc.rsc.rpc.RpcServer;
import io.netty.util.concurrent.Future;
import org.apache.commons.io.IOUtils;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author apktool
 * @package com.livy.rpc
 * @class RpcServerClientDemo
 * @description TODO
 * @date 2020-01-06 22:31
 */
public class RpcServerClientDemo {
    private Collection<Closeable> closeables = new ArrayList<>();
    private RSCConf emptyConfig = new RSCConf(null);

    public static void main(String[] args) throws Exception {
        RpcServerClientDemo serverClient = new RpcServerClientDemo();
        serverClient.start(args);
        serverClient.cleanUp();
    }

    public void start(String[] args) throws Exception {
        RpcServer server = autoClose(new RpcServer(emptyConfig));
        Rpc[] rpcs = createRpcConnection(server, emptyConfig);
        Rpc serverRpc = rpcs[0];
        Rpc client = rpcs[1];

        Message outbound = new Message("Hello World!");
        Future<Message> call = client.call(outbound, Message.class);
        Message reply = call.get(10, TimeUnit.SECONDS);
        System.out.println(reply.getMessage());
        System.out.println(outbound.getMessage());

        Message another = new Message("Hello again!");
        Future<Message> anotherCall = client.call(another, Message.class);
        Message anotherReply = anotherCall.get(10, TimeUnit.SECONDS);
        System.out.println(another.getMessage());
        System.out.println(anotherReply.getMessage());

        /*
        String errorMsg = "This is an error.";
        try {
            client.call(new ErrorCall(errorMsg)).get(10, TimeUnit.SECONDS);
        } catch (ExecutionException ee) {
            System.out.println(ee.getMessage());
        }
         */

        // Test from server to client too.
        Message serverMsg = new Message("Hello from the server!");
        Future<Message> serverCall = serverRpc.call(serverMsg, Message.class);
        Message serverReply = serverCall.get(10, TimeUnit.SECONDS);
        System.out.println(serverMsg.getMessage());
        System.out.println(serverReply.getMessage());
    }

    private Rpc[] createRpcConnection(RpcServer server, RSCConf clientConf) throws Exception {
        String secret = server.createSecret();
        ServerRpcCallback callback = new ServerRpcCallback();
        server.registerClient("client", secret, callback);

        Future<Rpc> clientRpcFuture = Rpc.createClient(
                clientConf,
                server.getEventLoopGroup(),
                "localhost",
                server.getPort(),
                "client",
                secret,
                new RpcDispatcherImpl()
        );

        System.out.printf("onNewClient() wasn't called.", callback.onNewClientCalled.await(10, TimeUnit.SECONDS));
        System.out.printf("onSaslComplete() wasn't called.", callback.onSaslCompleteCalled.await(10, TimeUnit.SECONDS));
        System.out.printf(String.valueOf(callback.client));
        Rpc serverRpc = autoClose(callback.client);
        Rpc clientRpc = autoClose(clientRpcFuture.get(10, TimeUnit.SECONDS));
        return new Rpc[]{serverRpc, clientRpc};
    }


    private class ServerRpcCallback implements RpcServer.ClientCallback {
        final CountDownLatch onNewClientCalled = new CountDownLatch(1);
        final CountDownLatch onSaslCompleteCalled = new CountDownLatch(1);
        Rpc client;

        @Override
        public RpcDispatcher onNewClient(Rpc client) {
            this.client = client;
            onNewClientCalled.countDown();
            return new RpcDispatcherImpl();
        }

        @Override
        public void onSaslComplete(Rpc client) {
            onSaslCompleteCalled.countDown();
        }

    }


    public void cleanUp() throws Exception {
        for (Closeable c : closeables) {
            IOUtils.closeQuietly(c);
        }
    }

    private <T extends Closeable> T autoClose(T closeable) {
        closeables.add(closeable);
        return closeable;
    }
}
