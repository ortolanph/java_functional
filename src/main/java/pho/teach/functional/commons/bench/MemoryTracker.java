package pho.teach.functional.commons.bench;

public class MemoryTracker {
    private final Runtime runtime = Runtime.getRuntime();
    private double startMemory;
    private double endMemory;
    private boolean tracking;

    public void start() {
        // Force garbage collection before measurement
        runtime.gc();
        startMemory = getUsedMemory();
        tracking = true;
    }

    public void stop() {
        if (tracking) {
            // Force garbage collection again
            runtime.gc();
            endMemory = getUsedMemory();
            tracking = false;
        }
    }

    public void reset() {
        startMemory = 0;
        endMemory = 0;
        tracking = false;
    }

    public double getMemoryUsed() {
        if (!tracking && endMemory >= startMemory) {
            return endMemory - startMemory;
        } else {
            return getUsedMemory() - startMemory;
        }
    }

    private double getUsedMemory() {
        return runtime.totalMemory() - runtime.freeMemory();
    }

    public double getMemoryUsedKB() {
        return getMemoryUsed() / 1024;
    }

    public double getMemoryUsedMB() {
        return getMemoryUsed() / (1024 * 1024);
    }

}