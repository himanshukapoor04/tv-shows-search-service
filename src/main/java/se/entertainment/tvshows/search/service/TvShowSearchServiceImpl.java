package se.entertainment.tvshows.search.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import se.entertainment.tvshows.search.api.v1.TvShowDto;
import se.entertainment.tvshows.search.external.TvMazeClient;
import se.entertainment.tvshows.search.model.TvShow;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TvShowSearchServiceImpl implements TvShowSearchService {

    private final TvMazeClient tvMazeClient;

    @Override
    public Flux<TvShowDto> getTopNShows(String showQueryString,
                                        int number) {
        return tvMazeClient.getTvShows(showQueryString)
                .collectList()
                .flatMap(shows -> handleTvMazeResponse(number, shows))
                .flux().flatMap(Flux::fromIterable);

    }

    private Mono<? extends List<TvShowDto>> handleTvMazeResponse(int number,
                                                                 List<TvShow> shows) {
        if (shows.isEmpty()) {
            return Mono.empty();
        } else {
            val sortedShows = shows.parallelStream()
                    .filter(this::areShowDetailsPresent)
                    .map(this::buildTvShowDto)
                    .sorted(this::compareShows)
                    .limit(number)
                    .collect(Collectors.toList());
            return Mono.just(sortedShows);

        }
    }

    private boolean areShowDetailsPresent(TvShow show) {
        return show.getShow() != null;
    }

    private TvShowDto buildTvShowDto(TvShow show) {
        return TvShowDto.builder()
                .score(show.getScore())
                .premiered(show.getShow().getPremiered())
                .name(show.getShow().getName())
                .rating(show.getShow().getRating() != null ? show.getShow()
                        .getRating().getAverage() :
                        null)
                .build();
    }

    private int compareShows(TvShowDto show1, TvShowDto show2) {
        Integer lhs = show1.getRating() != null ? show1.getRating() : 0;
        Integer rhs = show2.getRating() != null ? show2.getRating() : 0;
        return rhs.compareTo(lhs);
    }
}
