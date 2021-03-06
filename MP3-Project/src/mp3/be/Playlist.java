/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.be;

import java.io.Serializable;

/**
 *
 * @author Daniels PC
 */
public class Playlist implements Serializable
{
    private final int id;
    private String name;
    private int songs;

    public Playlist(int id, String name)
    {
        this.id = id;
        this.name = name;
        //this.songs = songs;
    }

    
    
    
    
     /**
     * Return the ID from Playlist
     * @return 
     */
    public int getId()
    {
        return id;
    }

    
    
    
    /**
     * Return the name from Playlist
     * @return 
     */
    public String getName()
    {
        return name;
    }

    
    /**
     * Set the name
     * @return 
     */
    public void setName(String name)
    {
        this.name = name;
    }

    
    /**
     * Gets songs
     * @return 
     */
    public int getSongs()
    {
        return songs;
    }

    
    
    /**
     * Sets songs
     * @param songs 
     */
    public void setSongs(int songs)
    {
        this.songs = songs;
    }
    
}
