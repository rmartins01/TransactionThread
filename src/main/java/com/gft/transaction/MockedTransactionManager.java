package com.gft.transaction;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

public class MockedTransactionManager implements PlatformTransactionManager {
    public TransactionStatus getTransaction(TransactionDefinition transactionDefinition) throws TransactionException {
        System.out.println("getTransaction in thread " + Thread.currentThread().getName() + " - transaction started");
        return new MockedTransactionStatus();
    }

    public void commit(TransactionStatus transactionStatus) throws TransactionException {
        System.out.println("commit");
    }

    public void rollback(TransactionStatus transactionStatus) throws TransactionException {
        System.out.println("rollback");
    }
}
