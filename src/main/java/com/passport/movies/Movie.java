package com.passport.movies;

public class Movie {

	private String director;
	private Integer duration;
	private String[] actors;
	private String[] genres;
	private String title;
	private String[] keywords;
	private String imdb;
	private String language;
	private String country;
	private String content_rating;
	private Double score;
	private Integer year;

	public Movie(String director, Integer duration, String[] actors, Integer year, String[] genres, String title,
			String[] keywords, String imdb, String language, String country, String content_rating, Double score) {
		super();
		this.director = director;
		this.duration = duration;
		this.actors = actors;
		this.genres = genres;
		this.title = title;
		this.keywords = keywords;
		this.imdb = imdb;
		this.language = language;
		this.country = country;
		this.content_rating = content_rating;
		this.score = score;
		this.year = year;
	}

	@Override
	public String toString(){
				
		return "{\"director\": " + "\"" + director + "\"" +
			   ",\"duration\": " + "\"" + duration + "\"" +
			   ",\"actors\": " + "\"" + String.join(", ", actors) + "\"" +
			   ",\"genres\": " + "\"" + String.join(", ", genres) + "\"" +
			   ",\"title\": " + "\"" + title + "\"" +
			   ",\"keywords\": " + "\"" + String.join(", ", keywords) + "\"" +
			   ",\"imdb\": " + "\"" + imdb + "\"" +
			   ",\"language\": " + "\"" + language + "\"" +				
			   ",\"country\": " + "\"" + country + "\"" +
			   ",\"content_rating\": " + "\"" + content_rating + "\"" +
			   ",\"score\": " + "\"" + score + "\"" +
			   ",\"year\": " + "\"" + year + "\"}";		
	}
	
	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String[] getActors() {
		return actors;
	}

	public void setActors(String[] actors) {
		this.actors = actors;
	}

	public String[] getGenres() {
		return genres;
	}

	public void setGenres(String[] genres) {
		this.genres = genres;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getKeywords() {
		return keywords;
	}

	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}

	public String getImdb() {
		return imdb;
	}

	public void setImdb(String imdb) {
		this.imdb = imdb;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContent_rating() {
		return content_rating;
	}

	public void setContent_rating(String content_rating) {
		this.content_rating = content_rating;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

}
