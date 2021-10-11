package org.example.service.impl;

import org.example.dao.ConnectionsDao;
import org.example.service.ConnectionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class ConnectionsServiceImpl implements ConnectionsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionsServiceImpl.class);

    private final ConnectionsDao connectionsDao;

    public ConnectionsServiceImpl(ConnectionsDao dao) {
        connectionsDao = dao;
    }

    @Override
    public Connection getConnection() {
        LOGGER.info("Start get connection");
        return connectionsDao.getConnection();
    }
}
