package pho.teach.functional.functions.bench;

public record TimeStatistic(String title, String methodName, long elapsedMillis) {

    public String toCSVLine() {
        return "%s,%s,%d".formatted(title, methodName, elapsedMillis);
    }
}
