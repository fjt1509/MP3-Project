/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.gui.controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author danni
 */
public class MainWindowController implements Initializable {

    @FXML
    private ListView<?> SongplayListViewer;
    @FXML
    private TableView<?> PlaylistsViewer;
    @FXML
    private TableView<?> SongsViewer;
    @FXML
    private TextField FilterTxtField;
    @FXML
    private Slider SliderBar;
    @FXML
    private TextField SongPlayerTxtField;
    @FXML
    private Circle PlayBtn;
    @FXML
    private Circle BackBtn;
    @FXML
    private Circle NextBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    @FXML
    private void eventEditPlaylistBtn(ActionEvent event)
    {
    }

    @FXML
    private void eventDeletePlaylistBtn(ActionEvent event)
    {
    }

    @FXML
    private void eventMoveDownBtn(ActionEvent event)
    {
    }

    @FXML
    private void eventDeletePlayListSongBtn(ActionEvent event)
    {
    }

    @FXML
    private void eventCloseBtn(ActionEvent event)
    {
    }

    @FXML
    private void eventDeleteSongBtn(ActionEvent event)
    {
    }

    @FXML
    private void eventMoveUpBtn(ActionEvent event)
    {
    }

    @FXML
    private void eventAddSongToPLBtn(ActionEvent event)
    {
    }

    @FXML
    private void eventSearchBtn(ActionEvent event)
    {
    }
    
}
