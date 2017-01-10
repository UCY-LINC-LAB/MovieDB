package com.passport.service;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.passport.movies.Movie;
import com.passport.movies.MovieCollection;

@Path("/utils/")
public class UtilsService {

	@GET
	@Path("/content-ratings/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getContentRatings(){
		ArrayList<String> ratings = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		
		for(Movie movie:MovieCollection.movies){
			if(!ratings.contains(movie.getContent_rating()) && !movie.getContent_rating().isEmpty()){
				ratings.add(movie.getContent_rating());
				builder.append("\"" + movie.getContent_rating() + "\", ");
			}
		}
		
		return "{ \"content_ratings\": [" + builder.toString().substring(0, builder.toString().length() - 2) + " ]}";
	}
	
	@GET
	@Path("/languages/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getLanguages(){
		ArrayList<String> languages = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		
		for(Movie movie:MovieCollection.movies){
			if(!languages.contains(movie.getLanguage()) && !movie.getLanguage().isEmpty()){
				languages.add(movie.getLanguage());
				builder.append("\"" + movie.getLanguage() + "\", ");
			}
		}
		
		return "{ \"languages\": [" + builder.toString().substring(0, builder.toString().length() - 2) + " ]}";
	}
	
	@GET
	@Path("/countries/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCountries(){
		ArrayList<String> countries = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		
		for(Movie movie:MovieCollection.movies){
			if(!countries.contains(movie.getCountry()) && !movie.getCountry().isEmpty()){
				countries.add(movie.getCountry());
				builder.append("\"" + movie.getCountry() + "\", ");
			}
		}
		
		return "{ \"countries\": [" + builder.toString().substring(0, builder.toString().length() - 2) + " ]}";
	}
	
	@GET
	@Path("/years/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getYears(){
		ArrayList<Integer> years = new ArrayList<Integer>();
		StringBuilder builder = new StringBuilder();
		
		for(Movie movie:MovieCollection.movies){
			if(!years.contains(movie.getYear())){
				years.add(movie.getYear());
				builder.append("\"" + movie.getYear() + "\", ");
			}
		}
		
		return "{ \"years\": [" + builder.toString().substring(0, builder.toString().length() - 2) + " ]}";
	}
}
