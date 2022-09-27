package spring.mvc.controllers.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.mvc.dto.CategoryDto;
import spring.mvc.services.CategoryService;
import spring.mvc.utils.FormatDate;

@Controller
@RequestMapping(path = { "/admin/category" })
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	public CategoryController() {
		System.out.println("CategoryController");
	}

	@RequestMapping(path = { "/list" }, method = { RequestMethod.GET })
	public String showList(Model model, HttpSession session) {
		List<CategoryDto> listCategory = categoryService.findAll();
		String messageServer = (String) session.getAttribute("messageServer");
		session.removeAttribute("messageServer");
		model.addAttribute("messageServer", messageServer);
		model.addAttribute("listCategory", listCategory);
		return "listCategory";
	}

	@RequestMapping(path = { "/add" }, method = { RequestMethod.GET })
	public String showAdd(Model model) {
		CategoryDto category = new CategoryDto();
		List<CategoryDto> listCategory = categoryService.findAll();
		model.addAttribute("category", category);
		model.addAttribute("listCategory", listCategory);
		return "addCategory";
	}

	@RequestMapping(path = { "/add" }, method = { RequestMethod.POST })
	public String checkAdd(CategoryDto categoryDto, Model model, HttpSession session) {
		try {
			categoryDto.setCreateAt(FormatDate.format(new Date()));
			categoryService.add(categoryDto);
			session.setAttribute("messageServer", "Add category successful");
		} catch (Exception e) {
			model.addAttribute("category", categoryDto);
			return "addCategory";
		}
		return "redirect:" + "/admin/category/list";
	}

	@RequestMapping(path = { "/edit" }, method = { RequestMethod.GET })
	public String showEdit(int id, Model model) {
		CategoryDto category = categoryService.findOne(id);
		List<CategoryDto> listCategory = categoryService.findAll();
		model.addAttribute("category", category);
		model.addAttribute("listCategory", listCategory);
		return "editCategory";
	}

	@RequestMapping(path = { "/edit" }, method = { RequestMethod.POST })
	public String checkEdit(HttpSession session, Model model, CategoryDto categoryDto) {
		CategoryDto category = categoryService.findOne(categoryDto.getId());
		categoryDto.setCreateAt(category.getCreateAt());
		categoryDto.setUpdateAt(FormatDate.format(new Date()));
		try {
			categoryService.update(categoryDto);
			session.setAttribute("messageServer", "Edit category successful");
		} catch (Exception e) {
			model.addAttribute("category", categoryDto);
			return "editCategory";
		}
		return "redirect:" + "/admin/category/list";
	}

	@RequestMapping(path = { "/delete" }, method = { RequestMethod.GET })
	public String checkDelete(Model model, HttpSession session, int id) {
		categoryService.delete(id);
		session.setAttribute("messageServer", "Delete category successful");
		return "redirect:" + "/admin/category/list";
	}
}
