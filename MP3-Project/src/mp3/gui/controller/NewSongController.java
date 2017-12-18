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
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import mp3.gui.model.MP3model;
import org.tritonus.share.sampled.file.TAudioFileFormat;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class NewSongController implements Initializable
{
    private String fileName;
    private String time;
    
    private MP3model mp3model;

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
    @FXML
    private TextField txtTime;
    @FXML private javafx.scene.control.Button CancelBtn2;
   
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        comboBox.getItems().removeAll(comboBox.getItems());
        comboBox.getItems().addAll("POP", "ROCK", "JAZZ", "KLASSISK", "TECHNO", "COUNTRY", "OPERA");
        comboBox.getSelectionModel().select("POP");
        
    }    
    
    
    /**
     * The "Choose" button adds a file.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void eventChooseFileBtn(ActionEvent event) throws IOException, UnsupportedAudioFileException, LineUnavailableException 
    {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new ExtensionFilter("AUDIO FILES", "*.mp3", "*.wav"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile !=null)
        {   
            txtFieldFilePath.setText(selectedFile.getAbsolutePath());
            System.out.println(selectedFile.getAbsolutePath());
            fileName = selectedFile.getName();
            URL url = Paths.get(selectedFile.getAbsolutePath()).toUri().toURL();
            Media musicFile = new Media(url.toString());
                     
            boolean compare = selectedFile.getName().toLowerCase().endsWith("mp3");
            
            if (compare)
            {
               time = mp3model.getDurationOfMp3(selectedFile);
            }
            else
            {
               time = mp3model.getDurationOfWav(selectedFile);
            }
            
            txtTime.setText(time);   
        }
    

        
        
    }

    
    /**
     * The "Save" button adds the song to the list.
     * @param event
     * @throws SQLException
     * @throws SQLServerException
     * @throws IOException 
     */
    @FXML
    private void eventSaveSongBtn(ActionEvent event) throws IOException
    {

        
        String title = txtFieldTitle.getText();
        String artist = txtFieldArtist.getText();
        String category = (String) comboBox.getSelectionModel().getSelectedItem();
        String time = txtTime.getText();
        
        try {
            mp3model.createSong(title, artist, category, time, fileName);
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("Ooops, there was an error!");

            alert.showAndWait();        
        }
        
        Stage stage = (Stage) saveSongbtn.getScene().getWindow();
        stage.close();
        
        
    }

    /**
     * The "Cancel" button closes the window.
     * @param event 
     */
    @FXML
    private void HandleCancelSongBtn(ActionEvent event)
    {
        Stage stage = (Stage) CancelBtn2.getScene().getWindow();
        stage.close();
    }

    void setModel(MP3model mp3model) 
    {
        this.mp3model = mp3model;
    }
    
}
