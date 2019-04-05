package se.entertainment.tvshows.search.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception class to raise HTTP errors
 */
public class TvShowError extends ResponseStatusException {

    public TvShowError(HttpStatus status, String reason) {
        super(status, reason);
    }
}
