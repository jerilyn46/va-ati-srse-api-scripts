package utilities;

import java.io.FileNotFoundException;
import java.io.IOException;

import platformIndependentCore.reporting.XrayServerReporting;

/**
 * Creates files with encrypted information to use for authenticate into
 * Jira/XRay. More information can be found in the ATI Guide: Creating Xray User
 * Auth Files document.<br>
 * <br>
 * <b> Instructions for Individual User Authentication</b>
 * <ul>
 * <li>Replace the String contents for JIRA_USER_NAME and CLEAR_USER_TOKEN
 * variables in the USER AUTH section below with the user name for Jira and the
 * API Authentication Token for the account</li>
 * <li>Uncomment the createUserAuthFiles() method call below in the main
 * method.</li>
 * </ul>
 * <br>
 * <br>
 * <b> Instructions for Service Account Authentication</b>
 * <ul>
 * <li>Replace the String contents for CLEAR_SERVICE_ACCT_PWD in the SERVICE
 * ACCOUNT AUTH section with the password for the service account .</li>
 * <li>Uncomment out the createServiceAccountAuthFile() method in the main
 * method below.</li>
 * </ul>
 * <br>
 * <br>
 * <b><i>NOTE: Please make sure that this file is NOT checked into source
 * control with any modifications to prevent credentials from getting into
 * source control. </b></i>
 *
 * @author VBAAUSRUDISC
 *
 */
public class EncryptAuthenticationFile {
	/******************************************************
	 * USER AUTH
	 *****************************************************/
	/** Jira User Name **/
	private static final String JIRA_USER_NAME = "";
	/** User API Auth Token **/
	private final static String CLEAR_USER_TOKEN = "";
	/** User Auth file which contains credentials **/
	private final static String USER_PWD_FILE = "c:/Automation/Tools/xray_server_auth.properties";

	/******************************************************
	 * SERVICE ACCOUNT AUTH
	 *****************************************************/
	/** Service Account file with encrypted password **/
	final static String SERVICE_ACCT_PWD_FILE = "c:/Automation/Tools/service_account.properties";
	/** Service Account password **/
	final static String CLEAR_SERVICE_ACCT_PWD = "";

	/**
	 * Main method for executing the methods to generate encrypted text in files
	 * used for authentication
	 *
	 * @param args none used
	 * @throws IOException           if error writing file
	 * @throws FileNotFoundException if file is not found
	 */
	public static void main(String[] args) throws IOException {
		XrayServerReporting xr = new XrayServerReporting();
		// Setup folder structure for files. Will create the folders required if not
		// there.
		xr.createAutomationToolsPropertiesFolder();
		/**
		 * Instructions for Service Account Authentication
		 *
		 * Replace the String contents for CLEAR_SERVICE_ACCT_PWD in the SERVICE ACCOUNT
		 * AUTH section with the password for the service account. Uncomment out the
		 * createServiceAccountAuthFile() method in the main method below.
		 *
		 */
//		xr.createUserAuthFiles(JIRA_USER_NAME, CLEAR_USER_TOKEN, USER_PWD_FILE);

		/**
		 * Instructions for Service Account Authentication
		 *
		 * Replace the String contents for CLEAR_SERVICE_ACCT_PWD in the SERVICE ACCOUNT
		 * AUTH section with the password for the service account . Uncomment out the
		 * createServiceAccountAuthFile() method in the main method below.
		 *
		 */
//		xr.createServiceAccountAuthFile(CLEAR_SERVICE_ACCT_PWD, SERVICE_ACCT_PWD_FILE);

	}

}
