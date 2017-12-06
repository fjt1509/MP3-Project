/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.be;

import java.io.Serializable;

/**
 *
 * @author frederik
 */
public class Song implements Serializable {
    
    private final int id;
    private String title;
    private String artist;
    private String category;
    private String fileName;
    
    
    public Song(int id, String title, String artist, String category, String fileName){
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.category = category;
        this.fileName = fileName;
    
    }
    /**
     * Return the ID from songs
     * @return 
     */
    public int getId() {
        return id;
    }
    
    
    /**
     * Return the filename from songs
     * @return 
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Set the filename to songs
     * @return 
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    
    
    /**
     * Get the title
     * @param title 
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * Set the title
     * @param title 
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Get the artist
     * @param artist 
     */
    public String getArtist()
    {
        return artist;
    }

    
    /**
     * Set the artist
     * @param artist 
     */
    public void setArtist(String artist)
    {
        this.artist = artist;
    }

    
    /**
     * Get the category
     * @param category 
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * Set the category
     * @param category 
     */
    public void setCategory(String category)
    {
        this.category = category;
    }
    
    
    /**
     * The "toString" method helps you to make the obj to text.
     * @return 
     */
    @Override
    public String toString()
    {
        return "Songs{" + "id=" + id + ", title=" + title + ", artist=" + artist + ", category=" + category + '}';
    }
}
