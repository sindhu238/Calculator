package com.example.calculator;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *This class exclusively takes input from user and checks if the input entered by user is correct.
 * It checks if operator precedence is satisfied.
 *
 * @author Sindhu
 * @since 20/1/2017
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String MULTIPLY = "*";
    private static final String DIVIDE = "/";
    private static final String EQUALS = "=";
    private static final String DOT = ".";
    private static final String CLEAR = "CLEAR";

    private Button one, two, three, four, five, six, seven, eight, nine, zero;
    private  Button plus, minus, multiply, divide, equals, dot, clear;
    private TextView outputTV;
    private Calculator calculator;
    private String leftNumber = "";
    private String rightNumber = "";
    private String number = "";
    private double result = 0;
    private String operation = "";
    private boolean opClicked = false;
    private boolean opPrecedence = false;
    private int count = 0;
    private String tempNum = "";
    private String tempOp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new Calculator();
        one = (Button)findViewById(R.id.one);
        two = (Button)findViewById(R.id.two);
        three = (Button)findViewById(R.id.three);
        four = (Button)findViewById(R.id.four);
        five = (Button)findViewById(R.id.five);
        six = (Button)findViewById(R.id.six);
        seven = (Button)findViewById(R.id.seven);
        eight = (Button)findViewById(R.id.eight);
        nine = (Button)findViewById(R.id.nine);
        zero = (Button)findViewById(R.id.zero);
        plus = (Button)findViewById(R.id.plus);
        minus = (Button)findViewById(R.id.minus);
        multiply = (Button)findViewById(R.id.multiply);
        divide = (Button)findViewById(R.id.divide);
        dot = (Button)findViewById(R.id.dot);
        clear = (Button)findViewById(R.id.clear);
        equals = (Button)findViewById(R.id.equals);
        outputTV = (TextView)findViewById(R.id.outputTV);
        outputTV.setText("");

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
        dot.setOnClickListener(this);
        clear.setOnClickListener(this);
        equals.setOnClickListener(this);

    }

    /*
    When a number is entered it is taken as a input number. When an operator is clicked necessary
    arithmetical operation is performed in @link Calculator. This function checks specifically for operator
    precedence.

    @throws IllegalArgumentException when invalid operations are done
     */
    @Override
    public void onClick(View v) {

        Button btn = (Button) v;
        String text = btn.getText().toString();
        switch (text) {
            case PLUS:
            case MINUS:
            case  MULTIPLY:
            case DIVIDE:
                count++;

                // Check if more than one arithmetic operations are to be performed in a sequence
                    if (count > 1) {

                        //Checks if operator precendence fails
                        if ((operation.equals("+") && text.equals("*")) ||
                                (operation.equals("+") && text.equals("/")) ||
                                (operation.equals("-") && text.equals("*")) ||
                                (operation.equals("-") && text.equals("/"))) {
                            opPrecedence = true;
                            //Stored into temp variables so that, first higher precedence computation
                            //is done and later remaining computations can be done on following result
                            tempNum = leftNumber;
                            tempOp = operation;

                            leftNumber = rightNumber;
                            rightNumber = "";

                            opClicked = true;
                            number += text;
                            operation = text;
                            outputTV.setText(number);

                        }
                        // If operator precedence is satisfied, then this checks for multiple
                        // same precedent computations in a sequence
                        else {
                            if (!rightNumber.equals("")) {
                                result = calculator.performOperation(leftNumber,rightNumber,operation);
                                leftNumber = String.valueOf(result);
                                rightNumber = "";
                                opClicked = true;
                                number += text;
                                operation = text;
                                outputTV.setText(number);
                            }
                        }
                    }

                    //For single arithmetic operations like eg.12+5
                    else {
                        leftNumber = number;
                        opClicked = true;
                        number += text;
                        operation = text;
                        outputTV.setText(number);
                    }

                break;
            case EQUALS:
                try
                {
                    //If same operator precedence is present
                    if (opPrecedence) {
                        result = calculator.performOperation(leftNumber,rightNumber,operation);
                        result = calculator.performOperation(tempNum,String.valueOf(result),tempOp);
                    } else {
                        result = calculator.performOperation(leftNumber,rightNumber,operation);

                    }
                    opClicked = false;
                    NumberFormat nf = new DecimalFormat("##.##########");
                    outputTV.setText(String.valueOf(nf.format(result)));
                    number = String.valueOf(nf.format(result));
                    leftNumber = "";
                    rightNumber = "";
                    count = 0;
                } catch (IllegalArgumentException e) {
                    Toast.makeText(this,"Invalid operation",Toast.LENGTH_SHORT).show();
                    clearData();
                } catch (Exception e) {
                    Toast.makeText(this,"Invalid operation",Toast.LENGTH_SHORT).show();
                    clearData();
                }

                break;
            case DOT:
                if (result != 0 && count == 0 && !opClicked) {
                } else {
                    number += text;
                    if (opClicked) {
                        rightNumber += text;
                    } else {
                        leftNumber += text;
                    }
                    outputTV.setText(number);
                }

                break;
            case CLEAR:
                clearData();
                clear.setTextColor(ContextCompat.getColor(this,R.color.light_gray));
                break;
            default:
                //If any operator is clicked then number entered is taken as second number
                if (opClicked) {
                    rightNumber += text;
                }
                if(number.equals("0")) {
                    number = "";
                }

                //Following the successful computation of a result, if a new number is entered, it starts a
                // new computation
                if (result != 0 && count == 0 && !opClicked) {
                    clearData();
                }
                number += text;
                outputTV.setText(number);
                break;

        }
        if (outputTV.getText() != "") {
            clear.setTextColor(ContextCompat.getColor(this,R.color.colorAccent));
        }
    }

    /*
    Resets all required fields to default values
     */
    public void clearData() {
        outputTV.setText("");
        leftNumber = "";
        rightNumber = "";
        number = "";
        result = 0;
        count = 0;
        opClicked = false;
        opPrecedence = false;
        operation = "";
        tempNum = "";
        tempOp = "";
    }
}
