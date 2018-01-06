package com.nowcoder.wenda;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.nowcoder.wenda.dao.UserDAO;
import com.nowcoder.wenda.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WendaApplication.class)
@Sql("/init-schema.sql")
public class InitDatabasesTests {

	@Autowired
	UserDAO userDAO;
	@Test
	public void initDatabases() {
		Random random = new Random();

		for(int i=0; i<11; ++i){
			User user = new User();
			user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png",
																random.nextInt(1000)));
			user.setName(String.format("USER%d", i));
			user.setPassword("");
			user.setSalt("");
			userDAO.addUser(user);
			user.setPassword("xx");
			userDAO.updatePassword(user);
		}

		User user = userDAO.selectById(1);
		System.out.println(user.getName());
		userDAO.deleteById(1);
	}

}
