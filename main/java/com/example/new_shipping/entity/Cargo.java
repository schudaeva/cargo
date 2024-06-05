package com.example.new_shipping.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue; // аннотация для работы со столбцами в скл, id указывается автоматически
import jakarta.persistence.GenerationType; // класс отвечающих за тип данных перечисление
import jakarta.persistence.Id; // указание на первичный ключ

@Entity //аннотация, укзание на то что класс является сущностью и относится к orm jpa
public class Cargo {
    private Long id;
    private String name;
    private String content;
    private String from_city;
    private String date_of_shipping;
    private String to_city;
    private String date_of_arrival;
    public Cargo(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public String getContent(){return content;}
    public void setContent(String content){this.content = content;}
    public String getFrom_city(){return from_city;}
    public void setFrom_city(String from_city ){this.from_city = from_city;}
    public String getDate_of_shipping() {return date_of_shipping;}
    public void setDate_of_shipping(String date_of_shipping){this.date_of_shipping = date_of_shipping;}
    public String getTo_city(){return to_city;}
    public void setTo_city(String to_city ){this.to_city = to_city;}
    public String getDate_of_arrival() {return date_of_arrival;}
    public void setDate_of_arrival(String date_of_arrival){this.date_of_arrival = date_of_arrival;}
}

