package enitities;
import java.util.List;
import java.util.ArrayList;

public interface CampRoles {

	// Constants are public, static, and final by default in an interface
	String CREATOR = "";
	ArrayList<String> COMMITTEE_LIST = new ArrayList<>(); // Initialized with an empty list
	ArrayList<String> ATTENDEE_LIST = new ArrayList<>(); // Initialized with an empty list
	ArrayList<String> BLACK_LIST = new ArrayList<>(); // Initialized with an empty list

	String getCreator();

	void setCreator(String Creator);

	ArrayList<String> getCommitteeList();

	void setCommitteeList(ArrayList<String> committeeList);

	ArrayList<String> getAttendeeList();

	void setAttendeeList(ArrayList<String> attendeeList);

	ArrayList<String> getBlackList();

	void setBlackList(ArrayList<String> blackList);

	void addToCommitteeList(String studentName);

	void addToAttendee(String studentName);
	void addToBlackList(String studentName);
	void removeFromAttendeeList(String studentName);


}
