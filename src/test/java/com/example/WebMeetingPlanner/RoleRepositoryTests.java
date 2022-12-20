package com.example.WebMeetingPlanner;

import com.example.WebMeetingPlanner.Model.Organization;
import com.example.WebMeetingPlanner.Model.Role;
import com.example.WebMeetingPlanner.Model.User;
import com.example.WebMeetingPlanner.Repository.OrganisationRepository;
import com.example.WebMeetingPlanner.Repository.RoleRepository;
import com.example.WebMeetingPlanner.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    RoleRepository repo;

    @Autowired
    OrganisationRepository organisationRepository;

    @Test
    public void testCreateRoles() {
        Role user = new Role("User");
        Role admin = new Role("Admin");
        Role customer = new Role("Organisation Officer");

        repo.saveAll(List.of(user, admin, customer));

        List<Role> listRoles = repo.findAll();

        assertThat(listRoles.size()).isEqualTo(3);
    }

//    @Test
//    public void testCreateOrganisation() {
//        Organization tracom = new Organization("Tracom");
//        Organization pergamon = new Organization("Pergamon");
//
//        organisationRepository.saveAll(List.of(tracom, pergamon));
//
//        List<Organization> listOrganisation = organisationRepository.findAll();
//
//        assertThat(listOrganisation.size()).isEqualTo(2);
//    }


    @Test
    public void testAddRoleToNewUser() {
        Role roleAdmin = roleRepo.findByName("Admin");

        User user = new User();
        user.setEmail("ronodennis72@gmail.com");
        user.setPassword("987654321");
        user.setFirstName("Dennis");
        user.setLastName("Rono");
//        user.setOrganization("Tracom");
        user.setEnabled(true);
//        user.setAccountNonLocked(true);
        user.addRole(roleAdmin);
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String encodedpassword=encoder.encode(user.getPassword());
        user.setPassword(encodedpassword);
//        repo.save(user);

        User savedUser = userRepo.save(user);

        assertThat(savedUser.getRoles().size()).isEqualTo(1);
    }

    @Test
    public void testAddRoleToExistingUser() {
        User user = userRepo.findById(1L).get();
        Role roleUser = roleRepo.findByName("User");
        Role roleAdmin = new Role(2l);

        user.addRole(roleUser);
        user.addRole(roleAdmin);

        User savedUser = userRepo.save(user);

        assertThat(savedUser.getRoles().size()).isEqualTo(2);
    }

}
