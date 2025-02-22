package pool;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class DBCPDataSourceOracle {
    
    private static BasicDataSource ds = new BasicDataSource();
    
    static {
        ds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        ds.setUsername("root");
        ds.setPassword("manager");
        ds.setDriverClassName("oracle.jdbc.OracleDriver");
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(10);
    }
    
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    
    private DBCPDataSourceOracle() { }
}