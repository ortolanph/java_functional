package pho.teach.functional.functions;

import pho.teach.functional.functions.hof.Displacement;

public class HighOrderFunctions {

    public double simpleTorricelli(double vi, double a, double di, double df) {
        return Math.sqrt(Math.pow(vi, 2) + 2 * a * (df - di));
    }

    public double simpleTorricelli(double vi, double a, Displacement displacement) {
        return Math.sqrt(Math.pow(vi, 2) + 2 * a * displacement.delta());
    }


}
