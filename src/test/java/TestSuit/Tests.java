package TestSuit;

import CodeToTest.TestingFeature;
import org.testng.Assert;
import org.testng.annotations.Test;




public class Tests {
@Test
    public static void Test1(){
    Assert.assertEquals(driver.get(startUrl), TestingFeature.checkUrl);
}
}

