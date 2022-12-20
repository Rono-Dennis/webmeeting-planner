package com.example.WebMeetingPlanner.controller;


import com.example.WebMeetingPlanner.Model.*;
import com.example.WebMeetingPlanner.Repository.OrganisationRepository;
import com.example.WebMeetingPlanner.Repository.RoomsRepository;
import com.example.WebMeetingPlanner.Service.CustomUserDetails;
import com.example.WebMeetingPlanner.Service.DatesService;
import com.example.WebMeetingPlanner.Service.MeetingService;
import com.example.WebMeetingPlanner.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class DatesIntervalController {
//	private static final String VIEW_DATES = "meeting_formm";
//	private static final String VIEW_INTERVAL = "interval";

	@Autowired
	private DatesService datesService;

	@Autowired
	private UserService userService;

	@Autowired
	private MeetingService meetingService;

	@Autowired
	private RoomsRepository roomsRepository;

	@Autowired
	private OrganisationRepository organisationRepository;


	@GetMapping("/schedule_meeting")
	public String redirectToDates(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) {

		List<Organization> listorganisation = organisationRepository.findAll();
		model.addAttribute("listorganisation", listorganisation);

		model.addAttribute("meeting", new Scheduling());

		String email = loggedUser.getUsername();
		User user = userService.getByEmail(email);

		long organizationId = user.getOrganization().getOrganisation_id();


		List<TracomRooms> listrooms = meetingService.getRoomsOrganization(organizationId);


		model.addAttribute("listsrooms", listrooms);

		return "meeting";

	}

//	@PostMapping("/meetingdetails")
//	public String SaveMeeting(Scheduling meeting) {
//		meetingRepository.save(meeting);
//		return "meetingdetails";
//	}

}




//	@GetMapping("/dates")
//	public String showDatesForm(Dates dates) {
//		return "form";
//
//	}



//	@PostMapping("/interval")
//	public String calculateInterval(Dates dates, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			return "form";
////					VIEW_DATES;
//		}
//		long interval = datesService.calculateDateInterval(dates. getStartDate(), dates.getEndDate());
//		model.addAttribute("interval", interval);

//		meetingRepository.save(startDate);
//		return "form";
//				VIEW_INTERVAL;
//	}


//}
