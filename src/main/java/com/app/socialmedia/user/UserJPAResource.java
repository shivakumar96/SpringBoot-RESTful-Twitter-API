package com.app.socialmedia.user;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserJPAResource {
    //private UserDAOService service;
    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserJPAResource(UserRepository userRepository, PostRepository postRepository){
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    //get all users
    @GetMapping(path = "/jpa/users")
    public List<User> retrieveAllUsers(){
        return this.userRepository.findAll();
    }

    //get user details based on id
    @GetMapping(path = "/jpa/users/{id}")
    public EntityModel<User> retrieveUserDetails(@PathVariable int id) throws UserNotFoundException{
        User user = this.userRepository.findById(id).orElse(null);
        if (user == null)
            throw new UserNotFoundException("{id}:"+id);
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = WebMvcLinkBuilder
                                    .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    //post user details based on id
    @GetMapping(path = "/jpa/users/{id}/posts")
    public List<Post> retrieveUserPosts(@PathVariable int id) throws UserNotFoundException{
        User user = this.userRepository.findById(id).orElse(null);
        if (user == null)
            throw new UserNotFoundException("{id}:"+id);

        return user.getPosts();
    }

    //create a user
    @PostMapping(path = "/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        //return uri of the user which is created.
        URI location = ServletUriComponentsBuilder
                           .fromCurrentRequest()
                           .path("/{id}")
                           .buildAndExpand(savedUser.getId())
                           .toUri();
        return ResponseEntity.created(location).build();
    }


    //post user posts details based on id
    @PostMapping(path = "/jpa/users/{id}/posts")
    public ResponseEntity<Object> createUserPost(@PathVariable int id, @Valid @RequestBody Post post) throws UserNotFoundException{
        User user = this.userRepository.findById(id).orElse(null);
        if (user == null)
            throw new UserNotFoundException("{id}:"+id);

        post.setUser(user);
        Post savedPost = postRepository.save(post);


        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }


    //delete user
    @DeleteMapping(path = "/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

}
