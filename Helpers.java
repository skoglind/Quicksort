import java.util.Random;

/**
 * Hjälpklass med alla metoder.
 * @author Fredrik Skoglind
 */
public class Helpers {
    private Random rnd;

    public Helpers() {
        rnd = new Random();
    }

    /**
     * Byt plats på två element i arrayen.
     * @param v         Arrayen att jobba med
     * @param i         Element 1
     * @param j         Element 2
     */
    public void swap(int[] v, int i, int j) {
        int temp = v[i];
        v[i] = v[j];
        v[j] = temp;
    }

    /**
     * Slumpmässigt tal mellan två värden.
     * @param first     Första värdet
     * @param last      Sista värdet
     */
    public int randomInt(int first, int last) {
        return rnd.nextInt((last-first)+1) + first;
    }

    /**
     * Kolla om listan redan är sorterad.
     * @param v         Arrayen att jobba med
     * @return          Sant/Falskt
     */
    public boolean isSorted(int[] v) {
        for(int i = 0; i < v.length-1; ++i) {
            if(v[i] > v[i+1]) { return false; }
        }

        return true;
    }

    /**
     * Kolla om listan redan är bakvänt sorterad.
     * @param v         Arrayen att jobba med
     * @return          Sant/Falskt
     */
    public boolean isReversed(int[] v) {
        for(int i = 0; i < v.length-1; ++i) {
            if(v[i] < v[i+1]) { return false; }
        }

        return true;
    }

    /**
     * Vänd på listan.
     * @param v         Arrayen att jobba med
     */
    public void reverse(int[] v) {
        for(int i = 0; i < (v.length/2); ++i) {
            this.swap(v, i, (v.length-(i+1)));
        }
    }

    /**
     * Returnera ett fixed pivot-värde.
     * @param v         Arrayen att jobba med
     * @param first     Första elementet
     * @param last      Sista elementet
     * @return          Pivot-värde
     */
    public int fixedPivotLast(int[] v, int first, int last) {
        return v[last];
    }

    /**
     * Returnera ett random pivot-värde.
     * @param v         Arrayen att jobba med
     * @param first     Första elementet
     * @param last      Sista elementet
     * @return          Pivot-värde
     */
    public int randomPivot(int[] v, int first, int last) {
        int pivotIndex = this.randomInt(first, last);
        int pivotValue = v[pivotIndex];
        this.swap(v, last, pivotIndex);

        return pivotValue;
    }

    /**
     * Returnera ett median pivot-värde, med random värden.
     * @param v         Arrayen att jobba med
     * @param first     Första elementet
     * @param last      Sista elementet
     * @return          Pivot-värde
     */
    public int randomMiddlePivot(int[] v, int first, int last) {
        int pivotIndex1 = this.randomInt(first, last);
        int pivotIndex2 = this.randomInt(first, last);
        int pivotIndex3 = this.randomInt(first, last);

        int selectedPivot;

        if ( v[pivotIndex1] > v[pivotIndex2] && v[pivotIndex1] > v[pivotIndex3] ) {
            selectedPivot = v[pivotIndex1];
            this.swap(v, last, pivotIndex1);
        } else if ( v[pivotIndex2] > v[pivotIndex1] && v[pivotIndex2] > v[pivotIndex3] ) {
            selectedPivot = v[pivotIndex2];
            this.swap(v, last, pivotIndex2);
        } else {
            selectedPivot = v[pivotIndex3];
            this.swap(v, last, pivotIndex3);
        }

        return selectedPivot;
    }

    /**
     * Returnera ett median pivot-värde.
     * @param v         Arrayen att jobba med
     * @param first     Första elementet
     * @param last      Sista elementet
     * @return          Pivot-värde
     */
    public int middlePivot(int[] v, int first, int last) {
        int pivotIndex1 = first;
        int pivotIndex2 = (last-first)/2;
        int pivotIndex3 = last;

        int selectedPivot = v[pivotIndex3];

        if ( v[pivotIndex1] > v[pivotIndex2] && v[pivotIndex1] > v[pivotIndex3] ) {
            selectedPivot = v[pivotIndex1];
            this.swap(v, last, pivotIndex1);
        } else if ( v[pivotIndex2] > v[pivotIndex1] && v[pivotIndex2] > v[pivotIndex3] ) {
            selectedPivot = v[pivotIndex2];
            this.swap(v, last, pivotIndex2);
        }

        return selectedPivot;
    }

    /**
     * Möblerar om arrayen, mindre till vänster och större till höger om pivot,
     * hanterar dubletter genom att hoppa över dem (Efter Stefans exempel).
     * @param v         Arrayen att jobba med
     * @param first     Första elementet
     * @param last      Sista elementet
     */
    public int[] partition_cc(int[] v, int first, int last, int pivot) {
        int low = first;
        int mid = first;
        int high = last;

        while (mid <= high) {
            int a = v[mid];
            if (a < pivot) {
                this.swap(v, mid, low);
                low++; mid++;
            } else if (a == pivot) {
                mid++;
            } else {
                this.swap(v, mid, high);
                v[high] = a;
                high--;
            }
        }

        return new int[]{low, high};
    }

     /**
     * Möblerar om arrayen, mindre till vänster och större till höger om pivot.
     * @param v         Arrayen att jobba med
     * @param first     Första elementet
     * @param last      Sista elementet
     */
    public int partition(int[] v, int first, int last, int pivot) {
        int position = first-1;

        for(int j = first; j < last; ++j){
            if(v[j] <= pivot) {
                position = position+1;
                this.swap(v, position, j);
            }
        }
        this.swap(v, position+1, last);

        return position+1;
    }
}