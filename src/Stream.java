import java.util.concurrent.RecursiveAction;

public class Stream extends RecursiveAction {

	final int CPUCOUNT = Runtime.getRuntime().availableProcessors();
	long MINTASKSIZE;
	long start;
	long end;
	double res;
	Stream(long minTaskSize, long startPoint, long endPoint) {
		MINTASKSIZE=minTaskSize;
		start = startPoint;
		end = endPoint;
		res = 0;
	}

	protected void compute() {
		if (CPUCOUNT == 1 || end - start  <= MINTASKSIZE) {
			System.out.println("	Run already! "+":" + (end - start + 1) + ":" + start +":" + end);
			for(long i = start; i <= end; i++) {
				res+=i*i;
			}
		} else {
			long middle = (start + end)/ 2;
			Stream task1,task2;
			invokeAll( task1 = new Stream(MINTASKSIZE,  start, middle),
					task2 = new Stream(MINTASKSIZE, middle+1, end));
			res = task1.res+task2.res;
		}
	}
}