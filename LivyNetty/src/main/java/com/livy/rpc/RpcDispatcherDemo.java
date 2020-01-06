package com.livy.rpc;

import com.livy.rpc.rsc.rpc.Rpc;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.util.concurrent.Future;
import org.apache.commons.io.IOUtils;

/**
 * @author apktool
 * @package com.livy.rpc
 * @class RpcDispatcherDemo
 * @description TODO
 * @date 2020-01-06 22:25
 */
public class RpcDispatcherDemo {
    public static void main(String[] args) {

        new RpcDispatcherDemo().start(args);
    }

    public void start(String[] args) {
        Rpc serverRpc = Rpc.createEmbedded(new RpcDispatcherImpl());
        Rpc clientRpc = Rpc.createEmbedded(new RpcDispatcherImpl());

        Message outbound = new Message("Hello World!");
        Future<Message> call = clientRpc.call(outbound, Message.class);

        transfer(serverRpc, clientRpc);

        IOUtils.closeQuietly(serverRpc);
    }

    private void transfer(Rpc serverRpc, Rpc clientRpc) {
        EmbeddedChannel client = (EmbeddedChannel) clientRpc.getChannel();
        EmbeddedChannel server = (EmbeddedChannel) serverRpc.getChannel();

        server.runPendingTasks();
        client.runPendingTasks();

        int count = 0;
        while (!client.outboundMessages().isEmpty()) {
            server.writeInbound((Object) client.readOutbound());
            count++;
        }
        server.flush();
        System.out.printf("Transferred {} outbound client messages.", count);

        count = 0;
        while (!server.outboundMessages().isEmpty()) {
            client.writeInbound((Object) server.readOutbound());
            count++;
        }
        client.flush();
        System.out.printf("Transferred {} outbound server messages.", count);
    }

}
