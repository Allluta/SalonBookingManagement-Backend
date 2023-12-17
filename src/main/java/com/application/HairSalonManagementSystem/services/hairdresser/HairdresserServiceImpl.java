package com.application.HairSalonManagementSystem.services.hairdresser;

import com.application.HairSalonManagementSystem.entities.Hairdresser;
import com.application.HairSalonManagementSystem.repositories.HairdresserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HairdresserServiceImpl {
    private final HairdresserRepository hairdresserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public HairdresserServiceImpl(HairdresserRepository hairdresserRepository) {
        this.hairdresserRepository = hairdresserRepository;
    }

    public Hairdresser createHairdresser(Hairdresser hairdresser) {

        String encodedPassword = passwordEncoder.encode(hairdresser.getPassword());
        hairdresser.setPassword(encodedPassword);

        hairdresser.setEmail(hairdresser.getEmail());

        return hairdresserRepository.save(hairdresser);
    }

    public List<Hairdresser> getAllHairdressers() {
        return hairdresserRepository.findAll();
    }

    public Hairdresser getHairdresserById(Long id) {
        Optional<Hairdresser> optionalHairdresser = hairdresserRepository.findById(id);
        if (optionalHairdresser.isPresent()) {
            return optionalHairdresser.get();
        } else {
            throw new EntityNotFoundException("Fryzjer o podanym ID nie istnieje");
        }
    }

    public Hairdresser updateHairdresser(Long id, Hairdresser updatedHairdresser) {
        Optional<Hairdresser> existingHairdresser = hairdresserRepository.findById(id);
        if (existingHairdresser.isPresent()) {
            Hairdresser hairdresser = existingHairdresser.get();
            hairdresser.setName(updatedHairdresser.getName());
            hairdresser.setDescription(updatedHairdresser.getDescription());
            hairdresser.setExperience(updatedHairdresser.getExperience());
            hairdresser.setWorkingHours(updatedHairdresser.getWorkingHours());
            hairdresser.setPassword(updatedHairdresser.getPassword());
            return hairdresserRepository.save(hairdresser);
        } else {
            throw new EntityNotFoundException("Profil fryzjera o podanym ID nie istnieje");

        }
    }

    public void deleteHairdresser(Long id) {
        hairdresserRepository.deleteById(id);
    }
}


