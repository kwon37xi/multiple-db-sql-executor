package kr.pe.kwonnam.multidbsql;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

public class ExternalClassLoaderTest {
    public static final String TEST_JAR = "src/test/resources/h2-1.3.176.jar";
    public static final String TEST_JAR2 = "/some/where/test.jar";
    private Logger log = LoggerFactory.getLogger(ExternalClassLoaderTest.class);

    @Test
    public void constructor_param_null() throws Exception {
        try {
            new ExternalClassLoader(null);
            failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException ex) {
            assertThat(ex).hasMessage("classpaths must not be empty.");
        }
    }

    @Test
    public void constructor_param_empty() throws Exception {
        try {
            new ExternalClassLoader(new ArrayList<>());
            failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException ex) {
            assertThat(ex).hasMessage("classpaths must not be empty.");
        }
    }

    @Test
    public void getUrls() throws Exception {
        List<String> classpaths = new ArrayList<>();
        classpaths.add(TEST_JAR);
        classpaths.add(TEST_JAR2);

        ExternalClassLoader externalClassLoader = new ExternalClassLoader(classpaths);

        final List<URL> urls = externalClassLoader.getUrls();
        assertThat(urls).hasSize(2)
            .contains(
                new File(TEST_JAR).toURI().toURL(),
                new File(TEST_JAR2).toURI().toURL()
            );
    }

    @Test
    public void loadClass() throws Exception {
        List<String> classpaths = new ArrayList<>();
        classpaths.add(TEST_JAR);

        ExternalClassLoader externalClassLoader = new ExternalClassLoader(classpaths);
        final Class<?> h2Driver = externalClassLoader.loadClass("org.h2.Driver");
        assertThat(h2Driver).isNotNull().matches(aClass -> aClass.getCanonicalName().equals("org.h2.Driver"), "loaded class must be org.h2.Driver");
    }
}
