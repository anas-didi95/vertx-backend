package com.anasdidi.backend.domain.echo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class EchoVerticle extends AbstractVerticle {

  private final Router router;

  public EchoVerticle(Router router) {
    this.router = router;
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    router.get("/echo").handler(this::echoHelloWorld);
    router.get("/echo/:name").handler(this::echoHelloName);
  }

  public void echoHelloWorld(RoutingContext routingContext) {
    HttpServerResponse response = routingContext.response();
    response//
        .putHeader("Content-Type", "application/json")//
        .putHeader("Accept", "application/json");
    response.end(new JsonObject()//
        .put("data", "Hello World")//
        .encode());
  }

  public void echoHelloName(RoutingContext routingContext) {
    HttpServerRequest request = routingContext.request();
    HttpServerResponse response = routingContext.response();

    String name = request.getParam("name");

    response//
        .putHeader("Content-Type", "application/json")//
        .putHeader("Accept", "application/json");
    response.end(new JsonObject()//
        .put("data", "Hello " + name)//
        .encode());
  }
}
