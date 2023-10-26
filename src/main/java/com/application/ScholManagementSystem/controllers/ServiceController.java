package com.application.ScholManagementSystem.controllers;

import com.application.ScholManagementSystem.entities.Service;

import com.application.ScholManagementSystem.services.service.ServiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private final ServiceServiceImpl serviceServiceImpl;

    @Autowired
    public ServiceController(ServiceServiceImpl serviceServiceImpl) {
        this.serviceServiceImpl = serviceServiceImpl;
    }

    @GetMapping
    public List<Service> getAllServices() {
        return serviceServiceImpl.getAllServices();
    }

    @GetMapping("/{id}")
    public Service getServiceById(@PathVariable Long id) {
        return serviceServiceImpl.getServiceById(id);
    }

    @PostMapping
    public Service addService(@RequestBody Service service) {
        return serviceServiceImpl.addService(service);
    }

    @PutMapping("/{id}")
    public Service updateService(@PathVariable Long id, @RequestBody Service service) {
        return serviceServiceImpl.updateService(id, service);
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Long id) {
        serviceServiceImpl.deleteService(id);
    }
}
