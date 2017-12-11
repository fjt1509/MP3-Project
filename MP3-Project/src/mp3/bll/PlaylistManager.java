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

    public PlaylistManager() throws IOException 
    {
        playlistDAL = new PlaylistDAL();
    }
    
    
    
    
        /**
         * Takes createPlaylist from the DAL
         * @param playlistName
         * @throws SQLServerException
         * @throws SQLException
         * @throws IOException 
         */
        public void createPlaylist(String playlistName) throws SQLServerException, SQLException, IOException
        {    
            PlaylistDAL playlistdal = new PlaylistDAL();
            playlistdal.createPlaylist(playlistName);
        }
    
        
        /**
         * Takes getAllPlaylist from the DAL
         * @return
         * @throws MP3Exception 
         */
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

    public void addSongToPlaylist(Song selectedSong, Playlist selectedPlaylist) throws SQLException 
    {
        playlistDAL.addSongToPlaylist(selectedSong, selectedPlaylist);
    }

    public List<Song> getSongsforPlaylist(Playlist selectedPlaylist) throws SQLException, IOException 
    {
        return playlistDAL.getSongsforPlaylist(selectedPlaylist);
    }

    public void deletePlaylist(Playlist selectedPlaylist) 
    {
        playlistDAL.deletePlaylist(selectedPlaylist);
    }

    public void updatePlaylist(int id, String updatedPlaylistName) 
    {
        playlistDAL.updatePlaylist(id, updatedPlaylistName);
    }

    public void removeSongFromPlaylist(Playlist selectedPlaylist, Song selectedSong) {
        playlistDAL.removeSongFromPlaylist(selectedPlaylist, selectedSong);
    }
    
    
}
