package helpers;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import enitities.*;
import enitities.Camp;
import enitities.Enquiry;
import enitities.Password;
import enitities.Staff;
import enitities.Student;
import enitities.Suggestion;

/**
 * The `OffOnline` class contains static methods for retrieving and storing
 * various entities such as camps, enquiries, suggestions, staff, students,
 * and passwords. It serves as a helper class for data retrieval and storage
 * to and from the csv databases in the CAMs (Camp Administration Management System).
 */
public class OffOnline{


    /**
     * Retrieves a list of camps based on user camp records and camp information records.
     * @param usercampRecords A list of user camp records.
     * @param campInfoRecords A list of camp information records.
     * @return An ArrayList of Camp objects representing the retrieved camps.
     */
    public static ArrayList<Camp> campsRetriever(ArrayList<Map<String, String>> usercampRecords, ArrayList<Map<String, String>> campInfoRecords) {
        ArrayList<Camp> allCamps = new ArrayList<>();
    
        for (Map<String, String> record : campInfoRecords) {
            String campName = record.get("name");
            String startDate = record.get("startDate");
            String endDate = record.get("endDate");
            String closingDate = record.get("closingDate");
            String location = record.get("location");
            String description = record.get("description");
            String staffIC = record.get("staffIC");
            int totalSlots = 0;
            int committeeSlots = 0;
            Faculty openTo = null;
            boolean visibility = "TRUE".equals(record.get("visibility"));
    
            try {
                totalSlots = Integer.parseInt(record.get("totalSlots"));
                committeeSlots = Integer.parseInt(record.get("committeeSlots"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
    
            try {
                openTo = Faculty.valueOf(record.get("openTo"));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
    
            ArrayList<String> attendeeList = new ArrayList<>();
            ArrayList<String> committeeList = new ArrayList<>();
            ArrayList<String> blackList = new ArrayList<>();
    
            for (Map<String, String> userRecord : usercampRecords) {
                if (campName.equals(userRecord.get("CampName"))) {
                    for (Map.Entry<String, String> entry : userRecord.entrySet()) {
                        String user = entry.getKey();
                        String value = entry.getValue();
    
                        if (!"CampName".equals(user)) {
                            switch (value) {
                                case "ATTENDEE":
                                    attendeeList.add(user);
                                    break;
                                case "COMMITTEE":
                                    committeeList.add(user);
                                    break;
                                case "BLACKLISTED":
                                    blackList.add(user);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
            }
    
            allCamps.add(new Camp(campName, startDate, endDate, closingDate, location, totalSlots, committeeSlots, description, staffIC, attendeeList, committeeList, openTo, visibility, blackList));
        }
    
        return allCamps;
    }

    /**
     * Retrieves a list of enquiries based on enquiry records.
     * @param enquiryRecords A list of enquiry records.
     * @return An ArrayList of Enquiry objects representing the retrieved enquiries.
     * @throws NoSuchAlgorithmException If a required cryptographic algorithm is not available.
     */
    public static ArrayList <Enquiry> enquiriesRetriever(ArrayList<Map<String, String>> enquiryRecords) throws NoSuchAlgorithmException {

        ArrayList <Enquiry> EnquiryList = new ArrayList<Enquiry>();

       for (Map<String, String> record : enquiryRecords) {
           Enquiry eq = new Enquiry(record.get("Sender"), record.get("CampName"), record.get("Message"), record.get("status"), record.get("reply"), record.get("enquiryResponder"));
           EnquiryList.add(eq);
           }
           return EnquiryList;
       }


    /**
     * Retrieves a list of suggestions based on suggestion records.
     * @param suggestionRecords Retrieves a list of suggestions based on suggestion records.
     * @return An ArrayList of Suggestion objects representing the retrieved suggestions.
     * @throws NoSuchAlgorithmException If a required cryptographic algorithm is not available.
     */
    public static ArrayList <Suggestion> suggestionsRetriever(ArrayList<Map<String, String>> suggestionRecords) throws NoSuchAlgorithmException {
        // Implementation details for retrieving student data
         String sender;
         String campName;
         String message;
         String status;
         ArrayList <Suggestion> SuggestionList = new ArrayList<Suggestion>();

        for (Map<String, String> sugRecord : suggestionRecords) {
            sender = sugRecord.get("Sender");
            campName = sugRecord.get("CampName");
            message = sugRecord.get("Message");
            status  =  sugRecord.get("status");


            Suggestion sg = new Suggestion( sender, campName, message, status);

            SuggestionList.add(sg);
            
            }
            return SuggestionList;
        }


    /**
     * Retrieves a list of staff members based on staff list records, user camp records, and password records.
     *
     * @param staffListRecords A list of staff list records.
     * @param usercampRecords  A list of user camp records.
     * @param passwordRecords  A list of password records.
     * @return An ArrayList of Staff objects representing the retrieved staff members.
     * @throws NoSuchAlgorithmException If a required cryptographic algorithm is not available.
     */
    public static ArrayList<Staff> staffsRetriever(ArrayList<Map<String, String>> staffListRecords, ArrayList<Map<String, String>> usercampRecords, ArrayList<Map<String, String>> passwordRecords) throws NoSuchAlgorithmException {
            ArrayList<Staff> staffList = new ArrayList<>();
        
            for (Map<String, String> staffRecord : staffListRecords) {
                String networkID = staffRecord.get("NetworkID");
                String name = staffRecord.get("Name");
                String email = staffRecord.get("Email");
                Faculty faculty = Faculty.valueOf(staffRecord.get("Faculty"));
                ArrayList<String> campIC = new ArrayList<>();
                String hashedPassword = "";
        
                for (Map<String, String> usercampRecord : usercampRecords) {
                    String campInfo = usercampRecord.get(networkID);
                    if (campInfo != null && campInfo.equals("CREATOR")) {
                        campIC.add(usercampRecord.get("CampName"));
                    }
                }
                for (Map<String, String> passwordRecord : passwordRecords){
                    if(passwordRecord.get("NetworkID").equals(networkID)){
                        hashedPassword = passwordRecord.get("HashedPassword");
                    }
                }
        
                staffList.add(new Staff(name, email, faculty, networkID, new Password(hashedPassword), campIC));
            }
            return staffList;
        }

    /**
     * Retrieves a list of students based on student list records, user camp records, and password records.
     *
     * @param studentListRecords A list of student list records.
     * @param usercampRecords    A list of user camp records.
     * @param passwordRecords    A list of password records.
     * @return An ArrayList of Student objects representing the retrieved students.
     * @throws NoSuchAlgorithmException If a required cryptographic algorithm is not available.
     */
    public static ArrayList<Student> studentsRetriever(ArrayList<Map<String, String>> studentListRecords, ArrayList<Map<String, String>> usercampRecords, ArrayList<Map<String, String>> passwordRecords) throws NoSuchAlgorithmException {
            ArrayList<Student> studentList = new ArrayList<>();
            for (Map<String, String> studentRecord : studentListRecords) {
                String networkID = studentRecord.get("NetworkID");
                String name = studentRecord.get("Name");
                String email = studentRecord.get("Email");
                Faculty faculty = Faculty.valueOf(studentRecord.get("Faculty"));
                int points = Integer.parseInt(studentRecord.getOrDefault("Points", "0"));
                ArrayList<String> campAttendee = new ArrayList<>();
                ArrayList<String> campCommittee = new ArrayList<>();
                ArrayList<String> campBlackList = new ArrayList<>();
                String hashedPassword = "";
                for (Map<String, String> usercampRecord : usercampRecords) {
                    String campInfo = usercampRecord.get(networkID);
                    if (campInfo != null) {
                        switch (campInfo) {
                            case "ATTENDEE":
                                campAttendee.add(usercampRecord.get("CampName"));
                                break;
                            case "COMMITTEE":
                                campCommittee.add(usercampRecord.get("CampName"));
                                break;
                            case "BLACKLISTED":
                                campBlackList.add(usercampRecord.get("CampName"));
                                break;
                            default:
                                break;
                        }
                    }
                }
                for (Map<String, String> passwordRecord : passwordRecords){
                    if(passwordRecord.get("NetworkID").equals(networkID)){
                        hashedPassword = passwordRecord.get("HashedPassword");
                    }
                }
                studentList.add(new Student(name, email, faculty, networkID, new Password(hashedPassword), campCommittee, campAttendee, points, campBlackList));
            }
            return studentList;
        }

    /**
     * Stores the attributes of camps in a list of maps.
     *
     * @param camps An ArrayList of Camp objects to store.
     * @return An ArrayList of maps which contained camp attributes.
     */
    public static ArrayList<Map<String, String>> campAttributesStorer(ArrayList<Camp> camps) {
        // Initialize the list that will store maps of camp records
        ArrayList<Map<String, String>> campRecords = new ArrayList<>();
        for (Camp camp : camps) {
            String visibility = "False";
            if (camp.getVisibility()) {
                visibility = "TRUE";
            }
            Map<String, String> campAttributes = new HashMap<>();
            campAttributes.put("name", camp.getName());
            campAttributes.put("location", camp.getLocation());
            campAttributes.put("startDate", DateHandler.DateToString(camp.getStartDate()));
            campAttributes.put("closingDate", DateHandler.DateToString(camp.getClosingDate()));
            campAttributes.put("endDate", DateHandler.DateToString(camp.getEndDate()));
            campAttributes.put("totalSlots", Integer.toString(camp.getTotalSlots()));
            campAttributes.put("committeeSlots", Integer.toString(camp.getCommitteeSlots()));
            campAttributes.put("description", camp.getDescription());
            campAttributes.put("staffIC", camp.getCreator());
            campAttributes.put("openTo", camp.getOpenTo().name());
            campAttributes.put("visibility", visibility);
            // Add the map of this camp's attributes to the list of camp records
            campRecords.add(campAttributes);
        }
        return campRecords;
    }

    /**
     * Stores the user camp relationships in a list of maps.
     *
     * @param camps   An ArrayList of Camp objects.
     * @param students An ArrayList of Student objects.
     * @param staffs   An ArrayList of Staff objects.
     * @return An ArrayList of maps containing user camp relationships.
     */
    public static ArrayList<Map<String, String>> userCampStorer(ArrayList<Camp> camps, ArrayList<Student> students, ArrayList<Staff> staffs) {
        ArrayList<Map<String, String>> userCampRecords = new ArrayList<>();
        ArrayList<String> networkIds = new ArrayList<>();

        // Looping through students and adding their network IDs
        for (Student student : students) {
            networkIds.add(student.getNetworkID());
        }

        // Looping through staff and adding their network IDs
        for (Staff staff : staffs) {
            networkIds.add(staff.getNetworkID());
        }

        for (Camp camp : camps) {
            Map<String, String> campAttributes = new HashMap<>();
            campAttributes.put("CampName", camp.getName());

            for (String networkId : networkIds) {
                if (camp.getAttendeeList().contains(networkId)) {
                    campAttributes.put(networkId, "ATTENDEE");
                } else if (camp.getBlackList().contains(networkId)) {
                    campAttributes.put(networkId, "BLACKLISTED");
                } else if (camp.getCommitteeList().contains(networkId)) {
                    campAttributes.put(networkId, "COMMITTEE");
                } else if (camp.getCreator().equals(networkId)) {
                    campAttributes.put(networkId, "CREATOR");
                } else {
                    campAttributes.put(networkId, "UNINVOLVED");
                }
            }
            userCampRecords.add(campAttributes);
        }
        return userCampRecords;
    }

    /**
     * Stores the attributes of students in a list of maps.
     *
     * @param students An ArrayList of Student objects to store.
     * @return An ArrayList of maps containing student attributes.
     */
    public static ArrayList<Map<String, String>> studentStorer(ArrayList<Student> students) {
        ArrayList<Map<String, String>> studentListRecords = new ArrayList<>();

        for (Student student : students) {
            Map<String, String> campAttributes = new HashMap<>();
            campAttributes.put("Name", student.getName());
            campAttributes.put("Email", student.getEmail());
            campAttributes.put("Faculty", student.getFaculty().name());
            campAttributes.put("Points", Integer.toString(student.getPoints()));
            campAttributes.put("NetworkID", student.getNetworkID());
            studentListRecords.add(campAttributes);
        }
        return studentListRecords;
    }

    /**
     * Stores the attributes of staff members in a list of maps.
     *
     * @param staffs An ArrayList of Staff objects to store.
     * @return An ArrayList of maps containing staff attributes.
     */
    public static ArrayList<Map<String, String>> staffStorer(ArrayList<Staff> staffs) {
        ArrayList<Map<String, String>> staffListRecords = new ArrayList<>();

        for (Staff staff : staffs) {
            Map<String, String> campAttributes = new HashMap<>();
            campAttributes.put("Name", staff.getName());
            campAttributes.put("Email", staff.getEmail());
            campAttributes.put("Faculty", staff.getFaculty().name());
            campAttributes.put("NetworkID", staff.getNetworkID());
            staffListRecords.add(campAttributes);
        }
        return staffListRecords;
    }

    /**
     * Stores the attributes of suggestions in a list of maps.
     *
     * @param suggestions An ArrayList of Suggestion objects to store.
     * @return An ArrayList of maps containing suggestion attributes.
     */
    public static ArrayList<Map<String, String>> suggestionsStorer(ArrayList<Suggestion> suggestions) {
        ArrayList<Map<String, String>> suggestRecords = new ArrayList<>();
        for(Suggestion suggestion: suggestions){
            Map<String, String> campAttributes = new HashMap<>();
            campAttributes.put("Sender",suggestion.getSender());
            campAttributes.put("CampName", suggestion.getCampName());
            campAttributes.put("Message", suggestion.getMessage());
            campAttributes.put("status", suggestion.getStatus());
            suggestRecords.add(campAttributes);
        }
        return suggestRecords;
    }


    /**
     * Stores the attributes of enquiries in a list of maps.
     *
     * @param enquiries An ArrayList of Enquiry objects to store.
     * @return An ArrayList of maps containing enquiry attributes.
     */
    public static ArrayList<Map<String, String>> enquiriesStorer(ArrayList<Enquiry> enquiries) {
        ArrayList<Map<String, String>> enquiryRecords = new ArrayList<>();
        for (Enquiry enquiry : enquiries){
            Map<String, String> campAttributes = new HashMap<>();
            campAttributes.put("Sender", enquiry.getSender());
            campAttributes.put("CampName", enquiry.getCampName());
            campAttributes.put("Message", enquiry.getMessage());
            campAttributes.put("status", enquiry.getStatus());
            campAttributes.put("reply", enquiry.getReply());
            campAttributes.put("enquiryResponder", enquiry.getEnquiryResponder());
            enquiryRecords.add(campAttributes);
        }
        return enquiryRecords;
    }

    /**
     * Stores the attributes of passwords in a list of maps.
     *
     * @param students An ArrayList of Student objects.
     * @param staffs   An ArrayList of Staff objects.
     * @return An ArrayList of maps containing password attributes.
     */
    public static ArrayList<Map<String, String>> passwordsStorer(ArrayList<Student> students, ArrayList<Staff> staffs){
        ArrayList<Map<String, String>> passwordRecords = new ArrayList<>();
        for (Staff staff: staffs){
            Map<String, String> campAttributes = new HashMap<>();
            campAttributes.put("NetworkID", staff.getNetworkID());
            campAttributes.put("HashedPassword", staff.getEncypted_password().getPW());
            campAttributes.put("UserType", "STAFF");
            passwordRecords.add(campAttributes);
        }
        for (Student student: students){
            Map<String, String> campAttributes = new HashMap<>();
            campAttributes.put("NetworkID", student.getNetworkID());
            campAttributes.put("HashedPassword", student.getEncypted_password().getPW());
            campAttributes.put("UserType", "STUDENT");
            passwordRecords.add(campAttributes);
        }
        return passwordRecords;
    }
}
