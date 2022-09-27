package spring.mvc.controllers.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.mvc.dto.BillDto;
import spring.mvc.dto.CategoryDto;
import spring.mvc.dto.DeliverDto;
import spring.mvc.dto.OrderItemDto;
import spring.mvc.dto.ProductDto;
import spring.mvc.dto.UserDto;
import spring.mvc.services.AreaService;
import spring.mvc.services.BillService;
import spring.mvc.services.CartService;
import spring.mvc.services.CategoryService;
import spring.mvc.services.OrderItemService;
import spring.mvc.services.ProductService;
import spring.mvc.services.UserService;
import spring.mvc.utils.FormatDate;

@Controller
public class HomeController {

	private final String Apikey = "p8oj1xot-jeuu-raak-1ptl-9q3u6sbqxr2h";
	@Autowired
	UserService userService;
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	AreaService areaService;
	@Autowired
	BillService billService;
	@Autowired
	CartService cartService;
	@Autowired
	OrderItemService orderItemService;

	public HomeController() {
		System.out.println("HomeControllers");
	}

	@RequestMapping(path = { "/search" }, method = { RequestMethod.POST })
	public String showSearch(String nameSearch, Model model, HttpSession session) {
		model.addAttribute("category", "all");
		List<ProductDto> listProduct = productService.findNameNotLock(nameSearch);
		List<CategoryDto> listCategory = categoryService.findAll();
		Object userId = session.getAttribute("loginUser");
		int countCart = 0;
		if (userId != null) {
			countCart = cartService.countByUserId((int) userId);
		}
		model.addAttribute("countCart", countCart);
		model.addAttribute("listProduct", listProduct);
		model.addAttribute("listCategory", listCategory);
		return "index";
	}

	@RequestMapping(path = { "/login" }, method = { RequestMethod.GET })
	public String getLogin(HttpSession session, Model model) {
		String messageServer = (String) session.getAttribute("messageServer");
		session.removeAttribute("messageServer");
		model.addAttribute("messageServer", messageServer);
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		return "loginUser";
	}

	@RequestMapping(path = { "/login" }, method = { RequestMethod.POST })
	public String checkLogin(Model model, UserDto userDto, HttpSession session) {
		String userName = userDto.getUserName();
		String password = userDto.getPassword();
		UserDto userInfo = userService.findOne(userName);
		if (userInfo != null && userInfo.getRoleId() == 3) {
			if (userInfo.getPassword().equals(password)) {
				if (!userInfo.isStatus()) {
					model.addAttribute("userName", userName);
					model.addAttribute("password", password);
					model.addAttribute("messageServer", "UserName has been locked!!!");
					return "loginUser";
				}
				// Login Success
				session.setAttribute("loginUser", userInfo.getId());
				session.setAttribute("userName", userName);
				System.out.println("Filter Info:\t" + new Date() + "\t/home");
				return "redirect:" + "/home";
			}
		}

		model.addAttribute("userName", userName);
		model.addAttribute("password", password);
		model.addAttribute("messageServer", "UserName or password invalid !!!");
		return "loginUser";

	}

	@RequestMapping(path = { "/register" }, method = { RequestMethod.GET })
	public String getRegist(HttpSession session, Model model) {
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		return "register";
	}

	@RequestMapping(path = { "/register" }, method = { RequestMethod.POST })
	public String checkRegist(HttpSession session, Model model, UserDto user) {
		model.addAttribute("user", user);
		List<UserDto> listUserDto = userService.findAll();
		for (UserDto userDto : listUserDto) {
			if (userDto.getUserName().equals(user.getUserName())) {
				model.addAttribute("messageServer", "UserName already exists");
				model.addAttribute("user", user);
				return "register";
			}
		}
		try {
			user.setCreateAt(FormatDate.format(new Date()));
			user.setRoleId(3);
			user.setStatus(true);
			userService.save(user);
			session.setAttribute("messageServer", "Regist successful");
		} catch (Exception e) {
			model.addAttribute("messageServer", "Error add user");
			model.addAttribute("user", user);
			return "register";
		}
		return "redirect:" + "/login";
	}

	@RequestMapping(path = { "/product" }, method = { RequestMethod.GET })
	public String getProduct(HttpSession session, Model model, int id) {
		List<ProductDto> listProduct = productService.findById(id);
		model.addAttribute("product", listProduct.get(0));
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
		Object userId = session.getAttribute("loginUser");
		int countCart = 0;
		if (userId != null) {
			countCart = cartService.countByUserId((int) userId);
		}
		model.addAttribute("countCart", countCart);
		return "_productDetail";
	}

	@RequestMapping(path = { "/home" }, method = { RequestMethod.GET })
	public String home(HttpSession session, Model model) {
		model.addAttribute("category", "all");
		List<ProductDto> listProduct = productService.findAllNotLock();
		List<CategoryDto> listCategory = categoryService.findAll();
		Object userId = session.getAttribute("loginUser");
		int countCart = 0;
		if (userId != null) {
			countCart = cartService.countByUserId((int) userId);
		}
		model.addAttribute("countCart", countCart);
		model.addAttribute("listProduct", listProduct);
		model.addAttribute("listCategory", listCategory);
		Object roleId = session.getAttribute("roleId");
		if (roleId != null) {
			// not a user
			if ((int) roleId != 3) {
				session.removeAttribute("loginUser");
				session.removeAttribute("roleId");
				session.invalidate();
			}
		}
		return "index";
	}

	@RequestMapping(path = { "/aboutMe" }, method = { RequestMethod.GET })
	public String aboutMe(HttpSession session, Model model) {
		Object userId = session.getAttribute("loginUser");
		int countCart = 0;
		if (userId != null) {
			countCart = cartService.countByUserId((int) userId);
		}
		model.addAttribute("countCart", countCart);
		return "_aboutMe";
	}

	@RequestMapping(path = { "" }, method = { RequestMethod.GET })
	public String home1(HttpSession session, Model model) {
		model.addAttribute("category", "all");
		List<ProductDto> listProduct = productService.findAllNotLock();
		List<CategoryDto> listCategory = categoryService.findAll();
		Object userId = session.getAttribute("loginUser");
		int countCart = 0;
		if (userId != null) {
			countCart = cartService.countByUserId((int) userId);
		}
		model.addAttribute("countCart", countCart);
		model.addAttribute("listProduct", listProduct);
		model.addAttribute("listCategory", listCategory);
		return "index";
	}

	@RequestMapping(path = { "/changeAccount" }, method = { RequestMethod.POST })
	public String checkAccount(HttpSession session, Model model, UserDto user) {
		Object userId = session.getAttribute("loginUser");
		if (userId == null) {
			return "redirect:" + "/login";
		}
		UserDto curentUser = userService.findById(user.getId());
		curentUser.setPhone(user.getPhone());
		curentUser.setEmail(user.getEmail());
		curentUser.setFullName(user.getFullName());
		curentUser.setGender(user.isGender());
		curentUser.setUpdateAt(FormatDate.format(new Date()));
		userService.save(curentUser);
		return "redirect:" + "/myAccount";
	}

	@RequestMapping(path = { "/myAccount" }, method = { RequestMethod.GET })
	public String myAccount(HttpSession session, Model model) {
		Object userId = session.getAttribute("loginUser");
		if (userId == null) {
			return "redirect:" + "/login";
		}
		UserDto userDto = userService.findById((int) userId);
		int countCart = 0;
		if (userId != null) {
			countCart = cartService.countByUserId((int) userId);
		}
		model.addAttribute("countCart", countCart);
		model.addAttribute("user", userDto);
		return "_myAccount";
	}

	@RequestMapping(path = { "/changePassword" }, method = { RequestMethod.POST })
	public String checkchangePassword(HttpSession session, Model model, String passOld, String passNew,
			String rePassNew) {
		Object userId = session.getAttribute("loginUser");
		if (userId == null) {
			return "redirect:" + "/login";
		}
		int countCart = 0;
		if (userId != null) {
			countCart = cartService.countByUserId((int) userId);
		}
		model.addAttribute("countCart", countCart);
		if (!passNew.equals(rePassNew)) {
			model.addAttribute("messageServer", "Not Matching Password.");
			return "_changePassword";
		}
		UserDto user = userService.findById((int) userId);

		if (user.getPassword().equals(passOld)) {
			user.setPassword(passNew);
			user.setUpdateAt(FormatDate.format(new Date()));

			try {
				userService.save(user);
				session.setAttribute("messageServer", "Change password successful");
				return "redirect:" + "/changePassword";
			} catch (Exception e) {
				model.addAttribute("messageServer", "Error Change Pass");
				return "_changePassword";
			}
		}
		model.addAttribute("messageServer", "Old passwords do not match!");
		return "_changePassword";
	}

	@RequestMapping(path = { "/changePassword" }, method = { RequestMethod.GET })
	public String changePassword(HttpSession session, Model model) {
		Object userId = session.getAttribute("loginUser");
		if (userId == null) {
			return "redirect:" + "/login";
		}
		int countCart = 0;
		if (userId != null) {
			countCart = cartService.countByUserId((int) userId);
		}
		model.addAttribute("countCart", countCart);
		return "_changePassword";
	}

	@RequestMapping(path = { "/confirmOrder" }, method = { RequestMethod.GET })
	public String checkAdd(String id, Model model, HttpSession session) {

		BillDto bill = billService.findOne(id);
		bill.setUpdateAt(FormatDate.format(new Date()));
		// completed
		bill.setStatus(2);
		billService.save(bill);

		return "redirect:" + "/paymentHistory";
	}

	@RequestMapping(path = { "/paymentHistory" }, method = { RequestMethod.GET })
	public String paymentHistory(HttpSession session, Model model) {
		Object userId = session.getAttribute("loginUser");
		if (userId == null) {
			return "redirect:" + "/login";
		}
		List<OrderItemDto> listOrderItem = orderItemService.findByUserId((int) userId);
		List<BillDto> listBill = new ArrayList<BillDto>();
		for (OrderItemDto orderItemDto : listOrderItem) {
			BillDto bill = billService.findOne(orderItemDto.getBillId());
			if (!listBill.contains(bill)) {
				listBill.add(bill);
			}
		}
		listBill = bubbleSort(listBill);
		int countCart = 0;
		if (userId != null) {
			countCart = cartService.countByUserId((int) userId);
		}
		model.addAttribute("countCart", countCart);
		model.addAttribute("listBill", listBill);
		return "_paymentHistory";
	}

	@RequestMapping(path = { "/paymentDetail" }, method = { RequestMethod.GET })
	public String paymentDetail(HttpSession session, Model model, String id) throws IOException, ParseException {
		Object userId = session.getAttribute("loginUser");
		if (userId == null) {
			return "redirect:" + "/login";
		}
		BillDto bill = billService.findOne(id);
		List<OrderItemDto> listOrder = new ArrayList<OrderItemDto>();
		listOrder = orderItemService.findIdBill(id);
		List<ProductDto> listProduct = new ArrayList<ProductDto>();
		for (OrderItemDto orderItemDto : listOrder) {
			listProduct.add(productService.findOne(orderItemDto.getProductId()));
		}
		model.addAttribute("bill", bill);
		model.addAttribute("listOrder", listOrder);
		model.addAttribute("listProduct", listProduct);

		List<DeliverDto> listDeliverDtos = new ArrayList<DeliverDto>();
		String messageServer = null;

		if (bill.getStatus() == 2 || bill.getStatus() == 1) {
			String deliverId = bill.getDeliverId();
			Map<String, String> headerparams = new HashMap<String, String>();
			headerparams.put("Tracking-Api-Key", Apikey);
			headerparams.put("Content-Type", "application/json");
			String result = null;
			String ReqURL = "https://api.trackingmore.com/v3/trackings/get?tracking_numbers=" + deliverId;
			result = sendGet(ReqURL, headerparams, "GET");
			JSONObject jsonObject = convertStringToJson(result);

			Long code = (Long) jsonObject.get("code");
			// Request response is successful
			if (code == 200) {
				JSONArray data = (JSONArray) jsonObject.get("data");
				JSONObject data1 = (JSONObject) data.get(0);
				JSONObject originInfo = (JSONObject) data1.get("origin_info");
				JSONArray trackinfo = (JSONArray) originInfo.get("trackinfo");
				listDeliverDtos = getDeliverInfo(trackinfo);
				String aString = "";
			} else {
				messageServer = "Error order not found";
			}
		}

		model.addAttribute("listDeliver", listDeliverDtos);
		model.addAttribute("messageServer", messageServer);

		int countCart = 0;
		if (userId != null) {
			countCart = cartService.countByUserId((int) userId);
		}
		model.addAttribute("countCart", countCart);
		return "_paymentDetail";
	}

	@RequestMapping(path = { "/category" }, method = { RequestMethod.GET })
	public String category(HttpSession session, Model model, int id) {
		model.addAttribute("category", String.valueOf(id));
		List<ProductDto> listProduct = productService.findByCategory(id);
		List<CategoryDto> listCategory = categoryService.findAll();
		Object userId = session.getAttribute("loginUser");
		int countCart = 0;
		if (userId != null) {
			countCart = cartService.countByUserId((int) userId);
		}
		model.addAttribute("countCart", countCart);
		model.addAttribute("listProduct", listProduct);
		model.addAttribute("listCategory", listCategory);
		return "index";
	}

	@RequestMapping(path = { "/logout" }, method = { RequestMethod.GET })
	public String logout(HttpSession session) {
		String userString = (String) session.getAttribute("userName");
		if (userString == null) {
			return "redirect:" + "/login";
		}
		session.removeAttribute("loginUser");
		session.invalidate();
		System.out.println("Filter Info:\t" + new Date() + "\t/logout");
		return "redirect:" + "/login";
	}

	public List<DeliverDto> getDeliverInfo(JSONArray trackinfo) throws IOException, ParseException {

		List<DeliverDto> result = new ArrayList<DeliverDto>();
		for (int i = 0; i < trackinfo.size(); i++) {
			DeliverDto deliverDto = new DeliverDto();
			JSONObject json1 = (JSONObject) trackinfo.get(i);
			deliverDto.setCheckpointDate((String) json1.get("checkpoint_date"));
			deliverDto.setCheckpointDeliveryStatus((String) json1.get("checkpoint_delivery_status"));
			deliverDto.setTrackingDetail((String) json1.get("tracking_detail"));
			deliverDto.setLocation((String) json1.get("location"));
			deliverDto.setCheckpointDeliverySubstatus((String) json1.get("checkpoint_delivery_substatus"));
			result.add(deliverDto);
		}

		return result;
	}

	public JSONObject convertStringToJson(String data) {
		JSONParser parser = new JSONParser();
		JSONObject json1 = null;
		try {
			json1 = (JSONObject) parser.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return json1;
	}

	private String sendPost(String url, Map<String, String> headerParams, List<String> bodyParams, String mothod) {
		OutputStreamWriter out = null;
		BufferedReader in = null;
		StringBuilder result = new StringBuilder();
		try {
			URL realUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();

			conn.setDoOutput(true);
			conn.setDoInput(true);

			conn.setRequestMethod(mothod);

			for (Map.Entry<String, String> entry : headerParams.entrySet()) {
				conn.setRequestProperty(entry.getKey(), entry.getValue());
			}
			conn.connect();

			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");

			StringBuffer sbBody = new StringBuffer();
			for (String str : bodyParams) {
				sbBody.append(str);
			}
			out.write(sbBody.toString());

			out.flush();

			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result.toString();
	}

	public static String sendGet(String url, Map<String, String> headerParams, String mothod) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);

			HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();

			connection.setRequestMethod(mothod);

			for (Map.Entry<String, String> entry : headerParams.entrySet()) {
				connection.setRequestProperty(entry.getKey(), entry.getValue());
			}

			connection.connect();

//			Map<String, List<String>> map = connection.getHeaderFields();
//
//			for (String key : map.keySet()) {
//				System.out.println(key + "--->" + map.get(key));
//			}

			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			connection.disconnect();
		} catch (Exception e) {
			System.out.println("Exception " + e);
			e.printStackTrace();
		}

		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public List<BillDto> bubbleSort(List<BillDto> data) {
		BillDto temp;
		int i, j;

		boolean swapped = false;
		for (i = 0; i < data.size() - 1; i++) {
			swapped = false;

			for (j = 0; j < data.size() - 1 - i; j++) {

				Date date1 = null;
				Date date2 = null;
				try {
					date1 = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(data.get(j).getCreateAt());
					date2 = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(data.get(j + 1).getCreateAt());
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// kiem xa xem so ke tiep co nho hon so hien tai hay khong
				// trao doi cac so.
				// (Muc dich: lam noi bot (bubble) so lon nhat)
				if (!date2.before(date1)) {
//					if (arr[j] > arr[j + 1]) {
					temp = data.get(j);
					data.set(j, data.get(j + 1));
					data.set(j + 1, temp);
					swapped = true;
				}
			}
			if (!swapped) {
				break;
			}
		}
		return data;
	}
}
