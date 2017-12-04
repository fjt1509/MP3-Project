/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.bll;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import mp3.be.Playlist;
import mp3.be.Song;
import mp3.dal.PlaylistDAL;
import mp3.dal.SongDAL;

/**
 *
 * @author Daniels PC
 */
public class PlaylistManager
{
    private PlaylistDAL playlistDAL;
    
        public void createPlaylist(String playlistName) throws SQLServerException, SQLException, IOException
        {    
            PlaylistDAL playlistdal = new PlaylistDAL();
            playlistdal.createPlaylist(playlistName);
        }
        
    public List<Playlist> getAllPlaylists() throws MP3Exception
    {
        try
        {
          return playlistDAL.getAllPlaylists();
        } 
        catch (SQLException ex)
        {
            throw new MP3Exception(ex);
        }
    }
}