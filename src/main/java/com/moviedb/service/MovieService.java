package com.moviedb.service;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.moviedb.movies.Movie;
import com.moviedb.movies.MovieCollection;



@Path("/movies/")
public class MovieService {

	@GET
	@Path("/size/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMoviesSize(){
		return "{ \"size\": \"" + MovieCollection.movies.size() + "\"}";
	}
		
	@GET
	@Path("/year/from={from}&to={to}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String getFromYear(@PathParam("from") String from, @PathParam("to") String to){
		ArrayList<Movie> movies = new ArrayList<>();
		for(Movie movie:MovieCollection.movies){
			if(movie.getYear() >= Integer.parseInt(from) && movie.getYear() <= Integer.parseInt(to)){
				movies.add(movie);
			}
		}
		
		return movies.toString();
	}

	@GET
	@Path("/rating/from={from}&to={to}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String getFromRating(@PathParam("from") String from, @PathParam("to") String to){
		ArrayList<Movie> movies = new ArrayList<>();
		for(Movie movie:MovieCollection.movies){
			if(movie.getScore() >= Double.parseDouble(from) && movie.getScore() <= Double.parseDouble(from)){
				movies.add(movie);
			}
		}
		
		return movies.toString();
	}
		
	@GET
	@Path("/keyword/{keywords}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String getFromKeywords(@PathParam("keywords") String keywords){
		ArrayList<Movie> movies = new ArrayList<>();
		for(Movie movie:MovieCollection.movies){
			String summary = movie.getDirector().toLowerCase() + " " + movie.getTitle().toLowerCase() + " " + String.join(" ", movie.getActors()).toLowerCase();
			for(String k: keywords.split(",")){
				if(summary.contains(k.toLowerCase())){
					movies.add(movie);
				}
			}
		}
		
		return movies.toString();
	}
}
