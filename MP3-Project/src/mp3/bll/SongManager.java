/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.bll;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.SQLException;
import mp3.be.Song;
import mp3.dal.SongDAL;

/**
 *
 * @author frederik
 */
public class SongManager {
    
        public void createSong(String title, String artist, String category, String fileName) throws SQLServerException, SQLException, IOException{
            SongDAL songdal = new SongDAL();
            songdal.createSong(title, artist, category, fileName);
        }
                
                

    
    
}
