var products = "";
var makes = "";
var models = "";
var types = "";
var content_rating = "";
var languages = "";
var countries = "";

$('#myModal').on('show.bs.modal', function (e) {
	var imdb = $(e.relatedTarget).attr("data-imdb");
	var title = $(e.relatedTarget).attr("data-title");
	var year = $(e.relatedTarget).attr("data-year");
	var score = $(e.relatedTarget).attr("data-score");
	var duration = $(e.relatedTarget).attr("data-duration");
	var director = $(e.relatedTarget).attr("data-director");
	var actors = $(e.relatedTarget).attr("data-actors");
	var genres = $(e.relatedTarget).attr("data-genres");
	var content = $(e.relatedTarget).attr("data-content");
	var country = $(e.relatedTarget).attr("data-country");
	var language = $(e.relatedTarget).attr("data-language");

	$('#myModal .modal-title').html(title);
	$('#myModal .modal-body .movie-item .movie-info .year').html(year);
	$('#myModal .modal-body .movie-item .movie-info .director').html(director);
	$('#myModal .modal-body .movie-item .movie-info .score').html(score);
	$('#myModal .modal-body .movie-item .movie-info .duration').html(duration);
	$('#myModal .modal-body .movie-item .movie-info .actors').html(actors);
	$('#myModal .modal-body .movie-item .movie-info .genres').html(genres);
	$('#myModal .modal-body .movie-item .movie-info .content-rating').html(content);
	$('#myModal .modal-body .movie-item .movie-info .language').html(language);
	$('#myModal .modal-body .movie-item .movie-info .country').html(country);
	$('#myModal .modal-body .imdb-redirect').attr('href', imdb);
})

$.ajax({
	url : "/rest/utils/years/",
	cache : false,
	success : function(data) {
		data.years.sort(function(a, b) {
			return b - a;
		});
		for (var i = 0; i < data.years.length; i++) {
			var type = data.years[i];
			if (types.indexOf("<option value='" + type + "'>" + type
					+ "</option>") == -1) {
				types += "<option value='" + type + "'>" + type + "</option>";
			}
		}

		$(".filter-start-date").append(types);
		$(".filter-end-date").append(types);
	}
});

$.ajax({
	url : "/rest/utils/languages/",
	cache : false,
	success : function(data) {
		data.languages.sort();
		for (var i = 0; i < data.languages.length; i++) {
			var type = data.languages[i];
			if (languages.indexOf("<option value='" + type + "'>" + type
					+ "</option>") == -1) {
				languages += "<option value='" + type + "'>" + type
						+ "</option>";
			}
		}

		$(".filter-language").append(languages);
	}
});

$.ajax({
	url : "/rest/utils/content-ratings/",
	cache : false,
	success : function(data) {
		data.content_ratings.sort();
		for (var i = 0; i < data.content_ratings.length; i++) {
			var type = data.content_ratings[i];
			if (content_rating.indexOf("<option value='" + type + "'>" + type
					+ "</option>") == -1) {
				content_rating += "<option value='" + type + "'>" + type
						+ "</option>";
			}
		}

		$(".filter-content").append(content_rating);
	}
});

$.ajax({
	url : "/rest/utils/countries/",
	cache : false,
	success : function(data) {
		data.countries.sort();
		for (var i = 0; i < data.countries.length; i++) {
			var type = data.countries[i];
			if (countries.indexOf("<option value='" + type + "'>" + type
					+ "</option>") == -1) {
				countries += "<option value='" + type + "'>" + type
						+ "</option>";
			}
		}

		$(".filter-country").append(countries);
	}
});

var filtersObject = {};

$(".filter").on("change", function() {
	var filterName = $(this).data("filter"), filterVal = $(this).val();

	if (filterVal == "") {
		delete filtersObject[filterName];
	} else {
		filtersObject[filterName] = filterVal;
	}

	var filters = "";

	for ( var key in filtersObject) {
		if (filtersObject.hasOwnProperty(key)) {
			filters += "[data-" + key + "='" + filtersObject[key] + "']";
		}
	}

	if (filters == "") {
		$(".product").show();
	} else {
		$(".product").hide();
		$(".product").hide().filter(filters).show();
	}
});

$(document).ajaxStart(function() {
	$(".spinner").removeClass("hidden");
});
$(document).ajaxComplete(function() {
	$(".spinner").addClass("hidden");
});

$(document)
		.ready(
				function() {
					$
							.ajax({
								type : 'GET',
								url : "/rest/movies/list",
								success : function(data) {
									for (var i = 0; i < data.length; i++) {
										$('ul.list-group')
												.append(
													'<a href="#" data-toggle="modal" data-target="#myModal"	data-imdb="'+data[i].imdb+'" data-director="'+data[i].director+'" data-duration="'+data[i].duration+'" data-actors="'+data[i].actors+'" data-genres="'+data[i].genres+'"	data-title="'+data[i].title+'" data-language="'+data[i].language+'"	data-country="'+data[i].country+'" data-content="'+data[i].content_rating+'"data-score="'+data[i].score+'"data-year="'+data[i].year+'" class="list-group-item"><span class="movie-title">'+data[i].title+'</span><span class="label label-primary">'+data[i].year+'</span><span class="label label-success">'+data[i].score+'</span>	<span class="label label-info">'+data[i].language+'</span></a>'
												);

									}
								}
							});
				});

$("#search-form")
		.submit(
				function(e) {
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

					$
							.ajax({
								type : 'GET',
								url : "/rest/movies/filter?yearfrom="
										+ start_date
										+ "&yearto="
										+ end_date
										+ "&ratingfrom="
										+ min_rating
										+ "&ratingto="
										+ max_rating
										+ "&keywords="
										+ query
										+ "&content_rating="
										+ content_rating
										+ "&language=" + lang,
								success : function(data) {
									for (var i = 0; i < data.length; i++) {
										$('ul.list-group')
												.append(
														'<li class="list-group-item"><span class="movie-title">'
																+ data[i].title
																+ '</span><span class="label label-primary">'
																+ data[i].year
																+ '</span><span class="label label-success">'
																+ data[i].score
																+ '</span><span class="label label-info">'
																+ data[i].language
																+ '</span><span class="label label-warning">'
																+ data[i].genres
																+ '</span><a class="imdb-link" href="'
																+ data[i].imdb
																+ '"><img class="imdb-logo" src="imdb.png"/></a></li>');
									}

								},
							});
				});

$('#slider')
		.slider(
				{
					range : true,
					min : 0,
					max : 10,
					values : [ 0, 10 ],
					slide : function(event, ui) {
						$('.ui-slider-handle:eq(0) .price-range-min').html(
								'' + ui.values[0]);
						$('.ui-slider-handle:eq(1) .price-range-max').html(
								'' + ui.values[1]);
						$('.price-range-both').html(
								'<i>' + ui.values[0] + '  </i>' + ui.values[1]);

						$('.price-range-min, .price-range-max').css('opacity',
								'1');
						$('.price-range-both').css('display', 'none');
					},
					create : function(event, ui) {
						$('.ui-slider-handle:eq(0)').append(
								'<span class="price-range-min value">' + 0
										+ '</span>');
						$('.ui-slider-handle:eq(1)').append(
								'<span class="price-range-max value">' + 10
										+ '</span>');
					}
				});
