package com.fullStack.media.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fullStack.media.model.Media;

@Repository
public interface MediaDao extends CrudRepository<Media, Long> {

    Iterable<Media> findByUsername(String username);
    Media findByUsernameAndProfile(String username, boolean profile);
    
    @Query("SELECT m FROM Media m WHERE upper(m.username) like concat('%', upper(?1), '%') OR upper(m.title) like concat('%', upper(?1), '%') OR upper(m.description) like concat('%', upper(?1), '%') OR upper(m.tags) like concat('%', upper(?1), '%')")
    Iterable<Media> searchMedia( String searchString);
}
