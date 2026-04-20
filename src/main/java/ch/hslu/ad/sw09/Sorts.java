package ch.hslu.ad.sw09;

public final class Sorts {

    static long insertionSort(final int[] array, final boolean showAnimation) {
        long cmpCount = 0;

        for (int i = 1; i < array.length; i++) {
            // Swap (not optimal) -> 3 assignments per iteration needed
            // for (int j = i; j > 0; j--) {
            //    if (array[j] < array[j - 1]) {
            //        int temp = array[j];
            //        array[j] = array[j - 1];
            //        array[j - 1] = temp;
            //
            //        SortingAnimation.instance().showArray(array, 20, j);
            //    }
            // }

            // Shift (better) -> only 1 assignment per iteration needed
            int element = array[i];
            int j = i;

            while (j > 0 && array[j - 1] > element) {
                array[j] = array[j - 1];
                j--;

                // --- Measurements ---
                cmpCount++;
                if (showAnimation) {
                    SortingAnimation.instance().showArray(array, 20, j);
                }
            }

            array[j] = element;
        }

        return cmpCount;
    }
}
