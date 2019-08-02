package com.stackroute.muzixApp.repository;

import com.stackroute.muzixApp.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrackRepository extends MongoRepository<Track,Integer> {
    @Query("{ 'name' : ?0}")
    public List<Track> findByName(String trackName);
}
