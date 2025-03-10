package com.example.demo.Service;

import com.example.demo.Model.Booking;
import com.example.demo.Repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Long id, Booking bookingDetails) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            booking.setClient(bookingDetails.getClient());
            booking.setMaster(bookingDetails.getMaster());
            booking.setItems(bookingDetails.getItems());
            booking.setBookingDate(bookingDetails.getBookingDate());
            return bookingRepository.save(booking);
        } else {
            throw new RuntimeException("Booking not found with id: " + id);
        }
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}