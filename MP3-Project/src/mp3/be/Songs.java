/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.be;

/**
 *
 * @author frederik
 */
public class Songs {
    
    private final int id;
    private String title;
    private String artist;
    private String category;
    
    
    public Songs(int id, String title, String artist, String category){
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.category = category;
    }
}
