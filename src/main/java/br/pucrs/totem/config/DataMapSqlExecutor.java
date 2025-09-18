package br.pucrs.totem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class DataMapSqlExecutor {
    @Autowired
    private DataSource dataSource;

    @EventListener(ApplicationReadyEvent.class)
    public void executeDataMapSql() {
        try (Connection conn = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("data-map.sql"));
            System.out.println("data-map.sql executed!");
        } catch (Exception e) {
            System.err.println("Error executing data-map.sql: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
