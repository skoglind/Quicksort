/**
 * InsertionSort - Sorteringsalgoritm.
 * @author Fredrik Skoglind
 */
public class InsertionSort implements IntSorter {
    private Helpers h;

    public InsertionSort() {
        h = new Helpers();
    }

    public void sort(int[] v) {
        for(int i = 1; i < v.length; ++i) {
            int j = i;

            while(j > 0 && v[j-1] > v[j]) {
                h.swap(v, j, j-1);
                j=j-1;
            }
        }
    }

    public void sort(int[] v, int first, int last) {
        for(int i = first+1; i < last+1; ++i) {
            int j = i;

            while(j > 0 && v[j-1] > v[j]) {
                h.swap(v, j, j-1);
                j=j-1;
            }
        }
    }
}