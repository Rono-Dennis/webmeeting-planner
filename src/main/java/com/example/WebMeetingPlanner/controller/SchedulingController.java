package com.example.WebMeetingPlanner.controller;


import ch.qos.logback.classic.BasicConfigurator;
import com.example.WebMeetingPlanner.Model.*;
import com.example.WebMeetingPlanner.Repository.*;
import com.example.WebMeetingPlanner.Service.CustomUserDetails;
import com.example.WebMeetingPlanner.Service.DatesService;
import com.example.WebMeetingPlanner.Service.MeetingService;
import com.example.WebMeetingPlanner.Service.UserService;
import com.example.WebMeetingPlanner.Utilities.UtilityNotification;
import com.example.WebMeetingPlanner.Utilities.UtilityPassword;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Controller
public class SchedulingController {

//	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulingController.class);
//	BasicConfigurator.configure();
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private DatesService datesService;

	@Autowired
	private UserService userService;

	@Autowired
	private MeetingsRepository meetingsRepository;

	@Autowired
	private MeetingService meetingService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoomsRepository roomsRepository;

	@Autowired
	private OrganisationRepository organisationRepository;


	@GetMapping("/Scheduling")
	public String SchedulingMeeting(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) {
		List<Organization> listorganisation = organisationRepository.findAll();
		model.addAttribute("listorganisation", listorganisation);

		String email = loggedUser.getUsername();
		User user = userService.getByEmail(email);

		long Organisation_id = user.getOrganization().getOrganisation_id();


		List<TracomRooms> listrooms = meetingService.getRoomsOrganization(Organisation_id);
		List<User> users = userService.getUsersByOrganisation(Organisation_id);

//		List<User> users1 = meetingsRepository.findByUserId(1);

//		model.addAttribute("users1", users1);
		model.addAttribute("room", new TracomRooms());

		model.addAttribute("listsrooms", listrooms);
		model.addAttribute("listusers", users);

		model.addAttribute("meetingschedules", new Scheduling());

		return "schedule";
	}


	@PostMapping("/meetingSaveSuccess")
	public String MeetingSave(HttpServletRequest request,@AuthenticationPrincipal CustomUserDetails loggedUser, Scheduling scheduling)
	{
		String mail = loggedUser.getUsername();
		User user =  userService.getByEmail(mail);
//		String email = request.getParameter(mail);

		scheduling.setOrganization(user.getOrganization());
		meetingsRepository.save(scheduling);

		long Organisation_id = user.getOrganization().getOrganisation_id();
		List<Scheduling> schedules = meetingService.getOrganizationMeetings(Organisation_id);





//			LocalDateTime today = LocalDateTime.now();
			List<Scheduling> schedule = meetingsRepository.findMeetingByDateTime(Organisation_id);

			for (Scheduling v : schedule){
				LocalDateTime dates = v.getStartDate();

//				Date dates = v.getStartDate(System.currentTimeMillis()+ 10000);

//				Instant instant = dates.toInstant((ZoneOffset) ZoneId.systemDefault()) ;
//				 long millisSinceEpoch = instant.toEpochMilli() ;

//				timer.schedule(task, news);
				/** current time*/
//				LocalDateTime now = LocalDateTime.now();
//
//				String strNow = now.toString();
//				String strDate = dates.toString();
//				System.out.println(strDate);
//				System.out.println(strNow);

//				if(now.isEqual(dates)){
//					System.out.println(now);
//					System.out.println(dates);}

			}



		return "meetingSavedSuccess";
	}

	@GetMapping("/SaveMeeting")
	public String Scheduling(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) {

		String mail = loggedUser.getUsername();

		User user =  userService.getByEmail(mail);

		long Organisation_id = user.getOrganization().getOrganisation_id();

		List<Scheduling> schedules = meetingService.getOrganizationMeetings(Organisation_id);
        List<Scheduling> listMeeting = meetingsRepository.findAll();
//		try {
//			String currentTime = String.valueOf(schedules.getStartDate());
//			System.out.println("upcoming time is"+ currentTime);
//
//		}catch (IndexOutOfBoundsException e) {
//			System.out.println("Exception : "+e.getMessage());
//
//		}

		LocalDateTime today = LocalDateTime.now();
		List<Scheduling> schedule = meetingsRepository.findMeetingByDateTime(Organisation_id);

		schedule.forEach(m -> {
			LocalDateTime dates = m.getStartDate();

			/** current time*/
			LocalDateTime now = LocalDateTime.now();

			System.out.println(now);

			/** current datetime minus 15 minutes **/
			LocalDateTime tobe = now.minus(15, ChronoUnit.MINUTES);



//			if(dates.isEqual(now))
//			{
//				String notificationMessage = UtilityNotification.getSiteURL(request)+"/homepage?";
//
//				List<User> users = userService.getUsersByOrganisation(Organisation_id);

//				users.forEach(n -> {
//                 System.out.println("/n Meeting name is "+m.getUsers());

//					String emails = n.getEmail();
//					try {
//						sendEmail(email);
//					} catch (MessagingException e) {
//						e.printStackTrace();
//					} catch (UnsupportedEncodingException e) {
//						e.printStackTrace();
//					}
//        });

				// send notification
//			}
//			model.addAttribute("date", currentTime);
		});

//		Scheduling schedule = new Scheduling();
//		schedule.getStartDate();


		model.addAttribute("listMeeting", schedules);
		model.addAttribute("meetingschedules", new Scheduling());
		return "ScheduledMeeting";
	}

	private void sendEmail(String emails)
			throws MessagingException, UnsupportedEncodingException
	{

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("TracomMeetingPlanner@gmail.com", "Tracom/Pergamon");
		helper.setTo(emails);

		String subject = "This is the notification For the upcoming meeting";

		String content = "<p>Hello,</p>"
				+ "<p>You have been requested to attend which is going to start in 15 minutes time.</p>"
				+ "<p>Please adhere to the meeting rules:</p>"
				+ "<p>To check meetings, click here:</p>"
				+ "<br>"
				+ "<p>Ignore this email if does not concerns you, "
				+ "or you have not made the request.</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}


}




//			String date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getDate());
//
//			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
//			LocalDate date = new LocalDate.now();

//			schedule.addAll(1,dates);


//		Optional<Scheduling> matchingObject = Objects.stream().
//				filter(p -> p.email().equals("testemail")).
//				findFirst();


//		for (Scheduling m : schedule) {


//			LocalDate dates = m.getStartDate();
//			List<Scheduling> dates =

//			Date date = new Date(String.valueOf(dates[0]));
//			LocalTime[] time = m.getStartTime();

//			for(int i = 0; i < date.get(); i++) {}
//			if (!date || !time) {
//			}
//			model.addAttribute("date", date);
//			model.addAttribute("time", time);

//        System.out.println("/n Meeting name is "+m.getStartDate());
//		}

//		String doNotRunBetween="18:00:00,18:30:00";//read from props file
//		String[] hhmmss = downTime.split(",");
//		if(isCurrentTimeBetween(hhmmss[0], hhmmss[1])){
//			System.out.println("NOT OK TO RUN");
//		}else{
//			System.out.println("OK TO RUN");
//		}


//		List<Scheduling> listMeeting = meetingsRepository.findAll();

//		model.addAttribute("meetingschedules", listMeeting);
