package com.example.demo.Controller;

import com.example.demo.Model.Booking;
import com.example.demo.Model.Client;
import com.example.demo.Model.Master;
import com.example.demo.Model.Item;
import com.example.demo.Service.BookingService;
import com.example.demo.Service.ClientService; // Импортируем ClientService
import com.example.demo.Service.MasterService; // Импортируем MasterService
import com.example.demo.Service.ItemService; // Импортируем ItemService
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookingGraphQLController {
    private final BookingService bookingService;
    private final ClientService clientService; // Добавляем ClientService
    private final MasterService masterService; // Добавляем MasterService
    private final ItemService itemService;

    public BookingGraphQLController(BookingService bookingService, ClientService clientService, MasterService masterService, ItemService itemService) {
        this.bookingService = bookingService;
        this.clientService = clientService;
        this.masterService = masterService;
        this.itemService = itemService;
    }

    // ... (другие методы)

    @MutationMapping
    public Booking createBooking(
            @Argument Long clientId,
            @Argument Long masterId,
            @Argument List<Long> itemIds,
            @Argument String bookingDate) {

        Booking booking = new Booking();

        Client client = clientService.getClientById(clientId);
        if (client == null) {
            throw new RuntimeException("Client not found");
        }
        booking.setClient(client);


        Master master = masterService.getMasterById(masterId);
        if (master == null) {
            throw new RuntimeException("Master not found");
        }
        booking.setMaster(master);


        List<Item> items = itemIds.stream()
                .map(itemId -> itemService.getItemById(itemId).orElse(null))
                .filter(item -> item != null)
                .collect(Collectors.toList());

        booking.setItems(items);

        booking.setBookingDate(bookingDate);

        return bookingService.createBooking(booking);
    }

    @MutationMapping
    public Booking updateBooking(
            @Argument Long id,
            @Argument Long clientId,
            @Argument Long masterId,
            @Argument List<Long> itemIds,
            @Argument String bookingDate) {

        Booking booking = bookingService.getBookingById(id);
        if (booking == null) {
            throw new RuntimeException("Booking not found");
        }

        Client client = clientService.getClientById(clientId);
        if (client == null) {
            throw new RuntimeException("Client not found");
        }
        booking.setClient(client);

        Master master = masterService.getMasterById(masterId);
        if (master == null) {
            throw new RuntimeException("Master not found");
        }
        booking.setMaster(master);

        List<Item> items = itemIds.stream()
                .map(itemId -> itemService.getItemById(itemId).orElse(null))
                .filter(item -> item != null)
                .collect(Collectors.toList());

        booking.setItems(items);

        booking.setBookingDate(bookingDate);

        return bookingService.createBooking(booking);
    }
}