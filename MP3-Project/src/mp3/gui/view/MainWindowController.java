/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.gui.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author danni
 */
public class MainWindowController implements Initializable {

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
    private Button AddSongToPLBtn;
    @FXML
    private Button SearchBtn;
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
    
}
