package com.passport.service;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.passport.movies.Movie;
import com.passport.movies.MovieCollection;

@Path("/movies/")
public class MovieService {

	@GET
	@Path("/size/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMoviesSize() {
		return "{ \"size\": \"" + MovieCollection.movies.size() + "\"}";
	}

	@GET
	@Path("/filter/")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String getFiltered(@QueryParam("yearfrom") String yearfrom, @QueryParam("yearto") String yearto,
			@QueryParam("ratingfrom") String ratingfrom, @QueryParam("ratingto") String ratingto,
			@QueryParam("keywords") String keywords, @QueryParam("yearto") String content_rating,
			@QueryParam("language") String language) {
		String returnValue = "";
		try {

			ArrayList<Movie> movies = new ArrayList<Movie>();
			for (Movie movie : MovieCollection.movies) {
				String summary = movie.getDirector().toLowerCase() + " " + movie.getTitle().toLowerCase() + " "
						+ String.join(" ", movie.getActors()).toLowerCase();

				boolean containsKeywords = false;
				boolean containsContentRating = false;
				boolean containsLanguage = false;

				if (keywords.isEmpty()) {
					containsKeywords = true;
				} else {
					for (String k : keywords.split(",")) {
						if (summary.contains(k.toLowerCase())) {
							containsKeywords = true;
							break;
						}
					}
				}

				if (language.isEmpty()) {
					containsLanguage = true;
				} else {
					if (movie.getLanguage().equals(language)) {
						containsLanguage = true;
					}
				}

				if (content_rating.isEmpty()) {
					containsContentRating = true;
				} else {
					if (content_rating.contains(movie.getContent_rating())) {
						containsContentRating = true;
					}
				}

				if (yearfrom.isEmpty()) {
					yearfrom = "0";
				}

				if (yearto.isEmpty()) {
					yearto = "2017";
				}

				if (ratingfrom.isEmpty()) {
					ratingfrom = "0";
				}

				if (ratingto.isEmpty()) {
					ratingto = "10";
				}

				if (content_rating.isEmpty()) {
					ratingto = "10";
				}

				if ((movie.getYear() >= Integer.parseInt(yearfrom)) && (movie.getYear() <= Integer.parseInt(yearto))
						&& (movie.getScore() >= Double.parseDouble(ratingfrom))
						&& (movie.getScore() <= Double.parseDouble(ratingto)) && containsKeywords
						&& containsContentRating
						&& containsLanguage) {
					movies.add(movie);
				}
			}

			returnValue = movies.toString();

		} catch (Exception e) {
			returnValue = "{\"status\": \"error\", \"message\": \"" + e.toString() + "\"}";
		}

		return returnValue;
	}

	@GET
	@Path("/year/from={from}&to={to}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String getFromYear(@PathParam("from") String from, @PathParam("to") String to) {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		for (Movie movie : MovieCollection.movies) {
			if (movie.getYear() >= Integer.parseInt(from) && movie.getYear() <= Integer.parseInt(to)) {
				movies.add(movie);
			}
		}

		return movies.toString();
	}

	@GET
	@Path("/list")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String getMoviesList() {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		for (Movie movie : MovieCollection.movies) {
			movies.add(movie);
		}

		return movies.toString();
	}

	@GET
	@Path("/rating/from={from}&to={to}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String getFromRating(@PathParam("from") String from, @PathParam("to") String to) {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		for (Movie movie : MovieCollection.movies) {
			if (movie.getScore() >= Double.parseDouble(from) && movie.getScore() <= Double.parseDouble(from)) {
				movies.add(movie);
			}
		}

		return movies.toString();
	}

	@GET
	@Path("/keyword/{keywords}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String getFromKeywords(@PathParam("keywords") String keywords) {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		for (Movie movie : MovieCollection.movies) {
			String summary = movie.getDirector().toLowerCase() + " " + movie.getTitle().toLowerCase() + " "
					+ String.join(" ", movie.getActors()).toLowerCase();
			for (String k : keywords.split(",")) {
				if (summary.contains(k.toLowerCase())) {
					movies.add(movie);
				}
			}
		}

		return movies.toString();
	}

	@GET
	@Path("/content-rating/{rating}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String getFromContentRating(@PathParam("rating") String rating) {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		for (Movie movie : MovieCollection.movies) {
			if (movie.getContent_rating().equals(rating)) {
				movies.add(movie);
			}
		}

		return movies.toString();
	}
}
