package spring.mvc.controllers.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import spring.mvc.dto.BillDto;
import spring.mvc.dto.CartDto;
import spring.mvc.dto.CheckoutInfo;
import spring.mvc.dto.OrderItemDto;
import spring.mvc.dto.ProductDto;
import spring.mvc.dto.UserDto;
import spring.mvc.services.BillService;
import spring.mvc.services.CartService;
import spring.mvc.services.OrderItemService;
import spring.mvc.services.ProductService;
import spring.mvc.services.UserService;
import spring.mvc.utils.FormatDate;

@Controller
@RequestMapping(path = { "/cart" })
public class CartController {
	final float UST_TO_VN = 22900f;
	final int FEE_SHIP = 10000;
	@Autowired
	CartService cartService;

	@Autowired
	UserService userService;
	@Autowired
	ProductService productService;
	@Autowired
	BillService billService;
	@Autowired
	OrderItemService orderItemService;

	public CartController() {
		System.out.println("CartController");
	}

	@RequestMapping(path = { "/checkout" }, method = { RequestMethod.GET })
	public String showCheckout(Model model, HttpSession session) {
		Object userId = session.getAttribute("loginUser");
		UserDto user = userService.findById((int) userId);
		int countCart = cartService.countByUserId((int) userId);
		model.addAttribute("countCart", countCart);

		List<CartDto> listCart = cartService.findByUser((int) userId);
		List<ProductDto> listProduct = new ArrayList<ProductDto>();
		long paymentVNTotal = 0;
		for (CartDto cartDto : listCart) {
			ProductDto productDto = productService.findOne(cartDto.getProductId());
			listProduct.add(productDto);
			paymentVNTotal += cartDto.getQuantity() * productDto.getPrice();
		}
		float paymentTotal = (float) Math.round((paymentVNTotal+FEE_SHIP) / UST_TO_VN * 100) / 100;
		CheckoutInfo checkoutInfo = new CheckoutInfo();
		checkoutInfo.setPaymentTotal(paymentTotal);
		checkoutInfo.setPaymentVNTotal(paymentVNTotal);
		model.addAttribute("checkoutInfo", checkoutInfo);
		model.addAttribute("user", user);
		model.addAttribute("listCart", listCart);
		model.addAttribute("listProduct", listProduct);
		return "_checkout";
	}

	@RequestMapping(path = { "/checkoutPaypal" }, method = { RequestMethod.POST }, consumes = "application/json")
	public String checkCheckoutPaypal(Model model, HttpSession session, HttpServletRequest request) throws IOException, ParseException {
		CheckoutInfo checkoutInfo = getCheckoutInfo(request);
		String orderId = checkoutInfo.getOrderId();
		if(orderId==null || "".equals(orderId)) {
			orderId = FormatDate.format(new Date());
		}
		BillDto billDto = new BillDto();
		billDto.setCreateAt(FormatDate.format(new Date()));
		billDto.setId(orderId);
		billDto.setDeliverId(checkoutInfo.getDeliverId());
		billDto.setPaymentId(checkoutInfo.getPaymentMethod());
		billDto.setSeatId(1);
		billDto.setStatus(0);
		billDto.setTotal(checkoutInfo.getPaymentVNTotal());
		billService.save(billDto);
		Object userId = session.getAttribute("loginUser");
		
		List<CartDto> listCart = cartService.findByUser((int)userId);
		for (CartDto cartDto : listCart) {
			cartService.delete(cartDto.getId());
			orderItemService.save(copyCartToOrder(cartDto, orderId));
		}
		return "redirect:" + "/paymentHistory";
	}

	public CheckoutInfo getCheckoutInfo(HttpServletRequest request) throws IOException, ParseException {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		String data = buffer.toString();
		data = data.trim(); 
		JSONParser parser = new JSONParser();
		JSONObject json1 = (JSONObject) parser.parse(data);
		CheckoutInfo checkoutInfo = new CheckoutInfo();
		checkoutInfo.setPaymentTotal(Float.valueOf((String) json1.get("paymentTotal")));
		checkoutInfo.setPaymentVNTotal(Long.valueOf((String) json1.get("paymentVNTotal")));
		checkoutInfo.setPaymentTotal(Float.valueOf((String) json1.get("shippingTotal")));
		checkoutInfo.setDeliverDay(Integer.valueOf((String) json1.get("deliverDay")));
		checkoutInfo.setPaymentMethod(Integer.valueOf((String) json1.get("paymentMethod")));
		checkoutInfo.setDeliverId((String) json1.get("deliverId"));
		checkoutInfo.setOrderId((String) json1.get("orderId"));
		return checkoutInfo;
	}

	@RequestMapping(path = { "/checkout" }, method = { RequestMethod.POST } /* , consumes = "application/json" */ )
	public String checkCheckout(Model model, HttpSession session, CheckoutInfo checkoutInfo) {

//		CheckoutInfo checkoutInfo = new CheckoutInfo();
		String orderId = checkoutInfo.getOrderId();
		if(orderId==null || "".equals(orderId)) {
			orderId = FormatDate.format(new Date());
		}
		BillDto billDto = new BillDto();
		billDto.setCreateAt(FormatDate.format(new Date()));
		billDto.setId(orderId);
		billDto.setDeliverId(checkoutInfo.getDeliverId());
		billDto.setPaymentId(checkoutInfo.getPaymentMethod());
		billDto.setSeatId(1);
		billDto.setStatus(0);
		billDto.setTotal(checkoutInfo.getPaymentVNTotal());
		billService.save(billDto);
		Object userId = session.getAttribute("loginUser");
		
		List<CartDto> listCart = cartService.findByUser((int)userId);
		for (CartDto cartDto : listCart) {
			cartService.delete(cartDto.getId());
			orderItemService.save(copyCartToOrder(cartDto, orderId));
		}
		return "redirect:" + "/paymentHistory";
	}

	@RequestMapping(path = { "/add" }, method = { RequestMethod.GET })
	public String addCart(HttpSession session, Model model, int id) {
		List<ProductDto> listProduct = productService.findById(id);
		Object userId = session.getAttribute("loginUser");
		CartDto dto = cartService.findCart(id, (int) userId);
		if (dto == null) {
			dto = new CartDto();
			dto.setQuantity(1);
			dto.setProductId(id);
			dto.setCreateAt(FormatDate.format(new Date()));
			dto.setUserId((int) userId);
		} else {
			dto.setQuantity(1 + dto.getQuantity());
			dto.setUpdateAt(FormatDate.format(new Date()));
		}
		cartService.save(dto);
		model.addAttribute("product", listProduct.get(0));
		int countCart = cartService.countByUserId((int) userId);
		model.addAttribute("countCart", countCart);
		return "redirect:" + "/home";
	}

	@RequestMapping(path = { "/add" }, method = { RequestMethod.POST })
	public String addCart(HttpSession session, Model model, int id, int quantity) {
		List<ProductDto> listProduct = productService.findById(id);
		Object userId = session.getAttribute("loginUser");
		CartDto dto = cartService.findCart(id, (int) userId);
		if (dto == null) {
			dto = new CartDto();
			dto.setQuantity(quantity);
			dto.setProductId(id);
			dto.setCreateAt(FormatDate.format(new Date()));
			dto.setUserId((int) userId);
		} else {
			dto.setQuantity(quantity + dto.getQuantity());
			dto.setUpdateAt(FormatDate.format(new Date()));
		}
		cartService.save(dto);
		model.addAttribute("product", listProduct.get(0));
		int countCart = cartService.countByUserId((int) userId);
		model.addAttribute("countCart", countCart);
		List<ProductDto> listProductRelated = productService.findByCategory(listProduct.get(0).getCategoryId());
		if (listProductRelated.isEmpty()) {
			listProductRelated = productService.findAllNotLock();
		}
		int length = listProductRelated.size();
		for (int j = 0; j < length; j++) {
			if (j > 2) {
				listProductRelated.remove(listProductRelated.size() - 1);
			}
		}
		model.addAttribute("listProductRelated", listProductRelated);
		return "_productDetail";
	}

	@RequestMapping(path = { "/list" }, method = { RequestMethod.GET })
	public String showList(Model model, HttpSession session) {
		Object userId = session.getAttribute("loginUser");
		int countCart = cartService.countByUserId((int) userId);
		model.addAttribute("countCart", countCart);

		List<CartDto> listCart = cartService.findByUser((int) userId);
		List<ProductDto> listProduct = new ArrayList<ProductDto>();
		for (CartDto cartDto : listCart) {
			listProduct.add(productService.findOne(cartDto.getProductId()));
		}
		model.addAttribute("listCart", listCart);
		model.addAttribute("listProduct", listProduct);
		return "_cart";
	}

//	@RequestMapping(path = { "/edit" }, method = { RequestMethod.GET })
//	public String showEdit(CartDto dto, Model model, HttpSession session) {
//		CartDto cartDto = cartService.findOne(id);
//		model.addAttribute("cart", cartDto);
//		return "editCart";
//	}

	@RequestMapping(path = { "/edit" }, method = { RequestMethod.POST })
	public String checkEdit(HttpSession session, Model model, CartDto cartDto) {
		CartDto cart = cartService.findOne(cartDto.getId());
		cart.setUpdateAt(FormatDate.format(new Date()));
		cart.setQuantity(cartDto.getQuantity());
		cartService.save(cart);
		return "redirect:" + "/cart/list";
	}

	@RequestMapping(path = { "/delete" }, method = { RequestMethod.GET })
	public String checkDelete(Model model, int id) {
		cartService.delete(id);
		return "redirect:" + "/cart/list";
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
