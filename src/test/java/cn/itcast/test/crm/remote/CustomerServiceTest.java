package cn.itcast.test.crm.remote;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.crm.domain.Customer;
import cn.itcast.crm.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerServiceTest {
	
	@Autowired
	private CustomerService customerService;

	@Test
	public void test1(){
		// 查询指定定区关联的客户
		List<Customer>  customers = customerService.findNoAssociationCustomers();
		System.out.println("size:  " + customers.size());
		System.out.println(customers.toString());
		
	}
	@Test
	public void test2(){
		// 查询未指定定区关联的客户
		List<Customer>  customers = customerService.findHasAssociationCustomers("fasfasd");
		System.out.println("size:  " + customers.size());
		System.out.println(customers.toString());
	}
	@Test
	public void test3(){
		// 将客户关联到定区上
		String[] ids = {"402881ec5a0849bb015a0849bbf80000"};
		customerService.assignedCustomerToDecidedZone(ids, "ffff");
	}
	@Test
	public void test4(){
		// 将客户关联到定区上
		System.out.println(customerService.findDecidedZoneIdByCustomerAddress("北京朝阳"));
	}
	
}
