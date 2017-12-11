/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.gui.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mp3.be.Song;
import mp3.bll.MP3Exception;
import mp3.gui.model.MP3model;

/**
 * FXML Controller class
 *
 * @author frederik
 */
public class EditSongController implements Initializable {
    
    
    
    private int id;
    private String title;
    private String artist;
    private String category;
    

    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtArtist;
    @FXML
    private ComboBox<String> comboBox;
    
    private MP3model mp3model;
    @FXML
    private Button saveChangesBtn;

    public EditSongController() {
//        this.mp3model = mp3model;
    }
    


    
    
    /**
     * Initializes the controller class.
     * @param selectedSong
     */    
    public void infoTransfer (Song selectedSong)
    {
        id = selectedSong.getId();
        title = selectedSong.getTitle();
        artist = selectedSong.getArtist();
        category = selectedSong.getCategory();
        
        txtTitle.setText(title);
        txtArtist.setText(artist);
        comboBox.getItems().removeAll(comboBox.getItems());
        comboBox.getItems().addAll("POP", "ROCK", "JAZZ", "KLASSISK");
        comboBox.getSelectionModel().select(category);
                
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
    }    

    @FXML
    private void eventSaveChangesbtn(ActionEvent event) throws SQLException, MP3Exception 
    {
        String updatedTitle = txtTitle.getText();
        String updatedArtist = txtArtist.getText();
        String updatedCategory = (String) comboBox.getSelectionModel().getSelectedItem();
        
        mp3model.updateSong(id, updatedTitle, updatedArtist, updatedCategory);
  
        
        Stage stage = (Stage) saveChangesBtn.getScene().getWindow();
        stage.close();
        
    }
    public void setModel(MP3model mp3model) 
    {
        this.mp3model = mp3model;
    }
    
    

    
}
