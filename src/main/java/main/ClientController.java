package main;

import main.model.Client;
import main.model.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController
{
    @Autowired
    private ClientRepository clientRepository;



    @GetMapping("/client/")
    public List<Client> list()
    {
        Iterable<Client> clientIterable = clientRepository.findAll();
        ArrayList<Client> clients = new ArrayList<>();
        for(Client client : clientIterable)
        {
            clients.add(client);
        }
        return clients;
    }

    @PostMapping("/client/")
    public void add(Client client)
    {
        clientRepository.save(client);
    }
}
