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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    /**
     * Creates a songObject in the database
     * @param title
     * @param artist
     * @param category
     * @param fileName
     * @return
     * @throws SQLServerException
     * @throws SQLException 
     */
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

    /**
     * Getting songs from the database
     * @return
     * @throws SQLException 
     */
    public List<Song> getAllSongs() throws SQLException
    {
        try (Connection con = dbConnector.getConnection()) //I create a connection as a resource using my DatabaseConnector object:
        {
            String sql = "SELECT * FROM Songs"; // I prepare my SQL

            Statement st = con.createStatement(); //I create a statement object
            ResultSet rs = st.executeQuery(sql); //I execute my SQL and receive a ResultSet

            List<Song> allSongs = new ArrayList<>(); // I Prepare a list for holding my returned companies
            while (rs.next()) //While there are companies (rows) in the result set:
            {
                Song song = getSongFromResultSetRow(rs);
                allSongs.add(song);
            }
            //I return all the found companies:
            return allSongs;
        }
        //The connection to the database i automatically closed by the "try with resources"..
        //The connection to the database i automatically closed by the "try with resources"..
    }
    
    
    /**
     *This method retrives all data from the database
     * @param rs
     * @return
     * @throws SQLException 
     */
    private Song getSongFromResultSetRow(ResultSet rs) throws SQLException
    {
        //I extract the data from the current row in the resultset:
        int id = rs.getInt("Id");
        String title = rs.getString("Title");
        String artist = rs.getString("Artist");
        String category = rs.getString("Category");
        String fileName = rs.getString("FileName");

        //I create the company object and add it to my list of results:
        Song song = new Song(id, title, artist, category, fileName);
        return song;
    }
    
    
    /**
     * This method removes a song from the database
     * @param song 
     */
    public void remove(Song song) 
    {
        try (Connection con = dbConnector.getConnection();)
        {
            Statement stmt = con.createStatement();
            stmt.execute("DELETE FROM Songs WHERE id="+song.getId());
            
        }
        catch (SQLException ex)
        {
            Logger.getLogger(SongDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
