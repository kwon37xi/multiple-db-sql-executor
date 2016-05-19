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

    public void executeSql(Database database, String sql) {
        if (StringUtils.isBlank(sql)) {
            return;
        }

        final Connection connection = getConnection(database);
        try {
            final Statement statement = connection.createStatement();

            final boolean executionType = statement.execute(sql);
            // executionType true : 쿼리로 ResultSet 존재
            // executionType false : update 로 ResultSet 없음
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage(), e);
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
