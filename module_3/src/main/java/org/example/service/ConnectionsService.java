package org.example.service;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionsService {

    Connection getConnection();

    void close() throws SQLException;
}
