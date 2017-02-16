package cn.itcast.bos.web.action.auth;

import java.util.List;

import org.eclipse.jdt.internal.compiler.lookup.ReductionResult;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.config.entities.ActionConfig;

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

}
