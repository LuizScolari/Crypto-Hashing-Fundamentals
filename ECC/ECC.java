package ECC;

public class ECC {
    // yˆ2 = xˆ3 + ax + b
    private double a;
    private double b;

    public ECC(double a, double b){
        this.a = a;
        this.b = b;
    }

    public Point doubleAndAdd(int n, Point p){
        Point temp = new Point(p.getX(), p.getY());

        String nBinary = Integer.toBinaryString(n);

        for(int i=1; i<nBinary.length(); ++i) {
            int actualBit = Integer.parseInt(""+nBinary.charAt(i));

            temp = pointAddition(temp, temp);

            if(actualBit == 1){
                temp = pointAddition(temp, temp);
            }
        }
        
        return temp;
    }

    public Point pointAddition(Point p, Point q){
        double x1 = p.getX();
        double y1 = p.getY();
        double x2 = q.getX();
        double y2 = q.getY();

        double m = 0;

        if(Double.compare(x1, x2) == 0 && Double.compare(y1, y2) == 0){
            m = (3*x1*x1+a) / (2*y1);
        } else {
            m = (y2 - y1) / (x2 - x1);
        }

        // point R(x,y)
        double x3 = m*m - x2 - x1;
        double y3 = m*(x1-x3) - y1;

        return new Point(x3, y3);
    }
}
