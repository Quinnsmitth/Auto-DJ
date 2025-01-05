package edu.ithaca.dragon;

import edu.ithaca.dragon.autodj.Song;

public interface Library {
    
/*
 * @return a string representing all songs in the library in alphabetical order 
 * (by artist first, then by song title)
 */
String allSongsAndArtists();

/*
 * @return information of a song
 * Retrieve information about a song within the library
 */
String songInfoString(Song song);

/*
 * @post Add a collection of songs to the library (import)
 */
void importCollection(Collection collection);

/*
 * @post Remove a collection of songs from the library (discontinue)
 * Remove given songs from the library. Also remove these songs from any playlist in which they occur. 
 */
void discontinueCollection(Collection collection);

}

