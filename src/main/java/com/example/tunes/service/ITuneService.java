package com.example.tunes.service;

import com.example.tunes.exception.NotRespondingException;
import com.example.tunes.model.Album;
import com.example.tunes.model.Artist;
import com.example.tunes.model.result.SearchArtistRequestResult;
import com.example.tunes.model.result.TopAlbumsRequestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ITuneService {
    @Value("${itunes.artists.url}")
    private String ARTIST_URL;
    @Value("${itunes.top5albums.url}")
    private String TOP5_ALBUMS_URL;
    @Autowired
    private RestTemplate restTemplate;

    private final String SERVICE_NAME = "ITunes";

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }

    @Cacheable("itunes_artists")
    public List<Artist> searchArtistRequest(String term) {
        SearchArtistRequestResult result = restTemplate.getForObject(ARTIST_URL, SearchArtistRequestResult.class, term);
        if(result == null) throw new NotRespondingException(SERVICE_NAME);

        return result.getResults();
    }

    @Cacheable("itunes_top5albums")
    public List<Album> top5AlbumsRequest(Integer artistId) {
        TopAlbumsRequestResult result = restTemplate.getForObject(TOP5_ALBUMS_URL, TopAlbumsRequestResult.class, artistId);
        if(result == null) throw new NotRespondingException(SERVICE_NAME);

        List<Album> onlyAlbumsResult = result.getResults().stream().filter(album -> album.getWrapperType().equals("collection")).collect(Collectors.toList());
        return onlyAlbumsResult;
    }
}
