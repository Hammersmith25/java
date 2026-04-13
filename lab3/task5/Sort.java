public class Sort {
    private Sort() {
    }

    public static <E> void swap(E[] array, int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static <E extends Comparable<E>> void bubbleSort(E[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    public static <E extends Comparable<E>> void mergeSort(E[] array) {
        if (array.length < 2) {
            return;
        }

        int middle = array.length / 2;
        E[] left = java.util.Arrays.copyOfRange(array, 0, middle);
        E[] right = java.util.Arrays.copyOfRange(array, middle, array.length);

        mergeSort(left);
        mergeSort(right);
        merge(array, left, right);
    }

    private static <E extends Comparable<E>> void merge(E[] target, E[] left, E[] right) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.length && j < right.length) {
            if (left[i].compareTo(right[j]) <= 0) {
                target[k++] = left[i++];
            } else {
                target[k++] = right[j++];
            }
        }

        while (i < left.length) {
            target[k++] = left[i++];
        }

        while (j < right.length) {
            target[k++] = right[j++];
        }
    }
}
