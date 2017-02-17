package cn.itcast.bos.web.action.auth;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.auth.Function;
import cn.itcast.bos.domain.user.User;
import cn.itcast.bos.web.action.base.BaseAction;

public class FunctionAction extends BaseAction implements ModelDriven<Function>{
	
	private static final long serialVersionUID = -5149146987594169408L;
	private Function function = new Function();

	@Override
	public Function getModel() {
		return function;
	}
	
	public String ajaxlist(){
		List<Function> functions = functionService.listAll();
		// 将结果数据转换成 json 返回
		ActionContext.getContext().put("functions", functions);
		
		return "ajaxlistSUCCESS";
	}
	
	/**
	 * 保存权限信息
	 */
	public String save(){
		functionService.saveFunction(function);
		return "saveSuccess";
	}
	
	/**
	 * 业务方法 --- 所有权限列表
	 * @return
	 */
	public String list(){
		
		List<Function> functions = functionService.listAll();
		// 放入值栈
		ActionContext.getContext().put("functions", functions);
		
		return "listSuccess";
	}
	/**
	 * 业务方法 --- 查询授权树
	 * @return
	 */
	public String treedata(){
		// 查询树，进行排序
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Function.class);
		// 按照 zinde 升序
		detachedCriteria.addOrder(Order.asc("zindex"));
		// 调用业务层，查询所有 function
		List<Function> functions = functionService.findTreeData(detachedCriteria);
		//放入值栈
		ActionContext.getContext().put("functions", functions);
		return "treedataSUCCESS";
	}
	/**
	 * 业务方法 --- 获取动态菜单
	 * @return
	 */
	public String menu(){
	
		
		
		// 复杂查询使用 QBC
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Function.class);
		// 查询当前用户具有的权限菜单
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if (!user.getUsername().equals("admin")) {
			// 多表关联, 每关联一个表，就创建一个别名
			detachedCriteria.createAlias("roles", "r");
			detachedCriteria.createAlias("r.users", "u");
			detachedCriteria.add(Restrictions.eq("u.id", user.getId()));
		}
		
		// 查询 generateMenu 为1的功能
		detachedCriteria.add(Restrictions.eq("generateMenu", "1"));
		// 按照 zindex 排序
		detachedCriteria.addOrder(Order.asc("zindex"));
		
		// 调用业务层
		List<Function> functions = functionService.findTreeData(detachedCriteria);
		
		// 压入值栈
		ActionContext.getContext().put("functions", functions);
		
		return "menuSUCCESS";
	}

}
