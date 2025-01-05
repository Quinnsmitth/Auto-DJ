package edu.ithaca.dragon;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.ithaca.dragon.autodj.Song;
import edu.ithaca.dragon.autodj.data.SongDataIO;


public class HashMapCollection implements Collection {
   private Map<String, Playlist> playlists;
   private String name;


   public HashMapCollection(String name) {
       playlists = new HashMap<>();
       this.name = name;
   }


   @Override
   public  String playlistNamesAndDurations(){
       StringBuilder sb = new StringBuilder();
       for (Map.Entry<String, Playlist> entry : playlists.entrySet()) {
           sb.append(entry.getKey()).append(": ").append(entry.getValue().duration()).append(" mins\n");
       }
       return sb.toString();
   }


   public String playlistInfo(){
           if (playlists.isEmpty()) {
               return "No playlists available.";
           }
  
           Playlist playlist = playlists.values().iterator().next();
           return "This playlist has " + playlist.allSongs() + " ... with a duration of "
                   + playlist.duration() + " mins.";
       }
  
   public void removePlaylist(String playlistName){
       if (playlists.containsKey(playlistName)) {
               playlists.remove(playlistName);
               System.out.println("Playlist '" + playlistName + "' has been removed.");
           } else {
               System.out.println("Playlist '" + playlistName + "' not found.");
           }
   }


 
   @Override
   public void addEmptyPlaylist(String playlistName) {
       if (playlists.containsKey(playlistName)) {
           System.out.println("A playlist with the name '" + playlistName + "' already exists.");
       } else {
           ArrayPlaylist newPlaylist = new ArrayPlaylist(10,playlistName);
           playlists.put(playlistName, newPlaylist);
           System.out.println("Empty playlist '" + playlistName + "' has been added.");
       }
   }
   
   @Override
   public void newRandomPlaylist(int duration, String name) throws IOException {
    if (playlists.containsKey(name)) {
        throw new IllegalArgumentException("A playlist with the name '" + name + "' already exists.");
    }

    Playlist randomPlaylist = new ArrayPlaylist(10, name); 
    int totalDuration = 0;

    Set<Song> allSongsSet = new HashSet<>(SongDataIO.buildSongListFromCsv("src/test/resources/Localify_100k_Tracks.csv"));
    List<Song> shuffledSongs = new ArrayList<>(allSongsSet);
    Collections.shuffle(shuffledSongs);

    for (Song randomSong : shuffledSongs) {
        if (totalDuration + randomSong.getDuration() <= duration) {
            randomPlaylist.add(randomSong);
            totalDuration += randomSong.getDuration();
        }
        if (totalDuration >= duration) { 
            break;
        }
    }

    if (randomPlaylist.getSize() == 0) {
        throw new IllegalArgumentException("Cannot create a random playlist with the given duration");
    }

    playlists.put(name, randomPlaylist);
}

   @Override 
   public void removeAllInstances(Song song){
       if (song == null) {
           throw new IllegalArgumentException("Song cannot be null");
       }
  
       for (Playlist playlist : playlists.values()) {
           if (playlist.contains(song)) {
               playlist.remove(song);
               System.out.println("Removed '" + song.getTitle() + "' from playlist.");
           }
       }
   }

   @Override
   public int getSize() {
       return playlists.size();
   }
   
   @Override
   public void addPlaylist(Playlist playlist) {
       if (playlist == null) {
           throw new IllegalArgumentException("Playlist cannot be null");
       }
       String playlistName = playlist.getName(); 
       if (playlists.containsKey(playlistName)) {
           throw new IllegalArgumentException("A playlist with the name '" + playlistName + "' already exists.");
       }
       playlists.put(playlistName, playlist); 
   }
   
   @Override
   public Playlist getPlaylist(String name) {
       if (!playlists.containsKey(name)) {
           throw new IllegalArgumentException("Playlist '" + name + "' not found.");
       }
       return playlists.get(name); 
   }
   
   @Override
   public String playlistInfo(String playlistName) {
       if (!playlists.containsKey(playlistName)) {
           return "Playlist '" + playlistName + "' not found.";
       }
       Playlist playlist = playlists.get(playlistName);
       return "Playlist '" + playlistName + "' has " + playlist.getSize() + " songs with a total duration of " + playlist.duration() + " mins."; // Assuming the Playlist class has getSize() and duration() methods
   }


    @Override
    public String getName() {
        return name;
    }


    @Override
    public ArrayList<String> getSongsAndArtists() {
        ArrayList<String> allSongsAndArtists = new ArrayList<>();
        for (Playlist playlist : playlists.values()) {
            ArrayList<String> playlistSongsAndArtists = playlist.allSongsAndArtists();
            allSongsAndArtists.addAll(playlistSongsAndArtists);
        }
        return allSongsAndArtists;
    }


}


  





