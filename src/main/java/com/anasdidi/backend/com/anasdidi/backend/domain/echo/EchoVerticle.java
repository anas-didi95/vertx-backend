package com.anasdidi.backend.domain.echo;

import com.anasdidi.backend.common.CommonConstant;
import com.anasdidi.backend.common.CommonVerticle;

import io.vertx.core.Promise;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class EchoVerticle extends CommonVerticle {

  private final Router router;

  public EchoVerticle(Router router) {
    this.router = router;
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    router.get("/echo").handler(this::doHelloWorld);
    router.get("/echo/:name").handler(this::doHelloName);
  }

  public void doHelloWorld(RoutingContext routingContext) {
    final String TAG = "[EchoVerticle::doHelloWorld]";

    HttpServerRequest request = routingContext.request();
    JsonObject requestBody = new JsonObject()//
        .put(CommonConstant.REQUEST_ID, (String) routingContext.get(CommonConstant.REQUEST_ID))//
        .put("s", request.getParam("s"));
    vertx.eventBus().send(CommonConstant.Event.LOGGER_DEBUG.key(),
        TAG + " :: requestBody=" + requestBody.encodePrettily());

    HttpServerResponse response = routingContext.response();
    sendResponse(response, CommonConstant.Status.OK,
        (requestBody.getString("s") != null && !requestBody.getString("s").isBlank() ? requestBody.getString("s")
            : "Hello World"));
  }

  public void doHelloName(RoutingContext routingContext) {
    final String TAG = "[EchoVerticle::doHelloName]";

    HttpServerRequest request = routingContext.request();
    JsonObject requestBody = new JsonObject()//
        .put(CommonConstant.REQUEST_ID, (String) routingContext.get(CommonConstant.REQUEST_ID))//
        .put("name", request.getParam("name"));
    vertx.eventBus().send(CommonConstant.Event.LOGGER_DEBUG.key(),
        TAG + " :: requestBody=" + requestBody.encodePrettily());

    HttpServerResponse response = routingContext.response();
    sendResponse(response, CommonConstant.Status.OK, "Hello " + requestBody.getString("name"));
  }
}
