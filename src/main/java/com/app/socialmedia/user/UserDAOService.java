package com.app.socialmedia.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDAOService {
    //JPA/Hibernate > database access

    private static List<User> users = new ArrayList<>();
    private static int userCount = 0;

    static {
        users.add(new User(++userCount,"Shiva", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount,"Kumar", LocalDate.now().minusMonths(2). minusYears(25)));
        users.add(new User(++userCount,"Suresh", LocalDate.now().minusYears(60)));
    }

    public List<User> findAll(){
        return users;
    }
    public User save(User user){
        user.setId(++userCount);
        users.add(user);
        return user;
    }
    public User findOne(int id){
        Predicate<User> predicate = (user)->user.getId() == id;
        User user = users.stream().filter(predicate).findFirst().orElse(null);
        return user;
    }

    public void deleteById(int id){
        Predicate<User> predicate = (user)->user.getId() == id;
        users.removeIf(predicate);
    }
}
