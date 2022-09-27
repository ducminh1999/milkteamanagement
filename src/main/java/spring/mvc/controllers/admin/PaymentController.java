package spring.mvc.controllers.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.mvc.dto.PaymentDto;
import spring.mvc.services.PaymentService;
import spring.mvc.utils.FormatDate;

@Controller
@RequestMapping(path = { "/admin/payment" })
public class PaymentController {
	@Autowired
	PaymentService paymentService;

	public PaymentController() {
		System.out.println("PaymentController");
	}

	@RequestMapping(path = { "/list" }, method = { RequestMethod.GET })
	public String showList(Model model) {
		List<PaymentDto> listPayment = paymentService.findAll();
		model.addAttribute("listPayment", listPayment);
		return "listPayment";
	}

	@RequestMapping(path = { "/add" }, method = { RequestMethod.GET })
	public String showAdd() {
		return "addPayment";
	}

	@RequestMapping(path = { "/add" }, method = { RequestMethod.POST })
	public String checkAdd(PaymentDto paymentDto, Model model, HttpSession session) {
		try {
			paymentDto.setCreateAt(FormatDate.format(new Date()));
			paymentService.add(paymentDto);
		} catch (Exception e) {
			model.addAttribute("payment", paymentDto);
			return "addPayment";
		}
		return "redirect:" + "/admin/payment/list";
	}

	@RequestMapping(path = { "/edit" }, method = { RequestMethod.GET })
	public String showEdit(int id, Model model, HttpSession session) {
		PaymentDto paymentDto = paymentService.findOne(id);
		model.addAttribute("payment", paymentDto);
		return "editPayment";
	}

	@RequestMapping(path = { "/edit" }, method = { RequestMethod.POST })
	public String checkEdit(HttpSession session, Model model, PaymentDto paymentDto) {
		PaymentDto payment = paymentService.findOne(paymentDto.getId());
		paymentDto.setCreateAt(payment.getCreateAt());
		paymentDto.setUpdateAt(FormatDate.format(new Date()));

		try {
			paymentService.update(paymentDto);
		} catch (Exception e) {
			model.addAttribute("payment", paymentDto);
			return "editPayment";
		}
		return "redirect:" + "/admin/payment/list";
	}

	@RequestMapping(path = { "/delete" }, method = { RequestMethod.GET })
	public String checkDelete(Model model, int id) {
		paymentService.delete(id);
		System.out.println("delete:" + id);
		return "redirect:" + "/admin/payment/list";
	}
}
