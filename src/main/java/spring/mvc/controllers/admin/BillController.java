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
import spring.mvc.dto.DeliverDto;
import spring.mvc.dto.OrderItemDto;
import spring.mvc.dto.ProductDto;
import spring.mvc.dto.UserDto;
import spring.mvc.services.BillService;
import spring.mvc.services.OrderItemService;
import spring.mvc.services.ProductService;
import spring.mvc.services.UserService;
import spring.mvc.utils.FormatDate;

@Controller
@RequestMapping(path = { "/admin/bill" })
public class BillController {
	@Autowired
	BillService billService;
	@Autowired
	OrderItemService orderService;
	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;

	private final String Apikey = "p8oj1xot-jeuu-raak-1ptl-9q3u6sbqxr2h";

	public BillController() {
		System.out.println("BillController");
	}

	@RequestMapping(path = { "/detail" }, method = { RequestMethod.GET })
	public String showDetail(String id, Model model, HttpSession session) throws IOException, ParseException {
		BillDto bill = billService.findOne(id);
		List<OrderItemDto> listOrder = new ArrayList<OrderItemDto>();
		listOrder = orderService.findIdBill(id);
		List<ProductDto> listProduct = new ArrayList<ProductDto>();
		for (OrderItemDto orderItemDto : listOrder) {
			listProduct.add(productService.findOne(orderItemDto.getProductId()));
		}
		model.addAttribute("bill", bill);
		model.addAttribute("listOrder", listOrder);
		model.addAttribute("listProduct", listProduct);

		List<DeliverDto> listDeliverDtos = new ArrayList<DeliverDto>();
		String messageServer = null;
		if (!bill.getDeliverId().equals(bill.getId())) {
			if (bill.getStatus() == 2 || bill.getStatus() == 1) {
				String deliverId = bill.getDeliverId();
				Map<String, String> headerparams = new HashMap<String, String>();
				headerparams.put("Tracking-Api-Key", Apikey);
				headerparams.put("Content-Type", "application/json");
				String result = null;
				String ReqURL = "https://api.trackingmore.com/v3/trackings/get?tracking_numbers=" + deliverId;
				result = sendGet(ReqURL, headerparams, "GET");
				JSONObject jsonObject = convertStringToJson(result);
				result = null;
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
		}

		model.addAttribute("listDeliver", listDeliverDtos);
		model.addAttribute("messageServer", messageServer);
		return "billDetail";
	}

	@RequestMapping(path = { "/deliver" }, method = { RequestMethod.POST })
	public String showDeliver(String billId, Model model, HttpSession session, String deliverId) {
		BillDto bill = billService.findOne(billId);

		List<OrderItemDto> listOrder = new ArrayList<OrderItemDto>();
		listOrder = orderService.findIdBill(billId);
		List<ProductDto> listProduct = new ArrayList<ProductDto>();
		for (OrderItemDto orderItemDto : listOrder) {
			listProduct.add(productService.findOne(orderItemDto.getProductId()));
		}
		model.addAttribute("bill", bill);
		model.addAttribute("listOrder", listOrder);
		model.addAttribute("listProduct", listProduct);
		int userId = listOrder.get(0).getUserId();
		UserDto user = userService.findById(userId);

		Map<String, String> headerparams = new HashMap<String, String>();
		headerparams.put("Tracking-Api-Key", Apikey);
		headerparams.put("Content-Type", "application/json");
		// ---bodyParams
		List<String> bodyParams = new ArrayList<String>();
		String result = null;
		String requestData = "[{\"tracking_number\": \"" + deliverId + "\",\"courier_code\": \"viettelpost\""
				+ ",\"order_number\": \"#1234\"" + ",\"destination_code\": \"LV\""
				+ ",\"logistics_channel\": \"4px channel\"" + ",\"customer_name\": \"" + user.getFullName()
				+ "\",\"customer_email\": \"" + user.getEmail() + "\",\"customer_phone\": \"+" + user.getPhone()
				+ "\",\"note\": \"check\"" + ",\"title\": \"Giao hang\"" + ",\"lang\": \"en\"}]";
		String messageServer = null;
		String ReqURL = "https://api.trackingmore.com/v3/trackings/create";
		bodyParams.add(requestData);
		result = sendPost(ReqURL, headerparams, bodyParams, "POST");
		JSONObject jsonObject = convertStringToJson(result);

		Long code = (Long) jsonObject.get("code");
		// Request response is successful
		if (code == 200) {
			JSONObject data = (JSONObject) jsonObject.get("data");
			List<JSONObject> error = (List<JSONObject>) data.get("error");
			if (!error.isEmpty()) {
				Long errorCode = (Long) error.get(0).get("errorCode"); // Tracking No. already exists.
				if (423 == errorCode) {
					messageServer = "Deliver Id already exists.";
					model.addAttribute("messageServer", messageServer);
					return "billDetail";
				}
			} else {
				if (bill != null) {
					bill.setDeliverId(deliverId);
					bill.setStatus(1);
					bill.setUpdateAt(FormatDate.format(new Date()));
					billService.save(bill);
					return "redirect:" + "/admin/bill/list";
				}
			}
		}

		messageServer = "Please enter Deliver code with 12 characters";
		model.addAttribute("messageServer", messageServer);
		return "billDetail";
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

	@RequestMapping(path = { "/list" }, method = { RequestMethod.GET })
	public String showList(Model model) {
		List<BillDto> listBill = billService.findAll();
		model.addAttribute("listBill", listBill);
		List<OrderItemDto> listOrder = new ArrayList<OrderItemDto>();
		List<String> listUser = new ArrayList<String>();
		for (BillDto billDto : listBill) {
			listOrder = orderService.findIdBill(billDto.getId());
			if (!listOrder.isEmpty()) {
				listUser.add(userService.findById(listOrder.get(0).getUserId()).getUserName());
			}
		}
		model.addAttribute("listUser", listUser);
		return "listBill";
	}

	@RequestMapping(path = { "/add" }, method = { RequestMethod.GET })
	public String showAdd() {
		return "addBill";
	}

	@RequestMapping(path = { "/revenue" }, method = { RequestMethod.POST })
	public String showRevenue(Model model, HttpSession session, String startDate, String endDate)
			throws java.text.ParseException {

		Date sDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
		Date eDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
		eDate.setDate(eDate.getDate() + 1);
		long sum = 0;
		List<BillDto> list = billService.findAll();

		List<OrderItemDto> listOrder = new ArrayList<OrderItemDto>();
		List<String> listUser = new ArrayList<String>();
		for (BillDto billDto : list) {
			listOrder = orderService.findIdBill(billDto.getId());
			if (!listOrder.isEmpty()) {
				listUser.add(userService.findById(listOrder.get(0).getUserId()).getUserName());
			}
		}
		if (eDate.before(sDate)) {
			model.addAttribute("messageServer", "End date before Start date");
			for (BillDto billDto : list) {
				if (billDto.getStatus() == 2 || (billDto.getPaymentId() == 2 && billDto.getStatus() == 1)) {
					sum += billDto.getTotal();
				}
			}

			model.addAttribute("sumBill", sum);
			model.addAttribute("listBill", list);
			model.addAttribute("listUser", listUser);
			return "revenue";
		}

		for (int i = 0; i < list.size(); i++) {
			Date date;
			if (list.get(i).getUpdateAt() == null || "".equals(list.get(i).getUpdateAt())) {
				date = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(list.get(i).getCreateAt());
			} else {
				date = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(list.get(i).getUpdateAt());
			}
			if (sDate.before(date) && date.before(eDate)) {
				if (list.get(i).getStatus() == 2 || (list.get(i).getPaymentId() == 2 && list.get(i).getStatus() == 1)) {
					sum += list.get(i).getTotal();
				}
			} else {
				list.remove(i);
				i--;
			}
		}

		model.addAttribute("listBill", list);
		model.addAttribute("listUser", listUser);
		model.addAttribute("sumBill", sum);
		return "revenue";
	}

	@RequestMapping(path = { "/revenue" }, method = { RequestMethod.GET })
	public String revenue(Model model, HttpSession session) {
		model.addAttribute("messageServer", "");
		List<BillDto> list = billService.findAll();
		long sum = 0;
		List<OrderItemDto> listOrder = new ArrayList<OrderItemDto>();
		List<String> listUser = new ArrayList<String>();
		for (BillDto billDto : list) {
			if (billDto.getStatus() == 2 || (billDto.getPaymentId() == 2 && billDto.getStatus() == 1)) {
				sum += billDto.getTotal();
			}
			listOrder = orderService.findIdBill(billDto.getId());
			if (!listOrder.isEmpty()) {
				listUser.add(userService.findById(listOrder.get(0).getUserId()).getUserName());
			}
		}
		model.addAttribute("listBill", list);
		model.addAttribute("listUser", listUser);
		model.addAttribute("sumBill", sum);
		return "revenue";
	}

	@RequestMapping(path = { "/add" }, method = { RequestMethod.POST })
	public String checkAdd(BillDto billDto, Model model, HttpSession session) {
		try {
			billDto.setCreateAt(FormatDate.format(new Date()));
			billService.save(billDto);
		} catch (Exception e) {
			model.addAttribute("bill", billDto);
			return "addBill";
		}
		return "redirect:" + "/admin/bill/list";
	}

	@RequestMapping(path = { "/edit" }, method = { RequestMethod.GET })
	public String showEdit(String id, Model model, HttpSession session) {
		BillDto billDto = billService.findOne(id);
		model.addAttribute("bill", billDto);
		return "editBill";
	}

	@RequestMapping(path = { "/edit" }, method = { RequestMethod.POST })
	public String checkEdit(HttpSession session, Model model, BillDto billDto) {
		BillDto bill = billService.findOne(billDto.getId());
		billDto.setCreateAt(bill.getCreateAt());
		billDto.setUpdateAt(FormatDate.format(new Date()));

		try {
			billService.save(billDto);
		} catch (Exception e) {
			model.addAttribute("bill", billDto);
			return "editBill";
		}
		return "redirect:" + "/admin/bill/list";
	}

	@RequestMapping(path = { "/delete" }, method = { RequestMethod.GET })
	public String checkDelete(Model model, String id) {
		billService.delete(id);
		return "redirect:" + "/admin/bill/list";
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
}
