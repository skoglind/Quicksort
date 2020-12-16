/**
 * QuickSort - Sorteringsalgoritm.
 * @author Fredrik Skoglind
 */
public class QuickSort implements IntSorter {
    private Helpers h;
    private InsertionSort iSort;
    private int cutoff = 7;

    public QuickSort() {
        h = new Helpers();
        iSort = new InsertionSort();
    }

    /**
     * Sorterar arrayen i stigande ordning.
     * @param v         Array som ska sorteras 
     */
    public void sort(int[] v) {
        qs(v, 0, v.length-1);
    }

    /**
     * QuickSort algoritm. 
     * @param v         Array som ska sorteras    
     * @param first     Första värdet att jobba med
     * @param last      Sista värdet att jobba med
     */
    private void qs(int[] v, int first, int last) {
        if( (last-first) < cutoff ) {
            iSort.sort(v, first, last);
        } else {
            int pivot = h.randomMiddlePivot(v, first, last);
            int[] mid = h.partition_cc(v, first, last, pivot);
            qs(v, first, mid[0]-1);
            qs(v, mid[1], last);
        }
    }
}