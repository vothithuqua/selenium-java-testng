package testng;

import org.testng.Assert;

public class Topic_01_Assert {

	public static void main(String[] args) {
		String fullName = "Automation Testing";
		
		// kết quả mong đợi 1 điều kiện trả về là True 
		Assert.assertTrue(fullName.contains("Manual"));
		
		// kết quả mong đợi 1 điều kiện trả về là false  
		Assert.assertFalse(fullName.contains("Manual"));
		
		//Mong đợi 2 đkien bằng nhau
		// Expected result
		// Actual Result 
		Assert.assertEquals(fullName, "Automation Testing");
		
		
	}

}
