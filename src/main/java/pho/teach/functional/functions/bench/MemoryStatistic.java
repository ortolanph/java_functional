package pho.teach.functional.functions.bench;

public record MemoryStatistic(String title, String methodName, double usedMemory) {

    public String toCSVLine() {
        return "%s,%s,%.2f".formatted(title, methodName, usedMemory);
    }

}
