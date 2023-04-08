package com.googlesheetgroup.googlesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

@SpringBootApplication
public class GooglesheetApplication {
	
	String key = "";
	static String uri = "";

	public static void main(String[] args) throws AuthenticationException,
			MalformedURLException, IOException, ServiceException {

		SpringApplication.run(GooglesheetApplication.class, args);
		SpreadsheetService service = new SpreadsheetService(
				"MySpreadsheetIntegration-v1");

		URL SPREADSHEET_FEED_URL = new URL(uri);

		SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL,
				SpreadsheetFeed.class);
		List<SpreadsheetEntry> spreadsheets = feed.getEntries();

		for (SpreadsheetEntry spreadsheet : spreadsheets) {
			// Print the title of this spreadsheet to the screen
			System.out.println(spreadsheet.getTitle().getPlainText());

		    // Make a request to the API to fetch information about all
		    // worksheets in the spreadsheet.
		    List<WorksheetEntry> worksheets = spreadsheet.getWorksheets();

		    // Iterate through each worksheet in the spreadsheet.
		    for (WorksheetEntry entry : worksheets) {
		      // Get the worksheet's title, row count, and column count.
		      String title = entry.getTitle().getPlainText();



		      System.out.println(entry.getPlainTextContent());
		    }
		}
}

}
