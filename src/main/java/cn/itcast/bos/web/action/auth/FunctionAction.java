package cn.itcast.bos.web.action.auth;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.auth.Function;
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

}