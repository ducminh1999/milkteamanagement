package spring.mvc.controllers.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.mvc.dto.RoleDto;
import spring.mvc.services.RoleService;
import spring.mvc.utils.FormatDate;

@Controller
@RequestMapping(path = { "/admin/role" })
public class RoleController {
	@Autowired
	RoleService roleService;

	public RoleController() {
		System.out.println("RoleController");
	}

	@RequestMapping(path = { "/list" }, method = { RequestMethod.GET })
	public String showList(Model model) {
		List<RoleDto> listRole = roleService.findAll();
		model.addAttribute("listRole", listRole);
		return "listRole";
	}

	@RequestMapping(path = { "/add" }, method = { RequestMethod.GET })
	public String showAdd() {
		return "addRole";
	}

	@RequestMapping(path = { "/add" }, method = { RequestMethod.POST })
	public String checkAdd(RoleDto roleDto, Model model, HttpSession session) {
		try {
			roleDto.setCreateAt(FormatDate.format(new Date()));
			roleService.add(roleDto);
		} catch (Exception e) {
			model.addAttribute("role", roleDto);
			return "addRole";
		}
		return "redirect:" + "/admin/role/list";
	}

	@RequestMapping(path = { "/edit" }, method = { RequestMethod.GET })
	public String showEdit(int id, Model model, HttpSession session) {
		RoleDto roleDto = roleService.findOne(id);
		model.addAttribute("role", roleDto);
		return "editRole";
	}

	@RequestMapping(path = { "/edit" }, method = { RequestMethod.POST })
	public String checkEdit(HttpSession session, Model model, RoleDto roleDto) {
		RoleDto role = roleService.findOne(roleDto.getId());
		roleDto.setCreateAt(role.getCreateAt());
		roleDto.setUpdateAt(FormatDate.format(new Date()));

		try {
			roleService.update(roleDto);
		} catch (Exception e) {
			model.addAttribute("role", roleDto);
			return "editRole";
		}
		return "redirect:" + "/admin/role/list";
	}

	@RequestMapping(path = { "/delete" }, method = { RequestMethod.GET })
	public String checkDelete(Model model, int id) {
		roleService.delete(id);
		System.out.println("delete:" + id);
		return "redirect:" + "/admin/role/list";
	}
}
