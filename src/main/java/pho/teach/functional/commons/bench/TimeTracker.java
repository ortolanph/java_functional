package pho.teach.functional.commons.bench;

import java.time.Duration;
import java.time.Instant;

public class TimeTracker {
    private Instant startTime;
    private Instant endTime;
    private boolean running;

    public void start() {
        startTime = Instant.now();
        running = true;
    }

    public void stop() {
        if (running) {
            endTime = Instant.now();
            running = false;
        }
    }

    public void reset() {
        startTime = null;
        endTime = null;
        running = false;
    }

    public Duration getElapsedTime() {
        if (startTime == null) {
            return Duration.ZERO;
        }
        return Duration.between(startTime, running ? Instant.now() : endTime);
    }

    public long getElapsedMillis() {
        return getElapsedTime().toMillis();
    }

    public long getElapsedNanos() {
        return getElapsedTime().toNanos();
    }

    @Override
    public String toString() {
        return getElapsedMillis() + " ms";
    }
}