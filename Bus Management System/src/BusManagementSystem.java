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
		int N = countLines(fileName);
		int S = countLines(stops);
		Graph graph = new Graph(S);
		if (fileName != null) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(fileName));
				reader.readLine();
				for (int i = 0; i < N-1; i++) {
					String line = reader.readLine();
					String[] parameters = line.trim().split(",");
					int source = Integer.parseInt(parameters[0]);
					int destination = Integer.parseInt(parameters[1]);
					float weight;
					if(Float.parseFloat(parameters[2]) == 0)
						weight = 2;
					else 
						weight = (Float.parseFloat(parameters[3])/100);
					Graph.addEgde(source, destination, weight);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
