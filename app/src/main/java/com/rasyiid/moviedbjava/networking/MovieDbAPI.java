package com.rasyiid.moviedbjava.networking;

import com.rasyiid.moviedbjava.base.Constants;
import com.rasyiid.moviedbjava.model.Discover;
import com.rasyiid.moviedbjava.model.GenreList;
import com.rasyiid.moviedbjava.model.MovieDetails;
import com.rasyiid.moviedbjava.model.Reviews;
import com.rasyiid.moviedbjava.model.Videos;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDbAPI {
    @GET("discover/movie?api_key="+Constants.API_KEY)
    Observable<Discover> getMovies(@Query("with_genres") Integer genre, @Query("page") Integer page);

    @GET("genre/movie/list?api_key="+Constants.API_KEY)
    Observable<GenreList> getGenres();

    @GET("movie/{movie_id}?api_key="+Constants.API_KEY)
    Observable<MovieDetails> getMovieDetails(@Path("movie_id") Integer movieId);

    @GET("movie/{movie_id}/reviews?api_key="+Constants.API_KEY)
    Observable<Reviews> getMovieReviews(@Path("movie_id") Integer movieId, @Query("page") Integer page);

    @GET("movie/{movie_id}/videos?api_key="+Constants.API_KEY)
    Observable<Videos> getMovieVideos(@Path("movie_id") Integer movieId);
}
