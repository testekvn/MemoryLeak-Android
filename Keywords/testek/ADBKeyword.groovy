package testek
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException


class ADBKeyword {
	/**
	 * Check if element present in timeout
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up
	 * @return true if element present, otherwise false
	 */
	@Keyword
	def isElementPresent_Mobile(TestObject to, int timeout){
		try {
			KeywordUtil.logInfo("Finding element with id:" + to.getObjectId())

			WebElement element = MobileElementCommonHelper.findElement(to, timeout)
			if (element != null) {
				KeywordUtil.markPassed("Object " + to.getObjectId() + " is present")
			}
			return true
		} catch (Exception e) {
			KeywordUtil.markFailed("Object " + to.getObjectId() + " is not present")
		}
		return false;
	}

	/**
	 * Get mobile driver for current session
	 * @return mobile driver for current session
	 */
	@Keyword
	def WebDriver getCurrentSessionMobileDriver() {
		return MobileDriverFactory.getDriver();
	}

	@Keyword
	def adbExecution(String cmd) {
		KeywordUtil.logInfo("Execute commands: ${cmd}")

		String command='/Users/vincent/Library/Android/sdk/platform-tools/adb ' + cmd;
		def proc=Runtime.getRuntime().exec(command);
		def outputStream=new StringBuffer();
		def errStream=new StringBuffer();
		proc.waitForProcessOutput(outputStream,errStream);
		println(outputStream.toString());
		println(errStream.toString())
		return outputStream;
	}

	@Keyword
	def getMemoryInfo(String packageName) {
		def res=new StringBuffer();
		def nativeHeapData = adbExecution('shell dumpsys meminfo '+ packageName +" | grep 'Native Heap'");

		String nativeResult  = nativeHeapData.toString().split("\n")[0];
		
		def match, total, allo;
		if (( match = nativeResult =~ /([0-9]+)/)) {
			match.find()
			total = match[4][0].toString();
			allo= match[5][0].toString();
			res.append("${total} ${allo}")
		}

		def dalvikHeapDate = adbExecution('shell dumpsys meminfo '+ packageName +" | grep 'Dalvik Heap'");
		
		String dalvikResult  = dalvikHeapDate.toString().split("\n")[0];
		
		if (( match = dalvikResult =~ /([0-9]+)/)) {
			match.find()
			total = match[4][0].toString();
			allo= match[5][0].toString();
			res.append(" ${total} ${allo}")
		}
		//println ("Result: " +res)
		return res;
	}
}