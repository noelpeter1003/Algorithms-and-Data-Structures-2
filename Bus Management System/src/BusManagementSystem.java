import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BusManagementSystem {

	static String stops = "stops.txt";
	static String stop_times = "stop_times.txt";
	static String transfers = "transfers.txt";
	
	public static void main(String[] args) {
//		Scanner input = new Scanner(System.in); 
//		System.out.print("Please enter the name of the stop you'd like to start your journey : " );
//		String inputStopA = input.nextLine();  
//		System.out.print("Please enter the name of the stop you'd like to end your journey : " );
//		String inputStopB = input.nextLine(); 
		makeGraph(transfers);
		Graph.printGraph();
//		findroute(inputStopA, inputStopB);
	}
	
	public static void findroute(String stopA, String stopB){
		int stopNumA = getStopNumber(stopA);
		int stopNumB = getStopNumber(stopB);
//		find the shortest path using a*
	}
	
	public static int getStopNumber(String stop){
//	searches the file for the string and gives back the number of the bus stop
		return 1;
	}
	
	public static int countLines(String fileName) {
		int lines = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			while (reader.readLine() != null) 
				lines++;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
	
	public static void makeGraph(String fileName){
		
	}
}
