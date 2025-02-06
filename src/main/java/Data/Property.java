package Data;

import java.io.*;
import java.util.Properties;

public class Property {
	 public static Properties prop = new Properties();


	    public static void readSessionManagement() throws Exception {

	        String path = new File("src/main/java/Data/SessionManagement.properties")
	                .getAbsolutePath();
	        prop.load(new InputStreamReader(new FileInputStream(path), "UTF8"));


	    }

	    public static void readRBCProperties() throws Exception {

	        String path = new File("src/main/java/Data/Rbc.properties")
	                .getAbsolutePath();
	        prop.load(new InputStreamReader(new FileInputStream(path), "UTF8"));


	    }

	public static void readCommonMethodProperties() throws Exception {

		String path = new File("src/main/java/Data/CommonMethod.properties")
				.getAbsolutePath();
		prop.load(new InputStreamReader(new FileInputStream(path), "UTF8"));


	}


	public static void readWBCProperties() throws Exception {

		String path = new File("src/main/java/Data/Wbc.properties")
				.getAbsolutePath();
		prop.load(new InputStreamReader(new FileInputStream(path), "UTF8"));


	}
	public static void readSummaryProperties() throws Exception {

		String path = new File("src/main/java/Data/Summary.properties")
				.getAbsolutePath();
		prop.load(new InputStreamReader(new FileInputStream(path), "UTF8"));


	}

	public static void readReportViewerProperties() throws Exception {

		String path = new File("src/main/java/Data/ReportViewer.properties")
				.getAbsolutePath();
		prop.load(new InputStreamReader(new FileInputStream(path), "UTF8"));


	}


    public static void readReportListingProperties() throws Exception  {

		String path = new File("src/main/java/Data/ReportListing.properties")
				.getAbsolutePath();
		prop.load(new InputStreamReader(new FileInputStream(path), "UTF8"));


	}
	public static void readPlateletProperties() throws Exception  {

		String path = new File("src/main/java/Data/Platelet.properties")
				.getAbsolutePath();
		prop.load(new InputStreamReader(new FileInputStream(path), "UTF8"));


	}


    public static void readReportSignOffProperties() throws IOException {
		String path = new File("src/main/java/Data/Platelet.properties")
				.getAbsolutePath();
		prop.load(new InputStreamReader(new FileInputStream(path), "UTF8"));


	}
	public static void readCommonToolsProperties() throws Exception{

		String path=new File("src/main/java/Data/CommonTools.properties").getAbsolutePath();
		prop.load(new InputStreamReader(new FileInputStream(path),"UTF8"));
	}
}
