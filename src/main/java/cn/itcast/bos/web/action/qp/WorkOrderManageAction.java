package cn.itcast.bos.web.action.qp;

import java.util.HashMap;
import java.util.List;
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
		if(conditionName != null && conditionName.trim().length() > 0 && conditionValue != null && conditionValue.trim().length() > 0){
			// 有条件所有，结合 lucence
			PageResponseBean pageResponseBean = workOrderManageService.queryByLuence(conditionName,conditionValue,page,rows);
			ActionContext.getContext().put("pageResponseBean", pageResponseBean);
		} else {
			// 无查询条件
			// 无条件查询所有
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(WorkOrderManage.class);
			PageRequestBean pageRequestBean = initPageRequestBean(detachedCriteria);
			
			//调用所有
			PageResponseBean pageResponseBean = workOrderManageService.pageQuery(pageRequestBean);
			ActionContext.getContext().put("pageResponseBean", pageResponseBean);
		}
		
		return "pageQuerySUCCESS";
	}
	
	private String conditionName;
	private String conditionValue;

	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}

	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}
	// 业务方法 --- 查询所有未审核的工作单
	public String list(){
		// 查询未审核的工作单
		List<WorkOrderManage> workOrderManages = workOrderManageService.listUnCheckWorkOrderManages();
		// 压入值栈
		ActionContext.getContext().put("workOrderManages", workOrderManages);
		return "listSUCCESS";
	}
	// 业务方法 --- 对工作单进行审核，启动中转流程
	public String check(){
		
		workOrderManageService.check(workOrderManage);
		
		return "checkSUCCESS";
	}
	
}
