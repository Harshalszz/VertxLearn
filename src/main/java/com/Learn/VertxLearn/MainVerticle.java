package com.Learn.VertxLearn;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MainVerticle extends AbstractVerticle {


    private final Router router;

    private final Integer serverPort;

    @Autowired
    public MainVerticle(Router router, @Value("${vertx.spring.port}") Integer serverPort) {
        this.router = router;
        this.serverPort = serverPort;
    }
}
