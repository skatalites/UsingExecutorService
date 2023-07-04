package edu.dev.demo;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Main {

    /**
     * This simple example shows how to use ExecutorService
     * 1. getting an instance from Executors.newSingleThreadExecutor()
     * 2. using the method execute to submit 2 simple tasks
     * One thing to have in mind is these tasks are running sequentially :)
     * @param args
     */
    public static void main(String[] args) {

        var start = System.currentTimeMillis();
        ExecutorService service = null;
        log.info("Current Thread: {}", Thread.currentThread().getName());

        try {
            service = Executors.newSingleThreadExecutor();

            service.execute(getRunnableTask1());
            service.execute(getRunnableTask2());

            var end = System.currentTimeMillis();
            log.info("Current Thread: {}, final time: {}", Thread.currentThread().getName(), end - start);

        } catch (Exception exception) {
            log.error("Exception: {}", exception.getMessage());
        } finally {
            assert service != null;
            service.shutdown();
        }

    }

    private static Runnable getRunnableTask1() {

        return () -> {
            var start = System.currentTimeMillis();
            log.info("Current Thread: {}, initial time: {}", Thread.currentThread().getName(), start);

            for (int i = 0; i < 10; i++) {
                log.info("Value using Runnable is: {}", i); // no sleep 1 task * 10 approx. 10 millis
            }

            var end = System.currentTimeMillis();
            log.info("Current Thread: {}, final time: {}", Thread.currentThread().getName(), end - start);
        };
    }

    @SneakyThrows
    private static Runnable getRunnableTask2() {

        return () -> {
            var start = System.currentTimeMillis();
            log.info("Current Thread: {}, initial time: {}", Thread.currentThread().getName(), start);

            for (int i = 0; i < 10; i++) {
                log.info("Value using Runnable is: {}", i); // no sleep 1 task * 10 approx. 10 millis
            }

            var end = System.currentTimeMillis();
            log.info("Current Thread: {}, final time: {}", Thread.currentThread().getName(), end - start);
        };
    }
}