import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class BusManagementSystem {

	static String stops = "stops.txt";
	static String stop_times = "stop_times.txt";
	static String transfers = "transfers.txt";

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		System.out.println(
				"Hi and Welcome to Vancouver's Bus Management System. Please choose one of the options below : ");
		System.out.println("1. Find the shortest path from any bus stop to your destination.");
		System.out.println("2. Bus Stop Info : Search by full name or by the first few characters of the name.");
		System.out.println("3. All trips of your choice by arrival time.");

		boolean quit = false;
		boolean validInput = false;
		int choice = 0;
		while (!validInput && !quit) {
			System.out.print("Please enter either 1, 2 or 3 (or quit): ");
			String inputString = input.next();
			try {
				int inputNumber = Integer.parseInt(inputString);
				if (inputNumber == 1 || inputNumber == 2 || inputNumber == 3) {
					validInput = true;
					choice = inputNumber;
				} else {
					System.out.println("Input number must be either 1, 2 or 3, not " + inputNumber);
				}
			} catch (NumberFormatException e) {
				if (inputString.equalsIgnoreCase("quit")) {
					quit = true;
				} else {
					System.out.println("Please enter a single digit number 1, 2 or 3, or quit : ");
				}
			}
		}

		if (choice == 1) {
			String inputStopA = "";
			String inputStopB = "";
			boolean valid1 = false;
			inputStopA = input.nextLine();
			while (!valid1 && !quit) {
				System.out.print("Please enter the name of the stop you'd like to start your journey : ");
				inputStopA = input.nextLine();
				if (inputStopA.equalsIgnoreCase("quit")) {
					quit = true;
				} else if (Pattern.matches(".*[a-zA-Z]+.*", inputStopA)) {
					valid1 = true;
				} else {
					System.out.println("Please input text");
				}
			}
			valid1 = false;
			while (!valid1 && !quit) {
				System.out.print("Please enter the name of the stop you'd like to end your journey : ");
				while (!input.hasNextLine()) {
				}
				inputStopB = input.nextLine();
				if (inputStopB.equalsIgnoreCase("quit")) {
					quit = true;
				} else if (Pattern.matches(".*[a-zA-Z]+.*", inputStopB)) {
					valid1 = true;
				} else {
					System.out.println("Please input text with at least one letter");
				}
			}
			if (!quit) {
				String[] result;
				try {
					System.out.println("Please wait for a few moments.......");
					result = getShortestRoute(inputStopA, inputStopB);
				} catch (IOException e) {
					result = new String[0];
				}
				if (result.length == 0) {
					System.out.println("No path route exists between these stops");
				} else if (result[0].equalsIgnoreCase("1") || result[0].equalsIgnoreCase("2")) {
					System.out.println("Invalid bus stop " + result[0] + " name");
				} else if (result[0].equalsIgnoreCase("3")) {
					System.out.println("Invalid bus stop 1 and bus stop 2");
				} else {
					for (int i = 0; i < result.length; i++) {
						System.out.println(result[i]);
					}
				}
			}

		} else if (choice == 2) {
			String inputName = "";
			boolean validInput2 = false;
			input.nextLine();
			while (!validInput2 && !quit) {
				System.out.print("Please enter the Full Name of the stop you'd find information about : ");
				inputName = input.nextLine();
				if (inputName.equalsIgnoreCase("exit")) {
					quit = true;
				} else if (Pattern.matches(".*[a-zA-Z]+.*", inputName)) {
					validInput2 = true;
				} else {
					System.out.println("Please enter full name of the bus stop.");
				}
			}
			if (!quit) {
				TST<Integer> st = new TST<Integer>();
				int N = countLines(stops);
				String[] stopName;
				String newStopName;
				int stopNumber;
				try {
					BufferedReader br = new BufferedReader(new FileReader(stops));
					br.readLine();
					for (int i = 0; i < N - 1; i++) {
						String line = br.readLine();
						String[] arr1 = line.split(",");
						stopNumber = Integer.parseInt(arr1[0]);
						stopName = arr1[2].split(" ");
						newStopName = reverse(stopName);
						st.put(newStopName, stopNumber);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				BufferedReader br = new BufferedReader(new FileReader(stops));
				String[] fields = br.readLine().split(",");
				for (int i = 0; i < N - 1; i++) {
					String line = br.readLine();
					String[] arr1 = line.split(",");
					if (st.get(inputName) == Integer.parseInt(arr1[0])) {
						for (int j = 0; j < (fields.length - 3); j++)
							System.out.println(fields[j] + " is " + arr1[j]);
						break;
					}
				}
			}

		} else if (choice == 3) {
			boolean validInput3 = false;
			String inputString = "";
			int hours = 0;
			int minutes = 0;
			int seconds = 0;
			while (!validInput3 && !quit) {
				System.out.print("Enter the arrival time in the format hh:mm:ss in 24 Hour format: ");
				inputString = input.next();
				String[] stringComponents = inputString.split(":");
				if (inputString.equalsIgnoreCase("exit")) {
					quit = true;
				} else if (stringComponents.length != 3) {
					System.out.println("Please input 3 values, in the format hh:mm:ss in 24 Hour format");
				} else {
					try {
						hours = Integer.parseInt(stringComponents[0]);
						if (hours < 0 || hours > 23) {
							System.out.println("Please enter a number for the hours in between 00-23");
						} else {
							try {
								minutes = Integer.parseInt(stringComponents[1]);
								if (minutes < 0 || minutes > 59) {
									System.out.println("Please enter a number for the hours in between 00-59");
								} else {
									try {
										seconds = Integer.parseInt(stringComponents[2]);
										if (seconds < 0 || seconds > 59) {
											System.out.println("Please enter a number for the hours in between 00-59");
										} else {
											validInput3 = true;
										}
									} catch (NumberFormatException e) {
										System.out.println("Please input a number in digits for seconds");
									}
								}
							} catch (NumberFormatException e) {
								System.out.println("Please input a number in digits for minutes");
							}
						}
					} catch (NumberFormatException e) {
						System.out.println("Please input a number in digits for hours");
					}
				}
			}
			if (!quit) {
				System.out.println("Please wait for a few moments.......");
				String[] result = searchByTime(inputString);
				if (result.length == 0) {
					System.out.println("No trips exist with this arrival time");
				} else {
					for (int i = 0; i < result.length; i++) {
						System.out.println(result[i]);
					}
				}
			}
		}
		System.out.println("Thanks for using the System, Hope to See You Again!!");
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

	// Part 1
	public static String[] getShortestRoute(String stop1, String stop2) throws IOException {
		int largestStopNumber = 0;
		BufferedReader stops = new BufferedReader(new FileReader("stops.txt"));
		Boolean stop1Found = false;
		Boolean stop2Found = false;
		int stop1ID = 0;
		int stop2ID = 0;
		try {
			String line = stops.readLine();
			while (line != null) {
				try {
					if (Integer.parseInt(line.split(",")[0]) > largestStopNumber) {
						largestStopNumber = Integer.parseInt(line.split(",")[0]);
					}
				} catch (NumberFormatException e) {
				}
				String[] splitline = line.split(",");
				String stopName = splitline[2];
				if (stop1.equalsIgnoreCase(stopName)) {
					stop1Found = true;
					stop1ID = Integer.parseInt(splitline[0]);
				}
				if (stop2.equalsIgnoreCase(stopName)) {
					stop2Found = true;
					stop2ID = Integer.parseInt(splitline[0]);
				}
				line = stops.readLine();
			}
			if (!stop1Found && stop2Found) {
				return new String[] { "1" };
			} else if (stop1Found && !stop2Found) {
				return new String[] { "2" };
			} else if (!stop1Found && !stop2Found) {
				return new String[] { "3" };
			}
		} catch (FileNotFoundException e) {
			System.out.println("FIle not found!");
		}

		int numberOfEdges = 0;
		numberOfEdges += countLines(stop_times);
		numberOfEdges += countLines(transfers);
		numberOfEdges -= 2;
		Graph graph = new Graph(largestStopNumber + 1, numberOfEdges);

		BufferedReader stopTimes = new BufferedReader(new FileReader("stop_times.txt"));
		BufferedReader stopTransfers = new BufferedReader(new FileReader("transfers.txt"));
		String stopTimesLastLine = stopTimes.readLine();
		String stopTimesCurrentLine = stopTimes.readLine();
		String transfersCurrentLine = stopTransfers.readLine();
		transfersCurrentLine = stopTransfers.readLine();

		String[] stopTimesLastLineComponents;
		String[] stopTimesCurrentLineComponents;
		while (stopTimesCurrentLine != null) {
			stopTimesLastLineComponents = stopTimesLastLine.split(",");
			stopTimesCurrentLineComponents = stopTimesCurrentLine.split(",");
			if (stopTimesLastLineComponents[0].equalsIgnoreCase(stopTimesCurrentLineComponents[0])) {
				graph.addNode(Integer.parseInt(stopTimesLastLineComponents[3]),
						Integer.parseInt(stopTimesCurrentLineComponents[3]), 1);
			}
			stopTimesLastLine = stopTimesCurrentLine;
			stopTimesCurrentLine = stopTimes.readLine();
		}

		String[] transfersCurrentLineComponents;
		while (transfersCurrentLine != null) {
			transfersCurrentLineComponents = transfersCurrentLine.split(",");
			if (transfersCurrentLineComponents[2].equalsIgnoreCase("2")) {
				graph.addNode(Integer.parseInt(transfersCurrentLineComponents[0]),
						Integer.parseInt(transfersCurrentLineComponents[1]),
						Float.parseFloat(transfersCurrentLineComponents[3]) / 100);
			} else if (transfersCurrentLineComponents[2].equalsIgnoreCase("0")) {
				graph.addNode(Integer.parseInt(transfersCurrentLineComponents[0]),
						Integer.parseInt(transfersCurrentLineComponents[1]), 2);
			}
			transfersCurrentLine = stopTransfers.readLine();
		}
		String[] result = Graph.djikstrasAlgorithm(stop1ID, stop2ID);
		return result;
	}

	// Part2
	public static String reverse(String[] stopName) {
		StringBuilder str = new StringBuilder();
		if (stopName[0].equals("WB") | stopName[0].equals("EB") | stopName[0].equals("SB") | stopName[0].equals("NB")) {
			for (int i = 1; i < stopName.length; i++) {
				str.append(stopName[i]);
				str.append(" ");
			}
			str.append(stopName[0]);
		} else {
			for (int i = 0; i < stopName.length; i++) {
				str.append(stopName[i]);
				str.append(" ");
			}
			str.delete(str.length() - 1, str.length());
		}
		return str.toString();
	}

	// Part 3
	public static String[] searchByTime(String inputString) {
		List<Pair> temp = new LinkedList<>();
		String readString = "";
		String[] splitStrings = new String[9];
		File fileObj = new File(stop_times);
		try {
			Scanner inputFile = new Scanner(fileObj);
			String tripId = "";
			Boolean tripValid = false;
			String stopsInTrip = "";
			while (inputFile.hasNextLine()) {
				readString = inputFile.nextLine();
				splitStrings = readString.split(",");
				if (splitStrings.length > 2 && checkValidTime(splitStrings[1])) {
					if (splitStrings[0].compareTo(tripId) != 0) {
						if (tripValid) {
							temp.add(new Pair(tripId, stopsInTrip));
						}
						tripValid = false;
						stopsInTrip = splitStrings[3];
						tripId = splitStrings[0];
					} else {
						stopsInTrip += " -> " + splitStrings[3];
					}
					if (compareTimes(inputString, splitStrings[1])) {
						tripValid = true;
					}
				}
			}
			if (tripValid) {
				temp.add(new Pair(tripId, stopsInTrip));
			}
			if (temp.size() == 0) {
				inputFile.close();
				return new String[0];
			}
			String[] array = new String[temp.size()];
			Collections.sort(temp);
			int i = 0;
			for (Pair p : temp) {
				array[i++] = "Trip ID is " + p.tripId + " with stops : " + p.stops;
			}
			inputFile.close();
			return array;
		} catch (Exception e) {
			System.out.println(e);
			return new String[0];
		}
	}

	public static Boolean checkValidTime(String time) {
		try {
			String[] components = time.split(":");
			int temp = Integer.parseInt(components[0].trim());
			if (temp < 0 || temp > 23) {
				return false;
			}
			for (int i = 1; i <= 2; ++i) {
				temp = Integer.parseInt(components[i]);
				if (temp < 0 || temp > 59) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static Boolean compareTimes(String time1, String time2) {
		try {
			String[] components1 = time1.split(":");
			String[] components2 = time2.split(":");
			if (components1.length != components2.length) {
				return false;
			}
			int temp1 = 0, temp2 = 0;
			for (int i = 0; i < components1.length; ++i) {
				temp1 = Integer.parseInt(components1[i].trim());
				temp2 = Integer.parseInt(components2[i].trim());
				if (temp1 != temp2) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}