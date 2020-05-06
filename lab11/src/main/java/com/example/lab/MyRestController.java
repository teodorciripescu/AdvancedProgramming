package com.example.lab;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class MyRestController {

    private final List<Player> players = new ArrayList<>();

    public MyRestController(){
        players.add(new Player("bestplayer"));
        players.add(new Player("niceplayer"));
    }

    @GetMapping("/players")
    public List<Player> getPlayers() {
        return players;
    }

    @PostMapping("/new_player")
    public int createProduct(@RequestParam String username) {
        int id = 1 + players.size();
        players.add(new Player(username));
        return id;
    }

    @PutMapping("/modify_name/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestParam String username) {
        Player player = findById(id);
        if (player == null) {
            return new ResponseEntity<>(
                    "Player not found!", HttpStatus.NOT_FOUND);
        }
        player.setUsername(username);
        return new ResponseEntity<>(
                "Player username updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        Player player = findById(id);
        if (player == null) {
            return new ResponseEntity<>(
                    "Player not found!", HttpStatus.GONE);
        }
        players.remove(player);
        return new ResponseEntity<>("Player removed!", HttpStatus.OK);
    }

    private Player findById(int id) {
        return players.get(id);
    }
}
