import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class NumberGenerator {
    private static final int SIZE = 3000;
    private static final String FILE_NAME = "numbers.txt";

    public static void generateNumbers() {
        Random rand = new Random();
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (int i = 0; i < SIZE; i++) {
                writer.write(rand.nextInt(10000) + "\n");
            }
            System.out.println("Archivo generado con 3000 números aleatorios.");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
}
