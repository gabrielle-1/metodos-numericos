import java.text.DecimalFormat;
import java.util.ArrayList;

import static java.lang.Double.NaN;

public class LeastSquaresMethod {

    private ArrayList<Double> x;
    private ArrayList<Double> y;
    private ArrayList<Double> xSquared = new ArrayList<>();
    private ArrayList<Double> ySquared = new ArrayList<>();
    private ArrayList<Double> xy = new ArrayList<>();
    int length = 0;
    int params = 2;
    double sumx = 0, sumy = 0, sumxSquared = 0, sumySquared = 0, sumxy = 0;

    public LeastSquaresMethod(ArrayList<Double> x, ArrayList<Double> y) {
        this.x = x;
        this.y = y;
        this.length = x.size();
    }

    public void calculate(){
        if (this.length > 0) {

            for (int i = 0; i < length; i++) {
                xSquared.add(Math.pow(x.get(i), 2));
                ySquared.add(Math.pow(y.get(i), 2));
                xy.add(x.get(i) * y.get(i));
            }

            for (int i = 0; i < length; i++) {
                sumx += x.get(i);
                sumy += y.get(i);
                sumxSquared += xSquared.get(i);
                sumySquared += ySquared.get(i);
                sumxy += xy.get(i);
            }

            AngularCoefficient angularCoefficient = new AngularCoefficient(this.length, this.sumxy, this.sumx,
                    this.sumy, this.sumxSquared);

            var valAngularCoefficient = angularCoefficient.calculate();

            Intercept intercept = new Intercept(this.length, this.sumx, this.sumy, angularCoefficient.calculate());

            var valIntercept = intercept.calculate();

            RSquared rSquared = new RSquared(this.sumy, angularCoefficient.calculate(), intercept.calculate(),
                    this.x, this.y, this.length);

            var valRSquared = rSquared.calculate();

            System.out.println(valRSquared);

            System.out.println(calculateR(sumy, angularCoefficient.calculate(), intercept.calculate()));

            showFormula(angularCoefficient.calculate(), intercept.calculate(), rSquared.calculate());
        }
    }

    private double calculateAngularCoefficient (int length, double sumxy, double sumxi, double sumyi, double sumx2){
        double result = 0.0;
        if (length > 0)
            result = ((length * sumxy - (sumxi * sumyi) ) / (length * sumx2 - Math.pow(sumxi, 2)));

        return result;
    }

    private double calculateIntercept (double sumyi, double angularCoefficient, double sumxi, int length){
        double result = 0.0;
        if (length > 0)
            result = (sumyi - angularCoefficient * sumxi) / length;

        return result;
    }

    private void showFormula (double angularCoefficient, double intercept, double R){
        String result = "";
        DecimalFormat df = new DecimalFormat("#.##");

        if (intercept != NaN && intercept != NaN) {
            System.out.println("y=" + df.format(intercept) + "+" + df.format(angularCoefficient) + "x");
        }

        if (R != NaN && R != NaN) {
            System.out.println("R² = " + df.format(R));
        }
    }

    private double calculateR (double sumyi, double angularCoefficient, double intercept){
        double result = 0;
        double yIdeal = sumyi / this.length;
        double sumYiLessYIdeal2 = 0.0;
        double sumYiLessYIdeal = 0.0;
        for (int i = 0; i < this.length; i++) {
            double x = this.x.get(i);
            double y = this.y.get(i);
            double yiLessYIdeal = intercept + angularCoefficient * x;
            sumYiLessYIdeal2 += Math.pow((y - yIdeal),2);
            sumYiLessYIdeal += (y - yiLessYIdeal) * (y - yiLessYIdeal);
        }

        // Calculate R^2
        double rSquared = 1 - (sumYiLessYIdeal / sumYiLessYIdeal2);

        if (rSquared != NaN) {
            result += rSquared;
        }
        return result;
    }

    public double calculateAIC() {
        double SQR = 0;

        for (int i = 0; i < this.length; i++) {
            SQR += Math.pow(this.y.get(i), 2);
        }
        SQR /= this.length;

        return this.length * Math.log(SQR) + 2 * this.params;
    }
}
