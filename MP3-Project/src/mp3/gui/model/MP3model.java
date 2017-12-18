/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.gui.model;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import mp3.be.Playlist;
import mp3.be.Song;
import mp3.bll.PlaylistManager;
import mp3.bll.SongManager;
import org.tritonus.share.sampled.file.TAudioFileFormat;

/**
 *
 * @author frederik
 */
public class MP3model
{

    private final ObservableList<Song> obsListSongs;
    private final ObservableList<Playlist> obsListPlaylists;
    private final ObservableList<Song> obsListSongsInPlaylist;

    SongManager songmanager = new SongManager();   
    PlaylistManager playlistmanager = new PlaylistManager();

    
    
    /**
     * The constructor of the class, 
     * that helps the controller recieve both songs and playlists 
     * into an observaableArrayList.
     * @throws IOException
     * @throws SQLException 
     */
    public MP3model() throws IOException, SQLException
    {
        this.obsListSongs = FXCollections.observableArrayList();
        obsListSongs.addAll(songmanager.getAllSongs());

        this.obsListPlaylists = FXCollections.observableArrayList();
        obsListPlaylists.addAll(playlistmanager.getAllPlaylists());
        
        this.obsListSongsInPlaylist = FXCollections.observableArrayList();
 
        

    }

    
    /**
     * ObservableList that returns SongsInView
     * @return 
     */
    public ObservableList<Song> getAllSongs()
    {
        return obsListSongs;
    }
    
    
    /**
     * ObservableList that returns PlaylistsInView
     * @return 
     */
    public ObservableList<Playlist> getAllPlaylist()
    {
        return obsListPlaylists;
    }
    
    /**
     * ObservableList that returns SongsInPlaylist
     * @return 
     */
    public ObservableList<Song> getAllSongsInPlaylist()
    {
        return obsListSongsInPlaylist;
    }
    
    /**
     * This methods lets us create a song
     * @param title
     * @param artist
     * @param category
     * @param fileName
     * @throws SQLServerException
     * @throws SQLException
     * @throws IOException 
     */
    public void createSong(String title, String artist, String category, String time, String fileName) throws SQLServerException, SQLException, IOException
    {
        songmanager.createSong(title, artist, category, time, fileName);
        obsListSongs.clear();
        obsListSongs.addAll(songmanager.getAllSongs());

    }

    
    /**
     * This methods let us create a playlist
     * @param playlistName
     * @throws SQLException
     * @throws SQLServerException
     * @throws IOException 
     */
    public void createPlaylist(String playlistName) throws SQLException, IOException
    {
        playlistmanager.createPlaylist(playlistName);
        obsListPlaylists.clear();
        obsListPlaylists.addAll(playlistmanager.getAllPlaylists());
    }

    
    /**
     * This method removes a song from the tableview 
     * and calls the remove method from the songmanager.
     * @param selectedSong 
     */
    public void remove(Song selectedSong)
    {
        songmanager.remove(selectedSong);
        obsListSongs.remove(selectedSong);
    }
    /**
     * this method adds the selectedSong to the selectedPlaylist.
     * 
     * @param selectedSong
     * @param selectedPlaylist
     * @throws SQLException
     * @throws IOException 
     */
    public void addSongToPlaylist(Song selectedSong, Playlist selectedPlaylist) throws SQLException, IOException 
    {
        playlistmanager.addSongToPlaylist(selectedSong, selectedPlaylist, obsListSongsInPlaylist);
        obsListSongsInPlaylist.clear();
        getSongsforPlaylist(selectedPlaylist);
    }
    /**
     * this method adds all songs from the playlistmanager to the SongsInPlaylist listView.
     * 
     * @param selectedPlaylist
     * @throws SQLException
     * @throws IOException 
     */
    public void getSongsforPlaylist(Playlist selectedPlaylist) throws SQLException, IOException 
    {
       obsListSongsInPlaylist.addAll(playlistmanager.getSongsforPlaylist(selectedPlaylist));      
    }
    /**
     * this method updates the songlist
     * @param id
     * @param updatedTitle
     * @param updatedArtist
     * @param updatedCategory
     * @throws SQLException 
     */
    public void updateSong(int id, String updatedTitle, String updatedArtist, String updatedCategory) throws SQLException 
    {
        songmanager.updateSong(id, updatedTitle, updatedArtist, updatedCategory);
        obsListSongs.clear();
        obsListSongs.addAll(songmanager.getAllSongs());
        obsListSongsInPlaylist.clear();
        
    }
    /**
     * this method deletes the selectedPlaylist.
     * 
     * @param selectedPlaylist
     * @throws SQLException
     * @throws IOException 
     */
    public void deletePlaylist(Playlist selectedPlaylist) throws SQLException, IOException 
    {
        playlistmanager.deletePlaylist(selectedPlaylist);
        obsListPlaylists.remove(selectedPlaylist);
        obsListSongsInPlaylist.clear();
        getSongsforPlaylist(selectedPlaylist);
    }
    /**
     * This method updates the playlist
     * @param id
     * @param updatedPlaylistName
     * @throws SQLException 
     */
    public void updatePlaylist(int id, String updatedPlaylistName) throws SQLException  
    {
        playlistmanager.updatePlaylist(id, updatedPlaylistName);
        obsListPlaylists.clear();
        obsListPlaylists.addAll(playlistmanager.getAllPlaylists());
    }
    /**
     * this method removes the selectedSong from the Playlist.
     *  
     * @param selectedPlaylist
     * @param selectedSong
     * @throws SQLException
     * @throws IOException 
     */
    public void removeSongFromPlaylist(Playlist selectedPlaylist, Song selectedSong) throws SQLException, IOException 
    {
        playlistmanager.removeSongFromPlaylist(selectedPlaylist, selectedSong);
        obsListSongsInPlaylist.clear();
        getSongsforPlaylist(selectedPlaylist);
    }
    /**
     * 
     * @param selectedPlaylist 
     */
    public void setSongsOrder(Playlist selectedPlaylist) 
    {
        songmanager.setSongsOrder(selectedPlaylist, obsListSongsInPlaylist);
    }
    /**
     * this method lets us set the selectedSongsOrder up 1 index in the listView.
     * 
     * @param selectedSong
     * @param selectedPlaylist 
     */
    public void moveSongUp(Song selectedSong, Playlist selectedPlaylist) 
    {
        int index = obsListSongsInPlaylist.indexOf(selectedSong);       
        int nextObject = index-1;
        
        Collections.swap(obsListSongsInPlaylist, index, nextObject);
        
        setSongsOrder(selectedPlaylist);     
    }
    /**
     * this method lets us set the selectedSongsOrder down 1 index in the listView.
     * 
     * @param selectedSong
     * @param selectedPlaylist 
     */
    public void moveSongDown(Song selectedSong, Playlist selectedPlaylist) 
    {
        int index = obsListSongsInPlaylist.indexOf(selectedSong);       
        int nextObject = index+1;
        
        Collections.swap(obsListSongsInPlaylist, index, nextObject);
        
        setSongsOrder(selectedPlaylist);         
    }
    /**
     * This method gets the duration of a .mp3 file
     * @param selectedFile
     * @return
     * @throws UnsupportedAudioFileException
     * @throws IOException 
     */
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
                
                    String strSec = songmanager.placeZeroIfNeeded(sec);
                    String seconds = String.format("%s",strSec);
                    duration = (min +  ":" + seconds);

                        
                        
            } else 
            {
                duration = null;                
            }
            return duration;
            
    }
    /**
     * This method gets the duration of a .wav file
     * @param selectedFile
     * @return
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException 
     */
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
