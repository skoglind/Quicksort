import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class SortingTest2 {
    private static int tests = 5;

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

        String[] orderName = {"Asc", "Desc", "Rnd", "Eq"};

        int[] testSize = {100,1000,10000,100000,1000000};

        for(int n = 0; n < testSize.length; ++n) {
            int numInts = testSize[n];
            int numRnd = (numInts / 2) +25;
            long time = 0;

            System.out.println( );
            System.out.print( testSize[n] + ": " );

            for(int j = 0; j < 4; ++j) {
                System.out.println( );
                System.out.print( "    " + orderName[j] + ": " );

                // Javasort
                    time = SortingTest2.sortTime(javaSort, j, numInts, numRnd);
                    timeData.put( orderName[j] + ";Javasort;" + numInts + "", time );
                    System.out.print( ".J" );

                // Insertion
                    time = SortingTest2.sortTime(insertionSort, j, numInts, numRnd);
                    timeData.put( orderName[j] + ";Insertion;" + numInts + "", time );
                    System.out.print( ".I" );

                // Quicksort (Fixed Pivot)
                    time = SortingTest2.sortTime(quickSortFixedPivot, j, numInts, numRnd);
                    timeData.put( orderName[j] + ";QS (Fixed);" + numInts + "", time );
                    System.out.print( ".QF" );

                // Quicksort (Random Pivot)
                    time = SortingTest2.sortTime(quickSortRandomPivot, j, numInts, numRnd);
                    timeData.put( orderName[j] + ";QS (Random);" + numInts + "", time );
                    System.out.print( ".QR" );

                // Quicksort (Fixed Pivot, Cut off)
                    time = SortingTest2.sortTime(quickSortFixedPivotInsertion, j, numInts, numRnd);
                    timeData.put( orderName[j] + ";QS (Fixed, Cut);" + numInts + "", time );
                    System.out.print( ".QFI" );


                // Quicksort (Random Pivot, Cut off)
                    time = SortingTest2.sortTime(quickSortRandomPivotInsertion, j, numInts, numRnd);
                    timeData.put( orderName[j] + ";QS (Random, Cut);" + numInts + "", time );
                    System.out.print( ".QRI" );
            }
        }

        System.out.println( );

        try{
            PrintWriter writer = new PrintWriter("sortingtest2_" + System.nanoTime() + ".csv", "UTF-8");

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
                    new Data(numInts, numRnd, Data.Order.RANDOM),
                    new Data(numInts, numRnd, Data.Order.RANDOM)};

        int[] dataEqual = d[dataOrder].get();
        int[] data = new int[numInts];

        long t0 = 0;
        long t1 = 0;
        int skipCount = (tests / 2);
        BigInteger time = BigInteger.valueOf(0);
        long totTime = 0;

        for(int i = 0; i < tests; ++i) {
            if(dataOrder == 3) {
                data = Arrays.copyOf(dataEqual, dataEqual.length);
            } else {
                data = d[dataOrder].get();
            }

            try {
                t0 = System.nanoTime();
                    sorter.sort( data );
                t1 = System.nanoTime();

                if(i >= skipCount) {
                    time = time.add(BigInteger.valueOf( (t1-t0) ));
                }
            } catch (Error e) {
                System.out.print( ".ERR->" );
                time = BigInteger.valueOf(-1);
                break;
            }
        }
        time = time.divide(BigInteger.valueOf( (tests-skipCount) ));

        return time.longValue();
    }
}