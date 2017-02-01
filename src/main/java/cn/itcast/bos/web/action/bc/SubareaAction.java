package cn.itcast.bos.web.action.bc;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.bc.Subarea;
import cn.itcast.bos.web.action.base.BaseAction;

public class SubareaAction extends BaseAction implements ModelDriven<Subarea>{
	
	private static final long serialVersionUID = -4445628862338996125L;
	
	
	private Subarea subarea = new Subarea();

	@Override
	public Subarea getModel() {
		return subarea;
	}
	
	// 业务方法 --- 添加或修改子分区
	public String saveOrUpdate(){
		subareaService.saveOrUpdate(subarea);
		
		return "saveOrUpdateSUCCESS";
	}
	

}
