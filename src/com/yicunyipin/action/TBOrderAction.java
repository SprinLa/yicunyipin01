package com.yicunyipin.action;

import com.opensymphony.xwork2.ActionSupport;
import com.yicunyipin.entity.*;
import com.yicunyipin.service.TBOrderService;
import com.yicunyipin.service.TBProductService;
import com.yicunyipin.util.NavUtil;
import com.yicunyipin.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class TBOrderAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;


	@Resource
	private TBOrderService tbOrderService;

	private String mainPage;
	private String navCode;

	private TBOrder tbOrder;

	private List<TBOrder> tbOrderList;

	private int status;
	private String orderNo;

	private String page;
	private String rows;

	private String id;

	private String orderNos;

	public TBOrderService getTbOrderService() {
		return tbOrderService;
	}

	public void setTbOrderService(TBOrderService tbOrderService) {
		this.tbOrderService = tbOrderService;
	}
	public String getOrderNos() {
		return orderNos;
	}

	public void setOrderNos(String orderNos) {
		this.orderNos = orderNos;
	}

	public TBOrder getTbOrder() {
		return tbOrder;
	}

	public void setTbOrder(TBOrder tbOrder) {
		this.tbOrder = tbOrder;
	}

	public List<TBOrder> getTbOrderList() {
		return tbOrderList;
	}

	public void setTbOrderList(List<TBOrder> tbOrderList) {
		this.tbOrderList = tbOrderList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public TBOrder getOrder() {
		return tbOrder;
	}

	public void setOrder(TBOrder tbOrder) {
		this.tbOrder = tbOrder;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public String getNavCode() {
		return navCode;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	public String findOrder()throws Exception{
		HttpSession session=request.getSession();
		TBUser currentUser=(TBUser) session.getAttribute("currentUser");
		if(tbOrder==null){
			tbOrder=new TBOrder();
		}
		if (currentUser == null){
			System.out.println("请先登录再查看订单信息!");
			return "login";
		}
		//TBUser user = EntityConvert.getTBUser(currentUser);
		tbOrder.setBuyerUser(currentUser);
		tbOrderList=tbOrderService.findOrder(tbOrder, null);
		System.out.println("tbOrderList:"+tbOrderList.size());
		if (tbOrderList.size()>0){
			tbOrder = tbOrderList.get(0);
		}
		navCode=NavUtil.genNavCode("我的订单 (当前用户:"+currentUser.getUserName()+")");
		mainPage="orderList2.jsp";
		return "orderlist";
	}

	//保存订单
	public String saveOrder()throws Exception{
		HttpSession session=request.getSession();
		TBProduct tbProduct = (TBProduct)session.getAttribute("product");
		TBUser currentUser=(TBUser)session.getAttribute("currentUser");
		if(tbOrder==null){
			tbOrder=new TBOrder();
		}
		if (currentUser == null){
			System.out.println("请先登录再查看填写订单!");
			return "login";
		}
		tbProduct.setOrderNum(tbProduct.getOrderNum()+1);
		tbOrder.setProduct(tbProduct);
		tbOrder.setBuyerUser(currentUser);
		tbOrder.setBuyerTime(new Date());
		int buyerId = currentUser.getId();
		int proId = tbProduct.getId();
		TBOrder getTBOrder = tbOrderService.getOrder(buyerId,proId);
		tbOrderService.saveOrUpdateOrder(tbOrder);
		navCode=NavUtil.genNavCode("提交订单");
		mainPage="orderFill-result.jsp";

		session.removeAttribute("product");
		return SUCCESS;
	}

	public String list()throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<TBOrder> tbOrderList=tbOrderService.findOrder(tbOrder, pageBean);
		long total=tbOrderService.getOrderCount(tbOrder);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"orderProductList"});
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		jsonConfig.registerJsonValueProcessor(User.class,new ObjectJsonValueProcessor(new String[]{"id","userName"}, User.class));
		JSONArray rows=JSONArray.fromObject(tbOrderList, jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}
	
	/**
	 * start kongjun
	 */
	
	//private HttpServletRequest request;

	private Date startTime;
	private Date endTime;
	
	private String start;
	private String end;
	
	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

//	@Resource
//	private TBOrderService tbOrderService;
	
	@Resource
	private TBProductService tbProductService;

//	private String mainPage;
//	private String navCode;
//
//	private TBOrder tbOrder;
//
//	private List<TBOrder> tbOrderList;
//
//	private int status;
//	private String orderNo;
//
//	private String page;
//	private String rows;
//
//	private String id;
//
//	private String orderNos;
//
//	public TBOrderService getTbOrderService() {
//		return tbOrderService;
//	}
//
//	public void setTbOrderService(TBOrderService tbOrderService) {
//		this.tbOrderService = tbOrderService;
//	}
//	public String getOrderNos() {
//		return orderNos;
//	}
//
//	public void setOrderNos(String orderNos) {
//		this.orderNos = orderNos;
//	}
//
//	public TBOrder getTbOrder() {
//		return tbOrder;
//	}
//
//	public void setTbOrder(TBOrder tbOrder) {
//		this.tbOrder = tbOrder;
//	}
//
//	public List<TBOrder> getTbOrderList() {
//		return tbOrderList;
//	}
//
//	public void setTbOrderList(List<TBOrder> tbOrderList) {
//		this.tbOrderList = tbOrderList;
//	}
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public String getPage() {
//		return page;
//	}
//
//	public void setPage(String page) {
//		this.page = page;
//	}
//
//	public String getRows() {
//		return rows;
//	}
//
//	public void setRows(String rows) {
//		this.rows = rows;
//	}
//
//	public int getStatus() {
//		return status;
//	}
//
//	public void setStatus(int status) {
//		this.status = status;
//	}
//
//	public String getOrderNo() {
//		return orderNo;
//	}
//
//	public void setOrderNo(String orderNo) {
//		this.orderNo = orderNo;
//	}
//
//	public TBOrder getOrder() {
//		return tbOrder;
//	}
//
//	public void setOrder(TBOrder tbOrder) {
//		this.tbOrder = tbOrder;
//	}
//
//	public String getMainPage() {
//		return mainPage;
//	}
//
//	public void setMainPage(String mainPage) {
//		this.mainPage = mainPage;
//	}
//
//	public String getNavCode() {
//		return navCode;
//	}
//
//	public void setNavCode(String navCode) {
//		this.navCode = navCode;
//	}
//
//	public String findOrder()throws Exception{
//		HttpSession session=request.getSession();
//		User currentUser=(User) session.getAttribute("currentUser");
//		if(tbOrder==null){
//			tbOrder=new TBOrder();
//		}
//		if (currentUser == null){
//			System.out.println("请先登录再查看订单信息!");
//			return "login";
//		}
//		TBUser user = EntityConvert.getTBUser(currentUser);
//		tbOrder.setBuyerUser(user);
//		tbOrderList=tbOrderService.findOrder(tbOrder, null);
//		System.out.println("tbOrderList:"+tbOrderList.size());
//		if (tbOrderList.size()>0){
//			tbOrder = tbOrderList.get(0);
//		}
//		navCode=NavUtil.genNavCode("我的订单");
//		mainPage="orderList2.jsp";
//		return "orderlist";
//	}
//
//	//保存订单
//	public String saveOrder()throws Exception{
//		HttpSession session=request.getSession();
//		//TBOrder tbOrder=new TBOrder();
//		Product product = (Product)session.getAttribute("product");
//		TBProduct tbProduct = EntityConvert.getTBProduct(product);
//		User currentUser=(User)session.getAttribute("currentUser");
//		if(tbOrder==null){
//			tbOrder=new TBOrder();
//		}
//		if (currentUser == null){
//			System.out.println("请先登录再查看填写订单!");
//			return "login";
//		}
//		TBUser tbUser = EntityConvert.getTBUser(currentUser);
//		tbOrder.setProduct(tbProduct);
//		tbOrder.setBuyerUser(tbUser);
//		tbOrder.setBuyerTime(new Date());
//		int buyerId = tbUser.getId();
//		int proId = tbProduct.getId();
//		TBOrder getTBOrder = tbOrderService.getOrder(buyerId,proId);
//		//if (getTBOrder != null){
//		//	getTBOrder.setOrderCount(getTBOrder.getOrderCount()+1);
//		//	tbOrderService.saveOrUpdateOrder(getTBOrder);
//		//}
//		//else {
//			tbOrderService.saveOrUpdateOrder(tbOrder);
//		//}
//		navCode=NavUtil.genNavCode("提交订单");
//		mainPage="orderFill-result.jsp";
//
//		session.removeAttribute("product");
//		return SUCCESS;
//	}
//
//	public String list()throws Exception{
//		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
//		List<TBOrder> tbOrderList=tbOrderService.findOrder(tbOrder, pageBean);
//		long total=tbOrderService.getOrderCount(tbOrder);
//		JsonConfig jsonConfig=new JsonConfig();
//		jsonConfig.setExcludes(new String[]{"orderProductList"});
//		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
//		jsonConfig.registerJsonValueProcessor(User.class,new ObjectJsonValueProcessor(new String[]{"id","userName"}, User.class));
//		JSONArray rows=JSONArray.fromObject(tbOrderList, jsonConfig);
//		JSONObject result=new JSONObject();
//		result.put("rows", rows);
//		result.put("total", total);
//		ResponseUtil.write(ServletActionContext.getResponse(), result);
//		return null;
//	}
//
//	@Override
//	public void setServletRequest(HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		this.request=request;
//	}
	
	public String getOrderList()throws Exception{
		TBUser user = (TBUser)request.getSession().getAttribute("currentUser");
		if(user == null){
			return "login";
		}else{
			//String pass = user.getPassword();
			//user.setPassword(MD5.getMD5(pass.getBytes()));
			List<TBProduct> productList = tbProductService.getProductByUser(user);
			System.out.println("ID :"+productList.get(0).getId() +"时间"+startTime+ "   "+ endTime);
			tbOrderList = tbOrderService.findOrderBySeller(productList.get(0).getId(), startTime, endTime);
			
			JSONArray rows = new JSONArray();
			for(TBOrder o :tbOrderList)
			{
				JSONObject r=new JSONObject();
				r.put("id", o.getId());
				r.put("memberName", o.getProduct().getUser().getMemberName());
				r.put("productName", o.getProduct().getUser().getProductName());
				r.put("buyerName", o.getBuyerName());
				r.put("buyerPhone", o.getBuyerPhone());
				r.put("buyerCompany", o.getBuyerCompany());
				r.put("buyerAddress", o.getBuyerAddress());
				r.put("orderCount", o.getOrderCount());
				r.put("orderSum", o.getProduct().getPrice()*o.getOrderCount());
				Date date = o.getBuyerTime();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				r.put("buyTime", format.format(date));
				System.out.println("sssss" + format.format(date));
				rows.add(r);
			}
			//JSONObject result=new JSONObject();
			//result.put("rows", rows);
			//System.out.println("rows:"+rows);
			//result.put("total", total);
			
			request.setAttribute("orderList", rows);
			//ResponseUtil.write(ServletActionContext.getResponse(), rows);
			//request.setAttribute("productList", productList);
			request.setAttribute("mainPage", "/background/goods/orderList.jsp");
			return "orderList";
		}
		
		
	}
	
	public void androidGetOrderList(){
		try{
		System.out.println("kaishi " + start + " end " + end );
		Map m = new HashMap<>();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		TBUser user = (TBUser)request.getSession().getAttribute("currentUser");
		System.out.println(user.toString());
//		TBUser user = new TBUser();
//		user.setId(11);
		if(user == null){
			m.put("code", "B0001");
			m.put("msg", "请登录");
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date s = sdf.parse(start);
			Date e = sdf.parse(end);
			System.out.println(s);
			System.out.println(e);
			//String pass = user.getPassword();
			//user.setPassword(MD5.getMD5(pass.getBytes()));
			List<TBProduct> productList = tbProductService.getProductByUser(user);
			//System.out.println("ID :"+productList.get(0).getId() +"时间"+s+ "   "+ e);
			if(productList.size() == 0){
				m.put("code", "A0000");
				//m.put("msg", "请登录");
				m.put("data", productList);
				m.put("msg", "没提交商品");
			}else{
				tbOrderList = tbOrderService.findOrderBySeller(productList.get(0).getId(), s, e);
				List<TBOrder> tbOrders = new ArrayList<TBOrder>();
				for(TBOrder dd :tbOrderList){
					System.out.println(dd.getBuyerCompany());
					TBOrder order = new TBOrder();
					order = (TBOrder) BeanUtils.cloneBean(dd);
					tbOrders.add(order);
				}
				
				//request.setAttribute("orderList", tbOrderList);
				//request.setAttribute("productList", productList);
				//request.setAttribute("mainPage", "/background/goods/orderList.jsp");
				//return "orderList";
				Map data = new HashMap<>();
				if(tbOrders.size() != 0){
					data.put("name", user.getMemberName());
					System.out.println(user.getMemberName());
					data.put("price", productList.get(0).getPrice());
					System.out.println(productList.get(0).getPrice());
					for(int i = 0;i<tbOrders.size();i++){
						tbOrders.get(i).setBuyerUser(null);
						tbOrders.get(i).setProduct(null);
					}
					data.put("list", tbOrders);
					data.put("isEmpty", 1);
					m.put("code", "A0000");
					m.put("data", data);
				}else{
					data.put("isEmpty", 0);
					m.put("code", "A0000");
					m.put("data", data);
				}
				
//				Iterator<Map.Entry<String, Object>> it = m.entrySet().iterator();
//				while(it.hasNext()) {
//					Map.Entry<String, Object> entry = it.next();
//					String key = entry.getKey();
//					Object value = entry.getValue();
//				}
				
//				JsonConfig config = new JsonConfig();
//				config.setJsonPropertyFilter(new PropertyFilter() {
//				@Override
//				 public boolean apply(Object source, String name, Object value) {
//			           if(name.equals("buyerUser")||name.equals("product")) {
//			             return true;
//			           } else {
//			             return false;
//			          }
//			        }
//				                });
//				
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
				JSONObject jsonObject = JSONObject.fromObject(m, jsonConfig);
				System.out.println(jsonObject.toString());
				out.println(jsonObject.toString());
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String orderSelect() throws Exception {
		request.setAttribute("mainPage", "/background/goods/orderSelect.jsp");
		return "orderSelect";
	}
	/**
	 * end kongjun
	 */
	
	/*start shaoshan*/
	public String customerList()throws Exception{
		//System.out.println("11");
		//System.out.println(verified);
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<TBOrder> orderList;
		//TBProduct s_product = new TBProduct();
		TBOrder s_order = new TBOrder();
        orderList = tbOrderService.findOrder(s_order, pageBean);
		
		long total=tbOrderService.getOrderCount(s_order);
		JSONArray rows = new JSONArray();
		for(TBOrder o :orderList)
		{
			JSONObject r=new JSONObject();
			r.put("id", o.getId());
			r.put("memberName", o.getProduct().getUser().getMemberName());
			r.put("productName", o.getProduct().getUser().getProductName());
			r.put("buyerName", o.getBuyerName());
			r.put("buyerPhone", o.getBuyerPhone());
			r.put("buyerCompany", o.getBuyerCompany());
			r.put("buyerAddress", o.getBuyerAddress());
			Date date = o.getBuyerTime();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			r.put("buyerTime", format.format(date));
			rows.add(r);
		}
		JSONObject result=new JSONObject();
		result.put("rows", rows);
		//System.out.println("rows:"+rows);
		result.put("total", total);
		//System.out.println("rows:"+result);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}

//	@Override
//	public void setServletRequest1(HttpServletRequest arg0) {
//		// TODO Auto-generated method stub
//		
//	}

	/*end shaoshan*/

}
