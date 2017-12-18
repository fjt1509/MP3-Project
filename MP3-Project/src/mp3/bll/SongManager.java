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
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import mp3.be.Playlist;
import mp3.be.Song;
import mp3.dal.SongDAL;

/**
 *
 * @author frederik
 */
public class SongManager
{

    private SongDAL songDAL;

    public SongManager() throws IOException
    {
        songDAL = new SongDAL();
    }

    
    /**
     * Retrives createSong from SongDAL
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
        songDAL.createSong(title, artist, category, time, fileName);
    }

    
    
    /**
     * retrives getAllSongs from SongDAL
     * @return
     * @throws MP3Exception 
     */
    public List<Song> getAllSongs() throws SQLException 
    {
            return songDAL.getAllSongs();
         
    }


    /**
     * Retrives remove from SongDAL
     * @param song 
     */
    public void remove(Song song)
    {
        songDAL.remove(song);
    }
    
    
    /**
     * Retrives updateSong from SongDAL
     * @param id
     * @param updatedTitle
     * @param updatedArtist
     * @param updatedCategory
     * @throws SQLException 
     */
    public void updateSong(int id, String updatedTitle, String updatedArtist, String updatedCategory) throws SQLException
    {
        songDAL.updateSong(id, updatedTitle, updatedArtist, updatedCategory);
    }

    
    /**
     * Retrives setSongOrder from SongDAL
     * @param selectedPlaylist
     * @param songs 
     */
    public void setSongsOrder(Playlist selectedPlaylist, List<Song> songs) 
    {
        for (int i=0; i<songs.size(); i++)
        { 
                songDAL.setSongsOrder(selectedPlaylist, songs.get(i), i+1);
          
        }
    }
    
    
    /**
     * This mehtod calculates from seconds to (minutes):(seconds)
     * @param timeInSeconds
     * @return 
     */
    public String getFormattedTimeString(long timeInSeconds) 
    {
        
        
        String timeStr = new String();
        long sec_term = 1;
        long min_term = 60 * sec_term;
        long hour_term = 60 * min_term;
        long result = Math.abs(timeInSeconds);
 
        int hour = (int) (result / hour_term);
        result = result % hour_term;
        int min = (int) (result / min_term);
        result = result % min_term;
        int sec = (int) (result / sec_term);
 
        if (timeInSeconds < 0) 
        {
        timeStr = "-";
        }
        if (hour > 0) 
        {
        timeStr += hour + "";
        }
        if (min > 0) 
        {
        timeStr += min + ":";
        }
        if (sec > 0) 
        {
        timeStr += sec + "";
        }
        return timeStr;
 }
    
    /**
     * This method places a zero infront of the amount of seconds so that the time shows "6:03" instead of "6:3"
     *
     * @param number
     * @return 
     */
    public String placeZeroIfNeeded(int number) 
    {
        return (number >=10)? Integer.toString(number):String.format("0%s",Integer.toString(number));
    }


   

}
