package edu.ithaca.dragon.autodj.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.ithaca.dragon.autodj.Song;

public class SongDataIOTest {
   
    
    @Test
    public void buildSongListFromJsonFileTest() throws IOException{
        List<Song> allSongs = SongDataIO.buildSongListFromJsonFile("src/test/resources/Localify_97k_Tracks.json");
        assertEquals(97350, allSongs.size());
        assertEquals("Resonate", allSongs.get(0).getTitle());
        System.out.println("Sample From json:");
        for (int i=0; i<50; i++){
            System.out.println(allSongs.get(i));
        }
    }

    
    @Test
    public void buildSongListFromCsvTest() throws IOException{
        List<Song> allSongs = SongDataIO.buildSongListFromCsv("src/test/resources/Localify_100k_Tracks.csv");
        assertEquals(97350, allSongs.size()); //the amount without any names with commas
        assertEquals("Resonate", allSongs.get(0).getTitle());
        System.out.println("Sample From csv:");
        for (int i=0; i<10; i++){
            System.out.println(allSongs.get(i));
        }
    }


    public static void main(String[] args){
        try {
            SongDataIO.buildFromCsvAndWriteToJson("src/test/resources/Localify_100k_Tracks.csv", "src/test/resources/Localify_97k_Tracks.json");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
