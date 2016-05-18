package kr.pe.kwonnam.multidbsql;

import java.util.List;
import java.util.Objects;

/**
 * Group of databases;
 */
public class DatabaseGroup {
    /** Database group name */
    private String name;

    /** JDBC Driver Class name */
    private String driverClassName;

    /** external jdbc library classpaths */
    private List<String> classpaths;

    /** databases */
    private List<Database> databases;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Objects.requireNonNull(name, "group name must not be null.");
        this.name = name;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public List<String> getClasspaths() {
        return classpaths;
    }

    public void setClasspaths(List<String> classpaths) {
        this.classpaths = classpaths;
    }

    public List<Database> getDatabases() {
        return databases;
    }

    public void setDatabases(List<Database> databases) {
        this.databases = databases;
    }
}
