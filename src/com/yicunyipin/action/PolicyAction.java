package com.yicunyipin.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.yicunyipin.entity.News;
import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.Policy;
import com.yicunyipin.service.PolicyService;
import com.yicunyipin.util.NavUtil;
import com.yicunyipin.util.PageUtil;
import com.yicunyipin.util.ResponseUtil;
import com.yicunyipin.util.StringUtil;

import org.springframework.stereotype.Controller;

@Controller
public class PolicyAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;

	@Resource
	private PolicyService policyService;
	private Policy policy;
//	private NewsService newsService;
	private String page;
//	private News news;
	private int policyId;
	private String mainPage;
	private String navCode;
//	private List<News> newsList;
	private List<Policy> policyList;
	private Policy s_policy;
	private Long total;
	private String pageCode;
	private String rows;
	private String ids;
	public PolicyAction() {
		// TODO Auto-generated constructor stub
	}

	
	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public Policy getPolicy() {
		return policy;
	}


	public void setPolicy(Policy policy) {
		this.policy = policy;
	}


	public PolicyService getPolicyService() {
		return policyService;
	}


	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
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


	


	public Policy getS_policy() {
		return s_policy;
	}


	public void setS_policy(Policy s_policy) {
		this.s_policy = s_policy;
	}
	
	

	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
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


	public List<Policy> getPolicyList() {
		return policyList;
	}


	public void setPolicyList(List<Policy> policyList) {
		this.policyList = policyList;
	}


	public Long getTotal() {
		return total;
	}


	public void setTotal(Long total) {
		this.total = total;
	}


	public String getPageCode() {
		return pageCode;
	}


	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	

	public int getPolicyId() {
		return policyId;
	}


	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}


	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	
	
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(StringUtil.isEmpty(page)){
			page="1";
		}System.out.println("111");
		PageBean pageBean=new PageBean(Integer.parseInt(page),8);
		policyList = policyService.findPolicyList(s_policy, pageBean);
		
		total = policyService.getPolicyCount(s_policy);
		
		

		StringBuffer param=new StringBuffer();
//		if(s_news!=null){
//			if(s_news.getNewsType()!=null){
//				param.append("s_news.newsType.id="+s_news.getNewsType().getId());
//			}
//			
//			/*if(StringUtil.isNotEmpty(s_news.getName())){
//				hql.append(" and name like ?");
//				param.add("%"+s_news.getName()+"%");
//			}*/
//		}
//		
		pageCode=PageUtil.genPagination(request.getContextPath()+"/news.action", total, Integer.parseInt(page), 8, param.toString());
		navCode=NavUtil.genNavCode("产业政策列表");
		mainPage="policyList.jsp";
		return SUCCESS;	
	}


	/**
	 *  admin后台-保存产品政策信息
	 * @return
	 * @throws Exception
	 */
	public String save()throws Exception{
		if(policy.getId()==0){
			policy.setAddTime(new Date());
			policy.setUpdateTime(new Date());
		}
		else {
			policy.setUpdateTime(new Date());
		}
		policyService.savePolicy(policy);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	/**
	 * admin分页查询产品政策信息
	 * @return
	 * @throws Exception
	 */
	public String list()throws Exception{
		System.out.println("page:"+page+"rows:"+rows);
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<Policy> policyList=policyService.findPolicyList(s_policy, pageBean);
		long total=policyService.getPolicyCount(s_policy);
		JsonConfig jsonConfig = new JsonConfig();
		//jsonConfig.registerJsonValueProcessor(NewsType.class, new ObjectJsonValueProcessor(new String[]{"id","typeName"}, NewsType.class));
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));  
		JSONArray rows=JSONArray.fromObject(policyList,jsonConfig);
		//System.out.println("rows"+rows);
		JSONObject result=new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		//System.out.println("result:"+result);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}  
	/**
	 * admin删除产业政策
	 * @return
	 * @throws Exception
	 */
	public String delete()throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			Policy policy =policyService.getPolicyById(Integer.parseInt(idsStr[i]));
			policyService.delete(policy);						
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	public String showPolicy()throws Exception{
		policy = policyService.getPolicyById(policyId);System.out.println(policyId);
		//news=newsService.getNewsById(newsId);
		mainPage="policyDetails.jsp";
		navCode=NavUtil.genNavCode("产业政策信息");
		return SUCCESS;
	}
	
	public String lianxiwomen()throws Exception{
		//policy = policyService.getPolicyById(policyId);System.out.println(policyId);
		//news=newsService.getNewsById(newsId);
		mainPage="lianxiwomen.jsp";
		navCode=NavUtil.genNavCode("联系我们");
		return SUCCESS;
	}
	
	public String fuwuzongzhi()throws Exception{
		//policy = policyService.getPolicyById(policyId);System.out.println(policyId);
		//news=newsService.getNewsById(newsId);
		mainPage="default.jsp";
		navCode=NavUtil.genNavCode("服务宗旨");
		return SUCCESS;
	}
	
	public String xiazai()throws Exception{
		//policy = policyService.getPolicyById(policyId);System.out.println(policyId);
		//news=newsService.getNewsById(newsId);
		mainPage="xiazai.jsp";
		navCode=NavUtil.genNavCode("会员申请APP下载");
		return SUCCESS;
	}
}
