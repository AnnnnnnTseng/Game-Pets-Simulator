package reportManagement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ReportWriter {

//    private static List<Report> reports_;
    private static List<Report> reports_ = new ArrayList<>();

    private static final String FILE_NAME = "PetParkRealtimeReports";

    //sync method: allow multiple threads access it.
    public static synchronized void addReport(Report aReport) {
        reports_.add(aReport);
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME, true))) {
            oos.writeObject(aReport);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public abstract void addReport(Report aReport);

    //    public List<Report> getReports() {return reports_; }
    public Stream<Report> getReports() {
    return reports_.stream();
    }
}
