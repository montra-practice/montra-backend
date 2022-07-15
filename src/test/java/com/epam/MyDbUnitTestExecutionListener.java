package com.epam;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyDbUnitTestExecutionListener extends TransactionDbUnitTestExecutionListener {


    @Autowired
    private DataSource dataSource;

    private static final List<String> IGNORED_TABLES = Lists.newArrayList(
            "TABLE_A",
            "TABLE_B"
    );

    private static final String SQL_DISABLE_REFERENTIAL_INTEGRITY = "SET REFERENTIAL_INTEGRITY FALSE";
    private static final String SQL_ENABLE_REFERENTIAL_INTEGRITY = "SET REFERENTIAL_INTEGRITY TRUE";

    private static final String SQL_FIND_TABLE_NAMES = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA='%s'";
    private static final String SQL_TRUNCATE_TABLE = "TRUNCATE TABLE %s.%s";

    private static final String SQL_FIND_SEQUENCE_NAMES = "SELECT SEQUENCE_NAME FROM INFORMATION_SCHEMA.SEQUENCES WHERE SEQUENCE_SCHEMA='%s'";
    private static final String SQL_RESTART_SEQUENCE = "ALTER SEQUENCE %s.%s RESTART WITH 1";

    private String schema = "PUBLIC";


    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        testContext.getApplicationContext()
                .getAutowireCapableBeanFactory()
                .autowireBean(this);

        cleanupDatabase();

        super.beforeTestMethod(testContext);
    }

    private void cleanupDatabase() throws SQLException {
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.execute(SQL_DISABLE_REFERENTIAL_INTEGRITY);

            Set<String> tables = new HashSet<>();
            try (ResultSet resultSet = statement.executeQuery(String.format(SQL_FIND_TABLE_NAMES, schema))) {
                while (resultSet.next()) {
                    tables.add(resultSet.getString(1));
                }
            }

            for (String table : tables) {
                if (!IGNORED_TABLES.contains(table)) {
                    statement.executeUpdate(String.format(SQL_TRUNCATE_TABLE, schema, table));
                }
            }

            Set<String> sequences = new HashSet<>();
            try (ResultSet resultSet = statement.executeQuery(String.format(SQL_FIND_SEQUENCE_NAMES, schema))) {
                while (resultSet.next()) {
                    sequences.add(resultSet.getString(1));
                }
            }

            for (String sequence : sequences) {
                statement.executeUpdate(String.format(SQL_RESTART_SEQUENCE, schema, sequence));
            }

            statement.execute(SQL_ENABLE_REFERENTIAL_INTEGRITY);
        }
    }
}
