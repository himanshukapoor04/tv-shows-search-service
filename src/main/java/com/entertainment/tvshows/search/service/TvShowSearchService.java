package com.entertainment.tvshows.search.service;

import reactor.core.publisher.Flux;
import com.entertainment.tvshows.search.api.v1.TvShowDto;

/**
 * Service to fetch tv shows.
 */
public interface TvShowSearchService {

    /**
     * Method to fetch top N shows from the TVMaze API
     * @param showQueryString show to search for
     * @param number of shows to show
     * @return {@link Flux<TvShowDto>} list of shows
     */
    Flux<TvShowDto> getTopNShows(String showQueryString,
                                 int number);
}
