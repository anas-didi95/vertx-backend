package com.anasdidi.backend.common;

public class CommonConstant {

  public static final String REQUEST_ID = "requestId";

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

  public enum Event {
    LOGGER_DEBUG("logger_event_debug"), //
    LOGGER_ERROR("logger_event_error");

    private String key;

    private Event(String key) {
      this.key = key;
    }

    public String key() {
      return key;
    }
  }
}
