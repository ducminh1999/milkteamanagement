package spring.mvc.controllers.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AutoPopulatingList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.mvc.dto.CategoryDto;
import spring.mvc.dto.ProductDto;
import spring.mvc.dto.UserDto;
import spring.mvc.services.CategoryService;
import spring.mvc.services.ProductService;
import spring.mvc.utils.FormatDate;

@Controller
@RequestMapping(path = { "/admin/product" })
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;

	public ProductController() {
		System.out.println("ProductController");
	}

	@RequestMapping(path = { "/search" }, method = { RequestMethod.POST })
	public String showSearch(String nameSearch, Model model) {
		List<ProductDto> listProduct = productService.findName(nameSearch);
		List<String> listCategory = new ArrayList<String>();
		
		for (ProductDto product : listProduct) {
			CategoryDto categoryDto = categoryService.findOne(product.getCategoryId());
			listCategory.add(categoryDto.getName());
		}
		model.addAttribute("messageServer", "");
		model.addAttribute("listProduct", listProduct);
		model.addAttribute("listCategory", listCategory);
		return "listProduct";
	}
	@RequestMapping(path = { "/list" }, method = { RequestMethod.GET })
	public String showList(Model model, HttpSession session) {
		List<ProductDto> listProduct = productService.findAll();
		List<String> listCategory = new ArrayList<String>();
		
		for (ProductDto product : listProduct) {
			CategoryDto categoryDto = categoryService.findOne(product.getCategoryId());
			listCategory.add(categoryDto.getName());
		}

		String messageServer = (String) session.getAttribute("messageServer");
		session.removeAttribute("messageServer");
		model.addAttribute("messageServer", messageServer);
		model.addAttribute("listProduct", listProduct);
		model.addAttribute("listCategory", listCategory);
		return "listProduct";
	}

	@RequestMapping(path = { "/add" }, method = { RequestMethod.GET })
	public String showAdd(Model model) {
		ProductDto product = new ProductDto();
		List<CategoryDto> listCategory = categoryService.findAll();
		model.addAttribute("product", product);
		model.addAttribute("listCategory", listCategory);
		return "addProduct";
	}

	@RequestMapping(path = { "/add" }, method = { RequestMethod.POST })
	public String checkAdd(ProductDto productDto, Model model, HttpSession session) {
		try {
			productDto.setCreateAt(FormatDate.format(new Date()));
			productService.add(productDto);
			session.setAttribute("messageServer", "Add product successful");
		} catch (Exception e) {
			List<CategoryDto> listCategory = categoryService.findAll();
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("product", productDto);
			return "addProduct";
		}
		return "redirect:" + "/admin/product/list";
	}

	@RequestMapping(path = { "/edit" }, method = { RequestMethod.GET })
	public String showEdit(int id, Model model) {
		ProductDto product = productService.findOne(id);
		List<CategoryDto> listCategory = categoryService.findAll();
		model.addAttribute("product", product);
		model.addAttribute("listCategory", listCategory);
		return "editProduct";
	}

	@RequestMapping(path = { "/edit" }, method = { RequestMethod.POST })
	public String checkEdit(HttpSession session, Model model, ProductDto productDto) {
		ProductDto product = productService.findOne(productDto.getId());
		productDto.setCreateAt(product.getCreateAt());
		productDto.setStatus(product.isStatus());
		productDto.setUpdateAt(FormatDate.format(new Date()));
		try {
			productService.update(productDto);
			session.setAttribute("messageServer", "Edit product successful");
		} catch (Exception e) {
			List<CategoryDto> listCategory = categoryService.findAll();
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("product", productDto);
			return "editProduct";
		}
		return "redirect:" + "/admin/product/list";
	}

	@RequestMapping(path = { "/delete" }, method = { RequestMethod.GET })
	public String checkDelete(Model model, HttpSession session, int id) {
		productService.delete(id);
		session.setAttribute("messageServer", "Delete product successful");
		return "redirect:" + "/admin/product/list";
	}
	@RequestMapping(path = { "/lock" }, method = { RequestMethod.GET })
	public String lockUser(HttpSession session, int id) {
		ProductDto productDto = productService.findOne(id);
		productDto.setStatus(!productDto.isStatus());
		productService.update(productDto);
		return "redirect:" + "/admin/product/list";
	}
}
