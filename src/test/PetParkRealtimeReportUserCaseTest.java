//package test;
//
//import static org.junit.Assert.*;
////import static org.mockito.Mockito.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//import java.util.stream.Stream;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import ReportManagement.Report;
//import ReportManagement.ReportWriter;
//import useCases.PetParkRealtimeReportUseCase;
//import globalEntities.Parameters;
//
//public class PetParkRealtimeReportUserCaseTest {
//
//    private static class MockReportWriter extends ReportWriter {
//        private List<Report> reports = new ArrayList<>();
//
//        @Override
//        public synchronized void addReport(Report aReport) {
//            reports.add(aReport);
//        }
//
//        public Stream<Report> getReports() {
//            return (Stream<Report>) reports;
//        }
//    }
//
//    private MockReportWriter mockReportWriter;
//    private PetParkRealtimeReportUseCase petParkRealtimeReportUserCase;
//
//    @Before
//    public void setUp() {
//        mockReportWriter = new MockReportWriter();
//        petParkRealtimeReportUserCase = new PetParkRealtimeReportUseCase(mockReportWriter);
//    }
//
//    @Test
//    public void testProduceReports() {
//        // Produce a single report
//        petParkRealtimeReportUserCase.produceReports();
//
//        // Verify that a report is added
//        assertEquals(1, mockReportWriter.getReports().size());
//    }
//
//    @Test
//    public void testStartProducing() throws InterruptedException {
//        // Start producing reports
//        petParkRealtimeReportUserCase.startProducing();
//
//        // Wait for a little longer than the report generation duration
//        TimeUnit.MILLISECONDS.sleep(Parameters.DURATION_OF_GENERATION_MS + 100);
//
//        // Verify that reports were generated and added to the mockReportWriter
//        assertTrue(mockReportWriter.getReports().size() > 0);
//    }
//}
