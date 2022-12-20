package com.example.WebMeetingPlanner.controller;


import com.example.WebMeetingPlanner.Model.Organization;
import com.example.WebMeetingPlanner.Repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrganisationController {



    @Autowired
    private OrganisationRepository organisationRepository;




    @GetMapping("/Addorganisation")
    public String AddOrganisation(Model model) {
        model.addAttribute("organisation", new Organization());

        return "AddOrganisation";
    }




    @PostMapping("/Saveorganisation")
    public String OrganisationSave(Organization organisation)
    {
        organisationRepository.save(organisation);
        return "AddOrganisationSuccess";
    }





    @GetMapping("/Savedorganisation")
    public String AddOrganisations(Model model)
    {
        List<Organization> addorganisation=organisationRepository.findAll();
        model.addAttribute("listorganisation",addorganisation);

        return "savedorganisation";
    }


}
