package reportManagement;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ReportReader {

    //Consistant File name???
    private static final String FILE_NAME = "reports";

    //Read objects from file and return as a stream
    public Stream<Report> readReports() {
        List<Report> returnReports = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            while (true) {
                try {
                    Report incident = (Report) ois.readObject();
                    returnReports.add(incident);
                } catch (ClassNotFoundException | IOException e) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnReports.stream();
    }

    //Print historic reports
    public void showReports(List<Report> aReportList) {

        int size = aReportList.size();

        if (size < 10) {
            for (Report report : aReportList) {
                System.out.println(report);
            }
        } else {
            for (int i = 0; i < 5; i++) {
                System.out.println(aReportList.get(i));
            }
            System.out.println("...");
            for (int i = size - 5; i < size; i++) {
                System.out.println(aReportList.get(i));
            }
        }
    }
}
