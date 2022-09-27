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

import spring.mvc.dto.BillDto;
import spring.mvc.dto.CartDto;
import spring.mvc.dto.CategoryDto;
import spring.mvc.dto.CheckoutInfo;
import spring.mvc.dto.OrderItemDto;
import spring.mvc.dto.ProductDto;
import spring.mvc.dto.SeatDto;
import spring.mvc.services.BillService;
import spring.mvc.services.CartService;
import spring.mvc.services.CategoryService;
import spring.mvc.services.OrderItemService;
import spring.mvc.services.ProductService;
import spring.mvc.services.SeatService;
import spring.mvc.utils.FormatDate;

@Controller
@RequestMapping(path = { "/admin/orderItem" })
public class OrderItemController {
	final float UST_TO_VN = 22900f;
	@Autowired
	OrderItemService orderItemService;
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	SeatService seatService;
	@Autowired
	CartService cartService;
	@Autowired
	BillService billService;

	public OrderItemController() {
		System.out.println("OrderItemController");
	}

	@RequestMapping(path = { "/checkout" }, method = { RequestMethod.POST } )
	public String checkCheckout(Model model, HttpSession session, int seatId) {
		Object userId = session.getAttribute("loginUser");

		List<CartDto> listCart = cartService.findByUser((int) userId);
//		List<ProductDto> listProduct = new ArrayList<ProductDto>();
		long paymentVNTotal = 0;
		for (CartDto cartDto : listCart) {
			ProductDto productDto = productService.findOne(cartDto.getProductId());
//			listProduct.add(productDto);
			paymentVNTotal += cartDto.getQuantity() * productDto.getPrice();
		}
		float paymentTotal = (float) Math.round((paymentVNTotal) / UST_TO_VN * 100) / 100;
		CheckoutInfo checkoutInfo = new CheckoutInfo();
		checkoutInfo.setPaymentTotal(paymentTotal);
		checkoutInfo.setPaymentVNTotal(paymentVNTotal);
		String orderId = checkoutInfo.getOrderId();
		if (orderId == null || "".equals(orderId)) {
			orderId = FormatDate.format(new Date());
		}
		BillDto billDto = new BillDto();
		billDto.setCreateAt(FormatDate.format(new Date()));
		billDto.setId(orderId);
		billDto.setDeliverId(orderId);
		billDto.setPaymentId(1);
		billDto.setSeatId(seatId);
		billDto.setStatus(2);
		billDto.setTotal(checkoutInfo.getPaymentVNTotal());
		billService.save(billDto);

		for (CartDto cartDto : listCart) {
			cartService.delete(cartDto.getId());
			orderItemService.save(copyCartToOrder(cartDto, orderId));
		}
		return "redirect:" + "/admin/bill/list";
	}

	@RequestMapping(path = { "/addOder" }, method = { RequestMethod.POST })
	public String addCart(HttpSession session, Model model, int productId, int quantity) {
		Object userId = session.getAttribute("loginUser");
		CartDto dto = cartService.findCart(productId, (int) userId);
		if (dto == null) {
			dto = new CartDto();
			dto.setQuantity(quantity);
			dto.setProductId(productId);
			dto.setCreateAt(FormatDate.format(new Date()));
			dto.setUserId((int) userId);
		} else {
			dto.setQuantity(quantity + dto.getQuantity());
			dto.setUpdateAt(FormatDate.format(new Date()));
		}
		cartService.save(dto);
		return "redirect:" + "/admin/orderItem/add";
	}

	@RequestMapping(path = { "/list" }, method = { RequestMethod.GET })
	public String showList(Model model) {
		List<OrderItemDto> listOrderItem = orderItemService.findAll();
		model.addAttribute("listOrderItem", listOrderItem);
		return "listOrderItem";
	}

	@RequestMapping(path = { "/add" }, method = { RequestMethod.GET })
	public String showAdd(Model model, HttpSession session) {
		List<ProductDto> listProduct = productService.findAllNotLock();
		List<CategoryDto> listCategory = categoryService.findAll();
		List<SeatDto> listSeat = seatService.findAll();
		model.addAttribute("listProduct", listProduct);
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("listSeat", listSeat);

		List<OrderItemDto> listOrderItem = orderItemService.findAll();
		model.addAttribute("listOrderItem", listOrderItem);

		Object userId = session.getAttribute("loginUser");

		List<CartDto> listCart = cartService.findByUser((int) userId);
		List<ProductDto> listProductCart = new ArrayList<ProductDto>();
		for (CartDto cartDto : listCart) {
			listProductCart.add(productService.findOne(cartDto.getProductId()));
		}
		model.addAttribute("listCart", listCart);
		model.addAttribute("listProductCart", listProductCart);
		return "addOrder";
	}

	@RequestMapping(path = { "/add" }, method = { RequestMethod.POST })
	public String checkAdd(OrderItemDto orderItemDto, Model model, HttpSession session) {
		try {
			orderItemDto.setCreateAt(FormatDate.format(new Date()));
			orderItemService.save(orderItemDto);
		} catch (Exception e) {
			model.addAttribute("orderItem", orderItemDto);
			return "addOrderItem";
		}
		return "redirect:" + "/admin/orderItem/list";
	}

	@RequestMapping(path = { "/edit" }, method = { RequestMethod.GET })
	public String showEdit(int id, Model model, HttpSession session) {
		OrderItemDto orderItemDto = orderItemService.findOne(id);
		model.addAttribute("orderItem", orderItemDto);
		return "editOrderItem";
	}

	@RequestMapping(path = { "/edit" }, method = { RequestMethod.POST })
	public String checkEdit(HttpSession session, Model model, OrderItemDto orderItemDto) {
		OrderItemDto orderItem = orderItemService.findOne(orderItemDto.getId());
		orderItemDto.setCreateAt(orderItem.getCreateAt());
		orderItemDto.setUpdateAt(FormatDate.format(new Date()));

		try {
			orderItemService.save(orderItemDto);
		} catch (Exception e) {
			model.addAttribute("orderItem", orderItemDto);
			return "editOrderItem";
		}
		return "redirect:" + "/admin/orderItem/list";
	}

	@RequestMapping(path = { "/delete" }, method = { RequestMethod.GET })
	public String checkDelete(Model model, int id) {
		orderItemService.delete(id);
		System.out.println("delete:" + id);
		return "redirect:" + "/admin/orderItem/list";
	}

	private OrderItemDto copyCartToOrder(CartDto cartDto, String billId) {
		OrderItemDto orderItemDto = new OrderItemDto();
		orderItemDto.setBillId(billId);
		orderItemDto.setCreateAt(FormatDate.format(new Date()));
		orderItemDto.setProductId(cartDto.getProductId());
		orderItemDto.setQuantity(cartDto.getQuantity());
		orderItemDto.setUserId(cartDto.getUserId());
		return orderItemDto;
	}
}
