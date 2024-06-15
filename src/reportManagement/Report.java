package reportManagement;

// Models real-time Report of the Pet Park

import java.io.Serializable;

public class Report implements Serializable {

    //used during deserialization to ensure that a loaded class corresponds exactly to a serialized object.
    private static final long serialVersionUID = 1L;

    private long reportTime;
    private int NumberOfPets;
    private String Weather;

    public Report(long aTime, int aNumberOfPet, String aWeather) {
        this.reportTime = aTime;
        this.NumberOfPets = aNumberOfPet;
        this.Weather = aWeather;
    }

    public long getTime() {
        return reportTime;
    }

    public int getNumberOfPets() {
        return NumberOfPets;
    }

    public String getWeather() {
        return Weather;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportTime=" + reportTime +
                ", NumberOfPets=" + NumberOfPets +
                ", Weather=" + Weather + '\'' +
                '}';
    }




}


