package com.entertainment.tvshows.search.external;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * Configuration class for reading the config specific to the Tv-Maze service
 */
@Data
@Validated
@Configuration
@ConfigurationProperties("entertainment.tv-show-search-service.tv-maze-service")
public class TvMazeConfig {

    @NotBlank
    private String url;

    @NotBlank
    private String path;
}
