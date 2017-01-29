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
		// 将分页查询参数，封装到 PageRequestBean 对象中
		PageRequestBean pageRequestBean = new PageRequestBean();
		pageRequestBean.setPage(page);
		pageRequestBean.setRows(rows);
		
		//条件对象
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
		pageRequestBean.setDetachedCriteria(detachedCriteria);
		
		//调用业务层，进行查询结果 PageResponseBean
		
		PageResponseBean pageResponseBean = staffService.pageQuery(pageRequestBean);
		// 将结果转换 json
		ActionContext.getContext().put("pageResponseBean", pageResponseBean);
		return "pageQuerySUCCESS";
	}

	@Override
	public Staff getModel() {
		return staff;
	}
	
	//属性驱动，分装分页参数
	private int page;
	private int rows;
	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
	

}
