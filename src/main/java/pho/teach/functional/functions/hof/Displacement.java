package pho.teach.functional.functions.hof;

public record Displacement(double start, double finish) {
    public double delta() {
        return finish - start;
    }
}
