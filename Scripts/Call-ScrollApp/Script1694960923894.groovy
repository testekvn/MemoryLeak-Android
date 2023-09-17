import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

//KeywordLogger log = new KeywordLogger()
String appPackage = 'com.vinsmart.android.dialer'

StringBuilder result = new StringBuilder()
//String command = '/Users/vincent/Library/Android/sdk/platform-tools/adb shell dumpsys meminfo ' + appPackage
//Runtime.getRuntime().exec(command)
println("1st: ${CustomKeywords.'testek.ADBKeyword.getMemoryInfo'( appPackage)}")
	
Mobile.startExistingApplication(appPackage)
for (def k : (0..10)) {
	device_Height = Mobile.getDeviceHeight()
	device_Width = Mobile.getDeviceWidth()
	int startX = device_Width / 2
	int startY = device_Height * 0.30
	int endY = device_Height * 0.70
	for (def index : (0..1)) {
	    Mobile.swipe(startX, endY, startX, startY)
	    Mobile.tap(findTestObject('Object Repository/android.widget.FrameLayout'), 0)
	    Mobile.tap(findTestObject('Object Repository/android.widget.ImageButton'), 0)
	}
	println("VDebug${k+1}: ${CustomKeywords.'testek.ADBKeyword.getMemoryInfo'( appPackage)}")
}
Mobile.closeApplication()



