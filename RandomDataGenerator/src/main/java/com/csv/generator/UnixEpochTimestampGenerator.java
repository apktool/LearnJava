package com.csv.generator;

import java.util.concurrent.TimeUnit;

/**
 * @author apktool
 * @package com.csv.demo.com.csv.generator
 * @class UnixEpochTimestampGenerator
 * @description TODO
 * @date 2020-07-28 20:15
 */
public class UnixEpochTimestampGenerator extends Generator<Long> {

    /**
     * The base timestamp used as a starting reference.
     */
    protected long startTimestamp;

    /**
     * The current timestamp that will be incremented.
     */
    protected long currentTimestamp;

    /**
     * The last used timestamp. Should always be one interval behind current.
     */
    protected long lastTimestamp;

    /**
     * The interval to increment by. Multiplied by {@link #timeUnits}.
     */
    protected long interval;

    /**
     * The units of time the interval represents.
     */
    protected TimeUnit timeUnits;

    /**
     * Default ctor with the current system time and a 60 second interval.
     */
    public UnixEpochTimestampGenerator() {
        this(60, TimeUnit.SECONDS);
    }

    /**
     * Ctor that uses the current system time as current.
     *
     * @param interval  The interval for incrementing the timestamp.
     * @param timeUnits The units of time the increment represents.
     */
    public UnixEpochTimestampGenerator(final long interval, final TimeUnit timeUnits) {
        this.interval = interval;
        this.timeUnits = timeUnits;
        // move the first timestamp by 1 interval so that the first call to nextValue
        // returns this timestamp
        initalizeTimestamp(-1);
        currentTimestamp -= getOffset(1);
        lastTimestamp = currentTimestamp;
    }

    /**
     * Ctor for supplying a starting timestamp.
     *
     * @param interval       The interval for incrementing the timestamp.
     * @param timeUnits      The units of time the increment represents.
     * @param startTimestamp The start timestamp to use.
     *                       NOTE that this must match the time units used for the interval.
     *                       If the units are in nanoseconds, provide a nanosecond timestamp {@code System.nanoTime()}
     *                       or in microseconds, {@code System.nanoTime() / 1000}
     *                       or in millis, {@code System.currentTimeMillis()}
     *                       or seconds and any interval above, {@code System.currentTimeMillis() / 1000}
     */
    public UnixEpochTimestampGenerator(final long interval, final TimeUnit timeUnits,
                                       final long startTimestamp) {
        this.interval = interval;
        this.timeUnits = timeUnits;
        // move the first timestamp by 1 interval so that the first call to nextValue
        // returns this timestamp
        currentTimestamp = startTimestamp - getOffset(1);
        this.startTimestamp = currentTimestamp;
        lastTimestamp = currentTimestamp - getOffset(1);
    }

    /**
     * Sets the starting timestamp to the current system time plus the interval offset.
     * E.g. to set the time an hour in the past, supply a value of {@code -60}.
     *
     * @param intervalOffset The interval to increment or decrement by.
     */
    public void initalizeTimestamp(final long intervalOffset) {
        switch (timeUnits) {
            case NANOSECONDS:
                currentTimestamp = System.nanoTime() + getOffset(intervalOffset);
                break;
            case MICROSECONDS:
                currentTimestamp = (System.nanoTime() / 1000) + getOffset(intervalOffset);
                break;
            case MILLISECONDS:
                currentTimestamp = System.currentTimeMillis() + getOffset(intervalOffset);
                break;
            case SECONDS:
                currentTimestamp = (System.currentTimeMillis() / 1000) +
                        getOffset(intervalOffset);
                break;
            case MINUTES:
                currentTimestamp = (System.currentTimeMillis() / 1000) +
                        getOffset(intervalOffset);
                break;
            case HOURS:
                currentTimestamp = (System.currentTimeMillis() / 1000) +
                        getOffset(intervalOffset);
                break;
            case DAYS:
                currentTimestamp = (System.currentTimeMillis() / 1000) +
                        getOffset(intervalOffset);
                break;
            default:
                throw new IllegalArgumentException("Unhandled time unit type: " + timeUnits);
        }
        startTimestamp = currentTimestamp;
    }

    @Override
    public Long nextValue() {
        lastTimestamp = currentTimestamp;
        currentTimestamp += getOffset(1);
        return currentTimestamp;
    }

    /**
     * Returns the proper increment offset to use given the interval and timeunits.
     *
     * @param intervalOffset The amount of offset to multiply by.
     * @return An offset value to adjust the timestamp by.
     */
    public long getOffset(final long intervalOffset) {
        switch (timeUnits) {
            case NANOSECONDS:
            case MICROSECONDS:
            case MILLISECONDS:
            case SECONDS:
                return intervalOffset * interval;
            case MINUTES:
                return intervalOffset * interval * (long) 60;
            case HOURS:
                return intervalOffset * interval * (long) (60 * 60);
            case DAYS:
                return intervalOffset * interval * (long) (60 * 60 * 24);
            default:
                throw new IllegalArgumentException("Unhandled time unit type: " + timeUnits);
        }
    }

    @Override
    public Long lastValue() {
        return lastTimestamp;
    }

    /**
     * @return The current timestamp as set by the last call to {@link #nextValue()}
     */
    public long currentValue() {
        return currentTimestamp;
    }
}

