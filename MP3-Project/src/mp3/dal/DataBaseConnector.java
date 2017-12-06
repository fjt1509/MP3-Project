/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;

public class DataBaseConnector
{
        private SQLServerDataSource dataSource;

        
    public DataBaseConnector() throws IOException
    {
       
        dataSource = new SQLServerDataSource();

        dataSource.setServerName("EASV-DB2");
        dataSource.setPortNumber(1433);
        dataSource.setDatabaseName("MyTunes_LiquidGold");
        dataSource.setUser("CS2017A_5_java");
        dataSource.setPassword("javajava");
        
    }
        
    /**
     * This method keeps the connection with the database
     * @return
     * @throws SQLServerException 
     */    
    public Connection getConnection() throws SQLServerException
    {
        return dataSource.getConnection();
    }
        

}



    




