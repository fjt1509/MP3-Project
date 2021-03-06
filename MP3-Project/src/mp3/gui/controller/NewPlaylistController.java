/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.gui.controller;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mp3.gui.model.MP3model;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class NewPlaylistController implements Initializable
{
    private MP3model mp3model;

    @FXML
    private Button SavePlaylistBtn;
    @FXML
    private TextField playlistName;
    @FXML 
    private javafx.scene.control.Button CancelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }    
    
    

    /**
     * This method creates a new playlist when the "Save" button is clicked.
     * @param event
     * @throws SQLException
     * @throws SQLServerException
     * @throws IOException 
     */
    @FXML
    private void eventSavePlaylistBtn (ActionEvent event) throws IOException
    {
        
       String playListName = playlistName.getText();

        try {
            mp3model.createPlaylist(playListName);
        } catch (SQLException ex) 
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("Ooops, there was an error!");

            alert.showAndWait();
        }
        
        Stage stage = (Stage) SavePlaylistBtn.getScene().getWindow();
        stage.close();
    }
    
    
    /**
     * The "Cancel" button closes the alertBox.
     * @param event 
     */
    @FXML
    private void eventCancelPlaylistBtn(ActionEvent event)
    {
        Stage stage = (Stage) CancelBtn.getScene().getWindow();
        stage.close();
    }
       
    
    public void setModel(MP3model mp3model) 
    {
        this.mp3model = mp3model;
    }
    
    
}
