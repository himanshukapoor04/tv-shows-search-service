package se.entertainment.tvshows.search.api.v1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO class for the response of TV show
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TvShowDto {

    private String name;
    private double score;
    private String premiered;

    @JsonIgnore
    private Integer rating;


}
