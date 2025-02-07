import java.util.*;

public class SortAlgorithms {

    // 🟢 Insertion Sort
    public static void insertionSort(List<Integer> list) {
        int n = list.size();
        for (int i = 1; i < n; i++) {
            int key = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
        forceProfilerDetection();
    }

    // 🟢 Merge Sort
    public static void mergeSort(List<Integer> list) {
        if (list.size() > 1) {
            int mid = list.size() / 2;
            List<Integer> left = new ArrayList<>(list.subList(0, mid));
            List<Integer> right = new ArrayList<>(list.subList(mid, list.size()));

            mergeSort(left);
            mergeSort(right);
            merge(list, left, right);
        }
        forceProfilerDetection();
    }

    private static void merge(List<Integer> list, List<Integer> left, List<Integer> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) list.set(k++, left.get(i++));
        while (j < right.size()) list.set(k++, right.get(j++));
    }

    // 🟢 Quick Sort
    public static void quickSort(List<Integer> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
        forceProfilerDetection();
    }

    private static int partition(List<Integer> list, int low, int high) {
        int pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j) < pivot) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, high);
        return i + 1;
    }

    // 🟢 Radix Sort
    public static void radixSort(List<Integer> list) {
        int max = Collections.max(list);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(list, exp);
        }
        forceProfilerDetection();
    }

    private static void countingSort(List<Integer> list, int exp) {
        int n = list.size();
        int[] output = new int[n];
        int[] count = new int[10];

        for (int num : list) {
            count[(num / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(list.get(i) / exp) % 10] - 1] = list.get(i);
            count[(list.get(i) / exp) % 10]--;
        }

        for (int i = 0; i < n; i++) {
            list.set(i, output[i]);
        }
    }

    // 🟢 Bucket Sort
    public static void bucketSort(List<Integer> list) {
        int max = Collections.max(list);
        int min = Collections.min(list);
        int bucketCount = (max - min) / 10 + 1;
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);

        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int num : list) {
            buckets.get((num - min) / 10).add(num);
        }

        list.clear();
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
            list.addAll(bucket);
        }
        forceProfilerDetection();
    }

    // 🟢 Heap Sort
    public static void heapSort(List<Integer> list) {
        int n = list.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(list, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            Collections.swap(list, 0, i);
            heapify(list, i, 0);
        }
        forceProfilerDetection();
    }

    private static void heapify(List<Integer> list, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && list.get(left) > list.get(largest)) {
            largest = left;
        }

        if (right < n && list.get(right) > list.get(largest)) {
            largest = right;
        }

        if (largest != i) {
            Collections.swap(list, i, largest);
            heapify(list, n, largest);
        }
    }

    // 🔹 Método para forzar la detección en el Profiler
    private static void forceProfilerDetection() {
        try {
            Thread.sleep(1);  // Pequeña pausa para asegurar que el Profiler detecte el método
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

