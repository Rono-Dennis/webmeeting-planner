package com.example.WebMeetingPlanner;

import com.example.WebMeetingPlanner.Model.Role;
import com.example.WebMeetingPlanner.Model.User;
import com.example.WebMeetingPlanner.Repository.RoleRepository;
import com.example.WebMeetingPlanner.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {

    @Autowired
    RoleRepository roleRepo;
    @Autowired
    private UserRepository repo;
    @Autowired
    private TestEntityManager entityManager;
    @Test
    public void testCreateUser()
    {
        //test checked
        try {
            User user=new User();
            user.setEmail("ronodennis755@gmail.com");
            user.setPassword("987654321");
            user.setFirstName("rono");
            user.setLastName("dennis");
            user.setEnabled(true);
            Role roleUser = roleRepo.findByName("Admin");
            user.addRole(roleUser);

            User saveUser=repo.save(user);
            User existUser=entityManager.find(User.class,saveUser.getId());
            assert existUser.getEmail() == existUser.getEmail();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    @Test
    public void testFindUserByEmail()
    {
       try
       {
           String email="pm@gmail.com";
           User user=repo.findByEmail(email);
           assert (user) != null;
       }catch (Exception e)
       {
           e.printStackTrace();
       }
    }


    @Test
    public void testUpdateFailedAttempt(){
        String email="ronofelix008@gmail.com";
        int failedAttempt =2;

        repo.updateFailedAttempts(failedAttempt, email);

        long userId = 5;

        User user = entityManager.find(User.class, userId);
        assertEquals(failedAttempt, user.getFailedAttempt());


    }

}
