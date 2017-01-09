package com.moviedb.service;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.moviedb.movies.MovieCollection;

public class ServerInitialization implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		ClassLoader classLoader = MovieCollection.class.getClassLoader();
		try {
			MovieCollection.initialize(classLoader.getResource("movie_metadata.csv").getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}