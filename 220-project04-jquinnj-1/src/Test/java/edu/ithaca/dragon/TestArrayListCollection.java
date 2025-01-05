package edu.ithaca.dragon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import edu.ithaca.dragon.autodj.Song;
import edu.ithaca.dragon.autodj.data.SongDataIO;

//Author: JJ Purpose: Test ArrayListCollection to ensure all methods are working correctly

public class TestArrayListCollection {

    public Collection sampleCollection() throws IOException{
        List<Song> allSongs = SongDataIO.buildSongListFromCsv("src/test/resources/Localify_100k_Tracks.csv");
        Playlist playlist1 = new LinkedNodePlaylist("Playlist1");
        for (int i=0; i<5; i++){
            playlist1.add(allSongs.get(i));
        }
        Playlist playlist2 = new LinkedNodePlaylist("Playlist2");
        for (int i=5; i<10; i++){
            playlist2.add(allSongs.get(i));
        }
        Playlist playlist3 = new LinkedNodePlaylist("Playlist3");
        for (int i=10; i<15; i++){
            playlist3.add(allSongs.get(i));
        }
        Collection myCollection = new ArrayListCollection(5, "myCollection");
        myCollection.addPlaylist(playlist1);
        myCollection.addPlaylist(playlist2);
        myCollection.addPlaylist(playlist3);
        return myCollection;
    }

    @Test
    public void testGetPlaylist() throws IOException{
        Collection myCollection = sampleCollection();
        List<Song> allSongs = SongDataIO.buildSongListFromCsv("src/test/resources/Localify_100k_Tracks.csv");
        Playlist playlist1 = new LinkedNodePlaylist("Playlist1");
        for (int i=0; i<5; i++){
            playlist1.add(allSongs.get(i));
        }
        assertEquals(playlist1.allSongs(), myCollection.getPlaylist("Playlist1").allSongs());

    }
    
    @Test
    public void testPlaylistNamesAndDurations() throws IOException {
        Collection myCollection = sampleCollection();

        assertEquals(myCollection.playlistNamesAndDurations(), "Playlist1 Duration: 24 minutes\n"+
        "Playlist2 Duration: 24 minutes\n"+
        "Playlist3 Duration: 20 minutes\n"+
        "Total Duration: 68 minutes.");
        Collection newCollection = new ArrayListCollection(5, "newCollection");
        assertThrows(NoSuchElementException.class, () -> newCollection.playlistNamesAndDurations(), "Collection is empty");  
    }

    @Test
    public void testPlaylistInfo() throws IOException {
        Collection myCollection = sampleCollection();
        assertEquals("This playlist has 5 songs with a duration of 24 minutes. The most popular artist in this playlist is John Brown's Body"
                                , myCollection.playlistInfo("Playlist1"));
        assertEquals("This playlist has 5 songs with a duration of 24 minutes. The most popular artist in this playlist is John Brown's Body"
                                , myCollection.playlistInfo("Playlist2"));
        assertEquals("This playlist has 5 songs with a duration of 20 minutes. The most popular artist in this playlist is The Gunpoets"
                                , myCollection.playlistInfo("Playlist3"));
        assertThrows(NoSuchElementException.class, () -> myCollection.playlistInfo("NOT A NAME"), "Playlist not found");  
        Collection newCollection = new ArrayListCollection(5, "newCollection");
        assertThrows(NoSuchElementException.class, () -> newCollection.playlistInfo("mY jAmS"), "Collection is empty");  

    }

    @Test
    public void testRemovePlaylist() throws IOException {
        Collection myCollection = sampleCollection();
        assertThrows(NoSuchElementException.class, () -> myCollection.removePlaylist("mY jAmS"), "Playlist not found");  
        myCollection.removePlaylist("Playlist1");
        assertEquals(2, myCollection.getSize());
        myCollection.removePlaylist("Playlist2");
        assertEquals(1, myCollection.getSize());
        myCollection.removePlaylist("Playlist3");
        assertEquals(0, myCollection.getSize());
        assertThrows(NoSuchElementException.class, () -> myCollection.removePlaylist("mY jAmS"), "Collection is empty");  
    }

    @Test
    public void testAddEmptyPlaylist() throws IOException {
        Collection myCollection = sampleCollection();
        myCollection.addEmptyPlaylist("Empty Playlist");
        assertEquals(4, myCollection.getSize());
    }

    @Test
    public void testNewRandomPlaylist() throws IOException {
        Collection myCollection = sampleCollection();
        myCollection.newRandomPlaylist(2000000, "Random Playlist");
        assertTrue(myCollection.getPlaylist("Random Playlist").duration() <= 2000000);
        assertEquals(4, myCollection.getSize());
        assertFalse(myCollection.getPlaylist("Random Playlist").isEmpty());
    }

    @Test
    public void testRemoveAllInstances() throws IOException {
        Collection myCollection = sampleCollection();
        List<Song> allSongs = SongDataIO.buildSongListFromCsv("src/test/resources/Localify_100k_Tracks.csv");

        Song songToRemove = allSongs.get(0);

        myCollection.getPlaylist("Playlist1").add(songToRemove);
        myCollection.getPlaylist("Playlist2").add(songToRemove);
        assertTrue(myCollection.getPlaylist("Playlist1").allSongs().contains(songToRemove.getTitle()));
        assertTrue(myCollection.getPlaylist("Playlist2").allSongs().contains(songToRemove.getTitle()));

        myCollection.removeAllInstances(songToRemove);
        assertFalse(myCollection.getPlaylist("Playlist1").contains(songToRemove));
        assertFalse(myCollection.getPlaylist("Playlist2").contains(songToRemove));
        assertFalse(myCollection.getPlaylist("Playlist3").contains(songToRemove));
    }
}
