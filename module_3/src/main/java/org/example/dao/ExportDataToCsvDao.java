package org.example.dao;

import org.example.entity.Accounts;

import java.time.Instant;

public interface ExportDataToCsvDao {
    void exportOperationsInPeriodToCsv(Accounts account,
                                       Instant dateFrom,
                                       Instant dateTo,
                                       String filePath);
}
