package kr.pe.kwonnam.multidbsql.configloader;

import kr.pe.kwonnam.multidbsql.DatabaseGroup;

/**
 * UTF-8 encoded {@code *.properties} based configuration loader.
 * *.properties file are not required to be encoded with {@code native2ascii}.
 */
public class PropertiesConfigurationLoader implements DatabaseConfigurationLoader {
    @Override
    public boolean checkLoadable(String argument) {
        return false;
    }

    @Override
    public DatabaseGroup load(String argument) {
        return null;
    }
}
