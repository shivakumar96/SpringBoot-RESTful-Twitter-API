package com.app.socialmedia.user;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.ResponseEntity.created;

@RestController
public class UserResource {
    private UserDAOService service;

    public UserResource(UserDAOService service){
        this.service = service;
    }

    //get all users
    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers(){
        return this.service.findAll();
    }

    //get user details based on id
    @GetMapping(path = "/users/{id}")
    public EntityModel<User> retrieveAllUsers(@PathVariable int id) throws UserNotFoundException{
        User user = this.service.findOne(id);
        if (user == null)
            throw new UserNotFoundException("{id}:"+id);
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = WebMvcLinkBuilder
                                    .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    //create a user
    @PostMapping(path = "/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
        //return uri of the user which is created.
        URI location = ServletUriComponentsBuilder
                           .fromCurrentRequest()
                           .path("/{id}")
                           .buildAndExpand(savedUser.getId())
                           .toUri();
        return ResponseEntity.created(location).build();
    }

    //delete user
    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable int id) {
        service.deleteById(id);
    }

}
