import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Archive archive = new Archive();
        ArrayList<String> valuesString;
        ArrayList<Double> xi = new ArrayList<>();
        ArrayList<Double> yi = new ArrayList<>();

        try {
            valuesString = archive.read("C:\\\\Users\\\\gabri\\\\OneDrive\\\\Documentos\\\\Programacao\\\\metodos-numericos\\\\src\\\\main\\\\resources\\\\file.txt");
            for (String valor : valuesString) {
                String[] split = valor.split(" ");

                double valorx = Double.parseDouble(split[0].replace(",", "."));
                double valory = Double.parseDouble(split[1].replace(",", "."));

                xi.add(valorx);
                yi.add(valory);
            }

            LeastSquaresMethod leastSquaresMethod = new LeastSquaresMethod(xi, yi);
            leastSquaresMethod.calculate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
