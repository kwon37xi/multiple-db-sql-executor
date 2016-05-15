package kr.pe.kwonnam.multidbsql.configloader;

import com.google.common.collect.Lists;
import kr.pe.kwonnam.multidbsql.Database;
import kr.pe.kwonnam.multidbsql.DatabaseGroup;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

/**
 * UTF-8 encoded {@code *.properties} based configuration loader.
 * *.properties file are not required to be encoded with {@code native2ascii}.
 */
public class PropertiesConfigurationLoader implements DatabaseConfigurationLoader {
    public static final String LOADABLE_EXTENSION = ".properties";

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    @Override
    public boolean checkLoadable(String argument) {
        if (argument == null) {
            return false;
        }
        return argument.endsWith(LOADABLE_EXTENSION);
    }

    @Override
    public DatabaseGroup load(String argument) {
        Path path = convertArgumentToPath(argument);

        Properties properties = loadProperties(argument, path);

        DatabaseGroup databaseGroup = buildDatabaseConfigurationFromProperties(properties);

        return databaseGroup;
    }

    private Path convertArgumentToPath(String argument) {
        Path path = Paths.get(argument);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(argument + " file does not exist.");
        }
        return path;
    }

    private Properties loadProperties(String argument, Path path) {
        Properties properties = new Properties();
        try {
            properties.load(Files.newBufferedReader(path, DEFAULT_CHARSET));
        } catch (IOException ex) {
            throw new IllegalStateException("Failed to load configuration from " + argument + ".", ex);
        }
        return properties;
    }

    private DatabaseGroup buildDatabaseConfigurationFromProperties(Properties properties) {
        DatabaseGroup databaseGroup = new DatabaseGroup();
        databaseGroup.setName(properties.getProperty("multidbsql.groupname"));

        final String classpathsProperty = properties.getProperty("multidbsql.classpath");
        if (!StringUtils.isBlank(classpathsProperty)) {
            final List<String> classpaths = Lists.newArrayList(classpathsProperty.split(","));
            databaseGroup.setClasspaths(classpaths);
        }

        String databaseProperty = properties.getProperty("multidbsql.database");
        if (StringUtils.isBlank(databaseProperty)) {
            throw new IllegalArgumentException("'multidbsql.database' property is required.");
        }
        String [] databaseNames = databaseProperty.split(",");

        List<Database> databases = Lists.newArrayList();

        for (String databaseName : databaseNames) {
            Database database = new Database(databaseName);
            String jdbcUrl = properties.getProperty("multidbsql.database." + databaseName + ".jdbcurl");
            database.setJdbcUrl(jdbcUrl);

            String username = properties.getProperty("multidbsql.database." + databaseName + ".username");
            database.setUsername(username);

            String password = properties.getProperty("multidbsql.database." + databaseName + ".password");
            database.setPassword(password);

            String validationQuery = properties.getProperty("multidbsql.database." + databaseName + ".validation_query");
            database.setValidationQuery(validationQuery);

            databases.add(database);
        }

        databaseGroup.setDatabases(databases);

        return databaseGroup;
    }
}
