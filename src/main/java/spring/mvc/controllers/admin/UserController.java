package spring.mvc.controllers.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.mvc.dto.AreaDto;
import spring.mvc.dto.BillDto;
import spring.mvc.dto.ProductDto;
import spring.mvc.dto.UserDto;
import spring.mvc.services.AreaService;
import spring.mvc.services.BillService;
import spring.mvc.services.ProductService;
import spring.mvc.services.UserService;
import spring.mvc.utils.FormatDate;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	ProductService productService;
	@Autowired
	AreaService areaService;
	@Autowired
	BillService billService;

	public UserController() {
		System.out.println("UserControllers");
	}

//	@RequestMapping(path = { "/home" }, method = { RequestMethod.GET })
//	public String home(HttpSession session) {
//		return "index";
//	}
	@RequestMapping(path = { "/admin/home" }, method = { RequestMethod.GET })
	public String getHome(Model model) {
		int countEmployee = userService.countEmployee();
		model.addAttribute("countEmployee", countEmployee);
		List<ProductDto> listPro = productService.findAll();
		model.addAttribute("countProduct", listPro.size());
		List<AreaDto> listArea = areaService.findAll();
		model.addAttribute("countArea", listArea.size());
		List<BillDto> listBill = billService.findAll();
		model.addAttribute("countBill", listBill.size());
		String strDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		long sumBillMonth = billService.sumTotalMonth(strDate.substring(3, 5));
		model.addAttribute("sumBillMonth", sumBillMonth);
		long sumBillDay = billService.sumTotalDay(strDate.substring(0, 10));
		model.addAttribute("sumBillDay", sumBillDay);
		return "home";
	}

	@RequestMapping(path = { "/admin/addUser" }, method = { RequestMethod.GET })
	public String showAdd(HttpSession session, Model model) {
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		return "addUser";
	}

	@RequestMapping(path = { "/admin/addUser" }, method = { RequestMethod.POST })
	public String checkAdd(HttpSession session, Model model, UserDto user) {
		user.setCreateAt(FormatDate.format(new Date()));
		model.addAttribute("user", user);
		List<UserDto> listUserDto = userService.findAll();
		for (UserDto userDto : listUserDto) {
			if (userDto.getUserName().equals(user.getUserName())) {
				model.addAttribute("messageServer", "UserName already exists");
				model.addAttribute("user", user);
				return "addUser";
			}
		}
		try {
			user.setPassword("12345678");
			userService.save(user);
			session.setAttribute("messageServer", "Add user successful");
		} catch (Exception e) {
			model.addAttribute("messageServer", "Error add user");
			model.addAttribute("user", user);
			return "addUser";
		}
		return "redirect:" + "/admin/listUser";
	}

	@RequestMapping(path = { "/admin/editUser" }, method = { RequestMethod.GET })
	public String showEdit(HttpSession session, Model model, int id) {
		UserDto user = userService.findById((int) id);
		model.addAttribute("user", user);
		return "editUser";
	}

	@RequestMapping(path = { "/admin/editUser" }, method = { RequestMethod.POST })
	public String checkEdit(HttpSession session, Model model, UserDto user) {
		UserDto curentUser = userService.findById(user.getId());
		user.setCreateAt(curentUser.getCreateAt());
		user.setStatus(curentUser.isStatus());
		user.setPassword(curentUser.getPassword());
		user.setUpdateAt(FormatDate.format(new Date()));

		List<UserDto> listUserDto = userService.findAll();
		for (UserDto userDto : listUserDto) {
			if (userDto.getUserName().equals(user.getUserName()) && userDto.getId() != user.getId()) {
				model.addAttribute("messageServer", "UserName already exists");
				model.addAttribute("user", user);
				return "editUser";
			}
		}
		try {
			userService.save(user);
			session.setAttribute("messageServer", "Edit user successful");
		} catch (Exception e) {
			model.addAttribute("messageServer", "Error Update");
			model.addAttribute("user", user);
			return "editUser";
		}
		return "redirect:" + "/admin/listUser";
	}

	@RequestMapping(path = { "/admin/listUser" }, method = { RequestMethod.GET })
	public String showListUser(Model model, HttpSession session) {
		String messageServer = (String) session.getAttribute("messageServer");
		session.removeAttribute("messageServer");
		List<UserDto> listUserDto = userService.findAllEmployee();
		model.addAttribute("listUser", listUserDto);
		model.addAttribute("messageServer", messageServer);
		return "listUser";
	}

	@RequestMapping(path = { "/admin/deleteUser" }, method = { RequestMethod.GET })
	public String deleteUser(HttpSession session, int id) {
		userService.delete(id);
		session.setAttribute("messageServer", "Delete user successful");
		return "redirect:" + "/admin/listUser";
	}

	@RequestMapping(path = { "/admin/lockUser" }, method = { RequestMethod.GET })
	public String lockUser(HttpSession session, int id) {
		UserDto user = userService.findById((int) id);
		user.setStatus(!user.isStatus());
		userService.save(user);
		return "redirect:" + "/admin/listUser";
	}

	@RequestMapping(path = { "/admin/changePassword" }, method = { RequestMethod.GET })
	public String showChangePass(HttpSession session, Model model) {
		return "changePassword";
	}

	@RequestMapping(path = { "/admin/changePassword" }, method = { RequestMethod.POST })
	public String checkChangePass(HttpSession session, Model model, String passOld, String passNew, String rePassNew) {
		if (!passNew.equals(rePassNew)) {
			model.addAttribute("messageServer", "Not Matching Password.");
			return "changePassword";
		}
		int id = (int) session.getAttribute("loginUser");
		UserDto user = userService.findById(id);

		if (user.getPassword().equals(passOld)) {
			user.setPassword(passNew);
			user.setUpdateAt(FormatDate.format(new Date()));

			try {
				userService.save(user);
				session.setAttribute("messageServer", "Change password successful");
				return "redirect:" + "/admin/changePassword";
			} catch (Exception e) {
				model.addAttribute("messageServer", "Error Change Pass");
				return "changePassword";
			}
		}
		model.addAttribute("messageServer", "Old passwords do not match!");
		return "changePassword";
	}
}
