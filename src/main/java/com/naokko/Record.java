package com.naokko;

public class Record {
    private String name;
    private String artist;
    private int yearrelease;
    private boolean favorite;

    public Record(){}
    public Record(String name, String artist, int yearRelease, boolean favorite) {
        this.name = name;
        this.artist = artist;
        this.yearrelease = yearRelease;
        this.favorite = favorite;
    }
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getArtist() {return artist;}
    public void setArtist(String artist) {this.artist = artist;}
    public int getYearRelease() {return yearrelease;}
    public void setYearRelease(int yearRelease) {this.yearrelease = yearRelease;}
    public boolean isFavorite() {return favorite;}
    public void setFavorite(boolean favorite) {this.favorite = favorite;}

}
