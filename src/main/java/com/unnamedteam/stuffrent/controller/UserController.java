package com.unnamedteam.stuffrent.controller;

import com.unnamedteam.stuffrent.exceptions.NotFoundException;
import com.unnamedteam.stuffrent.model.Contact;
import com.unnamedteam.stuffrent.repository.UserRepository;
import com.unnamedteam.stuffrent.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping
public class UserController {
    private AtomicLong id = new AtomicLong(0);
    @Autowired
    private UserRepository usersRepo;

    @GetMapping
    public Iterable<User> main(Map<String, Object> model) {
        Iterable<User> users = usersRepo.findAll();

        model.put("users", users);

        return users;
    }

    @PostMapping("registration")
    public String addUser(@RequestParam String username, @RequestParam String password,
                              Map<String, Object> model) {
        User user = new User();
        user.setId(id.incrementAndGet());
        user.setUsername(username);
        user.setPassword(password);
        usersRepo.save(user);

        List<User> users = usersRepo.findAll();

        model.put("users", users);

        return "saved";
    }

    @PostMapping("giveContacts")
    public String addContacts(@PathVariable String id, @RequestParam String firstName,
                      @RequestParam String lastName, @RequestParam String phoneNumber,
                      @RequestParam String address, Map<String, Object> model) {
        Contact contact = new Contact();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setPhoneNumber(phoneNumber);
        contact.setAddress(address);
        User user = findById(id);
        user.setContact(contact);
        return "updated";
    }

    @GetMapping("{id}")
    public String getOne(@PathVariable String id) throws NotFoundException{
        try{
            return findById(id).getUsername();
        } catch (NotFoundException e) {
            throw new NotFoundException("Can't find user with this id");
        }
    }
    @DeleteMapping("{delete}{id}")
    public String deleteUser(@PathVariable String id) throws NotFoundException{
        User user = findById(id);
        usersRepo.delete(user);
        return "User was deleted successfully";
    }
    public User findById(String id) throws NotFoundException{
        List<User> users = usersRepo.findAll();
        long idX = Long.valueOf(id);
        User user = users.stream()
                .filter(currentUser -> currentUser.getId().equals(idX))
                .findFirst()
                .orElseThrow(NotFoundException::new);
        return user;
    }
}
