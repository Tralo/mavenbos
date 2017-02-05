package cn.itcast.bos.web.action.qp;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.qp.WorkOrderManage;
import cn.itcast.bos.page.PageRequestBean;
import cn.itcast.bos.page.PageResponseBean;
import cn.itcast.bos.web.action.base.BaseAction;
/**
 * 工作单管理
 */
public class WorkOrderManageAction extends BaseAction implements ModelDriven<WorkOrderManage>{

	private static final long serialVersionUID = 1L;
	
	private WorkOrderManage workOrderManage = new WorkOrderManage();
	

	@Override
	public WorkOrderManage getModel() {
		return workOrderManage;
	}
	
	// 业务方法 --- 保存工作单
	public String saveOrUpdate(){
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 调用业务层完成保存操作
			workOrderManageService.saveOrUpdate(workOrderManage);
			map.put("result", "success");
			map.put("msg", "保存成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "failure");
			map.put("msg", "保存失败");
			
		}
		ActionContext.getContext().put("map", map);
		
		return "saveOrUpdateSUCCESS";
	}
	
	// 业务方法 --- 工作单分页查询
	public String pageQuery(){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(WorkOrderManage.class);
		PageRequestBean pageRequestBean = initPageRequestBean(detachedCriteria);
		
		
		PageResponseBean pageResponseBean = workOrderManageService.pageQuery(pageRequestBean);
		ActionContext.getContext().put("pageResponseBean", pageResponseBean);
		return "pageQuerySUCCESS";
	}
	

}
