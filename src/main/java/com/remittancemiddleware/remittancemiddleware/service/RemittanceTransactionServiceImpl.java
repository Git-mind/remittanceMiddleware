package com.remittancemiddleware.remittancemiddleware.service;

import com.remittancemiddleware.remittancemiddleware.customexception.CustomNotFoundException;
import com.remittancemiddleware.remittancemiddleware.dao.RemittanceTransactionDAO;
import com.remittancemiddleware.remittancemiddleware.dao.UserDAO;
import com.remittancemiddleware.remittancemiddleware.entity.Company;
import com.remittancemiddleware.remittancemiddleware.entity.User;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.TransactionStatus;
import com.remittancemiddleware.remittancemiddleware.entity.transaction.RemittanceTransaction;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RemittanceTransactionServiceImpl implements RemittanceTransactionService{
    private RemittanceTransactionDAO remittanceTransactionDAO;
    private UserDAO userDAO;

    public RemittanceTransactionServiceImpl (RemittanceTransactionDAO theRemittanceTransactionDAO, UserDAO theUserDAO){
        this.remittanceTransactionDAO = theRemittanceTransactionDAO;
        this.userDAO = theUserDAO;
    }

    @Override
    public List<RemittanceTransaction> findByTransactionStatusAndCompanyId(TransactionStatus status, int userId) throws CustomNotFoundException {

        Optional<User> result = userDAO.findById(userId);

        User theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        }
        else {
            // we didn't find the user
            throw new CustomNotFoundException("Did not find user id - " + userId);
        }
        Company company = theUser.getCompany();
        int companyId = company.getId();

        return remittanceTransactionDAO.findByTransactionStatusAndCompanyId(status, companyId);
    }

    @Override
    public List<RemittanceTransaction> findByCompanyId(int userId) throws CustomNotFoundException {
        Optional<User> result = userDAO.findById(userId);

        User theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        }
        else {
            // we didn't find the user
            throw new CustomNotFoundException("Did not find user id - " + userId);
        }
        Company company = theUser.getCompany();
        int companyId = company.getId();

        return remittanceTransactionDAO.findByCompanyId(companyId);
    }

    @Override
    public TransactionStatus getTransactionStatus(String status) throws CustomNotFoundException{
        TransactionStatus transactionStatus = null;
        if (status.equals("SUCCESSFUL")){
            transactionStatus = TransactionStatus.SUCCESSFUL;
        } else if (status.equals("PENDING_AML")){
            transactionStatus = TransactionStatus.PENDING_AML;
        } else if (status.equals("PENDING_COMPLIANCE_CHECKS")){
            transactionStatus = TransactionStatus.PENDING_COMPLIANCE_CHECKS;
        } else if (status.equals("REJECTED")){
            transactionStatus = TransactionStatus.REJECTED;
        } else{
            throw new CustomNotFoundException("Incorrect status input - " + status);
        }

        return transactionStatus;
    }


}
