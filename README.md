# Movie Database Webapp with Tomcat 7 under the hood
----

portability dependencies from PaaSport FP7 project added in .pom file... to run without portability just remove these dependencies

## Usage
1. Generate MovieDatabase.war file.
2. Deploy MovieDatabase.war file in /var/lib/tomcat7/webapps.
3. Make sure you have the right permissions.
4. Execute $ sudo service tomcat7 restart.
5. The webapp should be accessible from http://localhost:8080/MovieDatabase/

----
## API

| Path                                                                                           	| Method 	| Response                                                        	|
|------------------------------------------------------------------------------------------------	|--------	|-----------------------------------------------------------------	|
| /rest/utils/content-ratings/                                                                   	| GET    	| Returns all the unique values for Content Ratings e.g. PG-13    	|
| /rest/utils/languages/                                                                         	| GET    	| Returns all the unique values for Languages e.g. English        	|
| /rest/utils/countries/                                                                         	| GET    	| Returns all the unique values for Countries e.g. United Kingdom 	|
| /rest/utils/years/                                                                             	| GET    	| Returns all the unique values for Years e.g. 2015               	|
| /rest/movies/size/                                                                             	| GET    	| Returns the total size of movies in our database.               	|
| /rest/movies/filter?yearfrom=&yearto=&ratingfrom=&ratingto=&keywords=&content_rating=&language 	| GET    	| Returns the movies based on the results of the queries.         	|
| /rest/movies/rating/from=&to=                                                                  	| GET    	| Returns the movies between the given ratings.                   	|
| /rest/movies/keywords/key1,key2                                                                	| GET    	| Returns the movies containing the given keywords.               	|
| /rest/movies/content-rating/pg-13                                                              	| GET    	| Returns the movies that are of the given Content-Rating.        	| 
