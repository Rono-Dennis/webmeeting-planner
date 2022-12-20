package com.example.WebMeetingPlanner.Service;

import com.example.WebMeetingPlanner.Model.Organization;
import com.example.WebMeetingPlanner.Model.Role;
import com.example.WebMeetingPlanner.Model.User;
import com.example.WebMeetingPlanner.Repository.OrganisationRepository;
import com.example.WebMeetingPlanner.Repository.RoleRepository;
import com.example.WebMeetingPlanner.Repository.RoomsRepository;
import com.example.WebMeetingPlanner.Repository.UserRepository;
import com.example.WebMeetingPlanner.Exceptions.UserNotFoundExceptionn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserService {

    @Autowired
    private  BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepo;
    public static final int MAX_FAILED_ATTEMPTS = 5;
    public static final int USER_PER_PAGE = 3;
    private static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000; // 24 hours

    @Autowired
    private UserRepository repo;

    @Autowired
    private OrganisationRepository organisationRepository;


    @Autowired
    RoleRepository roleRepo;

    @Autowired
    RoomsRepository roomsRepository;


    public void registerDefaultUser(User user) {
        Role roleUser = roleRepo.findByName("User");
        user.addRole(roleUser);

        userRepo.save(user);
    }

    public void save(User user) {
        repo.save(user);
    }

    public void saveUserWithDefaultRole(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
          String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

//        user.setAccountNonLocked(true);
        user.setEnabled(true);
        Role roleuser = roleRepo.findByName("User");
        user.addRole(roleuser);

        userRepo.save(user);

    }

    public void Saave(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

         userRepo.save(user);
    }

    public List<User> listAll(){
        return userRepo.findAll();
    }

    public User get(Long id)
    {
        return userRepo.findById(id).get();
    }


    public List<Role> getRoles(){
        return roleRepo.findAll();
    }

    public List<Organization> getOrganization(){
        return organisationRepository.findAll();
    }


    public void updateResetPasswordToken(String token, String email) throws UserNotFoundExceptionn {
        User user = userRepo.findByEmail(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepo.save(user);
        } else {
            throw new UserNotFoundExceptionn("Could not find any User with the email " + email);
        }
    }

    public User getByResetPasswordToken(String token) {
        return userRepo.findByResetPasswordToken(token);
    }



    public void updateResetPasswordTokens(String tokens, String email) throws UserNotFoundExceptionn {
        User user = userRepo.findByEmail(email);
        if (user != null) {
            user.setSetPasswordToken(tokens);
            userRepo.save(user);
        } else {
            throw new UserNotFoundExceptionn("Could not find any User with the email " + email);
        }
    }

    public void createUser(User user){
        Optional<User> userByEmail = Optional.ofNullable(userRepo.findByEmail(user.getEmail()));
        if (userByEmail.isPresent()){
            try {
                throw new UserNotFoundExceptionn("Account with the same email exists!"+userByEmail);
            } catch (UserNotFoundExceptionn e) {
                e.printStackTrace();
            }
        }
        Role roleuser = roleRepo.findByName("User");
        user.addRole(roleuser);
        userRepo.save(user);
    }

    public void checkemail(String email) throws UserNotFoundExceptionn {
        User user = userRepo.findByEmail(email);


            if (user != null) {
                throw new UserNotFoundExceptionn("This email exist in the database " + email);


            } else if (user == null){
                Role roleuser = roleRepo.findByName("User");
                user.addRole(roleuser);
                repo.save(user);
            }


    }

    public User getBySetPasswordTokens(String tokens) {
        return userRepo.findBySetPasswordToken(tokens);
    }

    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

//        user.setAccountNonLocked(true);
        user.setEnabled(true);
        user.setResetPasswordToken(null);
        userRepo.save(user);
    }

    public User getByEmail(String email) {
      return userRepo.getUserByEmail(email);
    }

    public List<User> getUsersByOrganisation(long organisation_id) {
        List<User> users = userRepo.findUsersInOrganization(organisation_id);
         return users;
    }

    public long numberOfRoomsInOrganization(long organisation_id) {
        return roomsRepository.numberOfRoomsInOrganization(organisation_id);
    }

    public List<User> getOrganizationUsers(long organisation_id) {
        return repo.OrganizationUsers(organisation_id);
    }


    public void increaseFailedAttempts(User user) {
        int newFailAttempt = user.getFailedAttempt() +1;
        repo.updateFailedAttempts(newFailAttempt, user.getEmail());
    }

    public void lock(User user) {
        user.setAccountNonLocked(false);
        user.setLockTime(new Date());

        repo.save(user);
    }


}
