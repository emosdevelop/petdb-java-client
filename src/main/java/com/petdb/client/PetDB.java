package com.petdb.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class PetDB {

    private final ExecutorService pool = Executors.newFixedThreadPool(1);
    private final InetSocketAddress address;
    private Connection connection;

    public PetDB(InetSocketAddress address) {
        this.address = address;
        try {
            this.connection = new Connection(address);
            pool.submit(this.connection);
        } catch (IOException e) {
            e.printStackTrace();
            this.close();
        }
    }

    public void close() {
        pool.shutdown();
    }

    public String call(String query) {
        if (query.isEmpty() || query.isBlank()) {
            return "";
        }
        this.connection.newRequest(query.trim());
        return this.connection.getResponse();
    }
}
