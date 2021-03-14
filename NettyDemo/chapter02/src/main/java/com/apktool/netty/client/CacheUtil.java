package com.apktool.netty.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheUtil {
    public static Map<String, SyncFuture> cacheFuture = new ConcurrentHashMap<>();
}