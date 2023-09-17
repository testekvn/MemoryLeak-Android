
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import com.kms.katalon.core.testobject.TestObject

import java.lang.String


 /**
	 * Check if element present in timeout
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up
	 * @return true if element present, otherwise false
	 */ 
def static "testek.ADBKeyword.isElementPresent_Mobile"(
    	TestObject to	
     , 	int timeout	) {
    (new testek.ADBKeyword()).isElementPresent_Mobile(
        	to
         , 	timeout)
}

 /**
	 * Get mobile driver for current session
	 * @return mobile driver for current session
	 */ 
def static "testek.ADBKeyword.getCurrentSessionMobileDriver"() {
    (new testek.ADBKeyword()).getCurrentSessionMobileDriver()
}


def static "testek.ADBKeyword.adbExecution"(
    	String cmd	) {
    (new testek.ADBKeyword()).adbExecution(
        	cmd)
}


def static "testek.ADBKeyword.getMemoryInfo"(
    	String packageName	) {
    (new testek.ADBKeyword()).getMemoryInfo(
        	packageName)
}
