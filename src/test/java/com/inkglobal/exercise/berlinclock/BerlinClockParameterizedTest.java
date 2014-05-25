package com.inkglobal.exercise.berlinclock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * This test loads the BerlinClock class with a batch of test times and checks if it produces the correct output or not.
 *
 * @author Yu Lu (https://github.com/lushunshun)
 */
@RunWith(Parameterized.class)
public class BerlinClockParameterizedTest {
    private BerlinClock clockUnderTest;
    private TimeFeed mockTimeFeed;

    private String hour;
    private String minute;
    private String second;
    private String expectedTime;

    public BerlinClockParameterizedTest(String hour, String minute, String second, String expectedTime) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.expectedTime = expectedTime;
    }

    @Before
    public void setUp() {
        mockTimeFeed = Mockito.mock(TimeFeed.class);
        clockUnderTest = new BerlinClock(mockTimeFeed);
    }

    @Test
    public void checkTime() {
        when(mockTimeFeed.hour()).thenReturn(Integer.parseInt(hour));
        when(mockTimeFeed.minute()).thenReturn(Integer.parseInt(minute));
        when(mockTimeFeed.second()).thenReturn(Integer.parseInt(second));

        assertThat(clockUnderTest.now(), is(expectedTime));
    }

    @Parameterized.Parameters(name = "{0}:{1}:{2} -> {3}")
    public static Collection<Object[]> testTimes() {
        return Arrays.asList(new Object[][] {
                {"13", "17", "01", "O RROO RRRO YYROOOOOOOO YYOO"},
                {"00", "00", "00", "Y OOOO OOOO OOOOOOOOOOO OOOO"},
                {"23", "59", "59", "O RRRR RRRO YYRYYRYYRYY YYYY"},
                {"24", "00", "00", "Y RRRR RRRR OOOOOOOOOOO OOOO"},
                {"02", "30", "30", "Y OOOO RROO YYRYYROOOOO OOOO"},
                {"05", "01", "59", "O ROOO OOOO OOOOOOOOOOO YOOO"},
                {"03", "45", "21", "O OOOO RRRO YYRYYRYYROO OOOO"},
                {"12", "55", "20", "Y RROO RROO YYRYYRYYRYY OOOO"},
                {"15", "35", "05", "O RRRO OOOO YYRYYRYOOOO OOOO"},
                {"17", "28", "29", "O RRRO RROO YYRYYOOOOOO YYYO"},
                {"18", "46", "51", "O RRRO RRRO YYRYYRYYROO YOOO"},
                {"19", "59", "59", "O RRRO RRRR YYRYYRYYRYY YYYY"},
                {"20", "00", "00", "Y RRRR OOOO OOOOOOOOOOO OOOO"},
                {"22", "05", "45", "O RRRR RROO YOOOOOOOOOO OOOO"}
        });
    }

}