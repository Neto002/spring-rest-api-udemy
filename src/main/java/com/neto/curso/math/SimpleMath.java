package com.neto.curso.math;

import java.util.ArrayList;

public class SimpleMath {
    public Double sum(Double numberOne, Double numberTwo) {
        return numberOne + numberTwo;
    }

    public Double subtraction(Double numberOne, Double numberTwo) {
        return numberOne - numberTwo;
    }
    public Double multiplication(Double numberOne, Double numberTwo) {
        return numberOne * numberTwo;
    }
    public Double division(Double numberOne, Double numberTwo) {
        return numberOne / numberTwo;
    }

    public Double squareRoot(Double number) {
        return Math.sqrt(number);
    }

    public Double power(Double numberOne, Double numberTwo) {
        return Math.pow(numberOne, numberTwo);
    }

    public Double mean(ArrayList<Double> numbers) {
        double sum = 0D;

        for (Double number: numbers) {
            sum += number;
        }

        return sum/numbers.size();
    }
}
