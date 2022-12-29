package com.bside.server.module.auth.util;

import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.CustomException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

@Log4j2
public class KakaoUserInfo {

  public static JsonNode getKakaoUserInfo(String accessToken) {
    final String REQUEST_URL = "https://kapi.kakao.com/v2/user/me";
    final HttpClient CLIENT = HttpClientBuilder.create().build();
    final HttpPost POST = new HttpPost(REQUEST_URL);
    POST.addHeader("Authorization", "Bearer " + accessToken);

    try
    {
      final HttpResponse response = CLIENT.execute(POST);
      final int statusCode = response.getStatusLine().getStatusCode();
      if(statusCode != 200) {
        log.error("failed to httpRequest: {}, statusCode = {}", response, statusCode);
        throw new CustomException(ErrorCode.FAIL_TO_GET_KAKAO_INFO);
      }
      return new ObjectMapper().readTree(response.getEntity().getContent());
    } catch (IOException e) {
      throw new CustomException(ErrorCode.FAIL_TO_GET_KAKAO_INFO);
    }
  }
}
