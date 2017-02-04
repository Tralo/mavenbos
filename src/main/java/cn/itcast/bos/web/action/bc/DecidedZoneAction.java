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
/**
 * 定区管理
 */
public class DecidedZoneAction extends BaseAction implements ModelDriven<DecidedZone>{

	private static final long serialVersionUID = 1L;
	
	private DecidedZone decidedZone = new DecidedZone();

	@Override
	public DecidedZone getModel() {
		return decidedZone;
	}
	// 业务方法 --- 添加，修改定区
	public String saveOrUpdate(){
		// 调用业务层，添加定区
		decidedZoneService.saveOrUpdate(decidedZone, subareaId);
		return "saveOrUpdateSUCCESS";
	}
	
	private String[] subareaId;

	public void setSubareaId(String[] subareaId) {
		this.subareaId = subareaId;
	}
	
	// 业务方法 --- 定区分页查询
	public String pageQuery(){
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
	public String attachRegions(){
		Set<Subarea> subareasSet = decidedZoneService.attachRegions(decidedZone.getId());
		List<Subarea> subareas = new ArrayList<Subarea>(subareasSet);
		ActionContext.getContext().put("subareas", subareas);
		System.out.println("subareas:  " + subareas.toString());
		
		
		return "attachRegionsSUCCESS";
	}
	
	
	
	

}