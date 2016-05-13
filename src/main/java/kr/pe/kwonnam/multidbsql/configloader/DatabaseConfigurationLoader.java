package kr.pe.kwonnam.multidbsql.configloader;

import kr.pe.kwonnam.multidbsql.DatabaseGroup;

public interface DatabaseConfigurationLoader {
    boolean checkLoadable(String argument);
    DatabaseGroup load(String argument);
}
