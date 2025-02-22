package com.example.demo.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne  // Связь с Client
    @JoinColumn(name = "client_id") // Имя колонки в БД (FK)
    private Client client;

    @ManyToOne  // Связь с Master
    @JoinColumn(name = "master_id") // Имя колонки в БД (FK)
    private Master master;

    @ManyToMany  // Связь с Item
    @JoinTable(
            name = "booking_item", // Имя таблицы-связки
            joinColumns = @JoinColumn(name = "booking_id"), // Колонка для Booking
            inverseJoinColumns = @JoinColumn(name = "item_id")  // Колонка для Item
    )
    private List<Item> items;

    private String bookingDate;

    // Конструктор без аргументов (важно для Hibernate)
    public Booking() {}

    // Геттеры и сеттеры для всех полей (id, client, master, items, bookingDate)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }
}