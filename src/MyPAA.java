import mosdi.paa.DeterministicEmitter;
import mosdi.paa.PAA;
import java.util.Date;

public class MyPAA extends PAA implements DeterministicEmitter{
 
	double ps, pm;
	int laenge;
	/**
	 * @param args
	 */
	
	public MyPAA(){
		this(0.999, 0.999, 1000);
	}
	
	public MyPAA(double ps, double pm, int laenge){
		this.ps = ps;
		this.pm = pm;
		this.laenge = laenge;
	}
	
/*	public static void main(String[] args) {
		System.out.println("los gehts!");
		PAA aPAA = new myPAA();
		//System.out.println(aPAA.stateValueStartDistribution());
		//ausgabe2(aPAA.stateValueStartDistribution());
		
		//Teste, was für mich schneller ist, wegen der doublingtechnique
		Date startdate1 = new Date();
		double[][] ausgabe1 = aPAA.computeStateValueDistribution(100000);
		Date enddate1 = new Date();
		System.out.println(startdate1 + " "+ enddate1);

		Date startdate2 = new Date();
		double[] ausgabe2 = aPAA.waitingTimeForValue(500000, 100000);
		Date enddate2 = new Date();
		System.out.println(startdate2 + " "+ enddate2);
		
		// TODO Auto-generated method stub

	}*/

	public void ausgabe1(double[] anArray){
		for (int i = 0; i < anArray.length; i++){
			System.out.print(anArray[i] + " ");
		}
	}

	public void ausgabe2(double[][] anArray){
		for (int i = 0; i < anArray.length; i++){
			for (int j = 0; j < anArray[i].length; j++){
				System.out.print(anArray[i][j] + " ");
			}
			System.out.println(" ");
		}
	}
	
	@Override
	// Wkeit für Ausgabe entspricht dem Zustand: 
	// Stationaer (0) immer 0
	// Mobil (1) immer 1
	public double emissionProbability(int state, int emission) {
		if (state == emission){
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	// Emissionen sind 0 oder 1 für stehen bleiben oder ein Schritt gehen
	public int getEmissionCount() {
		return 2;
	}

	@Override
	// Starte mobil
	public int getStartState() {
		return 1;
	}

	@Override
	// Starte bei Laenge 0
	public int getStartValue() {
		return 0;
	}

	@Override
	// Zustaende 0 = stationaer und 1 = mobil
	public int getStateCount() {
		return 2;
	}

	@Override
	// Maximal erreichbarer Value = Laenge der Saeule
	public int getValueCount() {
		//System.out.println(this.laenge +1);
		return this.laenge + 2;
	}

	@Override
	// Immer Addition, ausser: Sollte schon Laenge erreicht sein, nicht mehr addieren
	public int performOperation(int state, int value, int emission) {
		if (value + emission < getValueCount() -1){
			return value + emission;
		} else {
			return value;
		}
	}

	@Override
	//Von 0 -> 0: ps und 0 -> 1: 1-ps
	//Von 1 -> 1: pm und 1 -> 0: 1-pm
	//Daher: Ob pm oder ps haengt vom state (Start)
	//bei Start = Ziel bleibt p sonst 1-p
	public double transitionProbability(int state, int targetState) {
		double p, ps = 0.99, pm = 0.99;
		switch (state){
		case 0: p = ps; break;
		case 1: p = pm; break;
		default: p = pm; break;	
		}
		if (state == targetState){
			return p;
		} else {
			return 1-p;
		}
	}

	@Override
	public int getEmission(int state) {
		if (state == 0){
			return 0;
		} else {
			return 1;	
		}
	}

}
