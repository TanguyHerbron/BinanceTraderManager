package manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeoutException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.zeroturnaround.exec.InvalidExitValueException;
import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.stream.LogOutputStream;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Logger logger = Logger.getLogger("BinanceTrader");	
	
		logger.setUseParentHandlers(false);
		
		FileHandler fh;
		
		int val = 1;
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("d-M-y_HH.mm.ss_");
			SimpleFormatter formatter = new SimpleFormatter();
			
			fh = new FileHandler("log/" + format.format(Calendar.getInstance().getTime()) + "binanceLogger.log");
			fh.setFormatter(formatter);
			logger.addHandler(fh);
			
			System.out.println("Program booted up");
			logger.info("Program booted up");
			
			while(val != 0)
			{
				val = startScript(logger);
			}
			
			/*while(val)
			{
				val = startScript(logger);
			}*/
			
		} catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }
	}
	
	private static int startScript(Logger logger)
	{
		int exitValue = 0;
		
		String s = null;

		/*try {
			//exitValue = new ProcessExecutor().command("python", "trader.py --symbol VENETH --profit 0.6 --stop_loss 0 --amount 0.04830275")
			exitValue = new ProcessExecutor().command("C:\\Users\\Guitoune\\AppData\\Local\\Programs\\Python\\Python36-32\\python.exe", "C:\\Users\\Guitoune\\Desktop\\test.py")
			.redirectOutput(new LogOutputStream() {
			    @Override
			    protected void processLine(String line) {
	                System.out.println(line);
	                logger.info(line);
			    }
			})
			.execute().getExitValue();
			
			logger.info("Script closed or crashed. Error : " + exitValue);
		} catch (InvalidExitValueException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TimeoutException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		boolean toReturn = false;
		
		try {
            //Process p = Runtime.getRuntime().exec("C:\\Users\\Guitoune\\AppData\\Local\\Programs\\Python\\Python36-32\\python.exe C:\\Users\\Guitoune\\Desktop\\test.py");
			//Process p = Runtime.getRuntime().exec("C:\\Users\\Guitoune\\AppData\\Local\\Programs\\Python\\Python36-32\\python.exe python\\trader.py");
			//Process p = Runtime.getRuntime().exec("python trader.py --symbol VENETH --profit 0.6 --stop_loss 0 --amount 0.04830275");
			Process p = Runtime.getRuntime().exec("./trader.sh");
			
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			
			while(!toReturn)
			{
	            while ((s = stdInput.readLine()) != null) {
	                System.out.println(":> " + s);
	                logger.info(s);
	            }
	            
	            if((s = stdError.readLine()) != null)
	            {
	            	System.out.println("Looks like a crash, restarting");
	            	logger.info("The trader crashed");
	            	logger.info(s);
	            	
	            	while((s = stdError.readLine()) != null)
	            	{
	            		logger.info(s);
	            		System.out.println(s);
	            	}
	            	
	            	toReturn = true;
	            }
			}
		} catch (IOException e) {
			logger.info("The whole program crashed");
			e.printStackTrace();
		}
		
		return exitValue;
	}

}


