package pictionary.test;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pictionary.CreateAcntPanel;

public class testCreateAcntPanel {
Robot tester;
	
	@Before
	public void runBeforeEveryTest () throws AWTException{
		tester = new Robot();
	}
	
	@After
	public void runAfterTest(){
		tester = null;
	}
	
	@Test
	public void testCreateAccount()
	{
		
	}


}
