package ch.hslu.ad.sw09;

public final class Sorts {

    public static long insertionSort(final int[] array, final boolean showAnimation) {
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

                cmpCount++;
                if (showAnimation) {
                    SortingAnimation.instance().showArray(array, 20, j);
                }
            }

            array[j] = element;

            if (showAnimation) {
                SortingAnimation.instance().showArray(array, 20, j);
            }
        }

        return cmpCount;
    }

    public static long selectionSort(final int[] a, final boolean showAnimation) {
        long cmpCount = 0;

        final int n = a.length;
        for (int i = n - 1; i >= 0; i--) {
            int maxpos = i; // Position des grössten Elements

            for (int j = i; j >= 0; j--) {
                if (a[j] > a[maxpos]) { // grösseres Element gefunden?
                    maxpos = j; // dann Position aktualisieren
                }

                cmpCount++;
                if (showAnimation) {
                    SortingAnimation.instance().showArray(a, 20, j);
                }
            }

            // kleinstes Element nach vorn tauschen
            int temp = a[maxpos];
            a[maxpos] = a[i];
            a[i] = temp;

            if (showAnimation) {
                SortingAnimation.instance().showArray(a, 20, maxpos);
            }
        }

        return cmpCount;
    }

    public static long bubbleSort(final int[] a, final boolean showAnimation) {
        long cmpCount = 0;

        final int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            // n - 1 for index starting at 0,
            // -1 additionally because last element already in order at this point
            for (int j = 0; j < (n - 1) - i; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }

                cmpCount++;
                if (showAnimation) {
                    SortingAnimation.instance().showArray(a, 20, j);
                }
            }
        }

        return cmpCount;
    }

    public static long bubbleSort2(final int[] a, final boolean showAnimation) {
        long cmpCount = 0;

        final int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            // n - 1 for index starting at 0,
            // -1 additionally because last element already in order at this point
            boolean isSwapped = false;
            for (int j = 0; j < (n - 1) - i; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    isSwapped = true;
                }

                cmpCount++;
                if (showAnimation) {
                    SortingAnimation.instance().showArray(a, 20, j);
                }
            }

            if (!isSwapped) break;
        }

        return cmpCount;
    }
}
