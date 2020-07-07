package com.anasdidi.backend.common;

import java.util.UUID;

public class CommonUtil {

  public static String generateUUID() {
    return UUID.randomUUID().toString().replace("-", "").toUpperCase();
  }
}
