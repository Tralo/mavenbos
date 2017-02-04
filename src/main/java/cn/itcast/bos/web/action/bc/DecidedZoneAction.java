package cn.itcast.bos.web.action.bc;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.bc.DecidedZone;
import cn.itcast.bos.web.action.base.BaseAction;
/**
 * 定区管理
 */
public class DecidedZoneAction extends BaseAction implements ModelDriven<DecidedZone>{

	private static final long serialVersionUID = 1L;
	
	private DecidedZone decidedZone = new DecidedZone();

	@Override
	public DecidedZone getModel() {
		return decidedZone;
	}
	// 业务方法 --- 添加，修改定区
	public String saveOrUpdate(){
		// 调用业务层，添加定区
		decidedZoneService.saveOrUpdate(decidedZone, subareaId);
		return "saveOrUpdateSUCCESS";
	}
	
	private String[] subareaId;

	public void setSubareaId(String[] subareaId) {
		this.subareaId = subareaId;
	}
	
	
	
	

}
