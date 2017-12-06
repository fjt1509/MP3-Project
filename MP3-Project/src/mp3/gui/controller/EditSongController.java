/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import mp3.be.Song;

/**
 * FXML Controller class
 *
 * @author frederik
 */
public class EditSongController implements Initializable {
    
    private String title;
    private String artist;

    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtArtist;


    
    
    /**
     * Initializes the controller class.
     */    
    public void infoTransfer (Song selectedSong)
    {
        title = selectedSong.getTitle();
        artist = selectedSong.getArtist();
        
        System.out.println(title + artist);
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        txtTitle.setText(title);
        txtArtist.setText(artist);
        
    }    
    

    
}
