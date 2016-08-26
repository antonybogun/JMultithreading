import java.util.concurrent.ForkJoinPool;

public class Start {
	public static void main(String[] args) {
		long TASKSIZE=0, MINTASKSIZE=0;
		try{
			TASKSIZE = MINTASKSIZE = Long.parseUnsignedLong(args[0]);
			final int exponent = 5;
			double[]  times = new double[exponent];
			ForkJoinPool fjp = new ForkJoinPool();
			Long beginT,endT;
			for(int i=0;i<times.length;i++) {
				System.out.println("Calculation with "+(i==0?1:(int)Math.pow(2,i))+" thread(s)");
				MINTASKSIZE/= (i==0)?1:2;
				beginT = System.nanoTime();
				Stream task = new Stream(MINTASKSIZE, 1, TASKSIZE);
				fjp.invoke(task);
				endT = System.nanoTime();
				times[i] = endT - beginT;
				System.out.println("Result with "+(i==0?1:(int)Math.pow(2,i))+" thread(s): " + task.res+"\n");
			}
			for(int i=0; i<times.length;i++)
				System.out.printf("Calculation with %2d thread(s) took %7.4f second(s) %n",(int)Math.pow(2,i),times[i]/1E9);
		}
		catch(Exception e){
			System.out.println("Error: "+e.getMessage());
		}
	}
}