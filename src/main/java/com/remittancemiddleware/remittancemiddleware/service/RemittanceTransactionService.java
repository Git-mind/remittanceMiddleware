package com.remittancemiddleware.remittancemiddleware.service;

import com.remittancemiddleware.remittancemiddleware.entity.transaction.RemittanceTransaction;
import com.remittancemiddleware.remittancemiddleware.enums.TransactionStatus;

import java.util.List;

public interface RemittanceTransactionService {
    List<RemittanceTransaction> findByTransactionStatusAndCompanyId(TransactionStatus status, int companyId);
}