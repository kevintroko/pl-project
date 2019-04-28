package Multithreading;

import Screen.MainPanel;

public class Consumer extends Thread {
	private long waitTime;
	private MainPanel dashboard;
    private Buffer buffer;
	private boolean isStart;
    
    public Consumer(Buffer buffer, MainPanel dashboard, int waitTime) {
        this.buffer = buffer;
        this.waitTime = waitTime;
        this.dashboard = dashboard;
        this.isStart = true;
    }
    
    @Override
    public void run() {
		String product;
        
        while (isStart) {
            try {
				product = this.buffer.consume();
			
            if (product != null) {
            	double result = getResult(product);
	            dashboard.removeElementOfRemainingList();
	            dashboard.addElementToCompletedList(product+"= "+result);
            	}
            } catch (InterruptedException interruptedException) {
				interruptedException.printStackTrace();
			       }try {
	               Thread.sleep(this.waitTime);
	           } catch(InterruptedException interruptedException) {
					System.out.println("CONSUMED FROM EMPTY BUFFER!!");
	           }
           }
        
       
    }

	public double getResult(String product){
		String[] result = product.split(" ");
		String operand = result[0];
		double resultAsFloat = Float.NaN;

		if (operand.charAt(0)=='+') {
			 resultAsFloat =(double) (Integer.parseInt(result[1]) + Integer.parseInt(result [2]));

		}
		else if (operand.charAt(0)=='-') {
			 resultAsFloat = (double)(Integer.parseInt(result[1]) - Integer.parseInt(result [2]));

		}
		else if (operand.charAt(0)=='*') {
			 resultAsFloat =(double) (Integer.parseInt(result[1]) * Integer.parseInt(result [2]));

		}
		else if (operand.charAt(0)=='/' && (Integer.parseInt(result[2]))!=0.0) {
			 resultAsFloat =(double) (Integer.parseInt(result[1]) / Integer.parseInt(result [2]));

		}
		return resultAsFloat;

	}
	public void setCancel(){
    	this.isStart = false;
	}
}
