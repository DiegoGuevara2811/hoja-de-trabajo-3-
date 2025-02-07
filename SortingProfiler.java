import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class SortingProfiler {
    private static final String FILE_NAME = "numbers.txt";

    // Intervalos corregidos hasta 3000 elementos
    private static final int[] INTERVALS = {10, 50, 100, 500, 1000, 2000, 3000};

    public static List<Integer> readNumbers() {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                numbers.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            System.err.println("❌ [" + Instant.now() + "] Error al leer el archivo: " + e.getMessage());
        }
        return numbers;
    }

    public static void profileSortingAlgorithms() {
        List<Integer> numbers = readNumbers();
        System.out.println("⚡ [" + Instant.now() + "] Iniciando pruebas con Profiler en IntelliJ...");

        // **Asegurar que todos los métodos sean detectados**
        for (int size : INTERVALS) {
            if (size > numbers.size()) break;
            List<Integer> subset = new ArrayList<>(numbers.subList(0, size));

            // Forzar la ejecución en el Profiler
            runSortTest("Insertion Sort", new ArrayList<>(subset), SortAlgorithms::insertionSort);
            runSortTest("Merge Sort", new ArrayList<>(subset), SortAlgorithms::mergeSort);
            runSortTest("Quick Sort", new ArrayList<>(subset), list -> SortAlgorithms.quickSort(list, 0, list.size() - 1));
            runSortTest("Radix Sort", new ArrayList<>(subset), SortAlgorithms::radixSort);
            runSortTest("Bucket Sort", new ArrayList<>(subset), SortAlgorithms::bucketSort);
            runSortTest("Heap Sort", new ArrayList<>(subset), SortAlgorithms::heapSort);
        }

        System.out.println("\n✅ [" + Instant.now() + "] Pruebas finalizadas. ");
    }

    private static void runSortTest(String algorithmName, List<Integer> subset, SortMethod method) {
        System.out.println("🔹 📏 [" + Instant.now() + "] Probando " + algorithmName + " con " + subset.size() + " elementos...");
        method.sort(subset);
        System.out.println("✅ [" + Instant.now() + "] " + algorithmName + " finalizado para " + subset.size() + " elementos\n");

        // **Forzar visibilidad en el Profiler con pausa mínima**
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            System.err.println("❌ [" + Instant.now() + "] Error en la pausa: " + e.getMessage());
        }
    }

    @FunctionalInterface
    interface SortMethod {
        void sort(List<Integer> list);
    }
}
