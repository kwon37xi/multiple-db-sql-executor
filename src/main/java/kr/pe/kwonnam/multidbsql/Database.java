package kr.pe.kwonnam.multidbsql;

import java.util.Objects;

/**
 * Single Database Configuration
 */
public class Database {
    /** Database name */
    private String name;
    private String jdbcUrl;
    private String username;
    private String password;
    private String validationQuery;

    public Database() {
    }

    public Database(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Database)) return false;
        Database database = (Database) o;
        return Objects.equals(getName(), database.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
