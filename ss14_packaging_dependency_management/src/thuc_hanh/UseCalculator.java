package thuc_hanh;

import java.security.NoSuchAlgorithmException;

public class UseCalculator {
    public static void main(String[] args) {
        System.out.println("Sum = " + Calculator.sum(5,9));
        System.out.println("Sub = " + Calculator.sub(5,9));
        System.out.println("Mul = " + Calculator.mul(5,9));

        try {
            System.out.println("Div = " + Calculator.divide(10,5));
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
