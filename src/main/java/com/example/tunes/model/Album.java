package com.example.tunes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Album {
    private Integer collectionId;
    private String collectionType;
    private String collectionName;
    private String collectionCensoredName;
    private String collectionExplicitness;
    private Double collectionPrice;
    private String collectionViewUrl;
    private String wrapperType;
    private String artistName;
    private String artistViewUrl;
    private String artworkUrl60;
    private String artworkUrl100;
    private Integer trackCount;
    private String copyright;
    private String country;
    private String currency;
    private String releaseDate;
    private String primaryGenreName;
}
