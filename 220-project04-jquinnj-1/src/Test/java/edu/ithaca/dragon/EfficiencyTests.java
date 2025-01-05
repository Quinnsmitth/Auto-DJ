package edu.ithaca.dragon;

import java.io.IOException;
import java.util.List;
import javax.management.RuntimeErrorException;
import org.jfree.data.category.DefaultCategoryDataset;
import edu.ithaca.dragon.autodj.Song;
import edu.ithaca.dragon.autodj.data.SongDataIO;
import edu.ithaca.efficiency.EfficiencyChart;

public class EfficiencyTests {
//Max amount of songs is 97349
    public static Playlist sampleArrayPlaylist(int amountOfData) throws IOException{
        List<Song> allSongs = SongDataIO.buildSongListFromCsv("src/test/resources/Localify_100k_Tracks.csv");
        Playlist arrayPlaylist = new ArrayPlaylist(50, "APlaylist");
        for (int i=0; i<amountOfData; i++){
            arrayPlaylist.add(allSongs.get(i));
        }
        return arrayPlaylist;
    }

    public static Playlist sampleLinkedNodePlaylist(int amountOfData) throws IOException{
        List<Song> allSongs = SongDataIO.buildSongListFromCsv("src/test/resources/Localify_100k_Tracks.csv");

        Playlist linkedNodePlaylist = new LinkedNodePlaylist("LNPlaylist");
        for (int i=0; i<amountOfData; i++){
            linkedNodePlaylist.add(allSongs.get(i));
        }
        return linkedNodePlaylist;
    }

    public static long timeAllSongs(Playlist playlist, int numToAverage) throws IOException{ //tests time it takes to get all songs
        long total = 0;
        for (int i=0; i<numToAverage; i++){
            long start = System.nanoTime();
            playlist.allSongs();
            long end = System.nanoTime();
            long runTime = (end - start);
            total+= runTime;
        }
        return total/numToAverage;
    }

    public static long timeDuration(Playlist playlist, int numToAverage) throws IOException{ //tests time it takes to get the duration of a playist
        long total = 0;
        for (int i=0; i<numToAverage; i++){
            long start = System.nanoTime();
            playlist.duration();
            long end = System.nanoTime();
            long runTime = (end - start);
            total+= runTime;
        }
        return total/numToAverage;
    }

    public static long timeAddSong(Playlist playlist, int numToAverage) throws IOException{ //tests time it takes to get add songs
        List<Song> allSongs = SongDataIO.buildSongListFromCsv("src/test/resources/Localify_100k_Tracks.csv");
        Song songToAdd = allSongs.get(97349);
        long total = 0;
        for (int i=0; i<numToAverage; i++){
            long start = System.nanoTime();
            playlist.add(songToAdd);
            long end = System.nanoTime();
            long runTime = (end - start);
            total+= runTime;
        }
        return total/numToAverage;
    }

    public static long timeRemoveSong(Playlist playlist, int numToAverage) throws IOException{ //tests time it takes to remove a song
        List<Song> allSongs = SongDataIO.buildSongListFromCsv("src/test/resources/Localify_100k_Tracks.csv");
        playlist.add(allSongs.get(0));
        Song songToRemove = allSongs.get(0);
        long total = 0;
        for (int i=0; i<numToAverage; i++){
            long start = System.nanoTime();
            playlist.remove(songToRemove);
            long end = System.nanoTime();
            long runTime = (end - start);
            total+= runTime;
            playlist.add(allSongs.get(0));
        }
        return total/numToAverage;
    }

    public static long timePlayNext(Playlist playlist, int numToAverage) throws IOException{ //tests time it takes to play the next song
        long total = 0;
        for (int i=0; i<numToAverage; i++){
            long start = System.nanoTime();
            playlist.playNext();
            long end = System.nanoTime();
            long runTime = (end - start);
            total+= runTime;
        }
        return total/numToAverage;
    }


    public static DefaultCategoryDataset collectAllSongsData(String playlistType) throws IOException{ //recieves a type of playlist, tests its effieciency when printing all songs for a large amount of data and prints the times
        
        final int numSongsToAddFirst = 1000;
        final int numDataPointsToPrint = 50;
        final int numToAverageOver = 500;
        Playlist playlist = null;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        System.out.println("----------------------------");
        System.out.println("Testing All Song Efficiency for " + playlistType);
        for(int i=1; i<numDataPointsToPrint; i++){
            int numSongsToAdd = numSongsToAddFirst * i;
            if (playlistType == "ArrayPlaylist"){
                playlist = sampleArrayPlaylist(numSongsToAdd);
            }
            else if (playlistType == "LinkedNodePlaylist"){
                playlist = sampleLinkedNodePlaylist(numSongsToAdd);
            }
            else{throw new RuntimeErrorException(null, "Playlist Type Invalid");}
            double avgTime = timeAllSongs(playlist, numToAverageOver);
            System.out.println((i+1)*numSongsToAdd + " \t\t " + avgTime);
            dataset.addValue( avgTime, playlistType, Integer.toString(i+1));
        }
        return dataset;
    }

    public static DefaultCategoryDataset collectDurationData(String playlistType) throws IOException{ //recieves a type of playlist, tests its effieciency when calculating the duration of the playlist for a large amount of data and prints the times
        
        final int numSongsToAddFirst = 1000;
        final int numDataPointsToPrint = 50;
        final int numToAverageOver = 500;
        Playlist playlist = null;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        System.out.println("----------------------------");
        System.out.println("Testing Duration Efficiency for " + playlistType);
        for(int i=1; i<numDataPointsToPrint; i++){
            int numSongsToAdd = numSongsToAddFirst * i;
            if (playlistType == "ArrayPlaylist"){
                playlist = sampleArrayPlaylist(numSongsToAdd);
            }
            else if (playlistType == "LinkedNodePlaylist"){
                playlist = sampleLinkedNodePlaylist(numSongsToAdd);
            }
            else{throw new RuntimeErrorException(null, "Playlist Type Invalid");}
            double avgTime = timeDuration(playlist, numToAverageOver);
            System.out.println((i+1)*numSongsToAdd + " \t\t " + avgTime);
            dataset.addValue( avgTime, playlistType, Integer.toString(i+1));
        }
        return dataset;
    }

    public static DefaultCategoryDataset collectAddSongData(String playlistType) throws IOException{ //recieves a type of playlist, tests its effieciency when adding a song for a large amount of data and prints the times
        
        final int numSongsToAddFirst = 1000;
        final int numDataPointsToPrint = 50;
        final int numToAverageOver = 500;
        Playlist playlist = null;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        System.out.println("----------------------------");
        System.out.println("Testing Add Song Efficiency for " + playlistType);
        for(int i=1; i<numDataPointsToPrint; i++){
            int numSongsToAdd = numSongsToAddFirst * i;
            if (playlistType == "ArrayPlaylist"){
                playlist = sampleArrayPlaylist(numSongsToAdd);
            }
            else if (playlistType == "LinkedNodePlaylist"){
                playlist = sampleLinkedNodePlaylist(numSongsToAdd);
            }
            else{throw new RuntimeErrorException(null, "Playlist Type Invalid");}
            double avgTime = timeAddSong(playlist, numToAverageOver);
            System.out.println((i+1)*numSongsToAdd + " \t\t " + avgTime);
            dataset.addValue( avgTime, playlistType, Integer.toString(i+1));
        }
        return dataset;
    }

    public static DefaultCategoryDataset collectRemoveSongData(String playlistType) throws IOException{ //recieves a type of playlist, tests its effieciency when removing a song for a large amount of data and prints the times
        
        final int numSongsToAddFirst = 1000;
        final int numDataPointsToPrint = 50;
        final int numToAverageOver = 500;
        Playlist playlist = null;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        System.out.println("----------------------------");
        System.out.println("Testing Remove Song Efficiency for " + playlistType);
        for(int i=1; i<numDataPointsToPrint; i++){
            int numSongsToAdd = numSongsToAddFirst * i;
            if (playlistType == "ArrayPlaylist"){
                playlist = sampleArrayPlaylist(numSongsToAdd);
            }
            else if (playlistType == "LinkedNodePlaylist"){
                playlist = sampleLinkedNodePlaylist(numSongsToAdd);
            }
            else{throw new RuntimeErrorException(null, "Playlist Type Invalid");}
            double avgTime = timeRemoveSong(playlist, numToAverageOver);
            System.out.println((i+1)*numSongsToAdd + " \t\t " + avgTime);
            dataset.addValue( avgTime, playlistType, Integer.toString(i+1));
        }
        return dataset;
    }

    public static DefaultCategoryDataset collectPlayNextData(String playlistType) throws IOException{ //recieves a type of playlist, tests its effieciency when playing the next song for a large amount of data and prints the times
        
        final int numSongsToAddFirst = 1000;
        final int numDataPointsToPrint = 50;
        final int numToAverageOver = 500;
        Playlist playlist = null;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        System.out.println("----------------------------");
        System.out.println("Testing Play Next Efficiency for " + playlistType);
        for(int i=1; i<numDataPointsToPrint; i++){
            int numSongsToAdd = numSongsToAddFirst * i;
            if (playlistType == "ArrayPlaylist"){
                playlist = sampleArrayPlaylist(numSongsToAdd);
            }
            else if (playlistType == "LinkedNodePlaylist"){
                playlist = sampleLinkedNodePlaylist(numSongsToAdd);
            }
            else{throw new RuntimeErrorException(null, "Playlist Type Invalid");}
            double avgTime = timePlayNext(playlist, numToAverageOver);
            System.out.println((i+1)*numSongsToAdd + " \t\t " + avgTime);
            dataset.addValue( avgTime, playlistType, Integer.toString(i+1));
        }
        return dataset;
    }


    public static void main(String[] args) throws IOException{ //Creates efficiency charts for each method in the playlist interface for both implementations of the interface
        new EfficiencyChart(collectAllSongsData("ArrayPlaylist"), "All Song Efficiency");
        new EfficiencyChart(collectAllSongsData("LinkedNodePlaylist"), "All Song Efficiency");
        new EfficiencyChart(collectDurationData("ArrayPlaylist"), "Duration Efficiency");
        new EfficiencyChart(collectDurationData("LinkedNodePlaylist"), "Duration Efficiency");
        new EfficiencyChart(collectAddSongData("ArrayPlaylist"), "Add Song Efficiency");
        new EfficiencyChart(collectAddSongData("LinkedNodePlaylist"), "Add Song Efficiency");
        new EfficiencyChart(collectRemoveSongData("ArrayPlaylist"), "Remove Song Efficiency");
        new EfficiencyChart(collectRemoveSongData("LinkedNodePlaylist"), "Remove Song Efficiency");
        new EfficiencyChart(collectPlayNextData("ArrayPlaylist"), "Play Next Efficiency");
        new EfficiencyChart(collectPlayNextData("LinkedNodePlaylist"), "Play Next Efficiency");
    }
    
}