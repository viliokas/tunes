package com.example.tunes.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AlbumTests {
    private String wrapperType = "a";
    private String collectionType = "b";
    private Integer collectionId = 1;
    private String collectionName = "c";
    private String artistName = "d";
    private String collectionCensoredName = "e";
    private String artistViewUrl = "f";
    private String collectionViewUrl = "g";
    private String artworkUrl60 = "h";
    private String artworkUrl100 = "i";
    private Double collectionPrice = 2.0;
    private String collectionExplicitness = "j";
    private Integer trackCount = 1;
    private String copyright = "k";
    private String country = "l";
    private String currency = "m";
    private String releaseDate = "n";
    private String primaryGenreName = "o";

    @Test
    public void gettersAndConstructorsTest() {
        Album fullArgConstructor = new Album(
                collectionId,
                collectionType,
                collectionName,
                collectionCensoredName,
                collectionExplicitness,
                collectionPrice,
                collectionViewUrl,
                wrapperType,
                artistName,
                artistViewUrl,
                artworkUrl60,
                artworkUrl100,
                trackCount,
                copyright,
                country,
                currency,
                releaseDate,
                primaryGenreName);

        Assertions.assertEquals(wrapperType, fullArgConstructor.getWrapperType());
        Assertions.assertEquals(collectionType, fullArgConstructor.getCollectionType());
        Assertions.assertEquals(collectionId, fullArgConstructor.getCollectionId());
        Assertions.assertEquals(collectionName, fullArgConstructor.getCollectionName());
        Assertions.assertEquals(artistName, fullArgConstructor.getArtistName());
        Assertions.assertEquals(collectionCensoredName, fullArgConstructor.getCollectionCensoredName());
        Assertions.assertEquals(artistViewUrl, fullArgConstructor.getArtistViewUrl());
        Assertions.assertEquals(collectionViewUrl, fullArgConstructor.getCollectionViewUrl());
        Assertions.assertEquals(artworkUrl60, fullArgConstructor.getArtworkUrl60());
        Assertions.assertEquals(artworkUrl100, fullArgConstructor.getArtworkUrl100());
        Assertions.assertEquals(collectionPrice, fullArgConstructor.getCollectionPrice());
        Assertions.assertEquals(collectionExplicitness, fullArgConstructor.getCollectionExplicitness());
        Assertions.assertEquals(trackCount, fullArgConstructor.getTrackCount());
        Assertions.assertEquals(copyright, fullArgConstructor.getCopyright());
        Assertions.assertEquals(country, fullArgConstructor.getCountry());
        Assertions.assertEquals(currency, fullArgConstructor.getCurrency());
        Assertions.assertEquals(releaseDate, fullArgConstructor.getReleaseDate());
        Assertions.assertEquals(primaryGenreName, fullArgConstructor.getPrimaryGenreName());
    }
}
