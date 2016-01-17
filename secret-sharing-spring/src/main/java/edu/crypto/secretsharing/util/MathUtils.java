package edu.crypto.secretsharing.util;

import java.util.List;

public class MathUtils {

    public static class Interval<Type> {
        Type min;
        Type max;

        public Interval(Type min, Type max) {
            this.min = min;
            this.max = max;
        }
    }

    public static String numberToString(Double number) {
        String numberString = "";
        if(MathUtils.isApproximatelyLong(number)) {
            numberString += number.longValue();
        } else {
            numberString += number;
        }
        return numberString;
    }


    public static boolean areAllLong(List<Double> numbers) {
        int i = 0;
        boolean areAllLong = true;
        while(areAllLong && i < numbers.size()) {
            areAllLong = isApproximatelyLong(numbers.get(i++));
        }
        return areAllLong;
    }

    public static boolean isApproximatelyLong(Double number) {
        long numberInLong = number.longValue();
        return number - numberInLong < 0.01;
    }

    private static Double computeAverage(List<Double> numbers) {
        int count = 0;
        double sum = 0.0;
        for (Double number : numbers) {
            count++;
            sum += number;
        }
        return sum/(double)count;
    }

    public static Interval<Long> computeLongInterval(List<Double> numbers) {
        double average = computeAverage(numbers);
        long min = (long) Math.ceil(average/50.0);
        long max = (long) Math.floor(average*2.0);
        return new Interval<>(min, max);
    }

    public static Interval<Double> computeDoubleInterval(List<Double> numbers) {
        double average = computeAverage(numbers);
        double min = average/50.0;
        double max = average * 2.0;
        return new Interval<Double>(min, max);
    }

}
