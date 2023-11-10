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
        this.initialState =initialState ;

    }


}
