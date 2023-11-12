package code;

public class State {

    int moneySpent;
    int prosperity;
    int food;
    int energy;
    int materials;
    InitialStateParser initialState ; 

    public State(int prosperity, int food, int energy, int materials, int moneySpent, InitialStateParser initialState) {
        this.prosperity= prosperity; 
        this.food=food; 
        this.energy=energy; 
        this.moneySpent=moneySpent; 
        this.materials= materials ; 
        this.initialState =initialState ; 

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
