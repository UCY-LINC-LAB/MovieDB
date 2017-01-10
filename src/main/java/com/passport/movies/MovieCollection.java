package com.passport.movies;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class MovieCollection {

	public static HashSet<Movie> movies = new HashSet<Movie>();
	
	public static void initialize(String csvpath) throws IOException{
		final Reader in = new FileReader(csvpath);
		final CSVParser parser = new CSVParser(in, CSVFormat.EXCEL.withHeader());
				
		for (CSVRecord record : parser) {
		    String director = record.get("director_name");
		    Integer duration = 0;
		    if(!record.get("duration").isEmpty()){
		    	duration = Integer.parseInt(record.get("duration"));
		    }
		    String[] actors = {record.get("actor_1_name"), record.get("actor_2_name"), record.get("actor_3_name")}; 
		    String[] genres = record.get("genres").split("\\|");	
		    String title = record.get("movie_title").substring(0, record.get("movie_title").length() - 2);
		    String[] keywords =  record.get("plot_keywords").split("\\|");
		    String imdb =  record.get("movie_imdb_link");
		    String language =  record.get("language");
		    String country =  record.get("country");
		    String content_rating =  record.get("content_rating");
		    Integer year = 0;
		    if(!record.get("title_year").isEmpty()){
		    	year = Integer.parseInt(record.get("title_year"));
		    }
		    Double score =  Double.parseDouble(record.get("imdb_score"));
		    
		    Movie movie = new Movie(director, duration, actors, year, genres, title, keywords, imdb, language, country, content_rating, score);
		    MovieCollection.movies.add(movie);
		}
		
		parser.close();
	}
	
	public static void main(String[] args) {
		try {
			ClassLoader classLoader = MovieCollection.class.getClassLoader();
			MovieCollection.initialize(classLoader.getResource("movie_metadata.csv").getPath());
			System.out.println(MovieCollection.movies.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
