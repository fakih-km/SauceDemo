package utilities;

import java.util.*;

import org.testng.Assert;
import org.testng.Reporter;

public class Helper {
	
	public static void logEvent(String message) {
		Reporter.log(message);
		System.out.println(message);
	}
	
	public static void stringAssertWithLog(String variableName, String actual, String expected) {
		Reporter.log("Asserting value of :" + variableName + "expected : " + expected + "actual :" +actual);
		System.out.println("Asserting value of :" + variableName + "expected : " + expected + "actual :" +actual);
		Assert.assertEquals(actual, expected);
	}
	
	public static void lstStringAssertWithLog(List<String> variableName, List<String> actual, List<String> expected) {
		Reporter.log("Asserting value of :" + variableName + "expected : " + expected + "actual :" +actual);
		System.out.println("Asserting value of : " + variableName + "\nexpected : " + expected + "\nactual :" +actual);
		Assert.assertEquals(actual, expected);
	}
}
