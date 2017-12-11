/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.gui.model;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mp3.be.Playlist;
import mp3.be.Song;
import mp3.bll.MP3Exception;
import mp3.bll.PlaylistManager;
import mp3.bll.SongManager;
import mp3.dal.PlaylistDAL;
import mp3.dal.SongDAL;

/**
 *
 * @author frederik
 */
public class MP3model
{

    private final ObservableList<Song> SongsInView;
    private final ObservableList<Playlist> PlaylistsInView;
    private final ObservableList<Song> playlistSongView;

    SongManager songmanager = new SongManager();
    
    PlaylistManager playlistmanager = new PlaylistManager();

    
    
    /**
     * The constructor of the class, 
     * that helps the controller recieve both songs and playlists 
     * into an observaableArrayList.
     * @throws IOException
     * @throws SQLException 
     */
    public MP3model() throws IOException, SQLException, MP3Exception
    {
        this.SongsInView = FXCollections.observableArrayList();
        SongsInView.addAll(songmanager.getAllSongs());

        this.PlaylistsInView = FXCollections.observableArrayList();
        PlaylistsInView.addAll(playlistmanager.getAllPlaylists());
        
        this.playlistSongView = FXCollections.observableArrayList();
 
        

    }

    
    /**
     * ObservableList that returns SongsInView
     * @return 
     */
    public ObservableList<Song> getAllSongs()
    {
        return SongsInView;
    }
    
    
    /**
     * ObservableList that returns PlaylistsInView
     * @return 
     */
    public ObservableList<Playlist> getAllPlaylist()
    {
        return PlaylistsInView;
    }
    
    
    public ObservableList<Song> getAllSongsInPlaylist()
    {
        return playlistSongView;
    }
    
    /**
     * This methods let us create a song
     * @param title
     * @param artist
     * @param category
     * @param fileName
     * @throws SQLServerException
     * @throws SQLException
     * @throws IOException 
     */
    public void createSong(String title, String artist, String category, String fileName) throws SQLServerException, SQLException, IOException
    {
        songmanager.createSong(title, artist, category, fileName);
    }

    
    /**
     * This methods let us create a playlist
     * @param playlistName
     * @throws SQLException
     * @throws SQLServerException
     * @throws IOException 
     */
    public void createPlaylist(String playlistName) throws SQLException, SQLServerException, IOException
    {
        PlaylistManager playlistmanager = new PlaylistManager();
        playlistmanager.createPlaylist(playlistName);
    }

    
    /**
     * This method removes a song from the tableview 
     * and calls the remove method from the songmanager.
     * @param selectedSong 
     */
    public void remove(Song selectedSong)
    {
        songmanager.remove(selectedSong);
        SongsInView.remove(selectedSong);
    }

    public void addSongToPlaylist(Song selectedSong, Playlist selectedPlaylist) throws SQLException 
    {
        playlistmanager.addSongToPlaylist(selectedSong, selectedPlaylist);
        
    }

    public void getSongsforPlaylist(Playlist selectedPlaylist) throws SQLException, IOException 
    {
       playlistSongView.addAll(playlistmanager.getSongsforPlaylist(selectedPlaylist));
        
    }

    public void updateSong(int id, String updatedTitle, String updatedArtist, String updatedCategory) throws SQLException 
    {
        songmanager.updateSong(id, updatedTitle, updatedArtist, updatedCategory);
    }


    
    
    

}
