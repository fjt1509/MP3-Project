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
import mp3.be.Playlist;
import mp3.be.Song;

/**
 *
 * @author Daniels PC
 */
public class PlaylistDAL
{
     private DataBaseConnector dbConnector;
    
    public PlaylistDAL() throws IOException
    {
        dbConnector = new DataBaseConnector();
    }

    
    /**
     * Creates a playlistObject in the database
     * @param playlistName
     * @return
     * @throws SQLServerException
     * @throws SQLException 
     */
    public Playlist createPlaylist(String playlistName) throws SQLServerException, SQLException
    {
        try (Connection con = dbConnector.getConnection())
        {
            String sql = "INSERT INTO Playlist VALUES (?);";

            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            

            statement.setString(1, playlistName);
            


            if (statement.executeUpdate() == 1)
            {
                //Good
                ResultSet rs = statement.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                Playlist c = new Playlist(id, playlistName);
                return c;
            }
            throw new RuntimeException("Can't create company");
        }
    }

    
    
    /**
     * Getting playlist from the database
     * @return
     * @throws SQLException 
     */
    public List<Playlist> getAllPlaylists() throws SQLException
    {
        try (Connection con = dbConnector.getConnection()) //I create a connection as a resource using my DatabaseConnector object:
        {
            String sql = "SELECT * FROM Playlist"; // I prepare my SQL

            Statement st = con.createStatement(); //I create a statement object
            ResultSet rs = st.executeQuery(sql); //I execute my SQL and receive a ResultSet

            List<Playlist> allPlaylists = new ArrayList<>(); // I Prepare a list for holding my returned companies
            while (rs.next()) //While there are companies (rows) in the result set:
            {
                Playlist playlist = getPlaylistFromResultSetRow(rs);
                allPlaylists.add(playlist);
            }
            //I return all the found companies:
            return allPlaylists;
        }
        //The connection to the database i automatically closed by the "try with resources"..
        //The connection to the database i automatically closed by the "try with resources"..
    }
    
    /**
     * This method retrives all data from the database
     * @param rs
     * @return
     * @throws SQLException 
     */
    private Playlist getPlaylistFromResultSetRow(ResultSet rs) throws SQLException
    {
        //I extract the data from the current row in the resultset:
        int id = rs.getInt("Id");
        String name = rs.getString("Name");


        //I create the company object and add it to my list of results:
        Playlist playlist = new Playlist(id, name);
        return playlist;
    }
    
}


