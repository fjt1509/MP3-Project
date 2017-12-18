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
         * Retrives the createPlaylist method from PlaylistDAL
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
         * Retrives the getAllPlaylist method from PlaylistDAL
         * @return
         * @throws MP3Exception 
         */
        public List<Playlist> getAllPlaylists() throws SQLException 
        {

          return playlistDAL.getAllPlaylists();
        }
 
    

    /**
     * Retrives the addSongToPlaylist method from PlaylistDAL
     * @param selectedSong
     * @param selectedPlaylist
     * @param songs
     * @throws SQLException 
     */
    public void addSongToPlaylist(Song selectedSong, Playlist selectedPlaylist, List<Song> songs) throws SQLException 
    {
        playlistDAL.addSongToPlaylist(selectedSong, selectedPlaylist, songs);
    }

    
    /**
     * Retrives the getSongsforPlaylist method from PlaylistDAL
     * @param selectedPlaylist
     * @return
     * @throws SQLException
     * @throws IOException 
     */
    public List<Song> getSongsforPlaylist(Playlist selectedPlaylist) throws SQLException, IOException 
    {
        return playlistDAL.getSongsforPlaylist(selectedPlaylist);
    }

    
    /**
     * Retrives the deletePlaylist method from PlaylistDAL
     * @param selectedPlaylist 
     */
    public void deletePlaylist(Playlist selectedPlaylist) 
    {
        playlistDAL.deletePlaylist(selectedPlaylist);
    }

    
    /**
     * Retrives the updatePlaylist method from PlaylistDAL
     * @param id
     * @param updatedPlaylistName 
     */
    public void updatePlaylist(int id, String updatedPlaylistName) 
    {
        playlistDAL.updatePlaylist(id, updatedPlaylistName);
    }

    
    /**
     * Retrives the removeSongFromPlaylist method from PlaylistDAL
     * @param selectedPlaylist
     * @param selectedSong 
     */
    public void removeSongFromPlaylist(Playlist selectedPlaylist, Song selectedSong)
    {

        playlistDAL.removeSongFromPlaylist(selectedPlaylist, selectedSong);
    }
    
    
}
