package org.example.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionsDao extends AutoCloseable {

    Connection getConnection();

    @Override
    void close() throws SQLException;
}
