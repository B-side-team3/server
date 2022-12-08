package com.bside.server.global.login.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class KakaoUserInfo {

  public static JsonNode getKakaoUserInfo(String accessToken) {
    final String RequestUrl = "https://kapi.kakao.com/v2/user/me";
    final HttpClient client = HttpClientBuilder.create().build();
    final HttpPost post = new HttpPost(RequestUrl);

    post.addHeader("Authorization", "Bearer " + accessToken);

    JsonNode returnNode = null;

    try {
      final HttpResponse response = client.execute(post);
      final int responseCode = response.getStatusLine().getStatusCode();

      System.out.println("\nSending 'POST' request to URL : " + RequestUrl);
      System.out.println("Response Code : " + responseCode);

      ObjectMapper mapper = new ObjectMapper();
      returnNode = mapper.readTree(response.getEntity().getContent());

    } catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return returnNode;
  }
}
