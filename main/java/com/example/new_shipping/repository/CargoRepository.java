package com.example.new_shipping.repository;

import java.util.List;
// Repository – указывает, что класс используется для задания перечня необходимых работ по поиску, получению и сохранению данных.
import com.example.new_shipping.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
// JpaRepository – это интерфейс фреймворка Spring Data предоставляющий набор стандартных методов JPA для работы с БД.
import org.springframework.data.jpa.repository.Query; // аннотация скл запроса
public interface CargoRepository extends JpaRepository<Cargo, Long>{
    @Query("SELECT p FROM Cargo p WHERE CONCAT(p.id, ' ' , p.name, ' ', p.content, ' ', p.from_city, ' ', p.date_of_shipping, ' ', p.to_city, ' ', p.date_of_arrival) LIKE %?1%")
    List<Cargo> search(String keyword);
}
// p - параметр, взаимодействуем с классом device
