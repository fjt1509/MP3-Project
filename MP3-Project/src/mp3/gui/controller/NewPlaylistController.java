/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Daniels PC
 */
public class NewPlaylistController implements Initializable
{

    @FXML
    private TextField playlistName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    @FXML private javafx.scene.control.Button CancelBtn;
    @FXML
    private void eventCancelPlaylistBtn(ActionEvent event)
    {
        Stage stage = (Stage) CancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void eventSavePlaylistBtn(ActionEvent event)
    {
        
    }
    
}
