package com.application.ScholManagementSystem.controllers;

import com.application.ScholManagementSystem.entities.Hairdresser;
import com.application.ScholManagementSystem.services.hairdresser.HairdresserServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hairdressers")

public class HairdresserController {
    private final HairdresserServiceImpl hairdresserServiceImpl;

    public HairdresserController(HairdresserServiceImpl hairdresserServiceImpl) {
        this.hairdresserServiceImpl = hairdresserServiceImpl;
    }


    @PostMapping
    public Hairdresser createHairdresser(@RequestBody Hairdresser hairdresser) {
        return hairdresserServiceImpl.createHairdresser(hairdresser);
    }

    @GetMapping
    public List<Hairdresser> getAllHairdressers() {
        return hairdresserServiceImpl.getAllHairdressers();
    }

    @PutMapping("/{id}")
    public Hairdresser updateHairdresser(@PathVariable Long id, @RequestBody Hairdresser hairdresser) {
        return hairdresserServiceImpl.updateHairdresser(id, hairdresser);
    }

    @DeleteMapping("/{id}")
    public void deleteHairdresser(@PathVariable Long id) {
        hairdresserServiceImpl.deleteHairdresser(id);
    }

}
