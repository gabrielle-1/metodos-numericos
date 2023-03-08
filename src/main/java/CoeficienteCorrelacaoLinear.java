import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static java.lang.Double.NaN;

public class CoeficienteCorrelacaoLinear {

    private ArrayList<Double> xi = new ArrayList<>();
    private ArrayList<Double> yi = new ArrayList<>();
    private ArrayList<Double> values;

    private ArrayList<Double> x2 = new ArrayList<>();
    private ArrayList<Double> y2 = new ArrayList<>();
    private ArrayList<Double> xy = new ArrayList<>();
    int length = 0;
    double x = 0.0, y = 0.0;
    double sumxi = 0, sumyi = 0, sumx2 = 0, sumy2 = 0, sumxy = 0;

    public CoeficienteCorrelacaoLinear(ArrayList<Double> xi, ArrayList<Double> yi) {
        this.xi = xi;
        this.yi = yi;
        this.length = xi.size();
    }

    public void calculate(){
        if (this.length > 0) {

            for (int i = 0; i < length; i++) {
                x2.add(Math.pow(xi.get(i), 2));
                y2.add(Math.pow(yi.get(i), 2));
                xy.add(xi.get(i) * yi.get(i));
            }

            for (int i = 0; i < length; i++) {
                sumxi += xi.get(i);
                sumyi += yi.get(i);
                sumx2 += x2.get(i);
                sumy2 += y2.get(i);
                sumxy += xy.get(i);
            }

            double angularCoefficient = calculateAngularCoefficient(length, sumxy, sumxi, sumyi, sumx2);
            double intercept = calculateIntercept(sumyi, angularCoefficient, sumxi, length);
            calculateR(sumyi, angularCoefficient, intercept);
            System.out.println(showFormula(angularCoefficient, intercept));
            System.out.println();
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

    private String showFormula (double angularCoefficient, double intercept){
        String result = "";
        if (intercept != NaN && intercept != NaN) {
            DecimalFormat df = new DecimalFormat("#.##");
            result = "y=" + df.format(intercept) + "+" + df.format(angularCoefficient) + "x";
        }
        return result;
    }

    private void calculateR (double sumyi, double angularCoefficient, double intercept){
        double result = 0;
        double yIdeal = sumyi / this.length;
        double sumYiLessYIdeal2 = 0.0;
        double sumYiLessYIdeal = 0.0;
        for (int i = 0; i < this.length; i++) {
            double x = this.xi.get(i);
            double y = this.yi.get(i);
            double yiLessYIdeal = intercept + angularCoefficient * x;
            sumYiLessYIdeal2 += Math.pow((y - yIdeal),2);
            sumYiLessYIdeal += (y - yiLessYIdeal) * (y - yiLessYIdeal);
        }

        // Calculate R^2
        double rSquared = 1 - (sumYiLessYIdeal / sumYiLessYIdeal2);

        if (rSquared != NaN) {
            result += rSquared;
        }
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("R² = " + df.format(result));
    }
}
