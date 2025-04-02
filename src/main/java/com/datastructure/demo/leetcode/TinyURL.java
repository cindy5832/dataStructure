package com.datastructure.demo.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

// LeetCode535
public class TinyURL {

    private Map<String, String> longToShort = new HashMap<>();
    private Map<String, String> shortToLong = new HashMap<>();
    private final String SHORT_PREFIX = "http://tinyurl.com/";
    private int id = 1;

    private final char[] toBase62 = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    /**
     * 31 % 16 = 15
     * 31 / 16 = 1
     * 1 % 16 = 1
     * 1 / 16 = 0
     **/
    public String toBase62(int number) {
        if (number == 0) {
            return String.valueOf(toBase62[0]);
        }
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            int r = number % 62;
            sb.append(toBase62[r]);
            number = number / 62;
        }
        return sb.toString();
    }

    /**
     * 讓 [長] [短] 網址一一對應
     * 1. 用 [隨機數] 作為短網址標識
     * 2. 用 [hash碼] 作為短網址標識
     * 3. 用 [遞增數] 作為短網址標識
     * 1) 多線程下可以使用嗎
     * 2) 分布式下可以使用嗎
     * 3) 4e9iAk 是如何生成的
     * a-z A-Z 0-9 62進制的數字
     **/
    public String encodeRandom(String longUrl) {
        String shortUrl = longToShort.get(longUrl);
        if (shortUrl != null) {
            return shortUrl;
        }
        // 生成短網址
        while (true) {
            int id = ThreadLocalRandom.current().nextInt();
            shortUrl = SHORT_PREFIX + id;
            if (!shortToLong.containsKey(shortUrl)) {
                longToShort.put(longUrl, shortUrl);
                shortToLong.put(shortUrl, longUrl);
                break;
            }
        }
        return shortUrl;
    }

    public String encodeHash(String longUrl) {
        String shortUrl = longToShort.get(longUrl);
        if (shortUrl != null) {
            return shortUrl;
        }
        // 生成短網址
        int id = longUrl.hashCode();
        while (true) {
            shortUrl = SHORT_PREFIX + id;
            if (!shortToLong.containsKey(shortUrl)) {
                longToShort.put(longUrl, shortUrl);
                shortToLong.put(shortUrl, longUrl);
                break;
            }
            id++;
        }
        return shortUrl;
    }

    public String encodeSequence(String longUrl) {

        String shortUrl = longToShort.get(longUrl);
        if (shortUrl != null) {
            return shortUrl;
        }
        // 生成短網址
        shortUrl = SHORT_PREFIX + toBase62(id);
        longToShort.put(longUrl, shortUrl);
        shortToLong.put(shortUrl, longUrl);
        id++;
        return shortUrl;
    }

    public String decode(String shortUrl) {
        return shortToLong.get(shortUrl);
    }
}
