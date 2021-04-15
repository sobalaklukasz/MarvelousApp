package com.app.dao;

import com.app.entity.CharacterVote;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Sql("classpath:/initial-test-data.sql")
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CharacterVoteRepositoryTest {

    @Autowired
    private CharacterVoteRepository characterVoteRepository;

    @Test
    public void should_find_all() {
        List<CharacterVote> allCharacters = characterVoteRepository.findAll();
        assertThat(allCharacters).hasSize(2);
        assertThat(allCharacters.get(0).getName()).isEqualTo("Hulk");
        assertThat(allCharacters.get(1).getName()).isEqualTo("Thor");
    }

    @Test
    public void should_vote_for_character() {
        List<CharacterVote> allCharactersBeforeVote = characterVoteRepository.findAll();
        assertThat(allCharactersBeforeVote.get(0).getVoteCount()).isEqualTo(123);
        characterVoteRepository.voteForCharacter(1);
        List<CharacterVote> allCharactersAfterVote = characterVoteRepository.findAll();
        assertThat(allCharactersAfterVote.get(0).getVoteCount()).isEqualTo(124);
    }
}