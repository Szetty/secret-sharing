package edu.crypto.secretsharing.util;

import java.util.Random;

public class RandomGenerator {

    public static Double generateRandomDoubleFromInterval(MathUtils.Interval<Double> interval) {
        Random r = new Random();
        double randomValue = interval.min + (interval.max - interval.min) * r.nextDouble();
        return Math.floor(randomValue * 100.0) / 100.0;
    }

    public static Long generateRandomLongFromInterval(MathUtils.Interval<Long> interval) {
        Random r = new Random();
        return Math.round(interval.min + (interval.max - interval.min) * r.nextDouble());
    }
}
