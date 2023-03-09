import static java.lang.Double.NaN;

public class Intercept {
    private int length = 0;
    private double sumx, sumy, angularCoefficient;

    public Intercept(int length, double sumx, double sumy, double angularCoefficient){
        this.length = length;
        this.sumy = sumy;
        this.sumx = sumx;
        this.angularCoefficient = angularCoefficient;
    }

    public double calculate(){
        double result = 0.0;
        boolean validateValues = this.validateValues();
        if (validateValues)
            result = (sumy - angularCoefficient * sumx) / length;
        return result;
    }

    private boolean validateValues(){
        boolean lengthIsValid = this.length != NaN && this.length != 0 && !Double.isInfinite(this.length) && this.length > 0;
        boolean sumxIsValid = this.sumx != NaN && this.sumx != 0 && !Double.isInfinite(this.sumx);
        boolean sumyIsValid = this.sumy != NaN && this.sumy != 0 && !Double.isInfinite(this.sumy);
        boolean angularCoefficientIsValid = this.angularCoefficient != NaN && this.angularCoefficient != 0 && !Double.isInfinite(this.angularCoefficient);
        return lengthIsValid && sumxIsValid && sumyIsValid && angularCoefficientIsValid;
    }
}
