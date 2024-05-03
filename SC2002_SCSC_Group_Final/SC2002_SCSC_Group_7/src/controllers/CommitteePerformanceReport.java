package controllers;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The {@code CommitteePerformanceReport} interface defines a method for generating a committee performance report.
 */
public interface CommitteePerformanceReport {

    /**
     * Generates a committee performance report.
     *
     * @throws FileNotFoundException If the file for the report is not found.
     */
    void CommitteePerformanceReport() throws FileNotFoundException;
}
