package com.antainfernandez.fiveInARowGame.controller;

import com.antainfernandez.fiveInARowGame.controller.dto.GameStatusDto;
import com.antainfernandez.fiveInARowGame.controller.dto.JoinGameRequest;
import com.antainfernandez.fiveInARowGame.controller.dto.PlayRequest;
import com.antainfernandez.fiveInARowGame.enums.GameStatus;
import com.antainfernandez.fiveInARowGame.model.Board;
import com.antainfernandez.fiveInARowGame.model.Game;
import com.antainfernandez.fiveInARowGame.model.Player;
import com.antainfernandez.fiveInARowGame.repository.GameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerTests {

    @Autowired
    GameRepository gameRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void e2e_start_join_play() throws Exception {
        String baseUrl = "http://localhost:" + port + "/game/new";
        URI uri = new URI(baseUrl);
        Player player1 = new Player();
        player1.setName("p1");
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        HttpEntity<Player> newGameRequest = new HttpEntity<>(player1, headers);
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, newGameRequest, String.class);
        assertEquals(200, result.getStatusCodeValue());

        //Game is created
        Game resultGame = gameRepository.getAll().get(0);
        assertEquals(resultGame.getGameStatus(), GameStatus.NEW);
        assertEquals(resultGame.getPlayer1().getDisk(), Board.DISK_O);


        String[] s=restTemplate.getForObject(
                "http://localhost:" + port + "/game/list", String[].class);

        baseUrl = "http://localhost:" + port + "/game/join";
        uri = new URI(baseUrl);
        JoinGameRequest joinGameRequest = new JoinGameRequest();
        Player player2 = new Player();
        player2.setName("p2");
        joinGameRequest.setPlayer(player2);
        joinGameRequest.setGameID(resultGame.getId());
        headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        HttpEntity<JoinGameRequest> joinRequest = new HttpEntity<>(joinGameRequest, headers);
        ResponseEntity<String> joinResult = this.restTemplate.postForEntity(uri, joinRequest, String.class);
        assertEquals(200, joinResult.getStatusCodeValue());

        //Game player 2 join so game is in progress
        assertEquals(resultGame.getGameStatus(), GameStatus.IN_PROGRESS);

        baseUrl = "http://localhost:" + port + "/game/play";
        uri = new URI(baseUrl);
        PlayRequest playRequest = new PlayRequest();
        playRequest.setPlayer(resultGame.getPlayer1());
        playRequest.setGameID(resultGame.getId());
        playRequest.setColumn(1);
        headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        HttpEntity<PlayRequest> playGameRequest = new HttpEntity<>(playRequest, headers);
        ResponseEntity<String> playGameResult = this.restTemplate.postForEntity(uri, playGameRequest, String.class);
        assertEquals(200, playGameResult.getStatusCodeValue());

        //Player 1 play - added disk in column 1
        assertEquals(resultGame.getBoard().getMatrix()[0][0], Board.DISK_O);

        Map<String, String> vars = new HashMap<>();
        vars.put("gameId", resultGame.getId());

        GameStatusDto response =
        this.restTemplate.getForObject("http://localhost:" + port + "/game/status/{gameId}",
                GameStatusDto.class, vars);

        assertEquals(response.getGameStatus(), GameStatus.IN_PROGRESS.toString());
    }

}
