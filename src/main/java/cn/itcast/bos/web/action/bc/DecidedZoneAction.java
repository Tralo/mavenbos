package cn.itcast.bos.web.action.bc;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.bc.DecidedZone;
import cn.itcast.bos.web.action.base.BaseAction;

public class DecidedZoneAction extends BaseAction implements ModelDriven<DecidedZone>{

	private static final long serialVersionUID = 1L;
	
	private DecidedZone decidedZone = new DecidedZone();

	@Override
	public DecidedZone getModel() {
		return decidedZone;
	}

}
