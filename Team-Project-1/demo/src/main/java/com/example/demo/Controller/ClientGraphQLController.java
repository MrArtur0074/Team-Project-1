package com.example.demo.Controller;

import com.example.demo.Model.Client;
import com.example.demo.Service.ClientService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ClientGraphQLController {
    private final ClientService clientService;

    public ClientGraphQLController(ClientService clientService) {
        this.clientService = clientService;
    }

    @QueryMapping
    public List<Client> clients() {
        return clientService.getAllClients();
    }

    @QueryMapping
    public Client client(@Argument Long id) {
        return clientService.getClientById(id);
    }

    @MutationMapping
    public Client createClient(@Argument String name, @Argument String email) {
        return clientService.createClient(name, email);
    }
}