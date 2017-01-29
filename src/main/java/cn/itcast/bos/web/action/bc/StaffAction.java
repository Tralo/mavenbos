package cn.itcast.bos.web.action.bc;

import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.bc.Staff;
import cn.itcast.bos.page.PageRequestBean;
import cn.itcast.bos.page.PageResponseBean;
import cn.itcast.bos.web.action.base.BaseAction;
/**
 * 取派员信息管理
 */
public class StaffAction extends BaseAction implements ModelDriven<Staff>{

	private static final long serialVersionUID = 1L;
	
	private Staff staff = new Staff();
	// 业务方法 --- 保存或修改 取派员
	public String saveOrUpdate(){
		// staff对象中，关联 游离状态 Standard 对象(只有 id)
		// 调用业务层
		staffService.saveOrUpdate(staff);
		
		return "saveOrUpdateSUCCESS";
	}
	
	// 业务方法 --- 分页列表查询
	public String pageQuery(){
		//条件对象
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
		
		//调用业务层，进行查询结果 PageResponseBean
		PageRequestBean pageRequestBean = initPageRequestBean(detachedCriteria);
		
		PageResponseBean pageResponseBean = staffService.pageQuery(pageRequestBean);
		// 将结果转换 json
		ActionContext.getContext().put("pageResponseBean", pageResponseBean);
		return "pageQuerySUCCESS";
	}
	// 业务方法 --- 作废
	public String delBatch(){
		//获取要作废取派员 i
		String[] ids = staff.getId().split(", ");
		// 调用业务层，作废
		staffService.delBatch(ids);
		
		return "delBatchSUCCESS";
	}
	// 业务方法 --- 还原
	public String recover(){
		//获取要作废取派员 i
		String[] ids = staff.getId().split(", ");
		// 调用业务层，作废
		staffService.recover(ids);
		return "recoverSUCCESS";
	}

	@Override
	public Staff getModel() {
		return staff;
	}
	

	

}
