package com.example.ashishdandriyal.androidtvapptutorial;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.ashishdandriyal.androidtvapptutorial.rest.DataWrapper;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;

public class Movie implements Parcelable {
    private static final String TAG = Movie.class.getSimpleName();

    static final long serialVersionUID = 727566175075960653L;
    private long id;
    private String title;
    private String studio;
    private String cardImageUrl;
    private String description;
    private String videoUrl;
    private String category;
    private String backgroundImageUrl;
    private String manifest;
    private String licenseUrl;

    public Movie() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getCardImageUrl() {
        return cardImageUrl;
    }

    public void setCardImageUrl(String cardImageUrl) {
        this.cardImageUrl = cardImageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() { return videoUrl; }

    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBackgroundImageUrl() {
        return backgroundImageUrl;
    }

    public void setBackgroundImageUrl(String backgroundImageUrl) {
        this.backgroundImageUrl = backgroundImageUrl;
    }

    public String getManifest() {
        return manifest;
    }

    public void setManifest(String manifest) {
        this.manifest = manifest;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public URI getCardImageURI() {
        try {
            return new URI(getCardImageUrl());
        } catch (URISyntaxException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", studio='" + studio + '\'' +
                ", cardImageUrl='" + cardImageUrl + '\'' +
                ", description='" + description + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", licenseUrl='" + licenseUrl + '\'' +
                ", manifest='" + manifest + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeString(this.title);
        parcel.writeString(this.cardImageUrl);
        parcel.writeString(this.description);
        parcel.writeString(this.studio);
        parcel.writeString(this.videoUrl);
        parcel.writeString(this.manifest);
        parcel.writeString(this.licenseUrl);
    }

    protected Movie(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        this.cardImageUrl = in.readString();
        this.description = in.readString();
        this.studio = in.readString();
        this.videoUrl = in.readString();
        this.manifest = in.readString();
        this.licenseUrl = in.readString();
    }

    public Movie(DataWrapper.Live live){
        this.id = live.getLiveId();
        this.title = live.getTitle();
        this.studio = live.getStudio();
        this.cardImageUrl = live.getImageUrl();
        this.description = live.getDescription();
        this.videoUrl = "";
        this.category = "";
        this.backgroundImageUrl = live.getImageUrl();
        this.manifest = live.getSourceUrl();
        this.licenseUrl = live.getLicenseUrl();
    }

    public Movie(DataWrapper.Vod vod){
        this.id = vod.getVodId();
        this.title = vod.getTitle();
        this.studio = vod.getStudio();
        this.cardImageUrl = vod.getImageUrl();
        this.description = vod.getDescription();
        this.videoUrl = "";
        this.category = "";
        this.backgroundImageUrl = vod.getImageUrl();
        this.manifest = vod.getSourceUrl();
        this.licenseUrl = vod.getLicenseUrl();
    }

    public Movie(DataWrapper.Epg epg){
        this.id = epg.getEpgId();
        this.title = epg.getTitle();
        this.studio = epg.getStudio();
        this.cardImageUrl = epg.getImageUrl();
        this.description = epg.getDescription();
        this.videoUrl = "";
        this.category = "";
        this.backgroundImageUrl = epg.getImageUrl();
        this.manifest = epg.getSourceUrl();
        this.licenseUrl = epg.getLicenseUrl();
        //TODO filtering on the basis of service id -> epg.getServiceId();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
