package com.unnamedteam.stuffrent.controller;


import com.unnamedteam.stuffrent.model.Thing;
import com.unnamedteam.stuffrent.model.ThingTaken;
import com.unnamedteam.stuffrent.service.ThingTakenService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ThingTakenController {
    private final ThingTakenService thingTakenService;

    @PostMapping(path = "/things/take", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ThingTaken take(@RequestBody ThingTaken thingTaken){
        return thingTakenService.save(thingTaken);
    }

    @GetMapping(path = "/things/taken")
    public List<ThingTaken> main(){
        return thingTakenService.getAllThingTaken();
    }

    @GetMapping(path = "/things/taken/{id}")
    public ThingTaken getThingTakenById(@PathVariable("id") long id){
        return thingTakenService.getThingTakenByThingId(id);
    }

    @DeleteMapping(path = "things/return/{thingTakenId}")
    public void retrieve(@PathVariable("thingTakenId") Long thingId){
        thingTakenService.delete(thingId);
    }
}

