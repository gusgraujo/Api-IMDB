package com.bee.beeWatching.Utils;

import com.bee.beeWatching.Controller.ParticipantController;
import com.bee.beeWatching.Model.Movie;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.sound.midi.SysexMessage;
import java.io.IOException;
import java.util.Date;

public class RequestApiMovie {
    private final String RAPID_API_KEY = "a4419070f3msh3fdc1ced2fb34b5p1db543jsn46c147b8013f";
    private final String RAPID_API_HOST = "online-movie-database.p.rapidapi.com";
    private static final Logger logger = LogManager.getLogger(RequestApiMovie.class);
    public String findMovieTitleById(String idMovie) throws IOException
    {
        String title = "";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(String.format("https://online-movie-database.p.rapidapi.com/title/get-details?tconst=%s",idMovie ))
                .get()
                .addHeader("X-RapidAPI-Key", RAPID_API_KEY)
                .addHeader("X-RapidAPI-Host", RAPID_API_HOST)
                .build();
        Response response = client.newCall(request).execute();
        ObjectNode node = new ObjectMapper().readValue(response.body().string(), ObjectNode.class);
        if (node.has("title")) {
            System.out.println("title: " + node.get("title"));
            title = node.get("title").asText();
        }
        return title;
    }

    public String findMovieTrailerById(String idMovie) throws IOException
    {
        String trailerUrl = "";
        String vidId = "";
        String vidUrl = "";
        OkHttpClient client = new OkHttpClient();

        Request requestMovieDetails = new Request.Builder()
                .url(String.format("https://online-movie-database.p.rapidapi.com/title/get-hero-with-promoted-video?tconst=%s",idMovie ))
                .get()
                .addHeader("X-RapidAPI-Key", RAPID_API_KEY)
                .addHeader("X-RapidAPI-Host", RAPID_API_HOST)
                .build();

        Response response = client.newCall(requestMovieDetails).execute();
        ObjectNode node = new ObjectMapper().readValue(response.body().string(), ObjectNode.class);

        vidUrl = node.get("topVideos").get(0).findValue("id").asText();

        logger.info("vidUrl = "+vidUrl);

        if(vidUrl != ""){
            vidId = vidUrl.substring(vidUrl.lastIndexOf("/") + 1);
            logger.info("vidId = "+vidId);

            Request requestTrailer = new Request.Builder()
                    .url(String.format("https://online-movie-database.p.rapidapi.com/title/get-video-playback?viconst=%s ",vidId ))
                    .get()
                    .addHeader("X-RapidAPI-Key", RAPID_API_KEY)
                    .addHeader("X-RapidAPI-Host", RAPID_API_HOST)
                    .build();

            response = client.newCall(requestTrailer).execute();
            logger.info("response "+response.isSuccessful());
            node = new ObjectMapper().readValue(response.body().string(), ObjectNode.class);
            trailerUrl = node.get("resource").get("encodings").get(0).findValue("playUrl").toString();
            logger.info("trailerUrl = "+ trailerUrl);
        }
        return trailerUrl;
    }

    public String findMovieByTitle(String title) throws IOException
    {
        String idMovie;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(String.format("https://online-movie-database.p.rapidapi.com/title/v2/find?title=%s&limit=1", title))
                .get()
                .addHeader("X-RapidAPI-Key", RAPID_API_KEY)
                .addHeader("X-RapidAPI-Host", RAPID_API_HOST)
                .build();

        Response response = client.newCall(request).execute();

        ObjectNode node = new ObjectMapper().readValue(response.body().string(), ObjectNode.class);

        idMovie = node.get("results").findValue("id").asText();
        idMovie = idMovie.substring(7,idMovie.length() - 1);

        System.out.println(idMovie);
        return idMovie;
    }

    public String getGenresById(String idMovie) throws IOException
    {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(String.format("https://online-movie-database.p.rapidapi.com/title/get-genres?tconst=%s",idMovie ))
                .get()
                .addHeader("X-RapidAPI-Key", RAPID_API_KEY)
                .addHeader("X-RapidAPI-Host", RAPID_API_HOST)
                .build();

        Response response = client.newCall(request).execute();

        return response.body().string();
    }

    public String getDescription(String idMovie) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(String.format("https://online-movie-database.p.rapidapi.com/title/get-overview-details?tconst=%s&currentCountry=PT",idMovie ))
                .get()
                .addHeader("X-RapidAPI-Key", RAPID_API_KEY)
                .addHeader("X-RapidAPI-Host", RAPID_API_HOST)
                .build();

        Response response = client.newCall(request).execute();
        ObjectNode node = new ObjectMapper().readValue(response.body().string(), ObjectNode.class);
        return node.get("plotSummary").findValue("text").asText();
    }
    public Movie formatMovie(String idMovie) throws IOException
    {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(String.format("https://online-movie-database.p.rapidapi.com/title/get-meta-data?ids=%s&region=US", idMovie))
                .get()
                .addHeader("X-RapidAPI-Key", RAPID_API_KEY)
                .addHeader("X-RapidAPI-Host", RAPID_API_HOST)
                .build();
        Response response = client.newCall(request).execute();

        if (response.isSuccessful()){
            ObjectNode node = new ObjectMapper().readValue(response.body().string(), ObjectNode.class);
            Date now = new Date();
            Movie movie = new Movie(
                    idMovie,
                    node.get(idMovie).findValue("title").findValue("title").asText(),
                    node.get(idMovie).findValue("year").asInt(),
                    null,
                    node.get(idMovie).findValue("image").findValue("url").asText(),
                    "",
                    getGenresById(idMovie),
                    node.get(idMovie).findValue("runningTimeInMinutes").asDouble(),
                    node.get(idMovie).findValue("ratings").findValue("rating").asDouble(),
                    0.0,
                    null,
                    null,
                    false
            );
            logger.info(node.get(idMovie).findValue("metacritic").has("metaScore"));
            if(node.get(idMovie).findValue("metacritic").has("metaScore")){
                movie.setRateMeta(node.get(idMovie).findValue("metacritic").findValue("metaScore").asDouble());
            }
            return movie;
        }else {
            return null;
        }

    }
}

