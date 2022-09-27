package spring.mvc.controllers.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.mvc.dto.AreaDto;
import spring.mvc.dto.UserDto;
import spring.mvc.services.AreaService;
import spring.mvc.utils.FormatDate;

@Controller
@RequestMapping(path = { "/admin/area" })
public class AreaController {
	@Autowired
	AreaService areaService;

	public AreaController() {
		System.out.println("AreaController");
	}

	@RequestMapping(path = { "/list" }, method = { RequestMethod.GET })
	public String showList(Model model, HttpSession session) {
		String userString = (String) session.getAttribute("userName");
		if (userString == null) {
			return "login";
		}
		List<AreaDto> listArea = areaService.findAll();
		String messageServer = (String) session.getAttribute("messageServer");
		session.removeAttribute("messageServer");
		model.addAttribute("messageServer", messageServer);
		model.addAttribute("listArea", listArea);
		return "listArea";
	}

	@RequestMapping(path = { "/add" }, method = { RequestMethod.GET })
	public String showAdd(HttpSession session, Model model) {
		String userString = (String) session.getAttribute("userName");
		if (userString == null) {
			return "login";
		}
		AreaDto area = new AreaDto();
		List<AreaDto> listArea = areaService.findAll();
		model.addAttribute("area", area);
		model.addAttribute("listArea", listArea);
		return "addArea";
	}

	@RequestMapping(path = { "/add" }, method = { RequestMethod.POST })
	public String checkAdd(AreaDto areaDto, Model model, HttpSession session) {
		String userString = (String) session.getAttribute("userName");
		if (userString == null) {
			return "login";
		}
		try {
			areaDto.setCreateAt(FormatDate.format(new Date()));
			areaService.add(areaDto);
			session.setAttribute("messageServer", "Add area successful");
		} catch (Exception e) {
			model.addAttribute("area", areaDto);
			return "addArea";
		}
		return "redirect:" + "/admin/area/list";
	}

	@RequestMapping(path = { "/edit" }, method = { RequestMethod.GET })
	public String showEdit(HttpSession session, int id, Model model) {
		String userString = (String) session.getAttribute("userName");
		if (userString == null) {
			return "login";
		}
		AreaDto area = areaService.findOne(id);
		List<AreaDto> listArea = areaService.findAll();
		model.addAttribute("area", area);
		model.addAttribute("listArea", listArea);
		return "editArea";
	}

	@RequestMapping(path = { "/edit" }, method = { RequestMethod.POST })
	public String checkEdit(HttpSession session, Model model, AreaDto areaDto) {
		String userString = (String) session.getAttribute("userName");
		if (userString == null) {
			return "login";
		}
		AreaDto area = areaService.findOne(areaDto.getId());
		areaDto.setCreateAt(area.getCreateAt());
		areaDto.setStatus(area.isStatus());
		areaDto.setUpdateAt(FormatDate.format(new Date()));
		try {
			areaService.update(areaDto);
			session.setAttribute("messageServer", "Edit area successful");
		} catch (Exception e) {
			model.addAttribute("area", areaDto);
			return "editArea";
		}
		return "redirect:" + "/admin/area/list";
	}

	@RequestMapping(path = { "/delete" }, method = { RequestMethod.GET })
	public String checkDelete(Model model, HttpSession session, int id) {
		String userString = (String) session.getAttribute("userName");
		if (userString == null) {
			return "login";
		}
		areaService.delete(id);
		session.setAttribute("messageServer", "Delete area successful");
		return "redirect:" + "/admin/area/list";
	}

	@RequestMapping(path = { "/lock" }, method = { RequestMethod.GET })
	public String lockUser(HttpSession session, int id) {
		String userString = (String) session.getAttribute("userName");
		if (userString == null) {
			return "login";
		}
		AreaDto area = areaService.findOne((int) id);
		area.setStatus(!area.isStatus());
		areaService.update(area);
		return "redirect:" + "/admin/area/list";
	}
}
