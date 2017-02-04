package cn.itcast.bos.web.action.bc;

import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.bc.DecidedZone;
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
	
	
	
	

}
