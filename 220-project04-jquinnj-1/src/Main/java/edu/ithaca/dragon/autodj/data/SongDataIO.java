package edu.ithaca.dragon.autodj.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import edu.ithaca.dragon.autodj.Song;
import edu.ithaca.dragon.io.JsonUtil;

public class SongDataIO {    

    public static List<Song> buildSongListFromJsonFile(String filename)throws IOException{
        return JsonUtil.listFromJsonFile(filename, Song.class);
    }

    //------------   below here you can see how a json file can be created from csv, if you're interested
    public static void buildFromCsvAndWriteToJson(String csvInputFilename, String jsonOutputFilename) throws IOException{
        List<Song> allSongs = buildSongListFromCsv(csvInputFilename);
        JsonUtil.toJsonFile(jsonOutputFilename, allSongs);    
    }
    
    public static List<Song> buildSongListFromCsv(String filename) throws IOException{
        Pattern pattern = Pattern.compile(",");
        BufferedReader in = new BufferedReader(new FileReader(filename));
        List<Song> allSongs = new ArrayList<>();
        String newLine = in.readLine();
        while (newLine != null){
            String[] pieces = pattern.split(newLine);
            if (pieces.length == 7){ //check whether there were commas in names, if so, skip this song
                boolean dataOk = true;
                for (String piece: pieces){ //check whether any fields are empty. If so, skip
                    if (piece.equals("")){
                        dataOk = false;
                    }
                }
                if (dataOk){
                    Song newSong = new Song(pieces[0], pieces[1], Integer.parseInt(pieces[6]), 0, 
                        Integer.parseInt(pieces[2]), Double.parseDouble(pieces[3]), Double.parseDouble(pieces[4]),Double.parseDouble(pieces[5]));
                    allSongs.add(newSong);
                }
            }
            newLine = in.readLine();
        }
        in.close();
        return allSongs;
    }
    
}
