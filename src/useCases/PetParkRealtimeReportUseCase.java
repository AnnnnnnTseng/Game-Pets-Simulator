package useCases;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import reportManagement.ReportReader;
import reportManagement.ReportWriter;
import reportManagement.Report;
import globalEntities.Parameters;


public class PetParkRealtimeReportUseCase {

    private Random random;
    private Timer timer;

    private ReportWriter reportWriter;

    //constructor
    public PetParkRealtimeReportUseCase(ReportWriter aWriter) {
        this.reportWriter = aWriter;
        this.random = new Random();
        this.timer = new Timer();
    }

    public void startProducing() {

        //produce report by preset duration.
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                produceReports();
            }
        }, 0, Parameters.MS_PER_REPORT);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                pauseProducing();
            }
        }, Parameters.DURATION_OF_GENERATION_MS);
    }


    public void produceReports() {

        //set report content.
        long reportTime = System.currentTimeMillis();
        int NumberOfPets = random.nextInt(10) + 1;
        String Weather = Parameters.WEATHER_CLASSIFICATIONS[random.nextInt(Parameters.WEATHER_CLASSIFICATIONS.length)];

        //new instance
        Report newReport = new Report(reportTime, NumberOfPets, Weather);
        ReportWriter.addReport(newReport);
    }



    private void pauseProducing() {
        timer.cancel();
    }

    public static void main(String[] args) {

        ReportWriter write = new ReportWriter();
        PetParkRealtimeReportUseCase PetParkReporter = new PetParkRealtimeReportUseCase(write);
        PetParkReporter.startProducing();

        try {
            Thread.sleep(Parameters.DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //List<Report> aReportList = write.getReports();
        List<Report> aReportList = (List<Report>) write.getReports().collect(Collectors.toList());;
        ReportReader reader = new ReportReader();
        reader.showReports(aReportList);
    }

}
