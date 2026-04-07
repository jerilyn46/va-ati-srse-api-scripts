package utilities;

import java.io.File;

import org.junit.Assert;

import platformIndependentCore.datafiles.CsvDataFile;
import platformIndependentCore.reporting.XrayServerReporting;
import platformIndependentCore.scripts.Arguments;
import platformIndependentCore.scripts.ScriptResults;
import platformIndependentCore.scripts.TestScript;
import platformIndependentCore.utilities.ConfigProperties;

/**
 * <b>ARGUMENTS: </b>
 * </p>
 * <ul>
 * <li><b>NONE</b></li>
 * </ul>
 * <p>
 * <b>RETURNS: </b>
 * </p>
 * <ul>
 * <li><b>N/A</b></li>
 * </ul>
 * <p>
 * <b>Script Name: </b> ReTryFailedXrayPosts.java
 * <p>
 * <b>Description: </b> This script can be run when script results failed to
 * post to xray at the end of script execution. This script will loop through
 * any logged failures in the XrayLog (located in
 * testResults\failedXRayPostJsonFiles\XrayLog.csv) and attempt to re-post the
 * results. Information on the status of the re-tries will print to the console
 * as the retries are made.
 * <p>
 * <b>Pre-Condition: </b> Previous script runs have failed to post results to
 * Xray
 * <p>
 * <b>Post-Condition: </b> Console window will show if all posts were
 * successful; An assert will execute to determine if all posts were successful
 *
 * @since Jul 8, 2021
 * @author 281JGIVE
 */
public class RetryFailedXrayPosts extends TestScript {

	/**
	 * the relative path to the JSON files to post without the filenames and
	 * extensions
	 **/
	public static final String XRAY_LOG_RELATIVE_PATH = "testResults/failedXRayPostJsonFiles/XRayLog.csv";

	/**
	 * Method to allow the test script to be run from the the PLAY button
	 *
	 * @param args script arguments
	 */
	public static void main(String[] args) {
		String xrayReportingFromConfig = ConfigProperties.getValue("XRAY_REPORTING");
		File file = new File(XRAY_LOG_RELATIVE_PATH);
		if (file.exists()) {
			ConfigProperties.setValue("XRAY_REPORTING", "FALSE");
			ScriptResults results = runScript(args);
			Assert.assertEquals("PASSED", results.getStatus());
			// Set the XRAY_REPORTING back to original value in the config.properties
			ConfigProperties.setValue("XRAY_REPORTING", xrayReportingFromConfig);
		} else {
			System.out.println("No Xray Post Log was found; no retries attempted.");
		}
	}

	@Override
	public void testScript(Arguments args) {
		CsvDataFile log = new CsvDataFile(XRAY_LOG_RELATIVE_PATH);

		XrayServerReporting xrayReporting = new XrayServerReporting();
		boolean allPostsSuccessful = xrayReporting.retryFailedXrayPosts(log);
		vpEquals("All Posts Successful", true, allPostsSuccessful);
	}
}
