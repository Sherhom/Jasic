package team.interpreter.jasic.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import team.interpreter.jasic.exception.CalculateSyntaxException;
import team.interpreter.jasic.utils.BoolOperationHelper;

public class BoolOperationHelperTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetResult() throws CalculateSyntaxException {
		BoolOperationHelper boolHelper = new BoolOperationHelper();
		boolean result1 = boolHelper.getResult(-3,"<",3);  
		boolean result2 = boolHelper.getResult(2,">",0);  
		boolean result3 = boolHelper.getResult(2,">=",2);  
		boolean result4 = boolHelper.getResult(3,"<=",2); 
		Assert.assertEquals("小于有问题", true , result1);  
		Assert.assertEquals("大于有问题", true , result2);  
		Assert.assertEquals("大于等于有问题", true , result3);  
		Assert.assertEquals("小于等于有问题", false , result4);  
	}

}
