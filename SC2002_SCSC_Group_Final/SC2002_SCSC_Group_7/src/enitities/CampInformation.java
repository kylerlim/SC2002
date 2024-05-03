package enitities;
import java.time.LocalDate;

import helpers.DateHandler;

public interface CampInformation {

	int IndexNo = 999; //to remove
	// Constants
	String NAME = "";
	LocalDate START_DATE = DateHandler.StringToDate("20231201"); // Assuming the date as String is intended
	LocalDate END_DATE = DateHandler.StringToDate("20231205"); 
	LocalDate CLOSING_DATE = DateHandler.StringToDate("20231130"); 
	String LOCATION = "NTU";
	int TOTAL_SLOTS = 50;
	int COMMITTEE_SLOTS = 10;
	String DESCRIPTION = "This is a camp";
	boolean VISIBILITY = false;
	Faculty OPEN_TO = Faculty.NTU; // Assuming Faculty is a defined enum or class

	// Getters and setters
	String getName();
	void setName(String name);

	LocalDate getStartDate(); // No longer returning string
	void setStartDate(); // No longer have parametere

	LocalDate getEndDate(); // No longer returning string
	void setEndDate(); // No longer have parametere

	LocalDate getClosingDate(); // No longer returning string
	void setClosingDate(); // No longer have parametere

	String getLocation();
	void setLocation();

	int getTotalSlots();
	void setTotalSlots();

	int getCommitteeSlots();
	void setCommitteeSlots();

	String getDescription(); // Corrected capitalization
	void setDescription(); // Corrected capitalization

	boolean getVisibility();
	void setVisibility();

	// Additional methods
	void printAttributes();

	Faculty getOpenTo();
	void setOpenTo(Faculty faculty);

}