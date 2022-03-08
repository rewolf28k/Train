package task1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Task1 {
	public static Map<String, Integer> dictionary = new TreeMap<>();
	
	public static void main(String[] args) throws FileNotFoundException, InterruptedException, ExecutionException {
		// get list path file
		Addition add = new Addition("src/input");
		List<String> listPathFile = add.getListPathFile();
		
		// create thread pool
		Callable<Map<String, Integer>> callable;
		Future<Map<String, Integer>> future;
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for(String pathFile : listPathFile) {
			callable = new CallableWorker(pathFile);
			
			future = executor.submit(callable);
			dictionary.putAll(future.get());
		}
		executor.shutdown();
		
		// wait when calc....
		while (!executor.isTerminated()) {
            // Running ...
        }
		
		try (PrintWriter printWriter = new PrintWriter("src/task1/output.txt")) {
			Set<String> words = dictionary.keySet();
			
			for(String target : words) {
				printWriter.write(target + " : " + dictionary.get(target) + "\n");
			}
		}
	}
}