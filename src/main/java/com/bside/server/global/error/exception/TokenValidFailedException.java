package com.bside.server.global.error.exception;

public class TokenValidFailedException extends RuntimeException {

  public TokenValidFailedException() {
    super("Failed to generate Token.");
  }

  private TokenValidFailedException(String message) {
    super(message);
  }
}
