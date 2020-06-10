package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes=CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {
    private ApplicationContext applicationContext;


	@Test
	void contextLoads() {
		System.out.println(applicationContext);
	}

	@Test
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
		AlphaDao alphaDao=applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao.seletAll());
		 alphaDao=applicationContext.getBean("alphaHibermate",AlphaDao.class);
		System.out.println(alphaDao.seletAll());
	}
	@Test
	public void testBean()  {

		AlphaService alphaService=applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
	}
//	@Test
//	public void testBeanConfig()  {
//		SimpleDateFormat simpleDateFormat=applicationContext.getBean(SimpleDateFormat.class);
//		System.out.println(simpleDateFormat);
//
//
//	}
	@Autowired
	@Qualifier("alphaHibermate")
	private AlphaDao alphaDao;

	@Test
	public void DI(){
		System.out.println(alphaDao.seletAll());
	}
}
