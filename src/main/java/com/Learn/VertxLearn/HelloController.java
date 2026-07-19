package com.Learn.VertxLearn;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.impl.HttpServerConnection;
import io.vertx.ext.web.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HelloController {


    private final Router router;

    @Autowired
    public HelloController(Router router) {
        this.router = router;
    }

    private void defineEndpoints(){
        router.get("/hello").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();

            response.setStatusCode(HttpResponseStatus.OK.code())
                    .end("Hello from vertx");
        });
    }
}
