/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.gui.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * FXML Controller class
 *
 * @author Daniels PC
 */
public class NewSongController implements Initializable
{

    @FXML
    private TextField txtFieldFilePath;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void eventChooseFileBtn(ActionEvent event) 
    {
        FileChooser fc = new FileChooser();
        //fc.getExtensionFilters().addAll(new ExtensionFilter("mp3"));
        File selectedFile = fc.showOpenDialog(null);
        
        
        if (selectedFile !=null)
        {
            txtFieldFilePath.setText(selectedFile.getAbsolutePath());
            System.out.println(selectedFile.getAbsolutePath());
            String fileName = selectedFile.getName();
            System.out.println(fileName);
        }
    }
    
}
