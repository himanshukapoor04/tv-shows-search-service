package com.entertainment.tvshows.search.external;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import com.entertainment.tvshows.search.exception.TvShowError;
import com.entertainment.tvshows.search.model.TvShow;

import java.util.logging.Level;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@RequiredArgsConstructor
@Slf4j
public class TvMazeClientImpl implements TvMazeClient {

    private final WebClient.Builder builder;
    private final TvMazeConfig tvMazeConfig;

    @Override
    public Flux<TvShow> getTvShows(String showName) {
        return builder.baseUrl(tvMazeConfig.getUrl())
                .build()
                .get()
                .uri(String.format(tvMazeConfig.getPath(), showName))
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(TvShow.class)
                .onErrorMap(throwable -> {
                    log.error("Error occurred while searching users", throwable);
                    return new TvShowError(HttpStatus.INTERNAL_SERVER_ERROR, "Error " +
                            "occurred while fetching the tv shows");
                })
                .log(log.getName(), Level.FINE);
    }
}
