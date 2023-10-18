package com.application.ScholManagementSystem.services.hairdresser;

import com.application.ScholManagementSystem.entities.Hairdresser;
import com.application.ScholManagementSystem.repositories.HairdresserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HairdresserServiceImpl {
    private final HairdresserRepository hairdresserRepository;

    public HairdresserServiceImpl(HairdresserRepository hairdresserRepository) {
        this.hairdresserRepository = hairdresserRepository;
    }
    public Hairdresser createHairdresser(Hairdresser hairdresser) {
        // Tutaj możesz dodać logikę walidacji danych, jeśli jest to wymagane
        return hairdresserRepository.save(hairdresser);
    }

    public List<Hairdresser> getAllHairdressers() {
        return hairdresserRepository.findAll();
    }

    public Optional<Hairdresser> getHairdresserById(Long id) {
        return hairdresserRepository.findById(id);
    }

    public Hairdresser updateHairdresser(Long id, Hairdresser updatedHairdresser) {
        Optional<Hairdresser> existingHairdresser = hairdresserRepository.findById(id);
        if (existingHairdresser.isPresent()) {
            Hairdresser hairdresser = existingHairdresser.get();
            // Tutaj możesz zaimplementować logikę aktualizacji danych profilu fryzjera
            hairdresser.setName(updatedHairdresser.getName());
            hairdresser.setDescription(updatedHairdresser.getDescription());
            hairdresser.setExperience(updatedHairdresser.getExperience());
            hairdresser.setWorkingHours(updatedHairdresser.getWorkingHours());
            return hairdresserRepository.save(hairdresser);
        } else {
            throw new EntityNotFoundException("Profil fryzjera o podanym ID nie istnieje");

        }
    }

    public void deleteHairdresser(Long id) {
        hairdresserRepository.deleteById(id);
    }
}
