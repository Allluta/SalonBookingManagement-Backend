package com.application.ScholManagementSystem.services.service;

import com.application.ScholManagementSystem.entities.Service;
import com.application.ScholManagementSystem.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceServiceImpl {

    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public Service getServiceById(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }

    public Service addService(Service service) {

        return serviceRepository.save(service);
    }

    public Service updateService(Long id, Service service) {
        // Dodaj logikę aktualizacji i zaktualizuj usługę
        return serviceRepository.save(service);
    }

    public void deleteService(Long id) {

        serviceRepository.deleteById(id);
    }
}