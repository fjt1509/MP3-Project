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
            throw new RuntimeException("Can't create Playlist");
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

    public void addSongToPlaylist(Song selectedSong, Playlist selectedPlaylist) throws SQLException 
    {
        int songID = selectedSong.getId();
        int playlistID = selectedPlaylist.getId();
        
        
        try (Connection con = dbConnector.getConnection())
        {
            String sql = "INSERT INTO PlaylistSong VALUES (?, ?, ?);";

            PreparedStatement statement = con.prepareStatement(sql);
            
            statement.setInt(1, playlistID);
            statement.setInt(2, songID);
            statement.setInt(3, 1);
            
            statement.executeUpdate();
            
        }  
        
    }

    public List<Song> getSongsforPlaylist(Playlist selectedPlaylist) throws SQLException, IOException 
    {
        int playlistID = selectedPlaylist.getId();
        
        try (Connection con = dbConnector.getConnection())
        {
            String sql = "SELECT * FROM PlaylistSong WHERE playlistID = ?";
            
            PreparedStatement statement = con.prepareStatement(sql);
            
            
            statement.setInt(1, playlistID);
            
            ResultSet rs = statement.executeQuery(); 
            SongDAL songDAL = new SongDAL();
            
            List<Song> allSongs = new ArrayList<>();
            
            while(rs.next())
            {
                int songID = rs.getInt("songID");
                Song song = songDAL.getSongById(songID);
                allSongs.add(song);
                
            }
            return allSongs;  
        }
        
    }

    public void deletePlaylist(Playlist selectedPlaylist) 
    {
        try (Connection con = dbConnector.getConnection())
        {
            String sql = "DELETE FROM PlaylistSong WHERE playlistID = ?; DELETE FROM Playlist WHERE id = ?;";

            PreparedStatement statement = con.prepareStatement(sql);
            
            statement.setInt(1, selectedPlaylist.getId());
            statement.setInt(2, selectedPlaylist.getId());
            
            statement.executeUpdate();        
        }
        catch (SQLException ex)
        {
            Logger.getLogger(SongDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updatePlaylist(int id, String updatedPlaylistName) 
    {
        try (Connection con = dbConnector.getConnection())
        {
            String sql = "UPDATE Playlist SET name = ? WHERE id = ?;";

            PreparedStatement statement = con.prepareStatement(sql);
            
            statement.setString(1, updatedPlaylistName);
            statement.setInt(2, id);
            
            statement.executeUpdate();
            
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(SongDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeSongFromPlaylist(Playlist selectedPlaylist, Song selectedSong) 
    {
                        System.out.println(selectedSong);

        try (Connection con = dbConnector.getConnection())
        {
            String sql = "DELETE FROM PlaylistSong WHERE playlistID = ? AND songID = ?;";

            PreparedStatement statement = con.prepareStatement(sql);
            
            statement.setInt(1, selectedPlaylist.getId());
            statement.setInt(2, selectedSong.getId());
            
            statement.executeUpdate();
            
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(SongDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }        
    
    
}


