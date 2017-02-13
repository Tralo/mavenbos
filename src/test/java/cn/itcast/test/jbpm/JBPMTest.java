package cn.itcast.test.jbpm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipInputStream;

import org.jbpm.api.ProcessEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JBPMTest {

	// 注入流程引擎
	@Autowired
	private ProcessEngine processEngine;

	@Test
	public void demo1() throws IOException {
		// 发布一个流程定义
		processEngine.getRepositoryService()
				.createDeployment()
				.addResourcesFromZipInputStream(new ZipInputStream(new FileInputStream("holiday.zip")))
				.deploy();
	}

}
