/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.gui.model;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.SQLException;
import mp3.be.Song;
import mp3.bll.SongManager;

/**
 *
 * @author frederik
 */
public class MP3model {
    
        public void createSong(String title, String artist, String category) throws SQLServerException, SQLException, IOException
        {
            SongManager songmanager = new SongManager();
            songmanager.createSong(title, artist, category);
        }

    
    
}
