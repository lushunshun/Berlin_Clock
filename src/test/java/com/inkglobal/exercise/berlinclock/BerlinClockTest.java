package com.inkglobal.exercise.berlinclock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * This test suite check if the berlin clock could produce the correct representations
 * of hours, minutes and seconds individually.
 *
 * @author Yu Lu (http://github.com/lushunshun/Berlin_Clock)
 */
public class BerlinClockTest {
    private BerlinClock clockUnderTest;
    private TimeFeed mockTimeFeed;

    @Before
    public void setUp() {
        mockTimeFeed = Mockito.mock(TimeFeed.class);
        clockUnderTest = new BerlinClock(mockTimeFeed);
    }

    /**
     * Given it's the lamp of seconds (top of the clock),
     * when the time is in even seconds,
     * then the yellow lamp should be on.
     */
    @Test
    public void LampOfSeconds_EvenSecond_YellowLampOn () {
        // go through all even seconds
        for (int i = 0; i < 60; i += 2) {
            when(mockTimeFeed.second()).thenReturn(i);

            assertThat(clockUnderTest.second(), is("Y"));
        }
    }

    @Test
    public void LampOfSeconds_OddSecond_YellowLampOff () {
        // go through all odd seconds
        for (int i = 1; i < 60; i += 2) {
            when(mockTimeFeed.second()).thenReturn(i);

            assertThat(clockUnderTest.second(), is("O"));
        }
    }

    /**
     * Given it's the lamps of minutes,
     * when it's in the first quarter of the hour,
     * then there should be no red lamps (lamps represent quarters) on.
     */
    @Test
    public void LampsOfMinutes_InFirstQuarter_NoRedLampsOn () {
        when(mockTimeFeed.minute()).thenReturn(14);
        assertThat(clockUnderTest.minute(), is("YYOOOOOOOOO YYYY"));
    }

    /**
     * Given it's the lamps of minutes,
     * when every 15 minutes past,
     * then there should be one more red lamp on.
     */
    @Test
    public void LampsOfMinutes_Every15MinutesPast_OneMoreRedLampOn () {
        when(mockTimeFeed.minute()).thenReturn(15);
        assertThat(clockUnderTest.minute(), is("YYROOOOOOOO OOOO"));

        when(mockTimeFeed.minute()).thenReturn(30);
        assertThat(clockUnderTest.minute(), is("YYRYYROOOOO OOOO"));

        when(mockTimeFeed.minute()).thenReturn(45);
        assertThat(clockUnderTest.minute(), is("YYRYYRYYROO OOOO"));
    }

    /**
     * Given it's the lamps of hours (top two rows of the clock),
     * when it's before 5am,
     * then there should be no red lamps on in first row
     */
    @Test
    public void LampsOfHours_Before5AM_NoLampsOnFirstRow () {
        when(mockTimeFeed.hour()).thenReturn(4);

        assertThat(clockUnderTest.hour(), is("OOOO RRRR"));
    }

    /**
     * Given it's the lamps of hours,
     * when every 5 hours past,
     * then there should be one more red lamp's on every 5 hours
     */
    @Test
    public void LampsOfHours_Every5HoursPast_OneMoreRedLampOn () {
        when(mockTimeFeed.hour()).thenReturn(5);
        assertThat(clockUnderTest.hour(), is("ROOO OOOO"));

        when(mockTimeFeed.hour()).thenReturn(10);
        assertThat(clockUnderTest.hour(), is("RROO OOOO"));

        when(mockTimeFeed.hour()).thenReturn(15);
        assertThat(clockUnderTest.hour(), is("RRRO OOOO"));

        when(mockTimeFeed.hour()).thenReturn(20);
        assertThat(clockUnderTest.hour(), is("RRRR OOOO"));
    }
}