package com.example.tunes.model.body;

import lombok.Getter;
import javax.validation.constraints.NotNull;

@Getter
public class FavouriteArtist {
    @NotNull(message="artistId cannot be null")
    Integer artistId;
}
