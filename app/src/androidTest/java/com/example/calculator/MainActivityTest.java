package com.example.calculator;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Sindhu on 10/01/17.
 */

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity tMainActivity;
    private Button mTestOne, mTestTwo, mTestThree, mTestFour, mTestFive, mTestSix, mTestSeven,
            mTestEight, mTestNine, mTestZero;
    private  Button mTestPlus, mTestMinus, mTestMultiply, mTestDivide, mTestEquals, mTestDot, mTestClear;
    private TextView outputTV;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    /*
    * Sets up the test environment before each test.
    * @see android.test.ActivityInstrumentationTestCase2#setUp()
    */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        tMainActivity = getActivity();
        mTestOne = (Button) tMainActivity.findViewById(R.id.one);
        mTestTwo = (Button) tMainActivity.findViewById(R.id.two);
        mTestThree = (Button) tMainActivity.findViewById(R.id.three);
        mTestFour = (Button) tMainActivity.findViewById(R.id.four);
        mTestFive = (Button) tMainActivity.findViewById(R.id.five);
        mTestSix = (Button) tMainActivity.findViewById(R.id.six);
        mTestSeven = (Button) tMainActivity.findViewById(R.id.seven);
        mTestEight = (Button) tMainActivity.findViewById(R.id.eight);
        mTestNine = (Button) tMainActivity.findViewById(R.id.nine);
        mTestZero = (Button) tMainActivity.findViewById(R.id.zero);
        mTestPlus = (Button) tMainActivity.findViewById(R.id.plus);
        mTestMinus = (Button) tMainActivity.findViewById(R.id.minus);
        mTestMultiply = (Button) tMainActivity.findViewById(R.id.multiply);
        mTestDivide = (Button) tMainActivity.findViewById(R.id.divide);
        mTestDot = (Button) tMainActivity.findViewById(R.id.dot);
        mTestEquals = (Button) tMainActivity.findViewById(R.id.equals);
        mTestClear = (Button) tMainActivity.findViewById(R.id.clear);
        outputTV = (TextView) tMainActivity.findViewById(R.id.outputTV);
    }

    public void testpreConditions() {
        assertNotNull("MainActivityTest is null",tMainActivity);
        assertNotNull("Button one is null",mTestOne);
        assertNotNull("Button two is null",mTestTwo);
        assertNotNull("Button three is null",mTestThree);
        assertNotNull("Button four is null",mTestFour);
        assertNotNull("Button five is null",mTestFive);
        assertNotNull("Button six is null",mTestSix);
        assertNotNull("Button seven is null",mTestSeven);
        assertNotNull("Button eight is null",mTestEight);
        assertNotNull("Button nine is null",mTestNine);
        assertNotNull("Button zero is null",mTestZero);
        assertNotNull("Button plus is null",mTestPlus);
        assertNotNull("Button minus is null",mTestMinus);
        assertNotNull("Button multiply is null",mTestMultiply);
        assertNotNull("Button divide is null",mTestDivide);
        assertNotNull("Button equals is null",mTestEquals);
        assertNotNull("Button dot is null",mTestDot);
        assertNotNull("Button clear is null",mTestClear);
    }

    /*
    Tests basic arithmetic operations
     */
    @SmallTest
    public void testBasicArithmetic() {
        TouchUtils.clickView(this,mTestOne);
        TouchUtils.clickView(this,mTestEight);
        TouchUtils.clickView(this,mTestPlus);
        TouchUtils.clickView(this,mTestTwo);
        TouchUtils.clickView(this,mTestEquals);
        assertEquals("Incorrect output",18+2,Integer.parseInt(outputTV.getText().toString()));
    }

    /*
    Tests operations having different arithmetic precedence
     */
    @SmallTest
    public void testDifferentArithmeticPrecedence() {
        TouchUtils.clickView(this,mTestOne);
        TouchUtils.clickView(this,mTestEight);
        TouchUtils.clickView(this,mTestPlus);
        TouchUtils.clickView(this,mTestTwo);
        TouchUtils.clickView(this,mTestMultiply);
        TouchUtils.clickView(this,mTestFour);
        TouchUtils.clickView(this,mTestEquals);
        assertEquals("Incorrect output",18+2*4,Integer.parseInt(outputTV.getText().toString()));

    }

    /*
    Tests operations having same arithmetic precedence
     */
    @SmallTest
    public void testSameArithmeticPrecedence() {
        TouchUtils.clickView(this,mTestOne);
        TouchUtils.clickView(this,mTestEight);
        TouchUtils.clickView(this,mTestMinus);
        TouchUtils.clickView(this,mTestTwo);
        TouchUtils.clickView(this,mTestPlus);
        TouchUtils.clickView(this,mTestFour);
        TouchUtils.clickView(this,mTestEquals);
        assertEquals("Incorrect output",18-2+4,Integer.parseInt(outputTV.getText().toString()));

    }

    /*
    Tests decimal operations
     */
    @SmallTest
    public void testDot() {
        TouchUtils.clickView(this,mTestOne);
        TouchUtils.clickView(this,mTestDot);
        TouchUtils.clickView(this,mTestEight);
        TouchUtils.clickView(this,mTestMinus);
        TouchUtils.clickView(this,mTestTwo);
        TouchUtils.clickView(this,mTestMultiply);
        TouchUtils.clickView(this,mTestFour);
        TouchUtils.clickView(this,mTestEquals);
        assertEquals("Incorrect output",1.8-2*4,Double.parseDouble(outputTV.getText().toString()));
    }

    /*
    Tests if output textfield is cleared when clear button is clicked
     */
    @SmallTest
    public void testClear() {
        TouchUtils.clickView(this,mTestOne);
        TouchUtils.clickView(this,mTestClear);
        assertEquals("Incorrect output","",outputTV.getText().toString());

    }
}
