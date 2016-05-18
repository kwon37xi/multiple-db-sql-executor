package kr.pe.kwonnam.multidbsql;

import org.apache.commons.collections4.CollectionUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExternalClassLoader {
    private List<URL> urls;
    private URLClassLoader urlClassLoader;

    public ExternalClassLoader(List<String> classpaths) {
        if (CollectionUtils.isEmpty(classpaths)) {
            throw new IllegalArgumentException("classpaths must not be empty.");
        }
        initializeUrls(classpaths);
        initializeUrlClassLoader();
    }

    private void initializeUrls(List<String> classpaths) {
        urls = new ArrayList<>();


        try {
            for (String classpath : classpaths) {
                urls.add(new File(classpath).toURI().toURL());
            }
        } catch (MalformedURLException e) {
            throw new IllegalStateException(e);
        }
    }

    private void initializeUrlClassLoader() {
        urlClassLoader = new URLClassLoader(urls.toArray(new URL[] {}), this.getClass().getClassLoader());
    }

    public List<URL> getUrls() {
        return Collections.unmodifiableList(urls);
    }

    /**
     * Load class from external classpaths.
     * @param className
     * @return
     */
    public Class<?> loadClass(String className) {
        try {
            return urlClassLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(className + " is not available.", e);
        }
    }
}
