package cn.itcast.bos.web.action.auth;

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

}
