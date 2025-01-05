package edu.ithaca.dragon;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Collections;
import edu.ithaca.dragon.autodj.Song;

public class HashMapLibrary implements Library {

    private HashMap<String, Collection> library; 

    public HashMapLibrary(){
        this.library = new HashMap<>();
    }

    /*
    * @return a string representing all songs in the library in alphabetical order 
    * (by artist first, then by song title)
    *
    public String allSongsAndArtists() {
        if (library.isEmpty()) {
            return "No Songs Available";
        }

        StringBuilder allSongs = new StringBuilder();
        List<String> artistNames = new ArrayList<>(library.keySet());
        Collections.sort(artistNames);

        for (String artistName : artistNames) {
            Collection artistCollection = library.get(artistName);

            if (artistCollection != null) {
                ArrayList<String> songs = artistCollection.getSongsAndArtists(); // Assuming Collection has a getSongs method
                Collections.sort(songs);

                for (String song : songs) {
                    allSongs.append(song).append("\n");
                }
            }
        }

        return allSongs.toString().trim();
        }
        */

        public String allSongsAndArtists() {
            if (library.isEmpty()) {
                throw new NoSuchElementException("Library is empty");
            }
        
            StringBuilder allSongs = new StringBuilder();
            List<String> artistNames = new ArrayList<>(library.keySet());
            Collections.sort(artistNames);
        
            for (String artistName : artistNames) {
                Collection artistCollection = library.get(artistName);
        
                if (artistCollection != null) {
                    allSongs.append("Artist: ").append(artistName).append("\n");
                    ArrayList<String> songs = artistCollection.getSongsAndArtists();
                    Collections.sort(songs);
        
                    for (String song : songs) {
                        allSongs.append("  ").append(song).append("\n");
                    }
                }
            }
        
            return allSongs.toString().trim();
        }
        

        /*
        * @return information of a song
        * Retrieve information about a song within the library
        */
        public String songInfoString(Song song){
            StringBuilder songInfo = new StringBuilder();
            songInfo.append(song.toString()).append("-\n");
            songInfo.append(song.getDuration()).append("\n");
            songInfo.append(song.getPlayCount()).append("\n");
            songInfo.append(song.getSpotifyDanceability()).append("\n");
            return songInfo.toString();
        }

        /*
        * @post Add a collection of songs to the library (import)
        */
        public void importCollection(Collection collection){
            library.put(collection.getName(), collection);
        }

        /*
        * @post Remove a collection of songs from the library (discontinue)
        * Remove given songs from the library. Also remove these songs from any playlist in which they occur. 
        */
        public void discontinueCollection(Collection collection){
            library.remove(collection.getName());
        }

}
