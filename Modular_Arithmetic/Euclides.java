package Modular_Arithmetic;

public class Euclides {
    public int gcd(int a, int b){
        if (a % b == 0){
            return b;
        }
        return gcd(b, a%b);
    }
}