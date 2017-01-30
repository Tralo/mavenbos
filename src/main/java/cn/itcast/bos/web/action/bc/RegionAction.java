package cn.itcast.bos.web.action.bc;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.bc.Region;
import cn.itcast.bos.web.action.base.BaseAction;

@SuppressWarnings("serial")
public class RegionAction extends BaseAction implements ModelDriven<Region>{
	
	private Region region = new Region();

	@Override
	public Region getModel() {
		return region;
	}

}
