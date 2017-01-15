package com.tianshaokai.crawler.core.util;


import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public class DigestHashUtil {
    public static String hash(String src){
        return Hashing.md5().hashString(src, Charsets.UTF_8).toString();
    }
}
