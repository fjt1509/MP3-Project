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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
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
    private MediaPlayer mediaplayer;
    private Song song;
    private boolean isPlaying;
    


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
    private TableColumn<Song, String> tableColumnTitle;
    @FXML
    private TableColumn<Song, String> tableColumnArtist;
    @FXML
    private TableColumn<Song, String> tableColumnCategory;
    @FXML
    private TableColumn<Song, String> tableColumnTime;



     @FXML
    private Button NewPlaylistBtn;
    @FXML
    private Button EditPlaylistBtn;
    @FXML
    private Button DeletePlaylistBtn;
    @FXML
    private Button MoveDownBtn;
    @FXML
    private Button DeleteSongPLBtn;
    @FXML
    private Button CloseBtn;
    @FXML
    private Button NewSongBtn;
    @FXML
    private Button EditSongBtn;
    @FXML
    private Button DeleteSongBtn;
    @FXML
    private Button MoveUpBtn;
    @FXML
    private Button AddSongToPLBtn;
    @FXML
    private Button SearchBtn;
    
    
    public MainWindowController() throws  IOException, SQLException
    {
     
      mp3model = new MP3model();
     
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tableColumnTitle.setCellValueFactory(new PropertyValueFactory("title"));
        tableColumnArtist.setCellValueFactory(new PropertyValueFactory("artist"));
        tableColumnCategory.setCellValueFactory(new PropertyValueFactory("category"));
        
        SongsViewer.setItems(mp3model.getAllSongs());
        isPlaying = false;
        
      
        

        
    }    

    @FXML
    private void eventEditSongBtn(ActionEvent event) throws IOException 
    {   
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

    @FXML
    private void eventPlayPausebtn(ActionEvent event) 
    {
        
        Song selectedSong = SongsViewer.getSelectionModel().getSelectedItem();
        String fileName = selectedSong.getFileName();
        
        String path = "file:///C:/Users/frederik/Desktop/Songs/"+fileName;
        Media musicFile = new Media(path);

        mediaplayer = new MediaPlayer(musicFile);
        mediaplayer.setVolume(0.9);  
                
        
        if (!isPlaying)
        {
            mediaplayer.play();
            isPlaying = true;
        }
        else if (isPlaying)
        {
            mediaplayer.pause();
            isPlaying = false;
        }
    }
    
    @FXML
    private void eventPlayBtn(ActionEvent event)
    {
    }

    @FXML
    private void eventNextBtn(ActionEvent event)
    {
    }

    @FXML
    private void eventPreviousBtn(ActionEvent event)
    {
    }

    
}
