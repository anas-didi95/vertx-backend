package com.anasdidi.backend;

import com.anasdidi.backend.common.CommonConstant;
import com.anasdidi.backend.common.CommonUtil;
import com.anasdidi.backend.domain.echo.EchoVerticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import logger.LoggerVerticle;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(final Promise<Void> startPromise) throws Exception {
    final String TAG = "[MainVerticle::start]";

    JsonObject propertyObject = new JsonObject();
    try {
      propertyObject.put("port", Integer.parseInt(System.getProperty("server.port")));
    } catch (Exception e) {
      propertyObject.put("port", 8888);
    }
    try {
      propertyObject.put("debug", Boolean.parseBoolean(System.getProperty("server.debug")));
    } catch (Exception e) {
      propertyObject.put("debug", true);
    }
    System.out.println(TAG + " :: propertyObject=" + propertyObject.encodePrettily());

    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());
    router.route().path("/*").handler(routingContext -> {
      routingContext.put(CommonConstant.REQUEST_ID, CommonUtil.generateUUID());
      routingContext.next();
    });

    vertx.deployVerticle(new LoggerVerticle(propertyObject.getBoolean("debug")));
    vertx.deployVerticle(new EchoVerticle(router));

    vertx.createHttpServer().requestHandler(router).listen(propertyObject.getInteger("port"), http -> {
      if (http.succeeded()) {
        startPromise.complete();
      } else {
        startPromise.fail(http.cause());
      }
    });
  }
}
