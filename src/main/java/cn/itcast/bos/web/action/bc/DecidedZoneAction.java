package cn.itcast.bos.web.action.bc;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.bc.DecidedZone;
import cn.itcast.bos.domain.bc.Region;
import cn.itcast.bos.domain.bc.Subarea;
import cn.itcast.bos.page.PageRequestBean;
import cn.itcast.bos.page.PageResponseBean;
import cn.itcast.bos.web.action.base.BaseAction;
import cn.itcast.crm.domain.Customer;

/**
 * 定区管理
 */
public class DecidedZoneAction extends BaseAction implements ModelDriven<DecidedZone> {

	private static final long serialVersionUID = 1L;

	private DecidedZone decidedZone = new DecidedZone();

	@Override
	public DecidedZone getModel() {
		return decidedZone;
	}

	// 业务方法 --- 添加，修改定区
	public String saveOrUpdate() {
		// 调用业务层，添加定区
		decidedZoneService.saveOrUpdate(decidedZone, subareaId);
		return "saveOrUpdateSUCCESS";
	}

	private String[] subareaId;

	public void setSubareaId(String[] subareaId) {
		this.subareaId = subareaId;
	}

	// 业务方法 --- 定区分页查询
	public String pageQuery() {
		// 封装 PageRequestBean
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DecidedZone.class);
		PageRequestBean pageRequestBean = initPageRequestBean(detachedCriteria);

		// 调用业务层， 查询 PageResponseBean
		PageResponseBean pageResponseBean = decidedZoneService.pageQuery(pageRequestBean);

		// 压入值栈返回
		ActionContext.getContext().put("pageResponseBean", pageResponseBean);

		return "pageQuerySUCCESS";
	}

	// 业务方法 --- 获取关联的
	public String attachRegions() {
		Set<Subarea> subareasSet = decidedZoneService.attachRegions(decidedZone.getId());
		List<Subarea> subareas = new ArrayList<Subarea>(subareasSet);
		ActionContext.getContext().put("datas", subareas);
		System.out.println("subareas:  " + subareas.toString());

		return "attachRegionsSUCCESS";
	}

	// 业务方法 --- 查询未关联的客户
	public String findNoAssociationCustomers() {
		// 调用 Hessian 获得远程列表
		List<Customer> customers = customerService.findNoAssociationCustomers();
		// 转换为 json
		ActionContext.getContext().put("customers", customers);
		return "findNoAssociationCustomersSUCCESS";
	}

	// 业务方法 --- 查询已经关联的客户
	public String findAssociationCustomers() {
		// 调用 Hessian 获得远程列表
		List<Customer> un_customers = customerService.findHasAssociationCustomers(decidedZone.getId());
		// 转换为 json
		ActionContext.getContext().put("un_customers", un_customers);
		return "findAssociationCustomersSUCCESS";
	}
	
	// 业务方法 --- 关联客户到定区
	public String assignedCustomerToDecidedZone(){
		// 指定客户到定区
		customerService.assignedCustomerToDecidedZone(customerIds, decidedZone.getId());
		return "assignedCustomerToDecidedZone";
	}
	
	private String[] customerIds;
	public void setCustomerIds(String[] customerIds) {
		this.customerIds = customerIds;
	}
	
	

}
