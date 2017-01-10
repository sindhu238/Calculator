package com.example.calculator;

/**
 *Performs all basic arithmetic operations that are need to be performed by the app
 * @author Sindhu
 * @since 9-1-2016
 */

public class Calculator {

    private double result = 0;
    private double leftNumber = 0;
    private double rightNumber = 0;

    /*
    Performs mathematical operations required for calculator app
    @param leftNumber       First number entered by user
    @param rightNumber      Second Number entered by user
    @param operation        Arithmetic operation selected by user
    @return result          Returns final computed result
     */
    public double performOperation(String leftNumber, String rightNumber, String operation) {
        this.leftNumber = Double.parseDouble(leftNumber);
        this.rightNumber = Double.parseDouble(rightNumber);

        switch (operation){
            case "+":
                result = this.leftNumber + this.rightNumber;
                break;
            case "-":
                result = this.leftNumber - this.rightNumber;
                break;
            case "*":
                result = this.leftNumber * this.rightNumber;
                break;
            case "/":
                result = this.leftNumber / this.rightNumber;
                break;
        }
        return result;
    }
}
