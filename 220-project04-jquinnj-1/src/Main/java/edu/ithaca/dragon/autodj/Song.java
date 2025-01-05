package edu.ithaca.dragon.autodj;

public class Song {
    private String artist;
    private String title;
    private int duration; 
    private int playCount;
    private int spotifyPopularity;
    private double spotifyDanceability;
    private double spotifyEnergy;
    private double spotifyValence;
    
    
    public Song(){}

    public Song(String artist, String title, int duration, int playCount, int spotifyPopularity, double spotifyDanceability,
            double spotifyEnergy, double spotifyValence) {
        this.artist = artist;
        this.title = title;
        this.duration = duration;
        this.playCount = playCount;
        
        this.spotifyPopularity = spotifyPopularity;
        this.spotifyDanceability = spotifyDanceability;
        this.spotifyEnergy = spotifyEnergy;
        this.spotifyValence = spotifyValence;
    }

    @Override
    public String toString(){
        return title + " by " + artist + ": " + duration;
    }

    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public int getPlayCount() {
        return playCount;
    }
    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }
    public int getSpotifyPopularity() {
        return spotifyPopularity;
    }
    public void setSpotifyPopularity(int spotifyPopularity) {
        this.spotifyPopularity = spotifyPopularity;
    }
    public double getSpotifyDanceability() {
        return spotifyDanceability;
    }
    public void setSpotifyDanceability(double spotifyDanceability) {
        this.spotifyDanceability = spotifyDanceability;
    }
    public double getSpotifyEnergy() {
        return spotifyEnergy;
    }
    public void setSpotifyEnergy(double spotifyEnergy) {
        this.spotifyEnergy = spotifyEnergy;
    }
    public double getSpotifyValence() {
        return spotifyValence;
    }
    public void setSpotifyValence(double spotifyValence) {
        this.spotifyValence = spotifyValence;
    }
}
