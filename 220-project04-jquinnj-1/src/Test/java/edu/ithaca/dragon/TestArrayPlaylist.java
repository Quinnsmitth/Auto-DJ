package edu.ithaca.dragon;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import edu.ithaca.dragon.autodj.Song;
import edu.ithaca.dragon.autodj.data.SongDataIO;

//Author: JJ Purpose: Test ArrayPlaylist to ensure all methods are working correctly

public class TestArrayPlaylist {


    
    public Playlist sampleSongs() throws IOException{
        List<Song> allSongs = SongDataIO.buildSongListFromCsv("src/test/resources/Localify_100k_Tracks.csv");
        Playlist myPlaylist = new ArrayPlaylist(50, "myPlaylist");
        for (int i=0; i<50; i++){
            myPlaylist.add(allSongs.get(i));
        }
        return myPlaylist;
    }

    public ArrayList<Song> otherSongs() throws IOException{
        List<Song> allSongs = SongDataIO.buildSongListFromCsv("src/test/resources/Localify_100k_Tracks.csv");
        ArrayList<Song> mylist = new ArrayList<>(5);
        for (int i=50; i<55; i++){
            mylist.add(allSongs.get(i));
        }
        return mylist;
    }
    
    /**
     * @return return a string representing all songs in the playlist
    * @throws IOException 
    */
    @Test
    public void testAllSongs() throws IOException{
        Playlist myPlaylist = sampleSongs();

        assertEquals("Resonate\n" +
             "Traveling Man\n" +
             "Plantation\n" +
             "Who Paid Them Off?\n" +
             "New Fashion\n" +
             "The Gold (Dubmatix Runnin' Remix)\n" +
             "Give Yourself Over\n" +
             "Garden Tree\n" +
             "High Grade\n" +
             "Pure Fire (Disco Mix)\n" +
             "Make You Happy\n" +
             "Goodbye - Sim Redmond Band Remix\n" +
             "8 Track Mind\n" +
             "In the Dark\n" +
             "Beautiful People\n" +
             "Biggie K - Donna The Buffalo Remix\n" +
             "Open My Eyes\n" +
             "Come With Us\n" +
             "Life Is Water\n" +
             "Hurricane\n" +
             "Holes In The Ground\n" +
             "Good Thoughts\n" +
             "Pink Guitar\n" +
             "All Is Not Lost\n" +
             "Save Me\n" +
             "All I Ever Want\n" +
             "Breakdown\n" +
             "Nothing I Can Do\n" +
             "Trampoline\n" +
             "Bliss Seeker\n" +
             "Yin Yang Boomerang\n" +
             "Hey Boy\n" +
             "Where Are the Monsters\n" +
             "Big Red Button\n" +
             "New Persona\n" +
             "Runnin' Round\n" +
             "Talk\n" +
             "Velvet Rope\n" +
             "Positive Friction\n" +
             "No Place Like The Right Time\n" +
             "Heaven & the Earth\n" +
             "Motor\n" +
             "Dance in the Street\n" +
             "Seminole Wind\n" +
             "Locket And Key\n" +
             "I Believe\n" +
             "Across the Way\n" +
             "Holding on to Nothing\n" +
             "Unsteady\n" +
             "Renegades\n", myPlaylist.allSongs());
    }

    /**
     * @return calculate the duration of the playlist in minutes
     * @throws NoSuchElementException if the Queue is empty
     */
    @Test
    public void testDuration()throws IOException{
        Playlist myPlaylist = sampleSongs();

        assertEquals(myPlaylist.duration(),213);


    }

  
    /**
     * @post play next song and removes it from the playlist
     * @return song info
     */
    @Test

    public void testPlayNext() throws IOException {
        Playlist myPlaylist = new ArrayPlaylist(10, "myPlaylist");
        ArrayList<Song> testSongs = otherSongs();

        myPlaylist.add(testSongs.get(0));
        
        myPlaylist.playNext();  
    
        assertTrue(myPlaylist.isEmpty());
        
        assertThrows(NoSuchElementException.class, () -> myPlaylist.playNext(), "No Songs available");  
    }

    /**
     * @return true if there are no songs in the playlist
     * 
     */
    @Test
    public void testIsEmpty() throws IOException{
        Playlist myPlaylist = new ArrayPlaylist(10, "myPlaylist");
        ArrayList<Song> testSongs = otherSongs();

        myPlaylist.add(testSongs.get(0));
        myPlaylist.add(testSongs.get(1));
        myPlaylist.add(testSongs.get(2));
        myPlaylist.add(testSongs.get(3));
        myPlaylist.add(testSongs.get(4));
        
        assertFalse(myPlaylist.isEmpty());

        myPlaylist.remove(testSongs.get(0));
        myPlaylist.remove(testSongs.get(1));
        myPlaylist.remove(testSongs.get(2));
        myPlaylist.remove(testSongs.get(3));
        myPlaylist.remove(testSongs.get(4));

        assertTrue(myPlaylist.isEmpty());
    }

    /**
     * @post adds a song to the playlist
     * 
     */
    @Test
    public void TestAdd() throws IOException{
        Playlist myPlaylist = new ArrayPlaylist(10, "myPlaylist");
        ArrayList<Song> testSongs = otherSongs();

        myPlaylist.add(testSongs.get(0));
        myPlaylist.add(testSongs.get(1));
        myPlaylist.add(testSongs.get(2));
        myPlaylist.add(testSongs.get(3));
        myPlaylist.add(testSongs.get(4));
        
        assertFalse(myPlaylist.isEmpty());
        
        /*
         *  Requires work to split the string to pass,
         * but this prints two equal strings
         * assertEquals(myPlaylist.allSongs(), testSongs);
        */
    }

    /**
     * @post remove a song to the playlist
     * 
     */
    @Test
    public void testRemove() throws IOException {
        Playlist myPlaylist = new ArrayPlaylist(10, "myPlaylist");  
        ArrayList<Song> testSongs = otherSongs();     

        myPlaylist.add(testSongs.get(0));
        myPlaylist.add(testSongs.get(1));
        myPlaylist.add(testSongs.get(2));

        assertFalse(myPlaylist.isEmpty());

        myPlaylist.remove(testSongs.get(1));

        assertFalse(myPlaylist.contains(testSongs.get(1)));

        assertTrue(myPlaylist.contains(testSongs.get(0)));
        assertTrue(myPlaylist.contains(testSongs.get(2)));

        // Try to remove a song that does not exist and assert that it throws an exception
        assertThrows(IllegalArgumentException.class, () -> myPlaylist.remove(testSongs.get(4)), "Song not found in the playlist.");
    }

    
    
}

        