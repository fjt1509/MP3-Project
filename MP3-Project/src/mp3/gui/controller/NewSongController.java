/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.gui.controller;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import mp3.gui.model.MP3model;

/**
 * FXML Controller class
 *
 * @author Daniels PC
 */
public class NewSongController implements Initializable
{
    private String fileName;

    @FXML
    private TextField txtFieldFilePath;
    @FXML
    private TextField txtFieldTitle;
    @FXML
    private TextField txtFieldArtist;
    private SplitMenuButton comboCategory;
    @FXML
    private Button saveSongbtn;
    @FXML
    private ComboBox comboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        comboBox.getItems().removeAll(comboBox.getItems());
        comboBox.getItems().addAll("POP", "ROCK", "JAZZ", "KLASSISK");
        comboBox.getSelectionModel().select("POP");
    }    
    
  
    
    @FXML
    private void eventChooseFileBtn(ActionEvent event) 
    {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new ExtensionFilter("AUDIO FILES", "*.mp3", "*.wav"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile !=null)
        {   
            txtFieldFilePath.setText(selectedFile.getAbsolutePath());
            System.out.println(selectedFile.getAbsolutePath());
            fileName = selectedFile.getName();
        }
    

        
        
    }

    @FXML
    private void eventSaveSongBtn(ActionEvent event) throws SQLException, SQLServerException, IOException 
    {
        MP3model model = new MP3model();
        
        String title = txtFieldTitle.getText();
        String artist = txtFieldArtist.getText();
        String category = (String) comboBox.getSelectionModel().getSelectedItem();

        
        model.createSong(title, artist, category, fileName);
        
        Stage stage = (Stage) saveSongbtn.getScene().getWindow();
        stage.close();
        
        
    }
    @FXML private javafx.scene.control.Button CancelBtn2;
    @FXML
    private void HandleCancelSongBtn(ActionEvent event)
    {
        Stage stage = (Stage) CancelBtn2.getScene().getWindow();
        stage.close();
    }
    
}
