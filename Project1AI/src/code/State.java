package code;
import code.Constants.*;

public class State {

    int moneySpent;
    int prosperity;
    int food;
    int energy;
    int materials;
    InitialStateParser initialState ; 
    Resource requestedResources;
    int delay; 
	
    public State(int prosperity, int food, int energy, int materials, int moneySpent, InitialStateParser initialState,int delay,Resource requestedResources) {
        this.prosperity= prosperity; 
        this.food=food; 
        this.energy=energy; 
        this.moneySpent=moneySpent; 
        this.materials= materials ; 
        this.initialState =initialState ; 
        this.delay = delay;
		this.requestedResources = requestedResources;
    }
    public String toString() {
        return " State { " +
        		"prosperity=" + prosperity +
                ",\tfood=" + food +
                ",\tmaterials=" + materials +
                ",\tenergy=" + energy +
                ",\tmoneySpent=" + moneySpent +
//                ",\n\tinitialState=" + initialState +
                '}';
    }


}
