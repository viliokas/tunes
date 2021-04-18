package com.example.tunes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Artist {
    Integer artistId;
    String wrapperType;
    String artistName;
    String amgArtistId;
    String artistType;
    String artistLinkUrl;
    String policyNumber;
    String primaryGenreName;
    String primaryGenreId;
}
