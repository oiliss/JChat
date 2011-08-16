/**********************************************************************
 * [ Четверг, Июль, 2011.07.21 15:25:09 Овчарик Игорь (oin) ]
 *   E-Mail: in.oiliss@gmail.com
 *   ICQ: 262-345-645
 *   Skype: oiliss
 *
 * Работа с сессией.
 *
 **********************************************************************
 */
package service.session;

import db_objects.User;
import hibernate.UserHelper;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author vernet
 */
public class SessionService {

	public static void set(HttpServletRequest hsr, String name, String value) {
		HttpSession session = hsr.getSession();
		session.setAttribute(name, value);
	}

	public static void remove(HttpServletRequest hsr, String name) {
		HttpSession session = hsr.getSession();
		session.removeAttribute(name);
	}

	public static String get(HttpServletRequest hsr, String name) {
		String s;
		HttpSession session = hsr.getSession();
		s = (String) session.getAttribute(name);
		return s;
	}

	public static String content(HttpServletRequest hsr) {
		String s = "<font size=+1><b><u>Session</u></b>:</font><br />\n";
		HttpSession session = hsr.getSession();
		Enumeration names = session.getAttributeNames();
		String name;
		for (Enumeration<String> e = names; e.hasMoreElements();) {
			name = e.nextElement();
			s += "[<b>" + name + "</b>] = " + session.getAttribute(name) + "<br />\n";
		}
		return s;
	}

	public static String request(HttpServletRequest hsr) {
		String s = "<font size=+1><b><u>Request</u></b>:</font><br />\n";
		Enumeration names;
		String name;
		s += "<b><u>Attributes</u></b>:<br />\n";
		names = hsr.getAttributeNames();
		for (Enumeration<String> e = names; e.hasMoreElements();) {
			name = e.nextElement();
			s += "[<b>" + name + "</b>] = " + hsr.getAttribute(name) + "<br />\n";
		}
		s += "<b><u>Parameters</u></b>:<br />\n";
		names = hsr.getParameterNames();
		for (Enumeration<String> e = names; e.hasMoreElements();) {
			name = e.nextElement();
			s += "[<b>" + name + "</b>] = " + hsr.getParameter(name) + "<br />\n";
		}
		return s;
	}

	public static String setSessionUserId(HttpServletRequest hsr) {
		String secUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		HttpSession session = hsr.getSession();
		User user;
		////String lastUserName = (String) httpSession.getAttribute("SPRING_SECURITY_LAST_USERNAME");
		Long userId = (Long) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");
		if (((secUserName == null) || secUserName.equals("roleAnonymous")) && (userId != null)) {
			session.removeAttribute("userId");
			session.removeAttribute("userName");
		} else if ((secUserName != null) && !secUserName.equals("roleAnonymous")
						&& ((userName == null) || !userName.equals(secUserName))) {
			user = new UserHelper().getUserByName(secUserName);
			if (user != null) {
				userId = user.getId();
				session.setAttribute("userId", userId);
				session.setAttribute("userName", secUserName);
			} else {
				session.removeAttribute("userId");
				session.removeAttribute("userName");
			}
		}
		String ret_str = "SecurityContextHolder.getContext().getAuthentication().getName() = " + secUserName + "<br/>";
		return ret_str;
	}

	public static Long getSessionUserId(HttpServletRequest hsr) {
		HttpSession httpSession = hsr.getSession();
		Long userId = (Long) httpSession.getAttribute("userId");
		return userId;
	}
}
