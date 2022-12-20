package com.example.WebMeetingPlanner.controller;


import com.example.WebMeetingPlanner.Model.Organization;
import com.example.WebMeetingPlanner.Model.TracomRooms;
import com.example.WebMeetingPlanner.Model.User;
import com.example.WebMeetingPlanner.Repository.OrganisationRepository;
import com.example.WebMeetingPlanner.Repository.RoomsRepository;
import com.example.WebMeetingPlanner.Service.CustomUserDetails;
import com.example.WebMeetingPlanner.Service.MeetingService;
import com.example.WebMeetingPlanner.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RoomController {



    @Autowired
    private RoomsRepository roomsRepository;

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private UserService userService;


    @GetMapping("/Addroom")
    public String AddRoom(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) {

        String email = loggedUser.getUsername();
        User user =  userService.getByEmail(email);

        long Organisation_id = user.getOrganization().getOrganisation_id();

        List<Organization> listorganisation = organisationRepository.findOrganization(Organisation_id);

        model.addAttribute("listorganisation", listorganisation);

        model.addAttribute("room", new TracomRooms());

        return "Addrooms";
    }




    @PostMapping("/Saveroom")
    public String RoomSave(@AuthenticationPrincipal CustomUserDetails loggedUser, TracomRooms tracomRooms)
    {
        String email = loggedUser.getUsername();
        User user =  userService.getByEmail(email);

        tracomRooms.setOrganization(user.getOrganization());
        roomsRepository.save(tracomRooms);
        return "savedrooms";
    }

    @GetMapping("/Saveroom")
    public String AddRooms(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model)
    {
        String email = loggedUser.getUsername();
        User user =  userService.getByEmail(email);

        long Organisation_id = user.getOrganization().getOrganisation_id();

        List<TracomRooms> addrromm=roomsRepository.findAllByOrganization(Organisation_id);

        model.addAttribute("listrooms",addrromm);

        return "savedrooms";
    }

    @GetMapping("/Organization/edit/{id}")
    public String editOrganisation(@PathVariable("id") Long id, Model model)
{

    TracomRooms  tracomRooms = roomsRepository.findById(id).get();
    model.addAttribute("room",tracomRooms);

    List<Organization> listorganisation = organisationRepository.findAll();

    model.addAttribute("listorganisation", listorganisation);

    return "Addrooms";
}

    @GetMapping("/Organization/delete/{id}")
    public String deleteRoom(@PathVariable("id") Long id, Model model)
    {
        roomsRepository.deleteById(id);
        return "redirect:/Saveroom";
    }

}
