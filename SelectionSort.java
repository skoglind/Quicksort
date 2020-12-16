/**
 * SelectionSort - Sorteringsalgoritm.
 * @author Fredrik Skoglind
 */
public class SelectionSort implements IntSorter {
    private Helpers h;

    public SelectionSort() {
        h = new Helpers();
    }

    public void sort(int[] v) {        
        for(int i = 0; i < v.length; ++i) {
            int min = i;
            for(int j = i+1; j < v.length; ++j) {
                if(v[j] < v[min]) {
                    min = j;
                }
                
                h.swap(v, i, min);
            }
            
        }
    }
}