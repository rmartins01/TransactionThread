package com.gft.transaction;

import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

public class MockedTransactionStatus implements TransactionStatus {
    public boolean isNewTransaction() {
        return false;
    }

    public boolean hasSavepoint() {
        return false;
    }

    public void setRollbackOnly() {

    }

    public boolean isRollbackOnly() {
        return false;
    }

    public void flush() {

    }

    public boolean isCompleted() {
        return false;
    }

    public Object createSavepoint() throws TransactionException {
        return null;
    }

    public void rollbackToSavepoint(Object o) throws TransactionException {

    }

    public void releaseSavepoint(Object o) throws TransactionException {

    }
}
