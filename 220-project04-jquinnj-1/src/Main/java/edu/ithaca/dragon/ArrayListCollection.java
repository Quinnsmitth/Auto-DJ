package edu.ithaca.dragon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import edu.ithaca.dragon.autodj.Song;
import edu.ithaca.dragon.autodj.data.SongDataIO;

public class ArrayListCollection implements Collection {
    
    ArrayList<Playlist> collection;
    private int size;
    private String name;

    public ArrayListCollection(int capacity, String name){
       this.collection = new ArrayList<>();
       this.size = 0;
       this.name = name;
    }

    public int getSize(){
        return size;
    }
    public String getName(){
        return name;
    }

    @Override
    public void addPlaylist(Playlist playlist) {
        collection.add(playlist);
        size++;
    }

    public Playlist getPlaylist(String name){
        for (int i = 0; i < collection.size(); i++) {
            Playlist playlist = collection.get(i);
            if (playlist.getName().equals(name)) {
                return playlist;
            }
        }
        throw new NoSuchElementException("Playlist Not Found.");
    }
    public Playlist getPlaylistIndex(int toFind) {
        if (toFind < 0 || toFind >= collection.size()) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    
        return collection.get(toFind);
    }

    @Override
    public String playlistNamesAndDurations() {
        if (size == 0){
            throw new NoSuchElementException("Collection is empty");
        }
        StringBuilder playlistNamesAndDurationString = new StringBuilder();
        int duration = 0;
        for (int i=0; i<size; i++){
            Playlist thisPlaylist = collection.get(i);
            String currentPlaylistName = thisPlaylist.getName();
            int currentPlaylistDuration = thisPlaylist.duration();
            playlistNamesAndDurationString.append(currentPlaylistName + " " + "Duration:" + " " + currentPlaylistDuration + " " + "minutes").append("\n");
            duration += thisPlaylist.duration();
        }
        return playlistNamesAndDurationString.toString() + "Total Duration:" + " " + duration + " " + "minutes.";
    }

    @Override
    public String playlistInfo(String playlistName) {
        if (size == 0){
            throw new NoSuchElementException("Collection is empty");
        }

        
        Playlist playlistToFind = null;
        boolean found = false;
        for (int i=0; i<size && found == false; i++){
            Playlist playlist = collection.get(i);
            if (playlist.getName().equals(playlistName)){
                playlistToFind = playlist;
                found = true;
            }
        }
        if (playlistToFind == null){
            throw new NoSuchElementException("Playlist not found");
        }
        StringBuilder playlistInfo = new StringBuilder();
        int numOfSongs = playlistToFind.getSize();
        int playlistDuration = playlistToFind.duration();
        String mostPlayedArtist = playlistToFind.mostPopularArtist();
        playlistInfo.append("This playlist has " + numOfSongs + " songs with a duration of " + playlistDuration + " minutes. The most popular artist in this playlist is " + mostPlayedArtist);
        return playlistInfo.toString();
    }

    @Override
    public void removePlaylist(String playlistName) {
        if (size == 0){
            throw new NoSuchElementException("Collection is empty");
        }
        boolean removed = false;
        for (int i = 0; i < collection.size(); i++) {
            Playlist playlist = collection.get(i);
            if (playlist.getName().equals(playlistName)) {
                collection.remove(i);
                removed = true;
                break;
            }
        }
        if (!removed) {
            throw new NoSuchElementException("Playlist not found");
        }
        size--;

    }

    @Override
    public void addEmptyPlaylist(String name) {
        Playlist emptyPlaylist = new LinkedNodePlaylist(name);
        collection.add(emptyPlaylist);
        size++;
    }

    @Override
    public void newRandomPlaylist(int duration, String name) throws IOException {
        Playlist randomPlaylist = new LinkedNodePlaylist(name);
        int totalDuration = 0;

        List<Song> allSongs = SongDataIO.buildSongListFromCsv("src/test/resources/Localify_100k_Tracks.csv");
        Collections.shuffle(allSongs);

        for (Song randomSong : allSongs) {
            if (totalDuration + randomSong.getDuration() <= duration) {
                randomPlaylist.add(randomSong);
                totalDuration += randomSong.getDuration();
            }
            if (totalDuration + randomSong.getDuration() >= duration) {
                break;
            }
        }

        if (randomPlaylist.getSize() == 0) {
            throw new IllegalArgumentException("Cannot create a random playlist with the given duration");
        }

        addPlaylist(randomPlaylist);
    }

    @Override
    public void removeAllInstances(Song song) {
        for (Playlist playlist : collection) {
            playlist.remove(song);
        }
    }

    public ArrayList<String> getSongsAndArtists(){
        ArrayList<String> allSongsString = new ArrayList<>();
        for (Playlist playlist : collection) {
            ArrayList<String> thisPlaylist = playlist.allSongsAndArtists();
            for (String song : thisPlaylist){
                allSongsString.add(song);
            }
            
        }
        return allSongsString;
    }
    
}
