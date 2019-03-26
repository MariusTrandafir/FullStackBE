package com.fullStack.media.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fullStack.media.model.Media;

@Repository
public interface MediaDao extends CrudRepository<Media, Long> {

    Iterable<Media> findByUsername(String username);
    Media findByUsernameAndProfile(String username, boolean profile);
}