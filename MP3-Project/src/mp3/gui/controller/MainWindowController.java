/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import mp3.be.Song;
import mp3.gui.model.MP3model;

/**
 * FXML Controller class
 *
 * @author danni
 */
public class MainWindowController implements Initializable {
    
    
    private MP3model mp3model;


    @FXML
    private ListView<Song> SongplayListViewer;
    @FXML
    private TableView<?> PlaylistsViewer;
    @FXML
    private TableView<Song> SongsViewer;
    @FXML
    private TextField FilterTxtField;
    @FXML
    private Slider SliderBar;
    @FXML
    private TextField SongPlayerTxtField;
 


     @FXML
    private TableView<Song> SongsplayListView;
    
    
    public MainWindowController() throws  IOException, SQLException
    {
     
      mp3model = new MP3model();
     
    }
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       SongplayListViewer.setItems(mp3model.getAllSongs());

        
    }    

    @FXML
    private void eventEditSongBtn(ActionEvent event) throws IOException {
 
             
        
        
    }

    @FXML
    private void eventNewSongBtn(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mp3/gui/view/NewSong.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1)); 
        stage.show();  
        
    }

    @FXML
    private void eventNewPlaylistBtn(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mp3/gui/view/NewPlaylist.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1)); 
        stage.show();  
    }

    
}
