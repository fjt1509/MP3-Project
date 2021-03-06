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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mp3.be.Playlist;
import mp3.gui.model.MP3model;

/**
 * FXML Controller class
 *
 * @author frederik
 */
public class EditPlaylistController implements Initializable {

    private int id; 
    private String playlistName;
    
    private MP3model mp3model;
    @FXML
    private TextField txtPlaylistName;
    @FXML
    private Button btnSaveChanges;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    /**
     * This Method allows us to save changes to the name of the Playlist we want to edit.
     * @param event
     * @throws SQLException 
     */
    @FXML
    private void eventSaveChanges(ActionEvent event) throws SQLException 
    {
        
        String updatedPlaylistName = txtPlaylistName.getText();
        
        mp3model.updatePlaylist(id, updatedPlaylistName);
        
        Stage stage = (Stage) btnSaveChanges.getScene().getWindow();
        stage.close();
    }
    
    public void setModel(MP3model mp3model) 
    {
        this.mp3model = mp3model;
    }
    /**
     * This method gets information from the selectedPlaylist.
     * and sets the textField to the selectedPlaylist name.
     * @param selectedPlaylist 
     */
    void infoTransfer(Playlist selectedPlaylist) 
    {
        id = selectedPlaylist.getId();
        playlistName = selectedPlaylist.getName();
        
        txtPlaylistName.setText(playlistName);
    }
    
}
