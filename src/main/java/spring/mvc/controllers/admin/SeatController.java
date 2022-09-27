package spring.mvc.controllers.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.mvc.dto.AreaDto;
import spring.mvc.dto.SeatDto;
import spring.mvc.services.AreaService;
import spring.mvc.services.SeatService;
import spring.mvc.utils.FormatDate;

@Controller
@RequestMapping(path = { "/admin/table" })
public class SeatController {
	@Autowired
	SeatService tableService;
	@Autowired
	AreaService areaService;

	public SeatController() {
		System.out.println("TableController");
	}

	@RequestMapping(path = { "/list" }, method = { RequestMethod.GET })
	public String showList(Model model, HttpSession session) {
		List<SeatDto> listTable = tableService.findAll();
		List<String> listArea = new ArrayList<String>();

		for (SeatDto table : listTable) {
			AreaDto areaDto = areaService.findOne(table.getAreaId());
			listArea.add(areaDto.getName());
		}

		String messageServer = (String) session.getAttribute("messageServer");
		session.removeAttribute("messageServer");
		model.addAttribute("messageServer", messageServer);
		model.addAttribute("listTable", listTable);
		model.addAttribute("listArea", listArea);
		return "listTable";
	}

	@RequestMapping(path = { "/add" }, method = { RequestMethod.GET })
	public String showAdd(Model model) {
		SeatDto table = new SeatDto();
		List<AreaDto> listArea = areaService.findAll();
		model.addAttribute("table", table);
		model.addAttribute("listArea", listArea);
		return "addTable";
	}

	@RequestMapping(path = { "/add" }, method = { RequestMethod.POST })
	public String checkAdd(SeatDto tableDto, Model model, HttpSession session) {
		try {
			tableDto.setCreateAt(FormatDate.format(new Date()));
			tableService.add(tableDto);
			session.setAttribute("messageServer", "Add table successful");
		} catch (Exception e) {
			List<AreaDto> listArea = areaService.findAll();
			model.addAttribute("listArea", listArea);
			model.addAttribute("table", tableDto);
			return "addTable";
		}
		return "redirect:" + "/admin/table/list";
	}

	@RequestMapping(path = { "/edit" }, method = { RequestMethod.GET })
	public String showEdit(int id, Model model) {
		SeatDto table = tableService.findOne(id);
		List<AreaDto> listArea = areaService.findAll();
		model.addAttribute("table", table);
		model.addAttribute("listArea", listArea);
		return "editTable";
	}

	@RequestMapping(path = { "/edit" }, method = { RequestMethod.POST })
	public String checkEdit(HttpSession session, Model model, SeatDto tableDto) {
		SeatDto table = tableService.findOne(tableDto.getId());
		tableDto.setCreateAt(table.getCreateAt());
		tableDto.setStatus(table.isStatus());
		tableDto.setUpdateAt(FormatDate.format(new Date()));
		try {
			tableService.update(tableDto);
			session.setAttribute("messageServer", "Edit table successful");
		} catch (Exception e) {
			List<AreaDto> listArea = areaService.findAll();
			model.addAttribute("listArea", listArea);
			model.addAttribute("table", tableDto);
			return "editTable";
		}
		return "redirect:" + "/admin/table/list";
	}

	@RequestMapping(path = { "/delete" }, method = { RequestMethod.GET })
	public String checkDelete(Model model, HttpSession session, int id) {
		tableService.delete(id);
		session.setAttribute("messageServer", "Delete table successful");
		return "redirect:" + "/admin/table/list";
	}

	@RequestMapping(path = { "/lock" }, method = { RequestMethod.GET })
	public String lockSeat(HttpSession session, int id) {
		SeatDto seat = tableService.findOne((int) id);
		seat.setStatus(!seat.isStatus());
		tableService.update(seat);
		return "redirect:" + "/admin/table/list";
	}
}
