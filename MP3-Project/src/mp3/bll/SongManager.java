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

    public void createSong(String title, String artist, String category, String fileName) throws SQLServerException, SQLException, IOException
    {
        songDAL.createSong(title, artist, category, fileName);
    }

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

    public void remove(Song song)
    {
        songDAL.remove(song);
    }

}
