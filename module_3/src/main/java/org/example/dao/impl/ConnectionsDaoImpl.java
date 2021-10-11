package org.example.dao.impl;

import org.example.Module3Main;
import org.example.dao.ConnectionsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionsDaoImpl implements ConnectionsDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionsDaoImpl.class);
    private static Connection connection = null;

    public ConnectionsDaoImpl(String username, String password) {
        Properties props = loadProperties();
        String url = props.getProperty("url");
        props.setProperty("user", username);
        props.setProperty("password", password);

        try {
            connection = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            LOGGER.error("Error: ", e);
            throw new RuntimeException(new SQLException(e));
        }
    }

    private Properties loadProperties() {
        Properties properties = new Properties();

        try (InputStream input = Module3Main.class.getResourceAsStream("/jdbc.properties")) {
            properties.load(input);
        } catch (IOException e) {
            LOGGER.error("Error: ", e);
            throw new UncheckedIOException(e);
        }

        return properties;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }
}
