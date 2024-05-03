package controllers;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The {@code generalCampReport} interface provides a method for generating a general camp report.
 */
public interface generalCampReport {

	/**
	 * Generates a general camp report, which includes information about the camp.
	 *
	 * @throws FileNotFoundException If the file to write the report is not found.
	 */
	void generalCampReport() throws FileNotFoundException;
}
