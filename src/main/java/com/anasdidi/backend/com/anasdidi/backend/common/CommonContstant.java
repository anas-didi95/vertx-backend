package com.anasdidi.backend.common;

public class CommonContstant {

  public enum Status {
    OK(200, "Ok");

    private int code;
    private String dscp;

    private Status(int code, String dscp) {
      this.code = code;
      this.dscp = dscp;
    }

    public int code() {
      return code;
    }

    public String dscp() {
      return dscp;
    }
  }
}
