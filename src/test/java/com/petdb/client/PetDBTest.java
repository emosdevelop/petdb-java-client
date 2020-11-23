package com.petdb.client;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PetDBTest {

    PetDB db;

    @BeforeEach
    void initDB() {
        db = new PetDB(new InetSocketAddress(12542));
        db.open();
    }

    @AfterEach
    void exitDB() {
        db.close();
    }

    @Test
    void testCall() {
        String result = db.call("set key value");
        assertEquals("OK", result);
    }

    @Test
    void testClose() {
        Throwable exceptionThatWasThrown = assertThrows(PetDBClientConnectionException.class, () -> {
            this.db.close();
            this.db.call("something");
        });
        assertEquals("Connection to PetDB server is not open",
                exceptionThatWasThrown.getMessage());
    }

    @Test
    void testOpenTwice() {
        Throwable exceptionThatWasThrown = assertThrows(PetDBClientConnectionException.class, () -> {
            this.db.open();
        });
        assertEquals("Connection to PetDB server is already open",
                exceptionThatWasThrown.getMessage());
    }
}
