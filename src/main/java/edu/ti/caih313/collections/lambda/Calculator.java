package edu.ti.caih313.collections.lambda;

public class Calculator {
    public static void main(String... args) {

        Calculator myApp = new Calculator();
        IntegerMath addition = Integer::sum;
        DoubleMath doubleAddition = Double::sum;
        IntegerMath subtraction = (a, b) -> a - b;
        DoubleMath doubleSubtraction = (a, b) -> a - b;
        IntegerMath multiply = (a, b) -> a * b;
        DoubleMath doubleMultiply = (a, b) -> a * b;
        IntegerMath divide = (a, b) -> a / b;
        DoubleMath doubleDivide = (a, b) -> a / b;
        UnaryMath absoluteValue = (a) -> (a >= 0) ? a : -a;
        UnaryMath inverse = (a) -> 1 / a;
        System.out.println("40 + 2 = " +
            myApp.operateBinary(40, 2, addition));
        System.out.println("40.00 + 2.00 = " +
            myApp.operateBinary(40.00, 2.00, doubleAddition));
        System.out.println("20 - 10 = " +
            myApp.operateBinary(20, 10, subtraction));
        System.out.println("20.00 - 10.00 = " +
            myApp.operateBinary(20.00, 10.00, doubleSubtraction));
        System.out.println("5 * 2 = " +
            myApp.operateBinary(5, 2, multiply));
        System.out.println("5.00 * 2.00 = " +
            myApp.operateBinary(5.00, 2.00, doubleMultiply));
        System.out.println("5 / 2 = " +
            myApp.operateBinary(5, 2, divide));
        System.out.println("5.00 / 2.00 = " +
            myApp.operateBinary(5.00, 2.00, doubleDivide));
        System.out.println("|-7| = " +
                myApp.operateBinary(-7.00, absoluteValue));
        System.out.println("5.00 inverse = " +
                myApp.operateBinary(5.00, inverse));
    }

    private int operateBinary(int a, int b, IntegerMath integerMath) {
        return integerMath.operation(a, b);
    }
    private  Double operateBinary(Double a, Double b, DoubleMath doubleMath){
        return doubleMath.operation(a, b);
    }
    private  Double operateBinary(Double a, UnaryMath unaryMath){
        return unaryMath.operation(a);
    }
}


