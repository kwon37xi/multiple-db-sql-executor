package kr.pe.kwonnam.multidbsql;

import java.util.List;

/**
 * Group of databases;
 */
public class DatabaseGroup {
    /** Database group name */
    private String name;

    /** external jdbc library classpaths */
    private List<String> classpaths;

    /** databases */
    private List<Database> databases;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
