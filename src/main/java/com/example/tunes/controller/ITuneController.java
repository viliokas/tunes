package com.example.tunes.controller;

import com.example.tunes.exception.UserNotFoundException;
import com.example.tunes.model.Album;
import com.example.tunes.model.Artist;
import com.example.tunes.model.User;
import com.example.tunes.model.body.FavouriteArtist;
import com.example.tunes.service.ITuneService;
import com.example.tunes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api")
public class ITuneController {

    @Autowired
    ITuneService iTuneService;
    @Autowired
    UserService userService;

    @GetMapping("search")
    ResponseEntity<List<Artist>> searchArtist(@RequestParam String term) {
        return ResponseEntity
                .ok()
                .body(iTuneService.searchArtistRequest(term));
    }

    @GetMapping("top")
    ResponseEntity<List<Album>> getTopAlbums(@RequestParam Integer userId) {
        Integer artistId = userService.getUserById(userId).getArtistId();
        return ResponseEntity
                .ok()
                .body(iTuneService.top5AlbumsRequest(artistId));
    }

    @PostMapping("favourite")
    ResponseEntity<User> addUserFavouriteArtist(@NotNull @RequestParam Integer userId, @Valid @RequestBody FavouriteArtist artist) {
        userService.addUser(new User(userId, artist.getArtistId()));
        return ResponseEntity
                .noContent()
                .build();
    }

}
