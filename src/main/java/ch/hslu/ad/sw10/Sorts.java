package ch.hslu.ad.sw10;

public final class Sorts {

    private static long qsCmpCount = 0;
    private static long hsCmpCount = 0;

    private static void swap(final int[] a, final int i, final int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

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

            cmpCount++;
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
            swap(a, maxpos, i);

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
                    swap(a, j, j + 1);
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
                    swap(a, j, j + 1);
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

    private static int qsPartition(
            final int[] a, final int start, final int end, final boolean showAnimation) {
        final int pivot = a[start];
        int i = start - 1;
        int j = end + 1;

        while (true) {
            do {
                i++;

                qsCmpCount++;
                if (showAnimation) {
                    SortingAnimation.instance().showArray(a, 20, i);
                }
            } while (a[i] < pivot);

            do {
                j--;

                qsCmpCount++;
                if (showAnimation) {
                    SortingAnimation.instance().showArray(a, 20, j);
                }
            } while (a[j] > pivot);

            if (i >= j) {
                return j;
            }

            swap(a, i, j);
        }
    }

    private static int qsPartitionRandom(
            final int[] a, final int start, final int end, final boolean showAnimation) {
        final int randomIdx = start + (int) (Math.random() * (end - start + 1));
        swap(a, start, randomIdx);

        final int pivot = a[start];
        int i = start - 1;
        int j = end + 1;

        while (true) {
            do {
                i++;

                qsCmpCount++;
                if (showAnimation) {
                    SortingAnimation.instance().showArray(a, 20, i);
                }
            } while (a[i] < pivot);

            do {
                j--;

                qsCmpCount++;
                if (showAnimation) {
                    SortingAnimation.instance().showArray(a, 20, j);
                }
            } while (a[j] > pivot);

            if (i >= j) {
                return j;
            }

            swap(a, i, j);
        }
    }

    private static void quickSort(
            final int[] a,
            final int start,
            final int end,
            final boolean randomPivot,
            final boolean showAnimation) {
        if (start < end) {
            int p;
            if (randomPivot) {
                p = qsPartitionRandom(a, start, end, showAnimation);
            } else {
                p = qsPartition(a, start, end, showAnimation);
            }
            quickSort(a, start, p, randomPivot, showAnimation);
            quickSort(a, p + 1, end, randomPivot, showAnimation);
        }
    }

    public static long quickSort(
            final int[] a, final boolean randomPivot, final boolean showAnimation) {
        qsCmpCount = 0;

        final int start = 0;
        final int end = a.length - 1;

        if (start < end) {
            int p;
            if (randomPivot) {
                p = qsPartitionRandom(a, start, end, showAnimation);
            } else {
                p = qsPartition(a, start, end, showAnimation);
            }
            quickSort(a, start, p, randomPivot, showAnimation);
            quickSort(a, p + 1, end, randomPivot, showAnimation);
        }

        return qsCmpCount;
    }

    private static void heapify(
            final int[] a, final int left, final int right, final boolean showAnimation) {
        // idx of first child
        int k = 2 * left + 1;

        // abort if out of sub array
        if (k > right) {
            if (showAnimation) {
                SortingAnimation.instance().showArray(a, 20, k);
            }
            return;
        }

        // exactly one child
        if (k + 1 > right) {
            if (a[left] < a[k]) {
                swap(a, left, k);
            }

            hsCmpCount++;
            if (showAnimation) {
                SortingAnimation.instance().showArray(a, 20, k);
            }
            return;
        }

        // two children
        if (a[k] < a[k + 1]) {
            k++;
        }
        hsCmpCount++;
        if (showAnimation) {
            SortingAnimation.instance().showArray(a, 20, k);
        }

        if (a[left] < a[k]) {
            swap(a, left, k);
            heapify(a, k, right, showAnimation);
        }

        hsCmpCount++;
        if (showAnimation) {
            SortingAnimation.instance().showArray(a, 20, k);
        }
    }

    public static long heapSort(final int[] a, final boolean showAnimation) {
        hsCmpCount = 0;

        final int n = a.length;
        int left = n / 2;
        int right = n - 1;

        // phase 1: build heap
        while (left > 0) {
            left = left - 1;
            heapify(a, left, right, showAnimation);
        }

        // phase 2: use heap to get max and put it to end
        while (right > 0) {
            swap(a, right, left);
            right--;
            heapify(a, left, right, showAnimation);
        }

        return hsCmpCount;
    }
}
