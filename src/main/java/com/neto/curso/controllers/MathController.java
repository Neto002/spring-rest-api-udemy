package com.neto.curso.controllers;

import com.neto.curso.converters.NumberConverter;
import com.neto.curso.exceptions.DivisionByZeroException;
import com.neto.curso.exceptions.UnsupportedMathOperationException;
import com.neto.curso.math.SimpleMath;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {

    private SimpleMath math = new SimpleMath();

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws UnsupportedMathOperationException {

        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return math.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double subtraction(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws UnsupportedMathOperationException {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return math.subtraction(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }
    @RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multiplication(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws UnsupportedMathOperationException {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return math.multiplication(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }
    @RequestMapping(value = "/division/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double division(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws UnsupportedMathOperationException, DivisionByZeroException {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        } else if (NumberConverter.convertToDouble(numberTwo) == 0D) {
            throw new DivisionByZeroException("The divisor can't be equal to 0!");
        }

        return math.division(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/squareRoot/{number}", method = RequestMethod.GET)
    public Double squareRoot(@PathVariable(value = "number") String number) throws UnsupportedMathOperationException {
        if (!NumberConverter.isNumeric(number)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return math.squareRoot(NumberConverter.convertToDouble(number));
    }

    @RequestMapping(value = "power/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double power(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws UnsupportedMathOperationException {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return math.power(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/mean/{numbers}", method = RequestMethod.GET)
    public Double mean(@PathVariable(value = "numbers") ArrayList<String> numbers) throws UnsupportedMathOperationException {
        ArrayList<Double> numbersConverted = new ArrayList<>();
        for (String number: numbers) {
            if (!NumberConverter.isNumeric(number)) {
                throw new UnsupportedMathOperationException("Please set a numeric value!");
            }
            numbersConverted.add(NumberConverter.convertToDouble(number));
        }

        return math.mean(numbersConverted);
    }
}
