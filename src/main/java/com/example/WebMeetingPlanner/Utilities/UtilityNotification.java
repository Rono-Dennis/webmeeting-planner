package com.example.WebMeetingPlanner.Utilities;

import javax.servlet.http.HttpServletRequest;

public class UtilityNotification {
    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
