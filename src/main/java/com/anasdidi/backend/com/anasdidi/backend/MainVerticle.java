package com.anasdidi.backend;

import com.anasdidi.backend.domain.echo.EchoVerticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(final Promise<Void> startPromise) throws Exception {
    int port = -1;
    try {
      port = Integer.parseInt(System.getProperty("server.port"));
    } catch (Exception e) {
      port = 8888;
    }
    System.out.println("HTTP server started on port " + port);

    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());
    vertx.deployVerticle(new EchoVerticle(router));

    vertx.createHttpServer().requestHandler(router).listen(port, http -> {
      if (http.succeeded()) {
        startPromise.complete();
      } else {
        startPromise.fail(http.cause());
      }
    });
  }
}
