package com.pairip.licensecheck;

public class RepeatedCheckMetadata {
    private final long durationToRetryMillis;
    private final long timeToRetryMillis;

    public RepeatedCheckMetadata(long j, long j2) {
        if (j <= 0) {
            throw new IllegalArgumentException("Duration to retry must be positive.");
        } else if (j2 > 0) {
            this.durationToRetryMillis = j;
            this.timeToRetryMillis = j2;
        } else {
            throw new IllegalArgumentException("Time to retry must be positive.");
        }
    }

    public long getDurationToRetryMillis() {
        return this.durationToRetryMillis;
    }

    public long getTimeToRetryMillis() {
        return this.timeToRetryMillis;
    }
}
