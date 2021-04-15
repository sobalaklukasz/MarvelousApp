package com.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CharacterVote {

    @Id
    private Integer id;
    private String name;
    private Integer voteCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }
}
