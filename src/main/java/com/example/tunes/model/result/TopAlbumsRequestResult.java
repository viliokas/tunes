package com.example.tunes.model.result;

import com.example.tunes.model.Album;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TopAlbumsRequestResult {
    private List<Album> results;
    private Integer resultCount;
}
