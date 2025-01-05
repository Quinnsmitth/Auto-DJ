package edu.ithaca.dragon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

import edu.ithaca.dragon.autodj.Song;

public class ArrayPlaylist implements Playlist {

    private ArrayList<Song> playlist;
    private int size;
    private String name;

    public ArrayPlaylist(int capacity, String name){
        this.name = name;
        this.playlist = new ArrayList<>();
        this.size = 0;
    }
    public String getName(){
        return name;
    }
    public int getSize(){
        return size;
    }
    // QUINN
    @Override
    public String allSongs() {

        StringBuilder allSongsString = new StringBuilder();

        for (int songIndex = 0; songIndex < this.size; songIndex++) {
            Song currentSong = playlist.get(songIndex);
            allSongsString.append(currentSong.getTitle()).append("\n");
        }
        return allSongsString.toString();
        
    }
    // QUINN
    @Override
    public int duration() {
        int totalDuration = 0;
        for (Song song: playlist){
            int songLength = song.getDuration();
            totalDuration += songLength;
        }
        return totalDuration/60000;
    }
    // QUINN
    @Override
    public Song playNext() {
        if (size == 0) {
            throw new NoSuchElementException("No Songs available");
        }

        Song nextSong = playlist.get(0);
        playlist.remove(0);
        size--;
        
        return nextSong;
    }
    //JJ
    @Override
    public boolean isEmpty() {
        if (size == 0){
            return true;
        }
        else{
            return false;
        }
    }
    //quinn was here
    //JJ
    @Override
    public void add(Song song) {
        playlist.add(this.size, song);
        size++;
    }
    //JJ
    @Override
    public void remove(Song song) {
        if (song == null) {
            throw new IllegalArgumentException("Song cannot be null");
        }
        boolean removed = playlist.remove(song); 

        if (removed) {
            size--; 
        } 
        else {
            throw new IllegalArgumentException("Song not found in the playlist");
        }
    }

    public boolean contains(Song song) {
        return playlist.contains(song);
    }

    public String mostPopularArtist() {
        HashMap<String, Integer> artistCount = new HashMap<>();
        String mostPopularArtist = null;
        int maxCount = 0;

        for (Song song : playlist) {
            String artist = song.getArtist();
            artistCount.put(artist, artistCount.getOrDefault(artist, 0) + 1);

            if (artistCount.get(artist) > maxCount) {
                maxCount = artistCount.get(artist);
                mostPopularArtist = artist;
            }
        }
        if (mostPopularArtist == null){
            throw new NoSuchElementException("No artist found");
        }
        return mostPopularArtist;
    }

    public Song getSongAtIndex(int index) {
        if (index < 0 || index >= playlist.size()) {
            throw new IndexOutOfBoundsException("Index is out of range: " + index);
        }
        return playlist.get(index);
    }

    public ArrayList<String> allSongsAndArtists() {
        ArrayList<String> allSongsString = new ArrayList<>();
    
        for (int songIndex = 0; songIndex < this.size; songIndex++) {
            Song currentSong = playlist.get(songIndex);
            allSongsString.add(currentSong.getArtist() + " - " + currentSong.getTitle());
        }
        return allSongsString;
    }
    
    
}
