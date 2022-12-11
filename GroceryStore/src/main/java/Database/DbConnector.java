package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Katecory
 */
public class DbConnector
{
    public static final String DEFAULT_DB_URL = "jdbc:mysql://localhost:3306/grocery_store_project?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true";
    public static final String DEFAULT_USER_NAME = "Katecory";
    public static final String DEFAULT_PASSWORD = "Poseidon23.";
    
    private String db_url;
    private String username;
    private String password;
    
    private Connection conn;
    
    public DbConnector(String db_url, String username, String password)
    {
         this.db_url = db_url;
         this.username = username;
         this.password = password;
         this.conn = null;
    }
    
    public DbConnector()
    {
        this.db_url = DEFAULT_DB_URL;
        this.username = DEFAULT_USER_NAME;
        this.password = DEFAULT_PASSWORD;
        this.conn = null;
    }
    
    public Statement createStatement() throws SQLException
    {
        if(this.conn == null) {
            throw new SQLException("No connection is setup!");
        }
        
        return conn.createStatement();
    }
    
    public PreparedStatement createPreparedStatement(String statement) throws SQLException
    {
        if(this.conn == null) {
            throw new SQLException("No connection is setup!");
        }
        
        return conn.prepareStatement(statement);
    }
    
    public void setConnectionParameter(String db_url, String username, String password)
    {
        this.db_url = db_url;
        this.username = username;
        this.password = password;
    }
    
    public void connect() throws ClassNotFoundException, SQLException
    {
        this.conn = DbConnector.getConnection(this.db_url, this.username, this.password);
    }
    
    public void disconnect() throws SQLException
    {
        this.conn.close();
        this.conn = null;
    }
    
    /**
     * create connection 
     * 
     * @author viettuts.vn
     * @param dbURL: database's URL
     * @param userName: username is used to login
     * @param password: password is used to login
     * @return connection
     */
    private static Connection getConnection(String dbURL, String userName, String password) 
            throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbURL, userName, password);
        return conn;
    }
}
