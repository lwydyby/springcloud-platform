package com.forezp;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.gameley.UserServiceApplication;
import com.gameley.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceApplicationTests {

	@Test
	public void testTSQL11() {
		/*
		 * 实体带查询使用方法  输出看结果
		 */
		EntityWrapper<User> ew = new EntityWrapper<User>();
		ew.setEntity(new User());
		/**
		 *
		 AND (user_name=#{ew.paramNameValuePairs.MPGENVAL1} AND id=1)
		 OR (user_status=#{ew.paramNameValuePairs.MPGENVAL2} OR status=1
		 AND user_nickname NOT LIKE #{ew.paramNameValuePairs.MPGENVAL3})
		 AND (new=xx AND hhh LIKE #{ew.paramNameValuePairs.MPGENVAL4})
		 AND (pwd=11 AND n1 IS NOT NULL AND n2 IS NOT NULL AND n3 IS NULL)
		 GROUP BY x1, x2,x3
		 HAVING (x1=11 AND x3=433)
		 ORDER BY dd, d1,d2
		 */
		ew.where("user_name={0}", "'zhangsan'").and("id=1")
				.orNew("user_status={0}", "0").or("status=1")
				.notLike("user_nickname", "notvalue")
				.andNew("new=xx").like("hhh", "ddd")
				.andNew("pwd=11").isNotNull("n1,n2").isNull("n3")
				.groupBy("x1").groupBy("x2,x3")
				.having("x1=11").having("x3=433")
				.orderBy("dd").orderBy("d1,d2");
		System.out.println(ew.getSqlSegment());
	}
}
