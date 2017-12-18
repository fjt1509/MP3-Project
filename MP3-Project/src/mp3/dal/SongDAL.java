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
import mp3.be.Playlist;
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
    public Song createSong(String title, String artist, String category, String time, String fileName) throws SQLServerException, SQLException
    {
        try (Connection con = dbConnector.getConnection())
        {
            String sql = "INSERT INTO Songs VALUES (?, ?, ?, ?, ?);";

            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, title);
            statement.setString(2, artist);
            statement.setString(3, category);
            statement.setString(4, time);
            statement.setString(5, fileName);


            if (statement.executeUpdate() == 1)
            {
                //Good
                ResultSet rs = statement.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                Song c = new Song(id, title, artist, category, time, fileName);
                return c;
            }
            throw new RuntimeException("Can't create song");
        }
    }

    /**
     * Getting songs from the database
     * @return
     * @throws SQLException 
     */
    public List<Song> getAllSongs() throws SQLException
    {
        try (Connection con = dbConnector.getConnection()) 
        {
            String sql = "SELECT * FROM Songs"; 

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            List<Song> allSongs = new ArrayList<>(); 
            while (rs.next()) 
            {
                Song song = getSongFromResultSetRow(rs);
                allSongs.add(song);
            }
           
            return allSongs;
        }
    }
    
    
    /**
     *This method retrives all data from the database
     * @param rs
     * @return
     * @throws SQLException 
     */
    private Song getSongFromResultSetRow(ResultSet rs) throws SQLException
    {
        int id = rs.getInt("Id");
        String title = rs.getString("Title");
        String artist = rs.getString("Artist");
        String category = rs.getString("Category");
        String time = rs.getString("time");
        String fileName = rs.getString("FileName");

        Song song = new Song(id, title, artist, category, time, fileName);
        return song;
    }
    
    public Song getSongById(int id) throws SQLException
    {
        try (Connection con = dbConnector.getConnection())
        {
            String sql = "SELECT * FROM Songs WHERE id = ?;";

            PreparedStatement statement = con.prepareStatement(sql);

            statement.setInt(1, id);


            
                ResultSet rs = statement.executeQuery();
                rs.next();

                Song c = getSongFromResultSetRow(rs);
                return c;
            

        }
    }
    
    
    /**
     * This method removes a song from the database
     * @param song 
     */
    public void remove(Song song) 
    {
        try (Connection con = dbConnector.getConnection();)
        {
            String sql = "DELETE FROM PlaylistSong WHERE SongID = ?; DELETE FROM Songs WHERE id = ?;";

            PreparedStatement statement = con.prepareStatement(sql);
            
            statement.setInt(1, song.getId());
            statement.setInt(2, song.getId());
            
            statement.executeUpdate();

            
        }
        catch (SQLException ex)
        {
            Logger.getLogger(SongDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateSong(int id, String updatedTitle, String updatedArtist, String updatedCategory) throws SQLException 
    {
        try (Connection con = dbConnector.getConnection())
        {
            String sql = "UPDATE Songs SET title = ?, artist = ?, category = ? WHERE id = ?;";

            PreparedStatement statement = con.prepareStatement(sql);
            
            statement.setString(1, updatedTitle);
            statement.setString(2, updatedArtist);
            statement.setString(3, updatedCategory);
            statement.setInt(4, id);
            
            statement.executeUpdate();
            
        }  
    }

    public void setSongsOrder(Playlist selectedPlaylist, Song selectedSong, int numberInPlaylist)  
    {
        try (Connection con = dbConnector.getConnection())
        {

                

                String sql = "UPDATE PlaylistSong SET numberInPlaylist = ? WHERE PlaylistID = ? AND SongID = ?;";

                PreparedStatement statement = con.prepareStatement(sql);
                
                statement.setInt(1, numberInPlaylist);
                statement.setInt(2, selectedPlaylist.getId());
                statement.setInt(3, selectedSong.getId());
                
            
            
                statement.executeUpdate();    
            
        }   
        catch (SQLException ex) 
        {
            Logger.getLogger(SongDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
