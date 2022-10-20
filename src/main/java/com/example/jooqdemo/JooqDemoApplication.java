package com.example.jooqdemo;

import com.example.jooqdemo.model.tables.records.CustomerRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;

import static com.example.jooqdemo.model.Tables.CUSTOMER;

@SpringBootApplication
@RequiredArgsConstructor
public class JooqDemoApplication {

    private final DSLContext dslContext;

    public static void main(String[] args) {
        SpringApplication.run(JooqDemoApplication.class, args);
    }

    @PostConstruct
    public void post() {
        String userName = "postgres";
        String password = "123";
        String url = "jdbc:postgresql://192.168.80.64:5432/postgres";

        // Connection is the only JDBC resource that we need
        // PreparedStatement and ResultSet are handled by jOOQ, internally
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            // ...

            DSLContext create = DSL.using(conn, SQLDialect.POSTGRES);

            CustomerRecord customerRecord = create
                    .select()
                    .from(CUSTOMER)
                    .where(CUSTOMER.ID.eq(1L))
                    .fetchOneInto(CUSTOMER);

            System.out.println(customerRecord.getId());
            System.out.println(customerRecord.getEmail());
            System.out.println(customerRecord.getFirstName());
            System.out.println(customerRecord.getLastName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
