package com.petdb.client;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;

import static org.junit.jupiter.api.Assertions.*;

class PetDBTest {

    PetDB db;

    @BeforeEach
    void initDB() {
        db = new PetDB(new InetSocketAddress(12542));
        db.start();
    }

    @AfterEach
    void exitDB() {
        db.close();
    }

    @Test
    void testCall() {
        String result = db.call("set abc c");
        assertEquals("OK",result);
        System.out.println(db.call("aa"));
        System.out.println(db.call("get abc"));
    }
}
