public class CapacityOptimizer {
	private static final int NUM_RUNS = 10;

	private static final double THRESHOLD = 5.0d;

	public static int getOptimalNumberOfSpots(int hourlyRate) {
		int sizeForLot = 1;
		while(true){
			int num = 0;
			System.out.println("==== Setting lot capacity to: " + sizeForLot);
			for(int i = 1; i <= NUM_RUNS; i++){
				ParkingLot lot = new ParkingLot(sizeForLot);
				Simulator test = new Simulator(lot, hourlyRate,Simulator.SIMULATION_DURATION);
				long mainStart = System.currentTimeMillis();
				test.simulate();
				long mainEnd = System.currentTimeMillis();
				System.out.println("Simulation run " + i + " (" + (mainEnd - mainStart) + "ms); Queue length at the end of the simulation run: " + test.getIncomingQueueSize());
				num += test.getIncomingQueueSize();
			}

			double average = num/NUM_RUNS;

			if(average <= THRESHOLD){
				break;
			}else{
				sizeForLot++;
			}
		}
		return sizeForLot;
	}

	public static void main(String args[]) {
	
		StudentInfo.display();

		long mainStart = System.currentTimeMillis();

		/*if (args.length < 1) {
			System.out.println("Usage: java CapacityOptimizer <hourly rate of arrival>");
			System.out.println("Example: java CapacityOptimizer 11");
			return;
		}

		if (!args[0].matches("\\d+")) {
			System.out.println("The hourly rate of arrival should be a positive integer!");
			return;
		}

		int hourlyRate = Integer.parseInt(args[0]);*/
		int hourlyRate = 2;
		int lotSize = getOptimalNumberOfSpots(hourlyRate);

		System.out.println();
		System.out.println("SIMULATION IS COMPLETE!");
		System.out.println("The smallest number of parking spots required: " + lotSize);

		long mainEnd = System.currentTimeMillis();

		System.out.println("Total execution time: " + ((mainEnd - mainStart) / 1000f) + " seconds");

	}
}