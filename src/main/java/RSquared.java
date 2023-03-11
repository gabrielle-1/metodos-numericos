import java.util.ArrayList;

import static java.lang.Double.NaN;

public class RSquared {

    private double sumy, angularCoefficient, intercept, yIdeal, length, sumyIdeal, sumyIdealSquared, rSquared;
    private ArrayList<Double> x;
    private ArrayList<Double> y;

    public RSquared(double sumy, double angularCoefficient, double intercept, ArrayList<Double> x, ArrayList<Double> y,
                    double length){
        this.length = length;
        this.sumy = sumy;
        this.yIdeal = this.sumy / this.length;
        this.angularCoefficient = angularCoefficient;
        this.intercept = intercept;
        this.x = x;
        this.y = y;
    }

    public double calculate(){
        double result = 0;
        boolean validateValues = this.validateValues();
        if (validateValues) {
            for (int i = 0; i < this.length; i++) {
                double x = this.x.get(i);
                double y = this.y.get(i);
                double yiLessYIdeal = intercept + angularCoefficient * x;
                sumyIdealSquared += Math.pow((y - yIdeal),2);
                sumyIdeal += (y - yiLessYIdeal) * (y - yiLessYIdeal);
            }

            this.rSquared = 1 - (sumyIdeal / sumyIdealSquared);
            if (rSquared != NaN)
                result += rSquared;
        }

        return result;

    }

    private boolean validateValues(){
        boolean sumyIsValid = this.sumy != NaN && this.sumy != 0 && !Double.isInfinite(this.sumy);
        boolean angularCoefficientIsValid = this.angularCoefficient != NaN && this.angularCoefficient != 0 && !Double.isInfinite(this.angularCoefficient);
        boolean interceptIsValid = this.intercept != NaN && this.intercept != 0 && !Double.isInfinite(this.intercept);
        return sumyIsValid && angularCoefficientIsValid && interceptIsValid;
    }

}
