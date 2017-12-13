/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.gui.model;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static javafx.scene.input.DataFormat.URL;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import mp3.be.Playlist;
import mp3.be.Song;
import mp3.bll.MP3Exception;
import mp3.bll.PlaylistManager;
import mp3.bll.SongManager;
import mp3.dal.PlaylistDAL;
import mp3.dal.SongDAL;
import org.tritonus.share.sampled.file.TAudioFileFormat;

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
    public void createSong(String title, String artist, String category, String time, String fileName) throws SQLServerException, SQLException, IOException, MP3Exception
    {
        songmanager.createSong(title, artist, category, time, fileName);
        SongsInView.clear();
        SongsInView.addAll(songmanager.getAllSongs());

    }

    
    /**
     * This methods let us create a playlist
     * @param playlistName
     * @throws SQLException
     * @throws SQLServerException
     * @throws IOException 
     */
    public void createPlaylist(String playlistName) throws SQLException, SQLServerException, IOException, MP3Exception
    {
        playlistmanager.createPlaylist(playlistName);
        PlaylistsInView.clear();
        PlaylistsInView.addAll(playlistmanager.getAllPlaylists());
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

    public void addSongToPlaylist(Song selectedSong, Playlist selectedPlaylist) throws SQLException, IOException 
    {
        playlistmanager.addSongToPlaylist(selectedSong, selectedPlaylist);
        playlistSongView.clear();
        getSongsforPlaylist(selectedPlaylist);
    }

    public void getSongsforPlaylist(Playlist selectedPlaylist) throws SQLException, IOException 
    {
       playlistSongView.addAll(playlistmanager.getSongsforPlaylist(selectedPlaylist));      
    }

    public void updateSong(int id, String updatedTitle, String updatedArtist, String updatedCategory) throws SQLException, MP3Exception 
    {
        songmanager.updateSong(id, updatedTitle, updatedArtist, updatedCategory);
        SongsInView.clear();
        SongsInView.addAll(songmanager.getAllSongs());
        playlistSongView.clear();
        
    }

    public void deletePlaylist(Playlist selectedPlaylist) throws SQLException, IOException 
    {
        playlistmanager.deletePlaylist(selectedPlaylist);
        PlaylistsInView.remove(selectedPlaylist);
        playlistSongView.clear();
        getSongsforPlaylist(selectedPlaylist);
    }

    public void updatePlaylist(int id, String updatedPlaylistName) throws MP3Exception 
    {
        playlistmanager.updatePlaylist(id, updatedPlaylistName);
        PlaylistsInView.clear();
        PlaylistsInView.addAll(playlistmanager.getAllPlaylists());
    }

    public void removeSongFromPlaylist(Playlist selectedPlaylist, Song selectedSong) throws SQLException, IOException 
    {
                System.out.println(selectedSong);

        playlistmanager.removeSongFromPlaylist(selectedPlaylist, selectedSong);
        playlistSongView.clear();
        getSongsforPlaylist(selectedPlaylist);
    }

    public void setSongsOrder(Playlist selectedPlaylist) 
    {
        songmanager.setSongsOrder(selectedPlaylist, playlistSongView);
    }

    public void moveSongUp(Song selectedSong, Playlist selectedPlaylist) 
    {
        int index = playlistSongView.indexOf(selectedSong);       
        int nextObject = index-1;
        
        Collections.swap(playlistSongView, index, nextObject);
        
        setSongsOrder(selectedPlaylist);     
    }

    public void moveSongDown(Song selectedSong, Playlist selectedPlaylist) 
    {
        int index = playlistSongView.indexOf(selectedSong);       
        int nextObject = index+1;
        
        Collections.swap(playlistSongView, index, nextObject);
        
        setSongsOrder(selectedPlaylist);         
    }
    
    public String getDurationOfMp3(File selectedFile) throws UnsupportedAudioFileException, IOException
    {
            String duration;
            AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(selectedFile);
            if(fileFormat instanceof TAudioFileFormat) 
            {
                Map<?, ?> properties = ((TAudioFileFormat) fileFormat).properties();
                String key = "duration";
                Long microseconds = (Long) properties.get(key);
                int mili = (int) (microseconds / 1000);
                int sec = (mili / 1000) % 60;
                int min = (mili /1000) / 60;
                duration = (min + ":" + sec);

                        
                        
            } else 
            {
                duration = null;                
            }
            return duration;
            
    }
    
    public String getDurationOfWav(File selectedFile) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(selectedFile);
        AudioFormat format = audioInputStream.getFormat();
        long frames = audioInputStream.getFrameLength();
        long durationInSeconds = (long) ((frames+0.0) / format.getFrameRate());   
       
        String time = songmanager.getFormattedTimeString(durationInSeconds);
        
        return time;
        
        
    }




    
    
    

}
