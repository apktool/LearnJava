package com.apktool.rpc.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheUtil {
    public static Map<String, BlockingRpcCallback> cacheFuture = new ConcurrentHashMap<>();
}