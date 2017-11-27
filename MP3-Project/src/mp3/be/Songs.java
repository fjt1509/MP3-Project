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
public class Songs implements Serializable {
    
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

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getArtist()
    {
        return artist;
    }

    public void setArtist(String artist)
    {
        this.artist = artist;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }
    
    @Override
    public String toString()
    {
        return "Songs{" + "id=" + id + ", title=" + title + ", artist=" + artist + ", category=" + category + '}';
    }
}
