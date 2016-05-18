package kr.pe.kwonnam.multidbsql.configloader;

import kr.pe.kwonnam.multidbsql.Database;
import kr.pe.kwonnam.multidbsql.DatabaseGroup;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

public class PropertiesConfigurationLoaderTest {
    private Logger log = LoggerFactory.getLogger(PropertiesConfigurationLoaderTest.class);

    private PropertiesConfigurationLoader loader;

    @Before
    public void setUp() throws Exception {
        loader = new PropertiesConfigurationLoader();
    }

    @Test
    public void checkLoadable_properties_true() throws Exception {
        assertThat(loader.checkLoadable("hello.properties")).isTrue();
        assertThat(loader.checkLoadable("something_config.properties")).isTrue();
    }

    @Test
    public void checkLoadable_false() throws Exception {
        assertThat(loader.checkLoadable(null)).isFalse();
        assertThat(loader.checkLoadable("")).isFalse();
        assertThat(loader.checkLoadable("  ")).isFalse();
        assertThat(loader.checkLoadable("config.xml")).isFalse();
        assertThat(loader.checkLoadable("config_properties.xml")).isFalse();
        assertThat(loader.checkLoadable("config_properties.yml")).isFalse();
    }

    @Test
    public void load_file_not_exist() throws Exception {
        try {
            loader.load("/unknown/path/to/some.properties");
            failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException ex) {
            assertThat(ex).hasMessage("/unknown/path/to/some.properties file does not exist.");
        }
    }

    @Test
    public void load() throws Exception {
        final DatabaseGroup dbGroup = loader.load("src/test/resources/test_config.properties");

        assertThat(dbGroup.getName()).isEqualTo("MultiDBSQL 테스트");
        assertThat(dbGroup.getDriverClassName()).isEqualTo("org.h2.Driver");
        assertThat(dbGroup.getClasspaths()).hasSize(2).contains("/somewhere/mysql_jdbc.jar", "postgresql.jar");
        assertThat(dbGroup.getDatabases()).hasSize(2)
            .containsOnly(new Database("db1"), new Database("디비2"));
    }

    @Test
    public void readDatabaseNames_no_name() throws Exception {
        Properties properties = new Properties(); // no props.
        try {
            loader.readDatabaseNames(properties);
            failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException ex) {
            assertThat(ex).hasMessage("'multidbsql.database' property is required.");
        }
    }

    @Test
    public void buildClasspaths_no_classpath() throws Exception {
        Properties properties = new Properties();
        assertThat(loader.buildClasspaths(properties)).isEmpty();
    }
}
