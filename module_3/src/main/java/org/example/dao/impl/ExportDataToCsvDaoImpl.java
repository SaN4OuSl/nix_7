package org.example.dao.impl;

import com.opencsv.CSVWriter;
import org.example.dao.ExportDataToCsvDao;
import org.example.entity.Accounts;
import org.example.entity.Operations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExportDataToCsvDaoImpl implements ExportDataToCsvDao {
    private final Connection connection;
    private final Logger LOGGER = LoggerFactory.getLogger(ExportDataToCsvDaoImpl.class);

    public ExportDataToCsvDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void exportOperationsInPeriodToCsv(Accounts account,
                                              Instant dateFrom,
                                              Instant dateTo,
                                              String filePath) {
        LOGGER.info("Starting export operations from {} to {} to csv file {}",
                dateFrom.toString(), dateTo.toString(), filePath);

        List<String[]> csvData = new ArrayList<>();
        String[] header = {"id", "result", "date"};
        csvData.add(header);

        List<Operations> operations = getOperationInPeriod(account, dateFrom, dateTo);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());
        for (Operations operation : operations) {
            csvData.add(new String[]{
                    String.valueOf(operation.getId()),
                    String.valueOf(operation.getResult()),
                    formatter.format(operation.getDateTime())});
        }

        int balance = getBalanceInPeriod(account, dateFrom, dateTo);

        try (Writer writer = new FileWriter(filePath); CSVWriter csvWriter = new CSVWriter(writer)) {
            csvWriter.writeAll(csvData);
            csvWriter.writeNext(new String[]{"total", String.valueOf(balance)});
        } catch (IOException e) {
            LOGGER.error("Error while working with file {}", filePath, e);
            throw new RuntimeException(new IOException("Error while working with file!", e));
        }
        LOGGER.info("Successful export operations from {} to {} to csv file {}",
                dateFrom, dateTo, filePath);
    }

    private List<Operations> getOperationInPeriod(Accounts account, Instant dateFrom, Instant dateTo) {
        LOGGER.info("Start getting operations from {} to {}!",
                dateFrom.toString(), dateTo.toString());
        List<Operations> operations = new ArrayList<>();

        try (PreparedStatement getOperationsStatement = connection.prepareStatement(
                "select * from Operations op where op.account_id = ? and op.date_time between ? and ?")) {

            getOperationsStatement.setLong(1, account.getId());
            getOperationsStatement.setTimestamp(2, Timestamp.from(dateFrom));
            getOperationsStatement.setTimestamp(3, Timestamp.from(dateTo));

            ResultSet resultSet = getOperationsStatement.executeQuery();

            while (resultSet.next()) {
                Operations operation = new Operations(
                        resultSet.getLong("id"),
                        account,
                        resultSet.getInt("result"),
                        resultSet.getTimestamp("date_time").toInstant()
                );
                operations.add(operation);
            }
        } catch (SQLException e) {
            LOGGER.error("Error getting operation from account with id {}", account.getId(), e);
            throw new RuntimeException(new SQLException("Error getting operation!"));
        }

        return operations;
    }

    private int getBalanceInPeriod(Accounts account, Instant dateFrom, Instant dateTo) {
        int balance;
        try (PreparedStatement getBalance = connection.prepareStatement(
                "select abs(sum(result)) from Operations op where " +
                        "op.account_id = ? and (op.date_time between ? and ?)")) {

            getBalance.setLong(1, account.getId());
            getBalance.setTimestamp(2, Timestamp.from(dateFrom));
            getBalance.setTimestamp(3, Timestamp.from(dateTo));

            ResultSet resultSet = getBalance.executeQuery();
            resultSet.next();
            balance = resultSet.getInt(1);

        } catch (SQLException e) {
            LOGGER.error("Error getting balance in period from account with id {}!", account.getId(), e);
            throw new RuntimeException(new SQLException("Error getting balance in period!", e));
        }

        return balance;
    }
}
