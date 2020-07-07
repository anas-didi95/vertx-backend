package com.anasdidi.backend.common;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;

public abstract class CommonVerticle extends AbstractVerticle {

  protected void sendResponse(HttpServerResponse response, CommonContstant.Status status, String body) {
    response//
        .putHeader("Content-Type", "application/json")//
        .putHeader("Accept", "aplication/json");

    response.setStatusCode(status.code()).end(new JsonObject()//
        .put("status", status.dscp())//
        .put("data", body)//
        .encode());
  }
}
