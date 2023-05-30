import mpi.MPI;
import java.util.Scanner;
import mpi.*;

public class ScatterGather {
    public static void main(String[] args) throws Exception{
        MPI.Init(args);
        //  Get rank of each process and size of communicator
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        
        
        int unitsize = 5;
        int root = 0;
        int send_buffer[] = null;
        //  1 process is expected to handle 4 elements
        send_buffer = new int [unitsize * size];
        int recieve_buffer[] = new int [unitsize];
        int new_recieve_buffer[] = new int [size];

        //  Set data for distribution
        if(rank == root) {
            int total_elements = unitsize * size;
            System.out.println("Enter " + total_elements + " elements");
            for(int i = 0; i < total_elements; i++) {
                System.out.println("Element " + i + "\t = " + i);
                send_buffer[i] = i;
            }
        }

        //  Scatter data to processes
        MPI.COMM_WORLD.Scatter(
            send_buffer,
            0,
            unitsize,
            MPI.INT,
            recieve_buffer,
            0,
            unitsize,
            MPI.INT,
            root
        );

        //  Calculate sum at non root processes
        //  Store result in first index of array
        for(int i = 1; i < unitsize; i++) {
            recieve_buffer[0] += recieve_buffer[i];
        }
        System.out.println(
            "Intermediate sum at process " + rank + " is " + recieve_buffer[0]
        );
        

        //  Gather data from processes
        MPI.COMM_WORLD.Gather(
            recieve_buffer,
            0,
            1,
            MPI.INT,
            new_recieve_buffer,
            0,
            1,
            MPI.INT,
            root
        );

        //  Aggregate output from all non root processes
        if(rank == root) {
            int total_sum = 0;
            for(int i = 0; i < size; i++) {
                total_sum += new_recieve_buffer[i];
            }
            System.out.println("Final sum : " + total_sum);
        }

        MPI.Finalize();
    }
}


/*install MPI,Compile: javac -cp $MPJ_HOME/lib/mpj.jar Asign2.java
(mpj.jar is inside lib folder in the downloaded MPJ Express)

Execute: $MPJ_HOME/bin/mpjrun.sh -np 4 Asign2

There has been a large amount of interest in parallel programming using Java. mpj is an MPI binding with Java along with the support for multicore architecture so that user can develop the code on it's own laptop or desktop. This is an effort to develop and run parallel programs according to MPI standard.
Conclusion :
 */