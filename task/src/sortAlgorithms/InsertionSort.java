package sortAlgorithms;

public class InsertionSort extends SortAlgorithm {

    public static int[] insertionSort(int[] array) {
        /* iterating over elements in the unsorted part */
        for (int i = 1; i < array.length; i++) {
            int elem = array[i]; // take the next element
            int j = i - 1;

            /* find a suitable position to insert and shift elements to the right */
            while (j >= 0 && array[j] > elem) {
                array[j + 1] = array[j]; // shifting
                j--;
            }
            array[j + 1] = elem; // insert the element in the found position in the sorted part
        }

        return array;
    }

    @Override
    int[] sort(int[] array) {
        return insertionSort(array);
    }
}
