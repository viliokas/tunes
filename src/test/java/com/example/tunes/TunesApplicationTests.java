package com.example.tunes;

import com.example.tunes.utils.FileHelper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TunesApplicationTests {


    @Value("${itunes.artists.url}")
    private String ARTIST_URL;
    @Value("${itunes.top5albums.url}")
    private String TOP5_ALBUMS_URL;

    @Autowired
    private MockMvc mockMvc;

    private MockRestServiceServer mockServer;

    @Autowired
    private RestTemplate restTemplate;

    @BeforeEach
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void searchArtis_get200() throws Exception {
        mockServer
                .expect(requestTo(new URI(ARTIST_URL.replace("{term}", "abba"))))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(FileHelper.readFileToString("itunes_search_artist_response.json"))
                );
        this.mockMvc.perform(get("/api/search?term=abba")).andExpect(status().isOk())
                .andExpect(content().json(FileHelper.readFileToString("search_artist_response.json")));
    }

    @Test
    public void searchArtisWhenITunesNotResponding_get503() throws Exception {
        mockServer
                .expect(requestTo(new URI(ARTIST_URL.replace("{term}", "abbaaa"))))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .body("")
                );
        this.mockMvc.perform(get("/api/search?term=abbaaa")).andExpect(status().isServiceUnavailable());
    }

    @Test
    @Order(1)
    public void addUserFavouriteArtist_get204() throws Exception {
        this.mockMvc.perform(post("/api/favourite?userId=11")
                .contentType("application/json").content("{\"artistId\": 3492}"))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(2)
    public void getTopAlbums_get200() throws Exception {
        mockServer
                .expect(requestTo(new URI(TOP5_ALBUMS_URL.replace("{artistId}", "3492"))))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(FileHelper.readFileToString("itunes_top_artist_albums_response.json"))
                );
        this.mockMvc.perform(get("/api/top?userId=11"))
                .andExpect(status().isOk())
                .andExpect(content().json(FileHelper.readFileToString("top_favourite_artist_albums_response.json")));
    }

    @Test
    public void getTopAlbumsWhenUserNotFound_get404() throws Exception {
        this.mockMvc.perform(get("/api/top?userId=15"))
                .andExpect(status().isNotFound());
    }
}
