package com.entertainment.tvshows.search.external;

import reactor.core.publisher.Flux;
import com.entertainment.tvshows.search.model.TvShow;

/**
 * Client to call TV Maze API
 */
public interface TvMazeClient {

    /**
     * Method to call the TV maze Rest API and fetch TV shows
     * @param showName name of the show to search for
     * @return {@link Flux<TvShow>}
     */
    Flux<TvShow> getTvShows(String showName);
}
