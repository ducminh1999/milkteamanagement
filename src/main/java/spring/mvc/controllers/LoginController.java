package spring.mvc.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.mvc.dto.UserDto;
import spring.mvc.services.UserService;
import spring.mvc.utils.FormatDate;
import spring.mvc.utils.ValidateData;

@Controller
public class LoginController {
	@Autowired
	UserService userService;

	public LoginController() {
		System.out.println("LoginControllers");
	}

	@RequestMapping(path = { "/loginAdmin" }, method = { RequestMethod.GET })
	public String showLogin() {
		return "login";
	}

	@RequestMapping(path = { "/loginAdmin" }, method = { RequestMethod.POST })
	public String checkLogin(Model model, UserDto userDto, HttpSession session) {
		String userName = userDto.getUserName();
		String password = userDto.getPassword();
		UserDto userInfo = userService.findOne(userName);
		if (userInfo != null && (userInfo.getRoleId() == 1 || userInfo.getRoleId() == 2)) {
			if (userInfo.getPassword().equals(password)) {
				if (!userInfo.isStatus()) {
					model.addAttribute("userName", userName);
					model.addAttribute("password", password);
					model.addAttribute("messageServer", "UserName has been locked!!!");
					return "login";
				}
				// Login Success
				session.setAttribute("loginUser", userInfo.getId());
				session.setAttribute("userName", userName);
				session.setAttribute("roleId", userInfo.getRoleId());
				System.out.println("Filter Info:\t" + new Date() + "\t/loginAdmin");
				return "redirect:" + "/admin/home";
			}
		}

		model.addAttribute("userName", userName);
		model.addAttribute("password", password);
		model.addAttribute("messageServer", "UserName or password invalid !!!");
		return "login";

	}

	@RequestMapping(path = { "/logoutAdmin" }, method = { RequestMethod.GET })
	public String logout(HttpSession session) {
		String userString = (String) session.getAttribute("userName");
		if (userString == null) {
			return "redirect:" + "/loginAdmin";
		}
		session.removeAttribute("loginUser");
		session.invalidate();
		System.out.println("Filter Info:\t" + new Date() + "\t/logoutAdmin");
		return "redirect:" + "/loginAdmin";
	}

//	@RequestMapping(path = { "/register" }, method = { RequestMethod.GET })
//	public String showRegister() {
//		return "register";
//	}
//
//	@RequestMapping(path = { "/register" }, method = { RequestMethod.POST })
//	public String checkRegister(HttpSession session, Model model, UserDto user, String rePassword) {
//		user.setCreateAt(FormatDate.format(new Date()));
//		if (userService.findOne(user.getEmail()) == null) {
//			if (ValidateData.validateEmail(user.getEmail())) {
//				userService.add(user);
//				return "redirect:" + "/login";
//			} else {
//				model.addAttribute("messageServer", "Email invalid");
//			}
//		} else {
//			model.addAttribute("messageServer", "Email already exists!!!");
//		}
//		model.addAttribute("userName", user.getUserName());
//		model.addAttribute("email", user.getEmail());
//		model.addAttribute("password", user.getPassword());
//		model.addAttribute("rePassword", rePassword);
//		System.out.println("Filter Info:\t" + new Date() + "\t/SpringMvcXml/register");
//		return "register";
//	}

}
