package cn.itcast.bos.web.action.bc;

import java.sql.Timestamp;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.bc.Standard;
import cn.itcast.bos.domain.user.User;
import cn.itcast.bos.page.PageRequestBean;
import cn.itcast.bos.page.PageResponseBean;
import cn.itcast.bos.web.action.base.BaseAction;

/**
 * 收派标准管理
 */
public class StandardAction extends BaseAction implements ModelDriven<Standard>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 模型驱动
	 */
	private Standard standard = new Standard();
	
	
	@Override
	public Standard getModel() {
		return standard;
	}
	
	// 业务方法 --- 保存标准
	public String save(){
		// 完善 model 数据
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		standard.setUser(user);
		standard.setUpdatetime(new Timestamp(System.currentTimeMillis()));
		standardService.saveStandard(standard);
		return "saveSUCCESS";
	}
	//业务方法 ---- 分页查询收派标准数据
	public String pageQuery(){
		//封装分页查询条件对象
		PageRequestBean pageRequestBean = new PageRequestBean();
		pageRequestBean.setPage(page);
		pageRequestBean.setRows(rows);
		
		//隐含条件，查询未删除标准
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Standard.class);// 查询标准表所有数据 from Standard
		detachedCriteria.add(Restrictions.eq("deltag", "0"));
		pageRequestBean.setDetachedCriteria(detachedCriteria);
		
		
		//调用业务层
		PageResponseBean pageResponseBean = standardService.pageQuery(pageRequestBean);
		// 将分页查询结果数据，转换 json 格式
		ActionContext.getContext().put("pageResponseBean", pageResponseBean);
		System.out.println("pageResponseBean:  " + pageRequestBean);
		
		return "pageQuerySUCCESS";
	}
	//业务方法 --- 批量删除
	public String delBatch(){
		//转换成多个id
		String[] ids = standard.getId().split(", ");
		standardService.delBatch(ids);
		return "delBatchSUCCESS";
	}
	
	public String ajaxlist(){
		//调用业务层将列表查出
		List<Standard> standards = standardService.ajaxlist();
		
		//将结果转换成json返回
		ActionContext.getContext().put("standards", standards);
		
		return "ajaxlistSUCCESS";
	}
	
	// 属性驱动
	private int page;
	private int rows;
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
}
