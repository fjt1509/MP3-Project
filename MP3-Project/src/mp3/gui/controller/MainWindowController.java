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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
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
import javafx.scene.control.Label;
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
import javafx.util.Duration;
import mp3.be.Playlist;
import mp3.be.Song;
import mp3.bll.MP3Exception;
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
    private String fileName;


    @FXML
    private TableView<Playlist> PlaylistsViewer;
    @FXML
    private TableView<Song> SongsViewer;
    @FXML
    private TableView<Song> viewPlaylistSongs;    
    
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

    @FXML
    private TableColumn<Song, String> playlistSongColumnNr;
    @FXML
    private TableColumn<Song, String> playlistSongColumnSong;
    
    @FXML
    private Label lblTrackArtist;
    @FXML
    private Label lblTrackTime;
    



    
    /**
     * The constructor for the class
     * @throws IOException
     * @throws SQLException 
     */
    public MainWindowController() throws  IOException, SQLException, MP3Exception
    {
     
      mp3model = new MP3model();
      
      
    }
    
    /**
     * method specifies a cell factory for each column. The cell factories are implemented by using the
     * PropertyValueFactory class, which uses the Title, Artist and Category  properties of the table columns as 
     * references to the corresponding methods of the Controller class
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        SongsViewer.setItems(mp3model.getAllSongs());       
        tableColumnTitle.setCellValueFactory (new PropertyValueFactory( "title"));
        tableColumnArtist.setCellValueFactory(new PropertyValueFactory("artist"));
        tableColumnCategory.setCellValueFactory(new PropertyValueFactory("category"));
        tableColumnTime.setCellValueFactory(new PropertyValueFactory("time"));
        

        PlaylistsViewer.setItems(mp3model.getAllPlaylist());        
        tableColumnName.setCellValueFactory(new PropertyValueFactory("name"));
        
        
        viewPlaylistSongs.setItems(mp3model.getAllSongsInPlaylist());
        playlistSongColumnSong.setCellValueFactory(new PropertyValueFactory("title"));
      
        
        
        
       
        
        SliderBar.setValue(100);
        
        viewPlaylistSongs.setPlaceholder(new Label("Choose a playlist"));
        
        


    }    
    

    @FXML
    private void eventEditSongBtn(ActionEvent event) throws IOException 
    {
        Song selectedSong = SongsViewer.getSelectionModel().getSelectedItem();        
        

        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mp3/gui/view/EditSong.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        EditSongController esc = fxmlLoader.getController();
        esc.setModel(mp3model);
        esc.infoTransfer(selectedSong);
        Stage stage = new Stage();
        stage.setScene(new Scene(root)); 
        stage.show(); 
        
    }

    @FXML
    private void eventNewSongBtn(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mp3/gui/view/NewSong.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        NewSongController nsc = fxmlLoader.getController();
        nsc.setModel(mp3model);
        Stage stage = new Stage();
        stage.setScene(new Scene(root1)); 
        stage.show();  
        
    }

    @FXML
    private void eventNewPlaylistBtn(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mp3/gui/view/NewPlaylist.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        NewPlaylistController npc = fxmlLoader.getController();
        npc.setModel(mp3model);
        Stage stage = new Stage();
        stage.setScene(new Scene(root1)); 
        stage.show();  
    }

    @FXML
    private void eventPlayPausebtn(ActionEvent event) throws MalformedURLException 
    {
        if(isPlaying)
        {
            mediaplayer.dispose();
            isPlaying = false;
        }
        String path = "./Songs/"+fileName;
        URL url = Paths.get(path).toAbsolutePath().toUri().toURL();
        Media musicFile = new Media(url.toString());
        musicFile.getDuration().toMinutes();
        mediaplayer = new MediaPlayer(musicFile);
        mediaplayer.play();
        mediaplayer.setRate(50.0);
        
        
        isPlaying = true;
    
        lblTrackArtist.setText(song.getTitle() + " By: " +  song.getArtist());
        
        SliderBar.setValue(mediaplayer.getVolume() * 100);
        SliderBar.valueProperty().addListener(new InvalidationListener() 
        {          
            @Override
            public void invalidated(Observable observable) 
            {
                mediaplayer.setVolume(SliderBar.getValue() / 100);
            }
        });
        
        mediaplayer.setOnEndOfMedia(new Runnable()
        {
            @Override public void run()
            {
               ActionEvent i = null;
                try 
                {
                    lblTrackArtist.setText("");
                    eventNextSongbtn(i);
                } 
                catch (MalformedURLException ex) 
                {
                    Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
    }
    
    @FXML
    private void eventPausebtn(ActionEvent event)
    {
        mediaplayer.pause();
    }

    @FXML
    private void eventMouseSelectInPlaylistclk(MouseEvent event) throws MalformedURLException 
    {
        SongsViewer.getSelectionModel().clearSelection();
        if(!viewPlaylistSongs.getSelectionModel().isEmpty())
        {    
            Song selectedSong = viewPlaylistSongs.getSelectionModel().getSelectedItem();
            song = selectedSong;
            fileName = selectedSong.getFileName();
            System.out.println(fileName);
            
                
        }
            
        
        
    }

    @FXML
    private void eventMouseSelectInSongsclk(MouseEvent event) 
    {
        viewPlaylistSongs.getSelectionModel().clearSelection();
        if(!SongsViewer.getSelectionModel().isEmpty())
        {
            Song selectedSong = SongsViewer.getSelectionModel().getSelectedItem();
            song = selectedSong;
            fileName = selectedSong.getFileName();
            System.out.println(fileName);  
        }
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
                else if (song.getTitle().toLowerCase().contains(newValue.toLowerCase())  ||   
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
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Delete confirmation");
        alert.setContentText("Are you sure you want to delete this song? the song will also be removed from all playlists");
        

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            Song selectedSong = SongsViewer.getSelectionModel().getSelectedItem();
            mp3model.remove(selectedSong);
        } else 
        {
            
        }
        

    }

    @FXML
    private void eventAddSongbtn(ActionEvent event) throws IOException 
    {
        Song selectedSong = SongsViewer.getSelectionModel().getSelectedItem();
        Playlist selectedPlaylist = PlaylistsViewer.getSelectionModel().getSelectedItem();
        boolean isInPlaylist = false;
        
        if (selectedSong!=null && selectedPlaylist!=null)
        {
           
            for(Song song : mp3model.getAllSongsInPlaylist())
            {
                if(selectedSong.getId() == song.getId())
                {
                    isInPlaylist = true;                    
                }
            }
            
            if(!isInPlaylist)
            {
            
                try 
                {
                    mp3model.addSongToPlaylist(selectedSong, selectedPlaylist);
                } 
                catch (SQLException ex) 
                {
                    ex.printStackTrace();
                }
            } 
            else
            {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information box");
                alert.setHeaderText("Playist error");
                alert.setContentText("You already have this song in the playlist");

                alert.showAndWait();
            }
       
        }
    }
    

    @FXML
    private void eventChoosePlaylistclk(MouseEvent event) throws IOException, SQLException 
    {
        if(!PlaylistsViewer.getSelectionModel().isEmpty())
        {
            viewPlaylistSongs.getItems().clear();
            Playlist selectedPlaylist = PlaylistsViewer.getSelectionModel().getSelectedItem();
            mp3model.getSongsforPlaylist(selectedPlaylist);
            
            
        }
    }

    @FXML
    private void eventDeletePlaylist(ActionEvent event) throws SQLException, IOException 
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Delete confirmation");
        alert.setContentText("Are you sure you want to delete this Playlist?");
        

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            Playlist selectedPlaylist = PlaylistsViewer.getSelectionModel().getSelectedItem();
            mp3model.deletePlaylist(selectedPlaylist);
        } else 
        {
            
        }
    }

    @FXML
    private void eventEditPlaylist(ActionEvent event) throws IOException 
    {
        Playlist selectedPlaylist = PlaylistsViewer.getSelectionModel().getSelectedItem(); 
        
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mp3/gui/view/EditPlaylist.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        EditPlaylistController epc = fxmlLoader.getController();
        epc.setModel(mp3model);
        epc.infoTransfer(selectedPlaylist);
        Stage stage = new Stage();
        stage.setScene(new Scene(root1)); 
        stage.show();
    }

    @FXML
    private void eventRemoveSongFromPlaylist(ActionEvent event) throws SQLException, IOException 
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Delete confirmation");
        alert.setContentText("Are you sure you wish to remove this song from the playlist?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            Playlist selectedPlaylist = PlaylistsViewer.getSelectionModel().getSelectedItem();
            Song selectedSong = viewPlaylistSongs.getSelectionModel().getSelectedItem();
            
        mp3model.removeSongFromPlaylist(selectedPlaylist, selectedSong);
        } else 
        {
            
        }
    }

    @FXML
    private void eventPushDownbtn(ActionEvent event) 
    {
       Playlist selectedPlaylist = PlaylistsViewer.getSelectionModel().getSelectedItem();
       Song selectedSong = viewPlaylistSongs.getSelectionModel().getSelectedItem();
       mp3model.moveSongDown(selectedSong, selectedPlaylist);
    }

    @FXML
    private void eventPushUpbtn(ActionEvent event) 
    {
       Playlist selectedPlaylist = PlaylistsViewer.getSelectionModel().getSelectedItem();
       Song selectedSong = viewPlaylistSongs.getSelectionModel().getSelectedItem();
       mp3model.moveSongUp(selectedSong, selectedPlaylist);
        
    }

    @FXML
    private void eventNextSongbtn(ActionEvent event) throws MalformedURLException 
    {
        if(!viewPlaylistSongs.getSelectionModel().isEmpty())
        {
            Song selectedSong = viewPlaylistSongs.getSelectionModel().getSelectedItem();
            int currentSong = viewPlaylistSongs.getItems().indexOf(selectedSong);
            int nextSong = currentSong+1;
        
            Song nextSelectedSong = viewPlaylistSongs.getItems().get(nextSong);
            song = nextSelectedSong;
            fileName = nextSelectedSong.getFileName();
            viewPlaylistSongs.getSelectionModel().clearAndSelect(nextSong);
   
            ActionEvent g = null;        
            eventPlayPausebtn(g);   
        
        
       /* if(isPlaying)
        {
            mediaplayer.dispose();
            isPlaying = false;
        }
        String path = "./Songs/"+fileName;
        URL url = Paths.get(path).toAbsolutePath().toUri().toURL();
        Media musicFile = new Media(url.toString());
        musicFile.getDuration().toMinutes();
        mediaplayer = new MediaPlayer(musicFile);
        mediaplayer.play();
        isPlaying = true;
        
        lblTrackArtist.setText(song.getTitle() + " By: " +  song.getArtist());

        
        SliderBar.setValue(mediaplayer.getVolume() * 100);
        SliderBar.valueProperty().addListener(new InvalidationListener() 
        {          
            @Override
            public void invalidated(Observable observable) 
            {
                mediaplayer.setVolume(SliderBar.getValue() / 100);
            }
        }); */
        }
    }

    @FXML
    private void eventPreviousSongbtn(ActionEvent event) throws MalformedURLException 
    {
        Song selectedSong = viewPlaylistSongs.getSelectionModel().getSelectedItem();
        int currentSong = viewPlaylistSongs.getItems().indexOf(selectedSong);
        int nextSong = currentSong-1;
        
        Song nextSelectedSong = viewPlaylistSongs.getItems().get(nextSong);
        song = nextSelectedSong;
        fileName = nextSelectedSong.getFileName();  
        viewPlaylistSongs.getSelectionModel().clearAndSelect(nextSong);
        
        ActionEvent g = null;       
        eventPlayPausebtn(g);

   
    /*     if(isPlaying)
        {
            mediaplayer.dispose();
            isPlaying = false;
        }
        String path = "./Songs/"+fileName;
        URL url = Paths.get(path).toAbsolutePath().toUri().toURL();
        Media musicFile = new Media(url.toString());
        musicFile.getDuration().toMinutes();
        mediaplayer = new MediaPlayer(musicFile);
        mediaplayer.play();
        isPlaying = true;
        
        lblTrackArtist.setText(song.getTitle() + " By: " +  song.getArtist());

        
        SliderBar.setValue(mediaplayer.getVolume() * 100);
        SliderBar.valueProperty().addListener(new InvalidationListener() 
        {          
            @Override
            public void invalidated(Observable observable) 
            {
                mediaplayer.setVolume(SliderBar.getValue() / 100);
            }
        });*/
    }


}

