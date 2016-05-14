package kr.pe.kwonnam.multidbsql.configloader;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

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
}
