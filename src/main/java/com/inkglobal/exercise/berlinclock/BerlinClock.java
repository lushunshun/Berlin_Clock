package com.inkglobal.exercise.berlinclock;

import java.util.Arrays;

/**
 * Implementation of a berlin clock (Uhr). It relies on a time feed as the source of time and shows times using
 * a combination of lamps.
 * <p>
 *     Please note, this implementation does not consider any error scenarios (e.g. invalid time feed etc).
 *     It assumes the time feed also supplies valid time values.
 * </p>
 *
 * @author  Yu Lu (https://github.com/lushunshun)
 */
public class BerlinClock {

    private TimeFeed timeFeed;

    public BerlinClock(TimeFeed timeFeed) {
        this.timeFeed = timeFeed;
    }

    public String now() {
        return second() + " " + hour() + " " + minute();
    }

    public String second() {
        int second = timeFeed.second();
        return (second % 2 == 0) ? "Y" : "O";
    }

    public String minute() {
        int minute = timeFeed.minute();
        return lampsForFiveMinuteSlots(minute / 5, minute / 15) + " " + lampsForOddMinutes(minute % 5);
    }

    /**
     * Get the lamps of the first row of minutes based on the supplied information.
     *
     * @param noOfFiveMinutes number of five minutes past
     * @param noOfQuarters number of quarters past
     * @return the string representation of the first row of minutes
     */
    private String lampsForFiveMinuteSlots(int noOfFiveMinutes, int noOfQuarters) {
        char[] minuteLamps = getLamps(11, noOfFiveMinutes, 'Y', 'O').toCharArray();

        // set the red lamps that mark quarters. There should be one red lamp every three lamps.
        for(int i = 1; i <= noOfQuarters; i ++) {
            minuteLamps[i * 3 - 1] = 'R';
        }

        return new String(minuteLamps);
    }

    /**
     * Get the lamps of the second row of minutes based on the supplied information.
     */
    private String lampsForOddMinutes(int noOfOddMinutes) {
        return getLamps(4, noOfOddMinutes, 'Y', 'O');
    }

    public String hour() {
        int hours = timeFeed.hour();
        return lampsForFiveHourSlot(hours / 5) + " " + lampsForOddHours(hours % 5);
    }

    /**
     * Get the lamps of the first row of hours based on the supplied information.
     *
     * @param noOfFiveHours number of five hours past
     * @return the string representation of the first row of hours
     */
    private String lampsForFiveHourSlot(int noOfFiveHours) {
        return getLamps(4, noOfFiveHours, 'R', 'O');
    }

    /**
     * Get the lamps of the second row of hours based on the supplied information.
     */
    private String lampsForOddHours(int noOfOddHours) {
        return getLamps(4, noOfOddHours, 'R', 'O');
    }

    /**
     * Create a single row of lamps using the supplied information. Lamps should be either on or off. The number of
     * "off" lamps will be the difference between the total number of lamps and the number of lamps that are turned on.
     *
     * @param totalLamps total number of lamps in the row
     * @param noOfOn number of lamps that are turned on
     * @param onSign the sign used to indicate an "on" lamp
     * @param offSign the sign used to indicate an "off" lamp
     * @return the string representation of a single row of lamps
     */
    private String getLamps(int totalLamps, int noOfOn, char onSign, char offSign) {
        char[] lamps = new char[totalLamps];
        Arrays.fill(lamps,offSign);
        Arrays.fill(lamps,0, noOfOn, onSign);

        return new String(lamps);
    }
}
