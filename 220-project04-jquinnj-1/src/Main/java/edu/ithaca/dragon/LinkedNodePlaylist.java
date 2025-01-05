package edu.ithaca.dragon;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

import edu.ithaca.dragon.autodj.Song;
import edu.ithaca.dragon.io.LinkedNode;

//Author: JJ Purpose: Implementation of Playlist using Linked Nodes to store songs in a playist

public class LinkedNodePlaylist implements Playlist {

    private LinkedNode<Song> front;
    private String name;
    private int size;

    public LinkedNodePlaylist(String name){
        this.front = null;
        this.name = name;
        this.size = 0;
    }

    public String getName(){
        return name;
    }

    public int getSize(){
        return size;
    }
    
    @Override
    public String allSongs() {

        StringBuilder allSongsString = new StringBuilder();

        LinkedNode<Song> current = front;

        while (current != null) {
            Song currentSong = current.getItem();
            allSongsString.append(currentSong.getTitle()).append("\n");
            current = current.getNext();
        }
        return allSongsString.toString();
    }

    @Override
    public int duration() {
        int totalDuration = 0;

        LinkedNode<Song> current = front;
        while (current != null){
            int songLength = current.getItem().getDuration();
            totalDuration += songLength;
            current = current.getNext();
        }
        return totalDuration/60000;
    }

    @Override
    public Song playNext() {
        LinkedNode<Song> current = front;
        if (current == null){
            throw new NoSuchElementException("No Songs available");
        }
        front = front.getNext();
        size--;
        return current.getItem();
    }

    @Override
    public boolean isEmpty() {
        if (front == null){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void add(Song song) {
        
        if (front == null){
            front = new LinkedNode<Song>(song);
        }
        
        else{
            LinkedNode<Song> current = front;

            while (current.getNext() != null){
                current = current.getNext();
            }
            current.setNext(new LinkedNode<Song>(song));
        }
        size ++;
    }

    @Override
    public void remove(Song song) {
        if (front == null) {
            throw new IllegalArgumentException("Playlist is empty");
        }
    
        if (front.getItem().equals(song)) {
            front = front.getNext();
            size--;
            return;
        }
        
        LinkedNode<Song> current = front;
        boolean found = false;
        while (current.getNext() != null){
            LinkedNode<Song> nextNode = current.getNext();
            if (nextNode.getItem().equals(song) && found == false) {
                current.setNext(nextNode.getNext()); 
                found = true;
                size--;
            }
            current = nextNode;
        }
    }
    

    @Override
    public boolean contains(Song song) {
        LinkedNode<Song> current = front;

        while (current != null){
            if (current.getItem() == song){
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public String mostPopularArtist() {
        if (front == null) {
            throw new NoSuchElementException("No artist found");
        }

        HashMap<String, Integer> artistCount = new HashMap<>();
        String mostPopularArtist = null;
        int maxCount = 0;

        LinkedNode<Song> current = front;
        while (current != null) {
            String artist = current.getItem().getArtist();
            artistCount.put(artist, artistCount.getOrDefault(artist, 0) + 1);

            if (artistCount.get(artist) > maxCount) {
                maxCount = artistCount.get(artist);
                mostPopularArtist = artist;
            }
            current = current.getNext();
        }

        return mostPopularArtist;
    }

    @Override
    public ArrayList<String> allSongsAndArtists() {
        ArrayList<String> allSongsString = new ArrayList<>();
        LinkedNode<Song> current = front;
        while (current != null) {
            Song currentSong = current.getItem();
            allSongsString.add(currentSong.getArtist() + " - " + currentSong.getTitle());
        }
        return allSongsString;
    }
    
}
