package com.inkglobal.exercise.berlinclock;

import com.inkglobal.exercise.berlinclock.BerlinClock;
import com.inkglobal.exercise.berlinclock.TimeFeed;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

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
        when(mockTimeFeed.second()).thenReturn(2);

        assertThat(clockUnderTest.toSecond(), is("Y"));
    }

    @Test
    public void LampOfSeconds_OddSecond_YellowLampOff () {
        when(mockTimeFeed.second()).thenReturn(27);

        assertThat(clockUnderTest.toSecond(), is("O"));
    }

    /**
     * Given it's the lamps of hours (top two rows of the clock),
     * when it's before 5am,
     * then there should be no red lamps on in first row
     */
    @Test
    public void LampsOfHours_Before5AM_NoLampsOnFirstRow () {
        when(mockTimeFeed.hour()).thenReturn(4);

        assertThat(clockUnderTest.toHour(), is("OOOO RRRR"));
    }

    /**
     * Given it's the lamps of hours,
     * when 5 hour past,
     * then there should be one more red lamp's on every 5 hours
     */
    @Test
    public void LampsOfHours_Every5HoursPast_OneMoreRedLampOn () {
        when(mockTimeFeed.hour()).thenReturn(5);
        assertThat(clockUnderTest.toHour(), is("ROOO OOOO"));

        when(mockTimeFeed.hour()).thenReturn(10);
        assertThat(clockUnderTest.toHour(), is("RROO OOOO"));

        when(mockTimeFeed.hour()).thenReturn(15);
        assertThat(clockUnderTest.toHour(), is("RRRO OOOO"));

        when(mockTimeFeed.hour()).thenReturn(20);
        assertThat(clockUnderTest.toHour(), is("RRRR OOOO"));
    }

    /**
     * Given it's the lamps of minutes,
     * when it's in the first quarter of the hour,
     * then there should be no red lamps (lamps represent quarters) on.
     *
     */
    @Test
    public void LampsOfMinutes_InFirstQuarter_NoRedLampsOn () {
        when(mockTimeFeed.minute()).thenReturn(14);
        assertThat(clockUnderTest.toMinute(), is("YYOOOOOOOOO YYYY"));
    }

    @Test
    public void LampsOfMinutes_Every15MinutesPast_OneMoreRedLampOn () {
        when(mockTimeFeed.minute()).thenReturn(15);
        assertThat(clockUnderTest.toMinute(), is("YYROOOOOOOO OOOO"));

        when(mockTimeFeed.minute()).thenReturn(30);
        assertThat(clockUnderTest.toMinute(), is("YYRYYROOOOO OOOO"));

        when(mockTimeFeed.minute()).thenReturn(45);
        assertThat(clockUnderTest.toMinute(), is("YYRYYRYYROO OOOO"));
    }
}