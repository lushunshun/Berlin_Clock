package com.inkglobal.exercise.berlinclock;

/**
 * Created by sparks on 24/05/2014.
 */
public class BerlinClock {

    private TimeFeed timeFeed;

    public BerlinClock(TimeFeed timeFeed) {
        this.timeFeed = timeFeed;
    }

    public String toSecond() {
        int second = timeFeed.second();
        return (second % 2 == 0) ? "Y" : "O";
    }

    public String toMinute() {

        return null;
    }

    public String toHour() {
        int hours = timeFeed.hour();

        return null;
    }

    public String now() {

        return toHour() + toMinute() + toSecond();
    }
}
