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
     * Takes createSong from the DAL
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
        songDAL.createSong(title, artist, category, fileName);
    }

    
    
    /**
     * Takes getAllSongs from the DAL
     * @return
     * @throws MP3Exception 
     */
    public List<Song> getAllSongs() throws MP3Exception
    {
        try
        {
            return songDAL.getAllSongs();
        } catch (SQLException ex)
        {
            throw new MP3Exception(ex);
        }
    }


    /**
     * Takes remove from the DAL
     * @param song 
     */
    public void remove(Song song)
    {
        songDAL.remove(song);
    }

    public void updateSong(int id, String updatedTitle, String updatedArtist, String updatedCategory) throws SQLException
    {
        songDAL.updateSong(id, updatedTitle, updatedArtist, updatedCategory);
    }

    public void setSongsOrder(Playlist selectedPlaylist, List<Song> songs) 
    {
        for (int i=0; i<songs.size(); i++)
        { 
            songDAL.setSongsOrder(selectedPlaylist, songs.get(i), i+1);
        }
    }

   

}
