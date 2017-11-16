package com.cinecrawler.myapp;

import java.util.HashMap;
import java.util.Map;

public class MovieSearchResultObject {

	private String id;
	private String movieName;
	private String imageUrl;
	private String year;
	private String runtime;
	private String synopsis;
	private String criticScore;
	private String audienceScore;
	Map<String, String> cast = new HashMap <String, String>();
	
	public MovieSearchResultObject(){
	}

	public MovieSearchResultObject(String id, String movieName, String imageUrl, String year, String runtime, String synopsis, String criticScore, String audienceScore, Map<String, String> cast){
		this.id = id;
		this.movieName = movieName;
		this.imageUrl = imageUrl;
		this.year = year;
		this.runtime = runtime;
		this.synopsis = synopsis;
		this.criticScore = criticScore;
		this.audienceScore = audienceScore;
		this.cast = cast;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	public String getCriticScore() {
		return criticScore;
	}

	public void setCriticScore(String criticScore) {
		this.criticScore = criticScore;
	}

	public String getAudienceScore() {
		return audienceScore;
	}

	public void setAudienceScore(String audienceScore) {
		this.audienceScore = audienceScore;
	}

	public Map<String, String> getCast() {
		return cast;
	}

	public void setCast(Map<String, String> cast) {
		this.cast = cast;
	}
	
	
	
	/*public String getId() {
		return id;
	}
	public void setId(Object object) {
		this.id = (String) object;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(Object object) {
		this.movieName = (String) object;
	}*/
	
}
