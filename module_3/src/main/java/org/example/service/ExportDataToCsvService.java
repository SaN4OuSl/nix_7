package org.example.service;

import org.example.entity.Accounts;
import org.example.entity.User;

import java.time.LocalDateTime;

public interface ExportDataToCsvService {
    void exportOperationsInPeriodToCsv(User user,
                                       Accounts account,
                                       LocalDateTime dateFrom,
                                       LocalDateTime dateTo,
                                       String filePath);
}
