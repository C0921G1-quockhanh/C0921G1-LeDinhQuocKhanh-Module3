package bai_tap;

public class QuadraticEquation {
    public static void main(String[] args) {

    }

    public static void quadraticEquation(double a, double b, double c) {
        double delta = b*b - 4*a*c;

        if (delta > 0) {
            double root1 = (-b + Math.sqrt(delta)) / (2*a);
            double root2 = (-b - Math.sqrt(delta)) / (2*a);
            System.out.println("The equation has two roots: root1 = " + root1 + " and root2 = " + root2);
        } else if (delta == 0) {
            double root = (-b) / (2*a);
            System.out.println("The equation has one root: " + root);
        } else {
            System.out.println("The equation is wrong!");
        }
    }
}
