package cn.itcast.bos.web.action.bc;

import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.bc.Subarea;
import cn.itcast.bos.page.PageRequestBean;
import cn.itcast.bos.page.PageResponseBean;
import cn.itcast.bos.web.action.base.BaseAction;

public class SubareaAction extends BaseAction implements ModelDriven<Subarea>{
	
	private static final long serialVersionUID = -4445628862338996125L;
	
	
	private Subarea subarea = new Subarea();

	@Override
	public Subarea getModel() {
		return subarea;
	}
	
	// 业务方法 --- 添加或修改子分区
	public String saveOrUpdate(){
		subareaService.saveOrUpdate(subarea);
		
		return "saveOrUpdateSUCCESS";
	}
	
	
	// 业务方法 --- 
	public String pageQuery(){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
		PageRequestBean pageRequestBean = initPageRequestBean(detachedCriteria);
		
		// 调用业务层
		PageResponseBean pageResponseBean = subareaService.pageQuery(pageRequestBean);
		ActionContext.getContext().put("pageResponseBean", pageResponseBean);
		
		return "pageQuerySUCCESS";
	}
	

}
