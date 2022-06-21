package main;

import main.model.Client;
import main.model.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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

    @RequestMapping(value = "/client", params = {"name", "surname", "patronymic", "email", "phoneNumber"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Client> search(@RequestParam(value = "name") String name,
    @RequestParam(value = "surname") String surname,
    @RequestParam(value = "patronymic") String patronymic,
    @RequestParam(value = "email") String email,
    @RequestParam(value = "phoneNumber") String phoneNumber)
    {

        Iterable<Client> clientIterable = clientRepository.findAll();
        ArrayList<Client> clients = new ArrayList<>();
        ArrayList<Client> clientsList = new ArrayList<>();
        for(Client client : clientIterable)
        {
            clients.add(client);
        }
        for(int i = 0; i < clients.size(); )
        {
            if(clients.get(i).getName().equals(name)
                    && clients.get(i).getSurname().equals(surname)
                    && clients.get(i).getPatronymic().equals(patronymic)
                    && clients.get(i).getEmail().equals(email)
                    && clients.get(i).getPhoneNumber().equals(phoneNumber))
            {
                i++;
                continue;
            }
            clients.remove(i);
        }
        return clients;
    }
    @DeleteMapping("/client/{id}")
    public void delete(@PathVariable int id)
    {
        clientRepository.deleteById(id);
    }

    @PutMapping("/client/{id}")
    public void updateClient(@PathVariable Integer id, String name, String surname, String patronymic, String email, String phoneNumber, String birthday)
    {
        Optional<Client> clients = clientRepository.findById(id);
        Client client = clients.get();
        client.setName(name);
        client.setSurname(surname);
        client.setPatronymic(patronymic);
        client.setEmail(email);
        client.setPhoneNumber(phoneNumber);
        client.setBirthday(birthday);
        clientRepository.save(client);
    }
}
