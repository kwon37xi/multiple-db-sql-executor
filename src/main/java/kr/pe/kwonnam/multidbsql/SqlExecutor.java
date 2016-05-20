package kr.pe.kwonnam.multidbsql;

import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Execute SQLs
 */
public class SqlExecutor {
    private DatabaseGroup databaseGroup;

    public SqlExecutor(DatabaseGroup databaseGroup) {
        this.databaseGroup = databaseGroup;

        loadDriverClass();
    }

    private void loadDriverClass() {
        ExternalClassLoader externalClassLoader = new ExternalClassLoader(databaseGroup.getClasspaths());
        externalClassLoader.loadClass(databaseGroup.getDriverClassName());
    }

    public void executeSql(Database database, String sql, StatementResultProcessor processor) {
        if (StringUtils.isBlank(sql)) {
            return;
        }

        try (Connection connection = getConnection(database); Statement statement = connection.createStatement()) {
            statement.execute(sql);
            processor.process(statement);
        } catch (Exception e) {
            processor.processException(e);
        }
    }

    private Connection getConnection(Database database) {
        try {
            return DriverManager.getConnection(database.getJdbcUrl(), database.getUsername(), database.getPassword());
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
