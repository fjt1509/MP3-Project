/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.gui.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import mp3.be.Song;

/**
 * FXML Controller class
 *
 * @author danni
 */
public class TestViewController implements Initializable {

    @FXML
    private TextField SearchField;
    @FXML
    private TableView<Std> TableView;
    @FXML
    private TableColumn<Song, String> TitleColumn;
    @FXML
    private TableColumn<Song, String> ArtistColumn;
    @FXML
    private TableColumn<Song, String> ColumnCategory;

    ObservableList list = FXCollections.observableArrayList(
            new Std("abc", "artist", "category"),
            new Std("cde", "artist", "category"),
            new Std("qwe", "artist", "category"),
            new Std("wqe", "artist", "category"),
            new Std("ewq", "artist", "category"),
            new Std("qrw", "artist", "category")
    );

    FilteredList filter = new FilteredList(list, e -> true);

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        ArtistColumn.setCellValueFactory(new PropertyValueFactory<>("artist"));
        ColumnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableView.setItems(filter);
    }
    /* Uses Filter Logic to Search for Title name. */
    @FXML
    private void SearchSong(KeyEvent event) { 
        
        
        SearchField.textProperty().addListener(((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
        
            
            filter.setPredicate((Predicate<? super Std>) (Std std) -> {

                
                if(newValue.isEmpty() || newValue==null) {
                    return true;
                }
                else if (std.getTitle().contains(newValue)) {
                    return true;
                }
                
                return false;
                
            });
            
            
                
            SortedList sort= new SortedList(filter);
            sort.comparatorProperty().bind(TableView.comparatorProperty());
            TableView.setItems(sort);
        
                }));
                }

    public class Std {

        SimpleStringProperty title;
        SimpleStringProperty artist;
        SimpleStringProperty category;

        public Std(String title, String artist, String category) {
            this.title = new SimpleStringProperty(title);
            this.artist = new SimpleStringProperty(artist);
            this.category = new SimpleStringProperty(category);

        }

        public String getTitle() {
            return title.get();
        }

        public String getArtist() {
            return artist.get();
        }

        public String getCategory() {
            return category.get();
        }
    }

}
