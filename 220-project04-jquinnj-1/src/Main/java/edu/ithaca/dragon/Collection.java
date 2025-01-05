package edu.ithaca.dragon;

import java.io.IOException;
import java.util.ArrayList;

import edu.ithaca.dragon.autodj.Song;

public interface Collection {
    String getName();
    
    /**
     * @post adds a playlist of songs to the collection
     */
    void addPlaylist(Playlist playlist);

    /**
     * @return playlist given its name
     */
    Playlist getPlaylist(String name);
    
    /**
     * @return a string representing all playlist names and their durations
     */
    String playlistNamesAndDurations();

    /**
     * @return a string representing the contents of a particular playlist 
     * "This playlist has ## songs with a duration of ##. The most popular artist
     * in this playlist is ##"
     */
    String playlistInfo(String playlistName);

    /**
     * @post remove a playlist
     */
    void removePlaylist(String playlistName);

    /**
     * @post add a new empty playlist
     */
    void addEmptyPlaylist(String name);

    /**
     * @throws IOException 
     * @post create a new random playlist of a specified duration
     * Make a new playlist with the given name, and populate it with a random group of songs that do not 
     * repeat (within this playlist) and are in some shuffled order (i.e., if the same random songs were 
     * chosen for another playlist, they should not play in the same order each time).  The playlist should 
     * have as many songs as can fit without going over the given duration.
     */
    void newRandomPlaylist(int duration, String name) throws IOException;

    /**
     * @post Remove a song from all playlists
     */
    void removeAllInstances(Song song);

    /**
     * @return size of collection
     */
    int getSize();

    /**
     * @return and arrayList of strings of all songs and artists
     */
    ArrayList<String> getSongsAndArtists();
    
}
