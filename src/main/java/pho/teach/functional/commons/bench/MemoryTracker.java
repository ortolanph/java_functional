package pho.teach.functional.commons.bench;

public class MemoryTracker {
    private final Runtime runtime = Runtime.getRuntime();
    private long startMemory;
    private long endMemory;
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

    public long getMemoryUsed() {
        if (!tracking && endMemory >= startMemory) {
            return endMemory - startMemory;
        } else {
            return getUsedMemory() - startMemory;
        }
    }

    private long getUsedMemory() {
        return runtime.totalMemory() - runtime.freeMemory();
    }

    public String getMemoryUsedKB() {
        return getMemoryUsed() / 1024 + " KB";
    }

    public String getMemoryUsedMB() {
        return getMemoryUsed() / (1024 * 1024) + " MB";
    }

    @Override
    public String toString() {
        return getMemoryUsedKB();
    }
}