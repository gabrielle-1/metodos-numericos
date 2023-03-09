import static java.lang.Double.NaN;

public class AngularCoefficient {

    private int length = 0;
    private double sumxy, sumx, sumy, sumxSquared;

    public AngularCoefficient(int length, double sumxy, double sumx, double sumy, double sumxSquared){
        this.length = length;
        this.sumxy = sumxy;
        this.sumx = sumx;
        this.sumy = sumy;
        this.sumxSquared = sumxSquared;
    }

    public double calculate(){
        double result = 0.0;
        boolean validateValues = this.validateValues();
        if (validateValues)
            result = ((this.length * this.sumxy - (this.sumx * this.sumy) ) / (this.length * this.sumxSquared - Math.pow(this.sumx, 2)));
        return result;
    }

    public boolean validateValues(){
        boolean lengthIsValid = this.length != NaN && this.length != 0 && !Double.isInfinite(this.length) && this.length > 0;
        boolean sumxyIsValid = this.sumxy != NaN && this.sumxy != 0 && !Double.isInfinite(this.sumxy);
        boolean sumxIsValid = this.sumx != NaN && this.sumx != 0 && !Double.isInfinite(this.sumx);
        boolean sumyIsValid = this.sumy != NaN && this.sumy != 0 && !Double.isInfinite(this.sumy);
        boolean sumxSquaredIsValid = this.sumxSquared != NaN && this.sumxSquared != 0 && !Double.isInfinite(this.sumxSquared);
        return lengthIsValid && sumxyIsValid && sumxIsValid && sumyIsValid && sumxSquaredIsValid;
    }

}
