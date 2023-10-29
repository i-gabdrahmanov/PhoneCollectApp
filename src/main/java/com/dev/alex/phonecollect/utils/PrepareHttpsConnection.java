package com.dev.alex.phonecollect.utils;

import com.dev.alex.phonecollect.model.OperatorEnum;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class PrepareHttpsConnection {

    public static final int CONNECTION_TIMEOUT = 4000;

    public static HttpsURLConnection prepareConnection(OperatorEnum operator) {
        final URL url;
        final HttpsURLConnection con;
        try {
            url = new URL(operator.getUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        try {
            con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        con.setRequestProperty("Content-Type", "application/json");
        con.setConnectTimeout(CONNECTION_TIMEOUT);
        con.setReadTimeout(CONNECTION_TIMEOUT);

        if (operator.getName().equals(OperatorEnum.MEGAFON.getName())) {
            con.setRequestProperty("authority", "api.shop.megafon.ru");
            con.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
            con.setRequestProperty("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
            con.setRequestProperty("cache-control", "max-age=0");
            con.setRequestProperty("cookie", "_ejwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJtZnItZXNob3AiLCJhdWQiOiJiMmMiLCJpYXQiOjE2OTgzMDMwNTUsImV4cCI6MTY5OTUxMjY1NSwianRpIjoiZW1wbG95ZWUiLCJwZCI6eyJyZXNlcnZlRW5hYmxlIjpmYWxzZSwibm9FbXBsb3llZSI6ZmFsc2UsInR5cGVJZCI6bnVsbCwicGVyc29ubmVsTnVtYmVyIjpudWxsLCJyZWdpb24iOm51bGwsInNhbG9uQ29kZSI6bnVsbH19.e35uVq7sx4Vc_jgFUxtqCYmCbh-I6TqJw2LoC99dPnU0jTB2Sj9ytc5NF0ithczJGPauhuW4lL8ncyogOcFIz0h9vn26lF0dliclVLMoTNF4BFH9C7WvaLlYfpG8UBQ0qRdMQZHcAZ96fatttN6BL0KUtO8Hp-OCqZAIuseGjl5we45cEwI88rGXve9qtKRUWdBmrF9cRkBO0gSd_0Edup4XC8r9OVOcWWUMYR-JB1vrk1_YrAN-9By7cujUVsTaEwrtw1wrb9Dm6W2AffdKVZahLboABwFZxUMD34Bbe1_feKKy_wMufvc99xjXdAYHjE_P5YDs0pNx-z9sXD5icA; _ajwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJtZnItZXNob3AiLCJhdWQiOiJiMmMiLCJpYXQiOjE2OTgzMDMwNTUsImV4cCI6MTY5OTUxMjY1NSwianRpIjoiY3VzdG9tZXIiLCJwZCI6eyJ1aWQiOjEwNDM5NjQ0OTgsImNsaWVudElkIjowLCJhdXRoU3RhdHVzIjowLCJhdXRoVGltZSI6bnVsbCwicnRpbUF1dGgiOm51bGx9fQ.ZPey55dFwGeMp10qULKRDuH6YqHKgLWzIquYoG3DKMuAOjPs0HZKtHPgJutchZVf574E9xGF8MakECXyml_rDyLZcki73swNwkaUNRHnoZgC-ZQSuJiiAqaxGWn19vT0MQzdl4pPUs71z0nV1sv4S1k9CdvFDIn5B8VEV6qCph7_3EjhGcCjF10LDKmC_G8ovCQJtuG0ImSh_YaX9LPNhqdBJsikC72tKEthkF3AAnqHygEy5Mh3e8WC-0OzbcocMbskIPnVIR7gdktrvq6LRMDb3ZHWcKvAu1YcnRF9lXLwNuO4uVQ8bYeEJGtGbb5lCb-ej6iL0BfGojvo-AH38Q; tmr_lvid=65c9b2b1e6d628739fae98d276cadd05; tmr_lvidTS=1698303053936; _ym_uid=1698303054255852902; _ym_d=1698303054; _ym_isad=1; _ym_visorc=b; mindboxDeviceUUID=fd10f08d-29f4-413f-9ca2-7eb8063e55d3; directCrm-session=%7B%22deviceGuid%22%3A%22fd10f08d-29f4-413f-9ca2-7eb8063e55d3%22%7D; branchId=116; SERVERID=webcl-waf-01; SERVERBINARIESID=webcl-bin-01");
            con.setRequestProperty("sec-ch-ua", "\"Chromium\";v=\"118\", \"Google Chrome\";v=\"118\", \"Not=A?Brand\";v=\"99\"");
            con.setRequestProperty("sec-ch-ua-mobile", "?0");
            con.setRequestProperty("sec-ch-ua-platform", "\"Windows\"");
            con.setRequestProperty("sec-fetch-dest", "document");
            con.setRequestProperty("sec-fetch-mode", "navigate");
            con.setRequestProperty("sec-fetch-site", "none");
            con.setRequestProperty("sec-fetch-user", "?1");
            con.setRequestProperty("upgrade-insecure-requests", "1");
            con.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36");


        }
        return con;
    }
}