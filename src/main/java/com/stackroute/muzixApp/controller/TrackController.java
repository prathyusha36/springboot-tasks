package com.stackroute.muzixApp.controller;

import com.stackroute.muzixApp.domain.Track;
import com.stackroute.muzixApp.exception.TrackAlreadyExistsException;
import com.stackroute.muzixApp.exception.TrackNotFoundException;
import com.stackroute.muzixApp.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class TrackController {
    private TrackService trackService;
    
    @Autowired
    public TrackController(TrackService trackService) {

        this.trackService=trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException {
        ResponseEntity responseEntity;
        trackService.saveTrack(track);
        responseEntity = new ResponseEntity<String>("successfully created", HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("tracks")
    public ResponseEntity<?> getAllTracks() {
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.OK);
    }

    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable("id") int id)throws TrackNotFoundException {
        ResponseEntity responseEntity;
        responseEntity = new ResponseEntity<Track>(trackService.getTrackById(id), HttpStatus.CREATED);
        return responseEntity;
        }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable("id") int id) {
        trackService.deleteTrack(id);
        return new ResponseEntity<String>("deleted", HttpStatus.FORBIDDEN);
    }

    @PutMapping("track")
    public ResponseEntity<?> updateTrack(@RequestBody Track track) {
        trackService.updateTrack(track);
        return new ResponseEntity<Track>(track, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<?> getTrackByName(@RequestParam(value="name") String name) {
        Track getTrack=(Track)trackService.findByName(name);
        return new ResponseEntity<Track>(getTrack,HttpStatus.OK);
    }
}
