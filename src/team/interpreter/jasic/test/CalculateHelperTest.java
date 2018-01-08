package team.interpreter.jasic.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import team.interpreter.jasic.exception.CalculateSyntaxException;
import team.interpreter.jasic.utils.CalculateHelper;

public class CalculateHelperTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInToPos() {
		CalculateHelper calHelper = new CalculateHelper();
		String s1 = calHelper.inToPos("(3,+4,)*5,-6,");
		String s2 = calHelper.inToPos("6,-8,/4,+3,*5,");
		String s3 = calHelper.inToPos("(7,-3,)*8,/(5,-2,)");
		Assert.assertEquals("计算后缀有问题", "3,4,+5,*6,-" , s1); 
		Assert.assertEquals("计算后缀有问题", "6,8,4,/-3,5,*+" , s2); 
		Assert.assertEquals("计算后缀有问题", "7,3,-8,*5,2,-/" , s3); 
		
		
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testOp_cal() {
		CalculateHelper calHelper = new CalculateHelper();
		double result1 = calHelper.op_cal(3, 2, '+');
		double result2 = calHelper.op_cal(2, 3, '-');
		double result3 = calHelper.op_cal(3, 2, '*');
		double result4 = calHelper.op_cal(-2, -1, '/');
		double result5 = calHelper.op_cal(6, 3, '%');
		double result6 = calHelper.op_cal(3, 4, '^');
		/*junit中没有assertEquals(double,double)的方法。
		因为double值是允许误差的。
		所以要实现double的断言要用assertEquals(double,double,double)这个方法。
		第三个参数是允许误差 。*/
		Assert.assertEquals("计算有问题", 5 , result1 , 0.0001); 
		Assert.assertEquals("计算有问题", -1 , result2, 0.0001); 
		Assert.assertEquals("计算有问题", 6 , result3, 0.0001); 
		Assert.assertEquals("计算有问题", 2 , result4, 0.0001); 
		Assert.assertEquals("计算有问题", 0 , result5, 0.0001); 
		Assert.assertEquals("计算有问题", 81 , result6, 0.0001); 
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testExcuteString() throws CalculateSyntaxException {
		CalculateHelper calHelper = new CalculateHelper();
		double d1 = calHelper.excuteString("(3,+4,)*5,-6,");
		double d2 = calHelper.excuteString("6,-8,/4,+3,*5,");
		double d3 = calHelper.excuteString("(7,-3,)*8,/(5,-2,)");
		Assert.assertEquals("计算有问题", 29 , d1 , 0.0001); 
		Assert.assertEquals("计算有问题", 19 , d2 , 0.0001); 
		Assert.assertEquals("计算有问题", 10.666666666666666 , d3 , 0.0001); 
		
	}
	
	

}
