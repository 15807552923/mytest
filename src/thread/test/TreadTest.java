package thread.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TreadTest {

	public static void main(String[] args) {

	/*	ThreadPoolExecutor tpe = new ThreadPoolExecutor(3, 5, 200, TimeUnit.MILLISECONDS	,new ArrayBlockingQueue<Runnable>(5));
		for(int i =0 ;i<100 ;i++){
			MyTask myT = new MyTask(i);
			tpe.execute(myT);
			System.out.println("线程池中线程数目："+tpe.getPoolSize()+"，队列中等待执行的任务数目："+
					 tpe.getQueue().size()+"，已执行玩别的任务数目："+tpe.getCompletedTaskCount());
		}
		tpe.shutdown();
	}
	*/
	
System.out.println("reverseBits = " +	reverseBits(43261596 ));
}

class MyTask implements Runnable{

	 private int taskNum;
	 public MyTask(int num){
		 this.taskNum = num;
	 }
	@Override
	public void run() {
		 System.out.println("正在执行task "+taskNum);
		 try {
		 Thread.currentThread().sleep(4000);
		 } catch (InterruptedException e) {
		 e.printStackTrace();
		 }
		 System.out.println("task "+taskNum+"执行完毕");
		 }
		
	}


public static int reverseBits(int n) {  
    int result = 0;  
    for(int i = 0; i < 32; i++) {  
        result <<= 1;  
        result |= (n >> i & 1);  
    }  
    return result;  
}  
	
}