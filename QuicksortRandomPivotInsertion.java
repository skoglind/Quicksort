/**
 * QuicksortRandomPivotInsertion - Sorteringsalgoritm.
 * @author Fredrik Skoglind
 */
public class QuicksortRandomPivotInsertion implements IntSorter {
    private Helpers h;
    private InsertionSort iSort;
    private int cutoff = 9;

    public QuicksortRandomPivotInsertion() {
        h = new Helpers();
        iSort = new InsertionSort();
    }

    /**
     * Sorterate arrayen i stigande ordning.
     * @param v         Arrayen att jobba med
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
        if( (last-first) <= cutoff ) {
            iSort.sort(v, first, last);
        } else {
            int pivot = h.randomPivot(v, first, last);
            int[] mid = h.partition_cc(v, first, last, pivot);
            qs(v, first, mid[0]-1);
            qs(v, mid[1], last);
        }
    }
 }