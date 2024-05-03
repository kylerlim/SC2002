package helpers;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import enitities.Camp;

public class DateHandler {

    public static String dateString;
    public static LocalDate dateDate;

    public static LocalDate StringToDate(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    // Function to convert LocalDate to String in YYYYMMDD format
    public static String DateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return date.format(formatter);
    }

    public static boolean CampClashes(Camp campCurrent, Camp campNew){
        boolean clashes = false;
        LocalDate currentStartDate = campCurrent.getStartDate();
        LocalDate currentEndDate = campCurrent.getEndDate();
        LocalDate newStartDate = campNew.getStartDate();
        LocalDate newEndDate = campNew.getEndDate();

        if (newStartDate.isBefore(currentEndDate) && newStartDate.isAfter(currentStartDate)) clashes = true;
        if (newEndDate.isBefore(currentEndDate) && newEndDate.isAfter(currentStartDate)) clashes = true;
        if (newStartDate.isEqual(currentStartDate) || newStartDate.isEqual(currentEndDate)) clashes = true;
        if (newEndDate.isEqual(currentStartDate) || newStartDate.isEqual(currentEndDate)) clashes = true;

        return clashes;
        
    }


}