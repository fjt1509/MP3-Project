/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.gui.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import mp3.be.Playlist;
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
    private TableView<Playlist> PlaylistsViewer;
    @FXML
    private TableView<Song> SongsViewer;
    @FXML
    private TextField FilterTxtField;
    @FXML
    private Slider SliderBar;
 
    @FXML
    private TableColumn<Song, String> tableColumnTitle;
    @FXML
    private TableColumn<Song, String> tableColumnArtist;
    @FXML
    private TableColumn<Song, String> tableColumnCategory;
    @FXML
    private TableColumn<Song, String> tableColumnTime;
    @FXML
    private TableColumn<Playlist, String> tableColumnName;



    
    
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
        
        tableColumnName.setCellValueFactory(new PropertyValueFactory("name"));
        
        PlaylistsViewer.setItems(mp3model.getAllPlaylist());
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
        mediaplayer.play();
    }
    
    @FXML
    private void eventPausebtn(ActionEvent event)
    {
        mediaplayer.pause();
    }

    @FXML
    private void eventStopbtn(ActionEvent event) 
    {
        mediaplayer.stop();
    }

    @FXML
    private void eventMouseSelectclk(MouseEvent event) throws MalformedURLException 
    {
                
        Song selectedSong = SongsViewer.getSelectionModel().getSelectedItem();
        String fileName = selectedSong.getFileName();
        System.out.println(fileName);
        
        String path = "./Songs/"+fileName;
       URL url = Paths.get(path).toAbsolutePath().toUri().toURL();
        Media musicFile = new Media(url.toString());
        musicFile.getDuration().toMinutes();
        // double songDuration = musicFile.getDuration().toSeconds();
        mediaplayer = new MediaPlayer(musicFile);
        mediaplayer.setVolume(0.9);  
        
    }
    
    
    @FXML
    private void eventSearchSong(KeyEvent event) 
    {   
        FilteredList filter = new FilteredList(SongsViewer.getItems(),e ->true);
        FilterTxtField.textProperty().addListener((observable, oldValue, newValue) -> {
            
            
            filter.setPredicate((Predicate<? super Song>) (Song song) -> {
                
                
                if(newValue.isEmpty() || newValue==null) {
                    return true;
                }
                else if (song.getTitle().toLowerCase().contains(newValue.toLowerCase())|| 
                        
                     song.getArtist().toLowerCase().contains(newValue.toLowerCase()) ||
                     song.getCategory().toLowerCase().contains(newValue.toLowerCase())
                        
                        
                        ) {
                    
                    return true;
                }
                
                return false;
            });
            SortedList sort= new SortedList(filter);
            sort.comparatorProperty().bind(SongsViewer.comparatorProperty());
            SongsViewer.setItems(sort);
        });
        
    }

    @FXML
    private void eventDeleteSongBtn(ActionEvent event)
    {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            Song selectedSong = SongsViewer.getSelectionModel().getSelectedItem();
            mp3model.remove(selectedSong);
        } else 
        {
            
        }
        

    }
}

