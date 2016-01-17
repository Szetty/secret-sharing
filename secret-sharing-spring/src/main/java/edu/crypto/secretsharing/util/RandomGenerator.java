package edu.crypto.secretsharing.util;

import java.util.Random;

/**
 * Created by szede_000 on 1/17/2016.
 */
public class RandomGenerator {

    public static Double generateRandomDouble() {
        Random r = new Random();
        double randomValue = Double.MIN_VALUE + (Double.MAX_VALUE - Double.MIN_VALUE) * r.nextDouble();
        return randomValue;
    }
}
