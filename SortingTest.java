import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class SortingTest {
    private static int tests = 500;

    public static void main(String[] args) {
        Map<String, Long> timeData = new TreeMap<String, Long>();

        InsertionSort insertionSort = new InsertionSort();
        SelectionSort selectionSort = new SelectionSort();
        JavaSort javaSort = new JavaSort();
        QuicksortFixedPivot quickSortFixedPivot = new QuicksortFixedPivot();
        QuicksortRandomPivot quickSortRandomPivot = new QuicksortRandomPivot();
        QuicksortMiddlePivot quickSortMiddlePivot = new QuicksortMiddlePivot();
        QuicksortFixedPivotInsertion quickSortFixedPivotInsertion = new QuicksortFixedPivotInsertion();
        QuicksortRandomPivotInsertion quickSortRandomPivotInsertion = new QuicksortRandomPivotInsertion();
        QuicksortMiddlePivotInsertion quickSortMiddlePivotInsertion = new QuicksortMiddlePivotInsertion();
        QuickSort quickSort = new QuickSort();

        System.out.println( );
        System.out.println( "#### SORTING TEST ####" );
        System.out.println( );

        String[] orderName = {"Asc", "Desc", "Rnd"};
        int[] testSize = {2,4,8,16,32,64,128,256,512,1024,2048,4096,8192,16384};

        for(int n = 0; n < testSize.length; ++n) {
            int numInts = testSize[n];
            int numRnd = (numInts / 2) +25;
            long time = 0;

            for(int j = 0; j < 3; ++j) {
                // Javasort
                    time = SortingTest.sortTime(javaSort, j, numInts, numRnd);
                    //System.out.println( "Javasort, " + numInts + ", " + numRnd + ", " + orderName[j] +  ", " + time );
                    timeData.put( orderName[j] + ";Javasort;" + numInts + "", time );

                // Insertion
                    if(numInts < 4096) {
                        time = SortingTest.sortTime(insertionSort, j, numInts, numRnd);
                        //System.out.println( "Insertion, " + numInts + ", " + numRnd + ", " + orderName[j] +  ", " + time );
                        timeData.put( orderName[j] + ";Insertion;" + numInts + "", time );
                    }

                // Selection
                    if(numInts < 4096) {
                        time = SortingTest.sortTime(selectionSort, j, numInts, numRnd);
                        //System.out.println( "Selection, " + numInts + ", " + numRnd + ", " + orderName[j] +  ", " + time );
                        timeData.put( orderName[j] + ";Selection;" + numInts + "", time );
                    }

                // Quicksort (Kattis)
                    time = SortingTest.sortTime(quickSort, j, numInts, numRnd);
                    //System.out.println( "QS (Kattis), " + numInts + ", " + numRnd + ", " + orderName[j] +  ", " + time );
                    timeData.put( orderName[j] + ";QS (Kattis);" + numInts + "", time );

                // Quicksort (Fixed Pivot)
                    time = SortingTest.sortTime(quickSortFixedPivot, j, numInts, numRnd);
                    //System.out.println( "QS (FixedPivot), " + numInts + ", " + numRnd + ", " + orderName[j] +  ", " + time );
                    timeData.put( orderName[j] + ";QS (Fixed);" + numInts + "", time );

                // Quicksort (Random Pivot)
                    time = SortingTest.sortTime(quickSortRandomPivot, j, numInts, numRnd);
                    //System.out.println( "QS (RandomPivot), " + numInts + ", " + numRnd + ", " + orderName[j] +  ", " + time );
                    timeData.put( orderName[j] + ";QS (Random);" + numInts + "", time );

                // Quicksort (Middle Pivot)
                    time = SortingTest.sortTime(quickSortMiddlePivot, j, numInts, numRnd);
                    //System.out.println( "QS (MiddlePivot), " + numInts + ", " + numRnd + ", " + orderName[j] +  ", " + time );
                    timeData.put( orderName[j] + ";QS (Middle);" + numInts + "", time );

                // Quicksort (Fixed Pivot, Cut off)
                    time = SortingTest.sortTime(quickSortFixedPivotInsertion, j, numInts, numRnd);
                    //System.out.println( "QS (FixedPivotInsertion), " + numInts + ", " + numRnd + ", " + orderName[j] +  ", " + time );
                    timeData.put( orderName[j] + ";QS (Fixed, Cut);" + numInts + "", time );

                // Quicksort (Random Pivot, Cut off)
                    time = SortingTest.sortTime(quickSortRandomPivotInsertion, j, numInts, numRnd);
                    //System.out.println( "QS (RandomPivotInsertion), " + numInts + ", " + numRnd + ", " + orderName[j] +  ", " + time );
                    timeData.put( orderName[j] + ";QS (Random, Cut);" + numInts + "", time );

                // Quicksort (Middle Pivot, Cut off)
                    time = SortingTest.sortTime(quickSortMiddlePivotInsertion, j, numInts, numRnd);
                    //System.out.println( "QS (MiddlePivotInsertion), " + numInts + ", " + numRnd + ", " + orderName[j] +  ", " + time );
                    timeData.put( orderName[j] + ";QS (Middle, Cut);" + numInts + "", time );
            }
        }

        try{
            PrintWriter writer = new PrintWriter("sortingtest_" + System.nanoTime() + ".csv", "UTF-8");

            for (Map.Entry<String, Long> entry : timeData.entrySet()) {
                writer.println( entry.getKey() + ";" + entry.getValue() );
                System.out.println( entry.getKey() + ";" + entry.getValue());
            }

            writer.close();
        } catch (IOException e) {
           // do something
        }

        // Print data
        for (Map.Entry<String, Long> entry : timeData.entrySet()) {
            System.out.println( entry.getKey() + ";" + entry.getValue());
        }

        System.out.println( );
        System.out.println( "#### END ####" );
        System.out.println( );
    }

    public static long sortTime(IntSorter sorter, int dataOrder, int numInts, int numRnd) {
        Data[] d = {new Data(numInts, numRnd, Data.Order.ASCENDING),
                    new Data(numInts, numRnd, Data.Order.DESCENDING),
                    new Data(numInts, numRnd, Data.Order.RANDOM)};
        int[] data = {};

        long t0 = 0;
        long t1 = 0;
        int skipCount = (tests / 2);
        BigInteger time = BigInteger.valueOf(0);
        long totTime = 0;

        for(int i = 0; i < tests; ++i) {
            data = d[dataOrder].get();

            t0 = System.nanoTime();
                sorter.sort( data );
            t1 = System.nanoTime();

            if(i >= skipCount) {
                time = time.add(BigInteger.valueOf( (t1-t0) ));
            }
        }
        time = time.divide(BigInteger.valueOf( (tests-skipCount) ));

        return time.longValue();
    }
}