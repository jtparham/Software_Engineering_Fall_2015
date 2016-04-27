package pictionary.test;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;




import pictionary.LoginPanel;

public class testLoginPanel {
	Robot tester;
	LoginPanel l;
	
	@Before
	public void runBeforeEveryTest () throws AWTException{
		tester = new Robot();
		l = new LoginPanel();
	}
	
	@After
	public void runAfterTest(){
		tester = null;
		l = null;
	}
	
	@Test
	public void testValidLogin()
	{
		//set the text in user name to a valid user name
		
		//set the text to password to a valid password
		
		//use robot to click login
		
		//if login is successful, return true
	}
	
	@Test
	public void testInvalidLogin()
	{
		//test invalid username
		
		//test invalid password
		
		//test invalid username and password
		
	}

}
