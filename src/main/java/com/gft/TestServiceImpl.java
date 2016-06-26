package com.gft;

import org.springframework.transaction.annotation.Transactional;

public class TestServiceImpl implements TestService {

    @Transactional
    public void doInTransaction() {
        System.out.println("TestServiceImpl.doInTransaction");
    }
}
