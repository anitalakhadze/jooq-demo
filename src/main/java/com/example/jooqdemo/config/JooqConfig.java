package com.example.jooqdemo.config;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JooqConfig {

    private final DSLContext dslContext;

    @Autowired
    public JooqConfig(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

}
