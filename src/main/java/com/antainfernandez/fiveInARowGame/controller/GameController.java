package com.antainfernandez.fiveInARowGame.controller;

import com.antainfernandez.fiveInARowGame.controller.dto.GameStatusDto;
import com.antainfernandez.fiveInARowGame.controller.dto.JoinGameRequest;
import com.antainfernandez.fiveInARowGame.controller.dto.PlayRequest;
import com.antainfernandez.fiveInARowGame.enums.GameStatus;
import com.antainfernandez.fiveInARowGame.model.Game;
import com.antainfernandez.fiveInARowGame.model.Player;
import com.antainfernandez.fiveInARowGame.repository.GameRepository;
import com.antainfernandez.fiveInARowGame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    GameService gameService;

    @Autowired
    GameRepository gameRepository;

    @PostMapping("/new")
    public @ResponseBody
    GameStatusDto startGame(@RequestBody Player player) {
        return createGameDto(gameService.startNewGame(player));
    }

    @PostMapping("/join")
    public @ResponseBody
    GameStatusDto joinGame(@RequestBody JoinGameRequest request) {
        GameStatusDto gameDto = createGameDto(gameService.connectToGame(request.getPlayer(), request.getGameID()));
        simpMessagingTemplate.convertAndSend("/topic/game/" + gameDto.getId(), gameDto);
        return gameDto;

    }

    @PostMapping("/play")
    public @ResponseBody
    GameStatusDto play(@RequestBody PlayRequest request) {
        GameStatusDto gameDto = createGameDto(gameService.play(request.getGameID(), request.getPlayer(), request.getColumn()));
        simpMessagingTemplate.convertAndSend("/topic/game/" + gameDto.getId(), gameDto);
        return gameDto;
    }


    @RequestMapping("/status/{gameId}")
    public @ResponseBody
    GameStatusDto getGameStatus(@PathVariable String gameId) {
        Game game = gameRepository.getAll().stream().filter(g -> g.getId().equals(gameId)).findFirst().get();
        GameStatusDto gameDto = createGameDto(game);
        return gameDto;
    }

    @RequestMapping("/list")
    public @ResponseBody
    List<String> gamesToJoin() {
        return gameRepository.getAll().stream().filter(game -> game.getGameStatus() == GameStatus.NEW).map(Game::getId).collect(Collectors.toList());
    }

    @RequestMapping("/help")
    public @ResponseBody
    String greeting() {
        return "help tutorial here";
    }


    private GameStatusDto createGameDto(Game game) {
        GameStatusDto gameDto = new GameStatusDto();
        gameDto.setId(game.getId());
        gameDto.setGameStatus(game.getGameStatus().toString());

        gameDto.setPlayer1(game.getPlayer1().getName());
        gameDto.setPlayer1Disk(String.valueOf(game.getPlayer1().getDisk()));
        gameDto.setPlayer2(game.getPlayer2() == null ? "" : game.getPlayer2().getName());
        gameDto.setPlayer2Disk(game.getPlayer2() == null ? "" : String.valueOf(game.getPlayer2().getDisk()));

        gameDto.setBoard(game.getBoard().print());
        gameDto.setWinner(game.getWinner() == null ? "none" : game.getWinner().getName());
        gameDto.setCurrentPlayer(game.getCurrentPlayer().getName());
        return gameDto;
    }
}
