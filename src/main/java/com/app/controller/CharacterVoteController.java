package com.app.controller;

import com.app.dao.CharacterVoteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterVoteController {

    private final CharacterVoteRepository characterVoteRepository;

    public CharacterVoteController(CharacterVoteRepository characterVoteRepository) {
        this.characterVoteRepository = characterVoteRepository;
    }

    @GetMapping("/characterVote/{id}")
    public String voteForCharacter(@PathVariable int id) {

        characterVoteRepository.voteForCharacter(id);
        return "Voted for id: " + id;
    }
}
