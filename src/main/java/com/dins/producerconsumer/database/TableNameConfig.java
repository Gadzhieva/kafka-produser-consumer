package com.dins.producerconsumer.database;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TableNameConfig {

    @Value("${table.name}")
    private String tableName;

    @Bean
    public PhysicalNamingStrategyStandardImpl physicalNamingStrategyStandard() {
        return new PhysicalNamingImpl();
    }

    class PhysicalNamingImpl extends PhysicalNamingStrategyStandardImpl {

        @Override
        public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
            return new Identifier(tableName, name.isQuoted());
        }
    }
}
