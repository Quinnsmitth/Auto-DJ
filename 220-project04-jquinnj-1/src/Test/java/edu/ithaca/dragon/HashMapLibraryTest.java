package edu.ithaca.dragon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.ithaca.dragon.autodj.Song;
import edu.ithaca.dragon.autodj.data.SongDataIO;

import java.io.IOException;
import java.util.List;

public class HashMapLibraryTest {

    private Collection sampleCollection() throws IOException {
        List<Song> allSongs = SongDataIO.buildSongListFromCsv("src/test/resources/Localify_100k_Tracks.csv");
        Playlist playlist1 = new LinkedNodePlaylist("Playlist1");
        for (int i = 0; i < 5; i++) {
            playlist1.add(allSongs.get(i));
        }
        Playlist playlist2 = new LinkedNodePlaylist("Playlist2");
        for (int i = 5; i < 10; i++) {
            playlist2.add(allSongs.get(i));
        }
        Playlist playlist3 = new LinkedNodePlaylist("Playlist3");
        for (int i = 10; i < 15; i++) {
            playlist3.add(allSongs.get(i));
        }
        Collection myCollection = new ArrayListCollection(5, "myCollection");
        myCollection.addPlaylist(playlist1);
        myCollection.addPlaylist(playlist2);
        myCollection.addPlaylist(playlist3);
        return myCollection;
    }

    private Library sampleLibrary() throws IOException {
        HashMapLibrary library = new HashMapLibrary();

        // Use the sampleCollection method to add a collection to the library
        Collection sampleCollection = sampleCollection();
        library.importCollection(sampleCollection);
        return library;
    }

    @Test
    public void testAllSongsAndArtists() throws IOException {
        Library library = sampleLibrary();
        System.out.println(library);
        String result = library.allSongsAndArtists();
        System.out.println(library);

        assertNotNull(result);
        assertTrue(result.contains("John Brown's Body - Resonate")); // Check if Playlist1's songs appear in the output
    }

    @Test
    public void testSongInfoString() throws IOException {
        Library library = sampleLibrary();
        List<Song> allSongs = SongDataIO.buildSongListFromCsv("src/test/resources/Localify_100k_Tracks.csv");
        Song testSong = allSongs.get(0);

        String songInfo = library.songInfoString(testSong);
        assertNotNull(songInfo);
        assertTrue(songInfo.contains(testSong.getTitle()));
    }

    @Test
    public void testImportCollection() throws IOException {
        Library library = sampleLibrary();
        Collection newCollection = sampleCollection();
        library.importCollection(newCollection);
        String result = library.allSongsAndArtists();
        assertTrue(result.contains("Playlist2")); // Verify Playlist2's songs are added
    }

    @Test
    public void testDiscontinueCollection() throws IOException {
        Library library = sampleLibrary();
        Collection sampleCollection = sampleCollection();
        library.discontinueCollection(sampleCollection);

        String result = library.allSongsAndArtists();
        assertEquals("No Songs Available", result); // Confirm collection is removed
    }

    
}
