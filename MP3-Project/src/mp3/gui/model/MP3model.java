/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.gui.model;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mp3.be.Song;
import mp3.bll.SongManager;
import mp3.dal.SongDAL;

/**
 *
 * @author frederik
 */
public class MP3model {
    
    
    private final ObservableList<Song> SongsInView;
    private SongDAL songDAL = new SongDAL();
    private SongManager songmanager = new SongManager();

    
    public MP3model() throws IOException, SQLException
    {
       this.SongsInView = FXCollections.observableArrayList(); 
       
       SongsInView.addAll(songDAL.getAllSongs());
        
    }
    
    
    public ObservableList<Song> getAllSongs()
    {
        return SongsInView;
    }
            
  
    
        public void createSong(String title, String artist, String category, String fileName) throws SQLServerException, SQLException, IOException
        {
            SongManager songmanager = new SongManager();
            songmanager.createSong(title, artist, category, fileName);
        }
        
        

        
        

    
    
}
