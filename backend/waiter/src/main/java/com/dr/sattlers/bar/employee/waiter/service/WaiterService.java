package com.dr.sattlers.bar.employee.waiter.service;

import com.dr.sattlers.bar.db.BasicConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

@Service
public class WaiterService {
    @Autowired
    private BasicConnectionPool connectionPool;

    // Use the connection pool to obtain connections
    public void doSomething() throws SQLException {
        try (Connection conn = connectionPool.getConnection()) {
            // Do something with the connection
        }
    }
}
