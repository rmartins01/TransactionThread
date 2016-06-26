package com.gft;

import org.springframework.transaction.annotation.Transactional;

public class RunnableThread implements Runnable {

    private TestService service;

    public RunnableThread(TestService service) {
        this.service = service;
    }

    @Transactional
    public void doInTransaction() {
        // Why does this method does not run inside a transaction if the method has the @Transactional annotation?
        System.out.println("RunnableThread.doInTransaction");
        service.doInTransaction();
    }

    public void run() {
        doInTransaction();
    }
}
