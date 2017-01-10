data = [
	{
		"make": "Gibson", // Name
		"model": "Les Paul", // Rating
		"type": "Electric", // Year
		"price": "$3,000", // Lang
		"content_rating": "P12",
		"country": "USA",
	}
];

var products = "";
var makes = ""; // Name
var models = ""; // Rating
var types = ""; // Year
var content_rating = "";
var languages= "";
var countries = "";


$.ajax({
  url: "http://localhost:8080/MovieDatabase/rest/utils/years/",
  cache: false,
  success: function(data){
// data.start_date
// data.end_date
// data.year
// data.content_rating
// data.country
	  data.years.sort(function(a,b){
		  return b-a;
	  });
  	for (var i = 0; i < data.years.length; i++) {
  		var type = data.years[i];
  		if (types.indexOf("<option value='"+type+"'>"+type+"</option>") == -1) {
			types += "<option value='"+type+"'>"+type+"</option>";
		}
  	}
// for (var i = 0; i < data.length; i++) {
// var make = data[i].make,
// model = data[i].model,
// type = data[i].type,
// price = data[i].price,, // Lang
// content_rating = data[i].content_rating;
// country = data[i].country;
//
// //create product cards
// products += "<div class='col-sm-4 product' data-make='"+make+"'
// data-model='"+model+"' data-type='"+type+"' data-price='"+rawPrice+"'><div
// class='product-inner text-center'>Name: "+make +"<br />Rating: "+model+"<br
// />Year: "+type+"<br />Language: "+price+"<br />Content Rating:
// "+content_rating+"<br />Country: "+country+"</div></div>";
//
// //create dropdown of Year
// if (types.indexOf("<option value='"+type+"'>"+type+"</option>") == -1) {
// types += "<option value='"+type+"'>"+type+"</option>";
// }
//
// //create dropdown of content_rating
// if (types.indexOf("<option
// value='"+content_rating+"'>"+content_rating+"</option>") == -1) {
// types += "<option value='"+content_rating+"'>"+content_rating+"</option>";
// }
//
// //create dropdown of country
// if (types.indexOf("<option value='"+country+"'>"+country+"</option>") == -1)
// {
// types += "<option value='"+country+"'>"+country+"</option>";
// }
// }
//
// $("#products").html(products);
// $(".filter-make").append(makes);
// $(".filter-model").append(models);
  	$(".filter-start-date").append(types);

  	$(".filter-end-date").append(types);
  }
});

$.ajax({
  url: "http://localhost:8080/MovieDatabase/rest/utils/languages/",
  cache: false,
  success: function(data){
	data.languages.sort();
  	for (var i = 0; i < data.languages.length; i++) {
  		var type = data.languages[i];
  		if (languages.indexOf("<option value='"+type+"'>"+type+"</option>") == -1) {
  			languages += "<option value='"+type+"'>"+type+"</option>";
		}
  	}

  	$(".filter-language").append(languages);
  }
});


$.ajax({
  url: "http://localhost:8080/MovieDatabase/rest/utils/content-ratings/",
  cache: false,
  success: function(data){
	data.content_ratings.sort();
  	for (var i = 0; i < data.content_ratings.length; i++) {
  		var type = data.content_ratings[i];
  		if (content_rating.indexOf("<option value='"+type+"'>"+type+"</option>") == -1) {
  			content_rating += "<option value='"+type+"'>"+type+"</option>";
		}
  	}

  	$(".filter-content").append(content_rating);
  }
});

$.ajax({
	  url: "http://localhost:8080/MovieDatabase/rest/utils/countries/",
	  cache: false,
	  success: function(data){
		data.countries.sort();
	  	for (var i = 0; i < data.countries.length; i++) {
	  		var type = data.countries[i];
	  		if (countries.indexOf("<option value='"+type+"'>"+type+"</option>") == -1) {
	  			countries += "<option value='"+type+"'>"+type+"</option>";
			}
	  	}

	  	$(".filter-country").append(countries);
	  }
	});

var filtersObject = {};

// on filter change
$(".filter").on("change",function() {
	var filterName = $(this).data("filter"),
		filterVal = $(this).val();

	if (filterVal == "") {
		delete filtersObject[filterName];
	} else {
		filtersObject[filterName] = filterVal;
	}

	var filters = "";

	for (var key in filtersObject) {
	  	if (filtersObject.hasOwnProperty(key)) {
			filters += "[data-"+key+"='"+filtersObject[key]+"']";
	 	 }
	}

	if (filters == "") {
		$(".product").show();
	} else {
		$(".product").hide();
		$(".product").hide().filter(filters).show();
	}
});

$( document ).ajaxStart(function() {
  $( ".spinner" ).removeClass("hidden");
});
$( document ).ajaxComplete(function() {
  $( ".spinner" ).addClass("hidden");
});

$( document ).ready(function() {
 $.ajax({
 type: 'GET',
 url: "http://localhost:8080/MovieDatabase/rest/movies/list",
 success: function(data){
 for (var i = 0; i < data.length; i++) {
	 $('ul.list-group').append('<li class="list-group-item"><span class="movie-title">'
			   + data[i].title +'</span><span class="label label-primary">'
			   + data[i].year + '</span><span class="label label-success">'
			   + data[i].score + '</span><span class="label label-info">'
			   + data[i].language + '</span><span class="label label-warning">'
			   + data[i].genres + '</span><a class="imdb-link" href="'
			   + data[i].imdb +'"><img class="imdb-logo" src="imdb.png"/></a></li>');

 }}
 });});

// on search form submit
$("#search-form").submit(function(e) {
	$('ul.list-group').html("");
	e.preventDefault();
	query = $("#search-form input").val().toLowerCase();

	min_rating = $(".ui-slider-handle:eq(0)").text();
	max_rating = $(".ui-slider-handle:eq(1)").text();

	start_date = $(".filter-start-date").val();
	end_date = $(".filter-end-date").val();

	lang = $(".filter-language").val();

	content_rating = $(".filter-content-rating").val();

	country = $(".filter-country").val();

	 $.ajax({
	   type: 'GET',
	   url: "http://localhost:8080/MovieDatabase/rest/movies/filter?yearfrom=" + start_date +"&yearto=" + end_date + "&ratingfrom=" + min_rating + "&ratingto=" + max_rating + "&keywords=" + query + "&content_rating=" + content_rating + "&language=" + lang,
	   success: function(data){
		   for (var i = 0; i < data.length; i++) {
			   $('ul.list-group').append('<li class="list-group-item"><span class="movie-title">'
					   + data[i].title +'</span><span class="label label-primary">'
					   + data[i].year + '</span><span class="label label-success">'
					   + data[i].score + '</span><span class="label label-info">'
					   + data[i].language + '</span><span class="label label-warning">'
					   + data[i].genres + '</span><a class="imdb-link" href="'
					   + data[i].imdb +'"><img class="imdb-logo" src="imdb.png"/></a></li>');
		  	}

	   },
	 });
});


$('#slider').slider({
	range: true,
	min: 0,
	max: 10,
	values: [0, 10],
	slide: function(event, ui) {
		$('.ui-slider-handle:eq(0) .price-range-min').html('' + ui.values[ 0 ]);
		$('.ui-slider-handle:eq(1) .price-range-max').html('' + ui.values[ 1 ]);
		$('.price-range-both').html('<i>' + ui.values[ 0 ] + '  </i>' + ui.values[ 1 ] );

		$('.price-range-min, .price-range-max').css('opacity', '1');
		$('.price-range-both').css('display', 'none');
	},
    create: function(event, ui){
    	$('.ui-slider-handle:eq(0)').append('<span class="price-range-min value">' + 0 + '</span>');
		$('.ui-slider-handle:eq(1)').append('<span class="price-range-max value">' + 10 + '</span>');
    }
});
