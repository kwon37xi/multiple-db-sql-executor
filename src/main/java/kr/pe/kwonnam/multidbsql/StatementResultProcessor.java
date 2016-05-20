package kr.pe.kwonnam.multidbsql;

import java.sql.Statement;

/**
 * Take executed {@link Statement} and process the results.
 */
public interface StatementResultProcessor {
    void process(Statement statement);
    void processException(Exception ex);
}
