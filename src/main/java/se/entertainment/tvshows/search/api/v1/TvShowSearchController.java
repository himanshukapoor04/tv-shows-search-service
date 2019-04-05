package se.entertainment.tvshows.search.api.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import se.entertainment.tvshows.search.service.TvShowSearchService;

import javax.validation.constraints.NotNull;

/**
 * Controller class for querying the shows
 */
@RestController
@RequestMapping("/api/v1/tv-search")
@RequiredArgsConstructor
@Slf4j
public class TvShowSearchController {

    private final TvShowSearchService tvShowSearchService;

    @GetMapping
    @RequestMapping("/shows/q={showName}&results={noOfResults}")
    public Flux<TvShowDto> getTopThreeShows(@PathVariable @NotNull String showName,
                                            @PathVariable @NotNull int noOfResults) {
        return tvShowSearchService.getTopNShows(showName, noOfResults);
    }

}
