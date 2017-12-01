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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mp3.gui.model.MP3model;

/**
 * FXML Controller class
 *
 * @author Daniels PC
 */
public class NewPlaylistController implements Initializable
{

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
    
    

    @FXML
    private void eventSavePlaylistBtn (ActionEvent event) throws SQLException, SQLServerException, IOException 
    {
        MP3model model = new MP3model();
        
       String playListName = playlistName.getText();

        model.createPlaylist(playListName);
        
        Stage stage = (Stage) SavePlaylistBtn.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void eventCancelPlaylistBtn(ActionEvent event)
    {
        Stage stage = (Stage) CancelBtn.getScene().getWindow();
        stage.close();
    }
    
}
