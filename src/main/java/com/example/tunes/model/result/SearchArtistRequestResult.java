package com.example.tunes.model.result;

import com.example.tunes.model.Artist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SearchArtistRequestResult {
    private List<Artist> results;
    private Integer resultCount;
}
