package com.example.WebMeetingPlanner.controller;



import com.example.WebMeetingPlanner.Exceptions.UserNotFoundExceptionn;
import com.example.WebMeetingPlanner.MailService.ResetPassword;
import com.example.WebMeetingPlanner.Model.Role;
import com.example.WebMeetingPlanner.Model.Scheduling;
import com.example.WebMeetingPlanner.Model.User;
import com.example.WebMeetingPlanner.Notification.Timing;
import com.example.WebMeetingPlanner.Repository.MeetingsRepository;
import com.example.WebMeetingPlanner.Repository.OrganisationRepository;
import com.example.WebMeetingPlanner.Repository.RoomsRepository;
import com.example.WebMeetingPlanner.Repository.UserRepository;
import com.example.WebMeetingPlanner.Service.CustomUserDetails;
import com.example.WebMeetingPlanner.Service.MeetingService;
import com.example.WebMeetingPlanner.Service.UserService;
import com.example.WebMeetingPlanner.SmsService.TwilioConfiguration;
import com.example.WebMeetingPlanner.Utilities.Utility;
import com.example.WebMeetingPlanner.Utilities.UtilityPassword;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

import java.time.*;
import java.util.*;

import static java.time.LocalDateTime.of;

@RestController
public class AppController {

    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository repo;

    @Autowired
    private MeetingsRepository meetingsRepository;

    @Autowired
    private RoomsRepository roomsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private Timing timing;

    @Autowired
    private ResetPassword resetPassword;

    @Autowired
    public AppController(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model){
        User user = userService.get(id);
        List<Role> listRoles = userService.getRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "user_form";
    }


    @RequestMapping(value = "/users/save", method = { RequestMethod.GET, RequestMethod.POST })
    public String saveUser(User user){
        userService.Saave(user);
        return "index";
    }

    @GetMapping("/index")
    public String viewHomePage(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) throws Exception {

       return "index";

    }
    @GetMapping("/homepage")
    public String meetingsUpdates(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model, User using, String mailing) throws Exception
    {
        String email = loggedUser.getUsername();
        User user =  userService.getByEmail(email);

        long Organisation_id = user.getOrganization().getOrganisation_id();


        List<Scheduling> schedules = meetingService.getOrganizationMeetings(Organisation_id);
        long meetingNumber= meetingsRepository.numberOfMeetingsInOrganization(Organisation_id);
        long rooms = userService.numberOfRoomsInOrganization(Organisation_id);
        long users = repo.numberOfUsersInOrganization(Organisation_id);

        List<Scheduling> schedule = meetingService.getOrganizationMeetings(Organisation_id);
        schedule.forEach(m -> {

            LocalDateTime eldt = schedule.get(0).getStartDate();

            ZonedDateTime zdt = eldt.atZone(ZoneId.systemDefault());
            Date output = Date.from(zdt.toInstant());

            System.out.println("Upcoming meeting on "+ eldt);
            System.out.println("Upcoming meeting on "+ output);
            Date dte= DateUtils.addDays( new Date(),+2);
            model.addAttribute("endDate", output);
            System.out.println("endate" + dte);


            LocalDateTime ldt = schedule.get(0).getStartDate().minusMinutes(15);

            long sendNotification = ldt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() -
                    LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            List<User> MailUsers = userService.getUsersByOrganisation(Organisation_id);

            MailUsers.forEach(n -> {
                System.out.println(" Meeting user is " + m.getUsers());

                String mails = n.getEmail();
                String phoneNumber = n.getPhoneNumber();

                timing.TimeNow(mails,phoneNumber,sendNotification);


            });

            model.addAttribute("listMeeting", schedules);
            model.addAttribute("meetingCount", meetingNumber);
            model.addAttribute("roomsCount", rooms);
            model.addAttribute("userCount",users);
            model.addAttribute("meetingschedules", new Scheduling());
        });
        return "indexx";

    }


    @GetMapping("/manpage")
    public String viewAdminPage()
    {
        return "user_form";
    }

    @GetMapping("/datatable")
    public String Datable()
    {
        return "datatables";
    }

    @GetMapping("/fixedtime")
    public String timecountdown()
    {
        return "indexx";
    }

    @GetMapping("/list_users")
    public String viewUsersList(@AuthenticationPrincipal CustomUserDetails loggedUser,Model model)
    {
        String email = loggedUser.getUsername();
        User user =  userService.getByEmail(email);

        long Organisation_id = user.getOrganization().getOrganisation_id();
        List<User> listUsers = userService.getOrganizationUsers(Organisation_id);
//        List<User> listUsers=userService.listAll();
        model.addAttribute("listUsers",listUsers);
        return "register_success";
    }

    @GetMapping("/add_users")
    public String addUser(Model model){

        model.addAttribute("user", new User());

        return "add_users";
    }

    @PostMapping("/process")
    public String adduser(User user)
    {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String encodedpassword=encoder.encode(user.getPassword());
        user.setPassword(encodedpassword);
        user.setEnabled(true);

        repo.save(user);
        return "forget_password";
    }



    @GetMapping("/forgot_password")
    public String showForgotPasswordForm(Model model) {
        model.addAttribute("pageTitle","forgot_password");
        return "forgot_password";
    }



    @PostMapping("/forget_password")
    public String UserEnterPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String tokens = RandomString.make(30);


        try {
            userService.updateResetPasswordTokens(tokens, email);
            String resetPasswordLink = UtilityPassword.getSiteURL(request) + "/reset_password?token=" + tokens;

            resetPassword.sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "We have sent a set password link to the user email. Please check.");

        } catch (UserNotFoundExceptionn ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error",  "error while sending email");
        }

        return "forget_password";
    }


    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = RandomString.make(255);


        try {
            userService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;

            resetPassword.sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");

        } catch (UserNotFoundExceptionn ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error",  "error while sending email");
        }

        return "forgot_password";
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        User user = userService.getByResetPasswordToken(token);
        model.addAttribute("token", token);

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        }

        return "reset_password_form";
    }

    @GetMapping("/reset_passwords")
    public String showResetPasswordForms(@Param(value = "token") String tokens, Model model) {
        User user = userService.getBySetPasswordTokens(tokens);
        model.addAttribute("token", tokens);

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "messages";
        }

        return "reset_password_forms";
    }
    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = userService.getByResetPasswordToken(token);
        model.addAttribute("title", "Reset your password");

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        } else {
            userService.updatePassword(user, password);

            model.addAttribute("message", "You have successfully changed your password.");
        }

        return "message";
    }

    @PostMapping("/reset_passwords")
    public String processResetPasswords(HttpServletRequest request, Model model) {
        String tokens = request.getParameter("token");
        String password = request.getParameter("password");

        User user = userService.getBySetPasswordTokens(tokens);
        model.addAttribute("title", "Reset your password");

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "messages";
        } else {
            userService.updatePassword(user, password);

            model.addAttribute("message", "You have successfully set your password.");
        }

        return "messages";
    }

}
