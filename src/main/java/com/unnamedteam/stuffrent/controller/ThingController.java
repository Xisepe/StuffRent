package com.unnamedteam.stuffrent.controller;

import com.unnamedteam.stuffrent.model.Thing;
import com.unnamedteam.stuffrent.service.ThingService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ThingController {
    private final ThingService thingService;

    @PostMapping(path = "/things/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Thing save(@RequestBody Thing thing){
        return thingService.save(thing);
    }

    @GetMapping(path = "/things")
    public List<Thing> main(){
        return thingService.getAllThings();
    }

    @GetMapping(path = "/things/{id}")
    public Thing getThingById(@PathVariable("id") long id){
        return thingService.getThingById(id);
    }

    @DeleteMapping(path = "things/delete/{thingId}")
    public void delete(@PathVariable("thingId") Long thingId){
        thingService.delete(thingId);
    }
}
