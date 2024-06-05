package com.example.new_shipping.service;


import java.util.List;
import java.util.Optional;

import com.example.new_shipping.entity.Cargo;
import com.example.new_shipping.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired; // связь всех зависимостей
import org.springframework.stereotype.Service;
// Service – указывает, что класс является сервисом для реализации бизнес логики.

@Service
public class CargoService {
    @Autowired
    private CargoRepository repo;

    public List<Cargo> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    public Cargo save(Cargo cargo) {return repo.save(cargo);}

    public Cargo get(Long id) {
        return repo.findById(id).get();
    }

    public Optional<Cargo> findById(long id) {
        return repo.findById(id);
    }


    public void delete(Long id) {
        repo.deleteById(id);
    }
}
