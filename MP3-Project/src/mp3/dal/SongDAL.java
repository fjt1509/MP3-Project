/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import mp3.be.Song;

/**
 *
 * @author frederik
 */
public class SongDAL 
{
    
    private DataBaseConnector dbConnector;

    public SongDAL() throws IOException
    {
        dbConnector = new DataBaseConnector();
    }

    public Song createSong(String title, String artist, String category, String fileName) throws SQLServerException, SQLException
    {
        try (Connection con = dbConnector.getConnection())
        {
            String sql = "INSERT INTO Songs VALUES (?, ?, ?, ?);";

            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, title);
            statement.setString(2, artist);
            statement.setString(3, category);
            statement.setString(4, fileName);


            if (statement.executeUpdate() == 1)
            {
                //Good
                ResultSet rs = statement.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                Song c = new Song(id, title, artist, category, fileName);
                return c;
            }
            throw new RuntimeException("Can't create company");
        }
    }
    
    
}
