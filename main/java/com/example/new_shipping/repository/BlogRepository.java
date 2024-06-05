package com.example.new_shipping.repository;



import java.util.List;
// Repository – указывает, что класс используется для задания перечня необходимых работ по поиску, получению и сохранению данных.
import com.example.new_shipping.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
// JpaRepository – это интерфейс фреймворка Spring Data предоставляющий набор стандартных методов JPA для работы с БД.
import org.springframework.data.jpa.repository.Query; // аннотация скл запроса
public interface BlogRepository extends JpaRepository<Blog, Long>{
    @Query("SELECT p FROM Blog p WHERE CONCAT(p.title, ' ', p.pub_date, ' ', p.bl_text, ' ', p.pic, ' ') LIKE %?1%")
    List<Blog> search(String keyword);
}
// p - параметр, взаимодействуем с классом device

