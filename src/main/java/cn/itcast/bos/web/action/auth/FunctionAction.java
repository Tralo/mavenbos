package cn.itcast.bos.web.action.auth;

import java.util.List;

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
	
	
	public String list(){
		
		List<Function> functions = functionService.listAll();
		// 放入值栈
		ActionContext.getContext().put("functions", functions);
		
		return "listSuccess";
	}

}
