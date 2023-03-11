import java.util.ArrayList;

import static java.lang.Double.NaN;

public class AIC {

    private double SQR;
    private int length, params;

    ArrayList<Double> y = new ArrayList<>();

    public AIC(double SQR, int length, int params, ArrayList<Double> y){
        this.SQR = SQR;
        this.length = length;
        this.params = params;
        this.y = y;
    }

    public double calculate() {
        this.SQR = this.calculateSQR();
        double result = 0;
        boolean validateValues = this.validateValues();
        if (validateValues)
            result = this.length * Math.log(this.SQR) + 2 * this.params;

        return result;
    }

        public double calculateSQR() {
            for (int i = 0; i < this.length; i++)
                this.SQR += Math.pow(this.y.get(i), 2);

            this.SQR /= this.length;
            return this.SQR;
        }

        public boolean validateValues() {
            boolean lengthIsValid = this.length != NaN && this.length != 0 && !Double.isInfinite(this.length) && this.length > 0;
            boolean paramsIsValid = this.params != NaN && this.params != 0 && !Double.isInfinite(this.params);
            boolean SQRIsValid = this.SQR != NaN && this.SQR != 0 && !Double.isInfinite(this.SQR);
            return lengthIsValid && paramsIsValid && SQRIsValid;
        }

}
