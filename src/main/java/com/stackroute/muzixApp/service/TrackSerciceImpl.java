package com.stackroute.muzixApp.service;

import com.stackroute.muzixApp.domain.Track;
import com.stackroute.muzixApp.exception.UserAlreadyExistsException;
import com.stackroute.muzixApp.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackSerciceImpl implements TrackService{
    private TrackRepository trackRepository;
    @Autowired
    public TrackSerciceImpl(TrackRepository trackRepository) {
        this.trackRepository=trackRepository;
    }
    @Override
    public Track saveTrack(Track track) throws UserAlreadyExistsException {
        if(trackRepository.existsById(track.getTrackId())) {
            throw new UserAlreadyExistsException("user already exists");
        }
       Track savedTrack=trackRepository.save(track);
       if(savedTrack==null) {
           throw new UserAlreadyExistsException("user already exists");
       }
        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks() {

        return trackRepository.findAll();
    }

    @Override
    public void deleteTrack(int trackId) {
        trackRepository.deleteById(trackId);
    }

    @Override
    public Track getTrackById(int trackId) {

       return trackRepository.findById(trackId).get();
    }

    @Override
    public Track updateTrack(Track track) {
       return trackRepository.save(track);
    }

    @Override
    public List<Track> findByName(String trackName) {
        return trackRepository.findByName(trackName);
    }


}
