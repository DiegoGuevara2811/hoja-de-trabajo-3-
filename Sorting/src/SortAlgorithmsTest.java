import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class SortAlgorithmsTest {

    // Método para evaluar si la lista está ordenada correctamente
    private boolean evaluate(List<Integer> original, List<Integer> sorted) {
        List<Integer> expected = new ArrayList<>(original);
        Collections.sort(expected);
        return expected.equals(sorted);
    }

    // Método auxiliar para generar listas aleatorias
    private List<Integer> generateRandomList(int size) {
        Random rand = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(rand.nextInt(10000)); // Números entre 0 y 9999
        }
        return list;
    }

    // Prueba para Insertion Sort
    @Test
    void testInsertionSort() {
        List<Integer> list = generateRandomList(100);
        List<Integer> copy = new ArrayList<>(list);
        SortAlgorithms.insertionSort(copy);
        assertTrue(evaluate(list, copy), "Insertion Sort falló");
    }

    // Prueba para Merge Sort
    @Test
    void testMergeSort() {
        List<Integer> list = generateRandomList(100);
        List<Integer> copy = new ArrayList<>(list);
        SortAlgorithms.mergeSort(copy);
        assertTrue(evaluate(list, copy), "Merge Sort falló");
    }

    // Prueba para Quick Sort
    @Test
    void testQuickSort() {
        List<Integer> list = generateRandomList(100);
        List<Integer> copy = new ArrayList<>(list);
        SortAlgorithms.quickSort(copy, 0, copy.size() - 1);
        assertTrue(evaluate(list, copy), "Quick Sort falló");
    }

    // Prueba para Radix Sort
    @Test
    void testRadixSort() {
        List<Integer> list = generateRandomList(100);
        List<Integer> copy = new ArrayList<>(list);
        SortAlgorithms.radixSort(copy);
        assertTrue(evaluate(list, copy), "Radix Sort falló");
    }

    // Prueba para Bucket Sort
    @Test
    void testBucketSort() {
        List<Integer> list = generateRandomList(100);
        List<Integer> copy = new ArrayList<>(list);
        SortAlgorithms.bucketSort(copy);
        assertTrue(evaluate(list, copy), "Bucket Sort falló");
    }

    // Prueba para Heap Sort
    @Test
    void testHeapSort() {
        List<Integer> list = generateRandomList(100);
        List<Integer> copy = new ArrayList<>(list);
        SortAlgorithms.heapSort(copy);
        assertTrue(evaluate(list, copy), "Heap Sort falló");
    }
}
