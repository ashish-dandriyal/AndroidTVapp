package com.example.ashishdandriyal.androidtvapptutorial.rest;

import android.provider.ContactsContract;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataWrapper {

    public static List<? extends DataWrapper> fromJson(String json, Class<?> claz) {
        if (claz.isAssignableFrom(Live.class)) {
            Type colType = new TypeToken<List<DataWrapper.Live>>() {}.getType();
            return new Gson().fromJson(json, colType);
        } else if (claz.isAssignableFrom(Vod.class)) {
            Type colType = new TypeToken<List<DataWrapper.Vod>>() {}.getType();
            return new Gson().fromJson(json, colType);
        } else if (claz.isAssignableFrom(Epg.class)) {
            Type colType = new TypeToken<List<DataWrapper.Epg>>() {}.getType();
            return new Gson().fromJson(json, colType);
        }
        return null;
    }

    public class Live extends DataWrapper {
        int liveId;
        String title;
        String sourceUrl;
        String licenseUrl;
        String imageUrl;
        String studio;
        String description;

        public String toString (){
            return "LiveID ["+this.liveId+"] Title ["+this.title+"] Source ["+sourceUrl+"] License ["+licenseUrl+"] Image ["+imageUrl+"]";
        }

        public int getLiveId() {
            return liveId;
        }

        public void setLiveId(int liveId) {
            this.liveId = liveId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSourceUrl() {
            return sourceUrl;
        }

        public void setSourceUrl(String sourceUrl) {
            this.sourceUrl = sourceUrl;
        }

        public String getLicenseUrl() {
            return licenseUrl;
        }

        public void setLicenseUrl(String licenseUrl) {
            this.licenseUrl = licenseUrl;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
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
    }

    public class Vod extends DataWrapper {
        int vodId;
        String title;
        String sourceUrl;
        String licenseUrl;
        String imageUrl;
        String studio;
        String description;

        public String toString (){
            return "VodID ["+this.vodId+"] Title ["+this.title+"] Source ["+sourceUrl+"] License ["+licenseUrl+"] Image ["+imageUrl+"]";
        }

        public int getVodId() {
            return vodId;
        }

        public void setVodId(int vodId) {
            this.vodId = vodId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSourceUrl() {
            return sourceUrl;
        }

        public void setSourceUrl(String sourceUrl) {
            this.sourceUrl = sourceUrl;
        }

        public String getLicenseUrl() {
            return licenseUrl;
        }

        public void setLicenseUrl(String licenseUrl) {
            this.licenseUrl = licenseUrl;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
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
    }

    public class Epg extends DataWrapper {
        int epgId;
        int serviceId;
        String title;
        String sourceUrl;
        String licenseUrl;
        String imageUrl;
        String studio;
        String description;

        public int getEpgId() {
            return epgId;
        }

        public void setEpgId(int epgId) {
            this.epgId = epgId;
        }

        public int getServiceId() {
            return serviceId;
        }

        public void setServiceId(int serviceId) {
            this.serviceId = serviceId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSourceUrl() {
            return sourceUrl;
        }

        public void setSourceUrl(String sourceUrl) {
            this.sourceUrl = sourceUrl;
        }

        public String getLicenseUrl() {
            return licenseUrl;
        }

        public void setLicenseUrl(String licenseUrl) {
            this.licenseUrl = licenseUrl;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
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
    }
}
