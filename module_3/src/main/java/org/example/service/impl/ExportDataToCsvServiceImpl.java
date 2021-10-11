package org.example.service.impl;

import org.example.dao.ExportDataToCsvDao;
import org.example.dao.impl.ExportDataToCsvDaoImpl;
import org.example.entity.Accounts;
import org.example.entity.User;
import org.example.service.ExportDataToCsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class ExportDataToCsvServiceImpl implements ExportDataToCsvService {
    private final ExportDataToCsvDao exportDataToCsvDao;
    private final Logger LOGGER = LoggerFactory.getLogger(ExportDataToCsvServiceImpl.class);

    public ExportDataToCsvServiceImpl(Connection connection) {
        this.exportDataToCsvDao = new ExportDataToCsvDaoImpl(connection);
    }

    @Override
    public void exportOperationsInPeriodToCsv(User user,
                                              Accounts account,
                                              LocalDateTime dateFrom,
                                              LocalDateTime dateTo,
                                              String filePath) {
        if (!user.getId().equals(account.getUser().getId())) {
            LOGGER.error("Error while export operations!" +
                            " Account with id {} doesn't belong to user with id {}!",
                    account.getId(), user.getId());
            throw new RuntimeException(new IllegalAccessException("Current account doesn't belong to user!"));
        }
        exportDataToCsvDao.exportOperationsInPeriodToCsv(account,
                dateFrom.toInstant(ZoneOffset.UTC),
                dateTo.toInstant(ZoneOffset.UTC),
                filePath);
    }
}
