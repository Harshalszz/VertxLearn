package com.Learn.VertxLearn;

import io.vertx.core.Vertx;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class VertxRunner implements CommandLineRunner {
    private Logger logger = Logger.getLogger(MainVerticle.class.getName());

    private final MainVerticle mainVerticle;

    private final Vertx vertx;

    @Autowired
    public VertxRunner(MainVerticle mainVerticle, Vertx vertx) {
        this.mainVerticle = mainVerticle;
        this.vertx = vertx;
    }


    @Override
    public void run(String... args) throws Exception {

        vertx.deployVerticle(mainVerticle)
                .onSuccess(depId -> logger.log(Level.INFO, "Successfully de[o;ed a main verticle"))
                .onFailure(throwable -> {
                    logger.log(Level.SEVERE, "Fau;ed to deploy {0}" ,throwable);
                    System.exit(-1);
                });
    }

    @PreDestroy
    public void destroy(){

        System.out.println("Shutdown the vertx");
        CountDownLatch countDownLatch= new CountDownLatch(1);
        vertx.close()
                .onComplete(handler ->{
                    if(handler.succeeded()){
                        System.out.println("Successfully shutdown ");
                    }
                    countDownLatch.countDown();
                });

        try {
            countDownLatch.await(30, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            System.out.println("Exiting the application ");
        }
    }

}
