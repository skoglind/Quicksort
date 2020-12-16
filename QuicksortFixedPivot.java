/**
 * QuicksortFixedPivot - Sorteringsalgoritm.
 * @author Fredrik Skoglind
 */
public class QuicksortFixedPivot implements IntSorter {
    private Helpers h;

    public QuicksortFixedPivot() {
        h = new Helpers();
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
    private void qs(int[] v, int first, int last){
        // Basfall, vid bara två värden
        if(first >= last) { return; }

        int pivot = h.fixedPivotLast(v, first, last);
        int[] mid = h.partition_cc(v, first, last, pivot);

        qs(v, first, mid[0]-1);
        qs(v, mid[1], last);
    }
 }