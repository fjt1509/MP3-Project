/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.bll;

import java.sql.SQLException;

/**
 *
 * @author frederik
 */
public class MP3Exception extends Exception {


    public MP3Exception(String message)
    {
        super(message);
    }

    public MP3Exception(String message, Throwable cause)
    {
        super(message, cause);
    }

    public MP3Exception(Throwable cause)
    {
        super(cause);
    }
    
}
