package com.trip97.modules.attraction.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;

@Getter
@JsonSerialize
public class Attraction {

    public int id;
    public String title;
    public String overview;
    public String address;
	public String firstImage;
    public double latitude;
    public double longitude;
    public int contentTypeId;
    public int sidoCode;
    public int gugunCode;
    public double rating;
    public int reviewCount;

    public Attraction(String title, String address, double latitude, double longitude) {
		super();
		this.title = title;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Attraction(int id, String title, String address, double latitude, double longitude, int contentTypeId, int sidoCode, int gugunCode) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.contentTypeId = contentTypeId;
        this.sidoCode = sidoCode;
        this.gugunCode = gugunCode;
    }

	public Attraction(int id, String title, String overview, String address,String firstImage, double latitude, double longitude,
			int contentTypeId, int sidoCode, int gugunCode, double rating, int reviewCount) {
		super();
		this.id = id;
		this.title = title;
		this.overview = overview;
		this.address = address;
		this.firstImage=firstImage;
		this.latitude = latitude;
		this.longitude = longitude;
		this.contentTypeId = contentTypeId;
		this.sidoCode = sidoCode;
		this.gugunCode = gugunCode;
		this.rating = rating;
		this.reviewCount = reviewCount;
	}
	
	
}
