package com.anasdidi.backend;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MainVerticle extends AbstractVerticle {

	@Override
	public void start(final Promise<Void> startPromise) throws Exception {
		int port = -1;
		try {
			port = Integer.parseInt(System.getenv("server.port"));
		} catch (Exception e) {
			port = 8888;
		}
		System.out.println("HTTP server started on port " + port);

		vertx.createHttpServer().requestHandler(req -> {
			req.response().putHeader("content-type", "text/plain").end("Hello from Vert.x!");
		}).listen(port, http -> {
			if (http.succeeded()) {
				startPromise.complete();
			} else {
				startPromise.fail(http.cause());
			}
		});
	}
}
