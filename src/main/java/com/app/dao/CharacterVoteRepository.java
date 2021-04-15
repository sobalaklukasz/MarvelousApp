package com.app.dao;

import com.app.entity.CharacterVote;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "characterVote", path = "characterVote")
public interface CharacterVoteRepository extends CrudRepository<CharacterVote, Integer> {

    List<CharacterVote> findAll();

    @Transactional
    @Modifying
    @Query("update CharacterVote cv set cv.voteCount = cv.voteCount + 1 where cv.id = :id")
    void voteForCharacter(@Param(value = "id") int id);
}
