package com.example.demo.Service;

import com.example.demo.Model.Booking;
import com.example.demo.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Получить все бронирования
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Получить бронирование по ID
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    // Создать новое бронирование
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    // Обновить существующее бронирование
    public Booking updateBooking(Long id, Booking bookingDetails) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            // Обновляем поля бронирования
            booking.setClient(bookingDetails.getClient());
            booking.setMaster(bookingDetails.getMaster());
            booking.setItems(bookingDetails.getItems());
            booking.setBookingDate(bookingDetails.getBookingDate());
            return bookingRepository.save(booking);
        } else {
            throw new RuntimeException("Booking not found with id: " + id);
        }
    }

    // Удалить бронирование по ID
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}