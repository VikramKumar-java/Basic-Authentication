package com.Secutity.SpringSecurity.Controller;

import com.Secutity.SpringSecurity.Entity.Room;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @PostMapping
    @PreAuthorize("hasAuthority('ROOM_ADD')")
//    @PostAuthorize("returnObject.assignedTo == authentication.name")
    public String addRooms(){
        return "room added";

    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROOM_VIEW')")
    public Room getRoomById(@PathVariable Long id){
        return new Room(id,"vicky");
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROOM_VIEW_ALL')")
    public String getRooms(){
        return "Got ALl Rooms";
    }
}
