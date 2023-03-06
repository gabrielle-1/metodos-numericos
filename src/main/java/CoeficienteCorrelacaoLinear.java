import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Double.NaN;

public class CoeficienteCorrelacaoLinear {

    public static void main(String[] args) {

        Archive archive = new Archive();

        ArrayList<Double> xi = new ArrayList<>();
        ArrayList<Double> yi = new ArrayList<>();
        ArrayList<String> values;

        ArrayList<Double> x2 = new ArrayList<>();
        ArrayList<Double> y2 = new ArrayList<>();
        ArrayList<Double> xy = new ArrayList<>();

        double x = 0.0, y = 0.0;
        int sumxi = 0, sumyi = 0, sumx2 = 0, sumy2 = 0, sumxy = 0;

        try {
            values = archive.read("C:\\Users\\gabri\\OneDrive\\Documentos\\atividade-metodos-numericos\\src\\main\\resources\\file.txt");

            for (String valor : values) {
                String[] split = valor.split(" ");
                System.out.println(values);

                double valorx = Double.parseDouble(split[1].replace("," , "."));
                double valory = Double.parseDouble(split[1].replace("," , "."));

                x = valorx;
                y = valory;

                xi.add(x);
                yi.add(y);
            }

            int length = xi.size();

            if (length > 0) {

                for (int i = 0; i < length; i++) {
                    System.out.println(xi.get(i) + " - " + yi.get(i));
                }

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
                calculateR(length, sumxy, sumxi, sumyi, sumx2, sumy2);
                System.out.println(showFormula(angularCoefficient, intercept));
                System.out.println();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Fim do programa.");
        }
    }

    private static double calculateAngularCoefficient (int length, double sumxy, double sumxi, double sumyi, double sumx2){
        double result = 0.0;
        if (length > 0)
            result = ((length * sumxy - (sumxi * sumyi) ) / (length * sumx2 - Math.pow(sumxi, 2)));

        return result;
    }

    private static double calculateIntercept (double sumyi, double angularCoefficient, double sumxi, int length){
        double result = 0.0;
        if (length > 0)
            result = (sumyi - angularCoefficient * sumxi) / length;

        return result;
    }

    private static String showFormula (double angularCoefficient, double intercept){
        String result = "";
        if (intercept != NaN && intercept != NaN) {
            result = "y=" + intercept + "+" + angularCoefficient + "x";
        }
        return result;
    }

    private static void calculateR (int length, double sumxy, double sumxi, double sumyi, double sumx2, double sumy2){
        int rxy = 0;
        String result = "Rxy=";
        if (length > 0) {
            double up =  (length * sumxi) - (sumxi * sumyi);
            double down = Math.sqrt(length * sumx2 - (Math.pow(sumxi, 2))) * (Math.sqrt(length * sumy2 - Math.pow(sumyi, 2)));
            rxy = (int) (up/down);
            if (rxy != NaN) {
                result += rxy;
            }
        }
        System.out.println(result);
    }

}
