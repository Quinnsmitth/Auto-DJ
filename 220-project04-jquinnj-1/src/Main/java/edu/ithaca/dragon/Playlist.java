package edu.ithaca.dragon;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import edu.ithaca.dragon.autodj.Song;

public interface Playlist {
    
    /**
     * @return return a string representing the name of the playlist
     */
    String getName();

    /**
     * @return return an int representing the size of the playlist
     */
    int getSize();

    /**
     * @return return a string representing all songs in the playlist
     */
    String allSongs();

    /**
     * @return calculate the duration of the playlist in minutes
     * @throws NoSuchElementException if the Queue is empty
     */
    int  duration();

    /**
     * @post play next song and removes it from the playlist
     * @return song info
     */
    Song playNext();

    /**
     * @return true if there are no songs in the playlist
     * 
     */
    boolean isEmpty();

    /**
     * @post adds a song to the playlist
     * 
     */
    void add(Song song);

    /**
     * @post remove a song to the playlist
     * 
     */
    void remove(Song song);

    /**
     * @return true if song is present in the playlist
     */
    boolean contains(Song song);

    /**
     * @return the name of the most popular artist in the playlist
     */
    String mostPopularArtist();

    /**
     * @return and arrayList of strings of all songs and artists
     */
    ArrayList<String> allSongsAndArtists();
    
    
}
