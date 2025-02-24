package com.example.demo.Service;

import com.example.demo.Model.Client;
import com.example.demo.Repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    // Конструктор для внедрения зависимости ClientRepository
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // Получить всех клиентов
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Получить клиента по ID
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    // Создать нового клиента
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    // Обновить существующего клиента
    public Client updateClient(Long id, Client clientDetails) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client != null) {
            // Обновляем поля клиента
            client.setName(clientDetails.getName());
            client.setEmail(clientDetails.getEmail());
            client.setPhone(clientDetails.getPhone());
            // Сохраняем обновлённого клиента
            return clientRepository.save(client);
        } else {
            // Если клиент не найден, выбрасываем исключение
            throw new RuntimeException("Client not found with id: " + id);
        }
    }

    // Удалить клиента по ID
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}