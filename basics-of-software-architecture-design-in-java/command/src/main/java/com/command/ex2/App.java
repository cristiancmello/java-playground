package com.command.ex2;

/**
 * Created by david on 09/04/17.
 * <p>
 * Commands package a piece of computation ... this computation
 * may be invoked long after the command objects are created
 * <p>
 * - it can be invoked by different threads !!!
 * - schedulers, thread pools, job queues ...
 */
public class App {
    public static void main(String[] args) {

        final Algorithm algorithm = new Algorithm();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                algorithm.produce();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                algorithm.consume();
            }
        });

        t1.start();
        t2.start();

    }
}