package com.stackroute.muzixApp.service;

import com.stackroute.muzixApp.domain.Track;
import com.stackroute.muzixApp.exception.UserAlreadyExistsException;

import java.util.List;

public interface TrackService {
    public Track saveTrack(Track track) throws UserAlreadyExistsException;
    public List<Track> getAllTracks() ;
    public void deleteTrack(int trackId);
    public Track getTrackById(int trackId) ;
    public Track updateUser(Track track);
}
