package code;

import code.Constants.*;

public class State {

    int moneySpent;
    int prosperity;
    int food;
    int energy;
    int materials;
    InitialStateParser initialState;
    Resource requestedResources;
    int delay;

    public State(int prosperity, int food, int energy, int materials, int moneySpent, InitialStateParser initialState, int delay, Resource requestedResources) {
        this.prosperity = prosperity;
        this.food = food;
        this.energy = energy;
        this.moneySpent = moneySpent;
        this.materials = materials;
        this.initialState = initialState;
        this.delay = delay;
        this.requestedResources = requestedResources;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + prosperity;
        result = 31 * result + food;
        result = 31 * result + materials;
        result = 31 * result + energy;
        result = 31 * result + delay;
        result = 31 * result + moneySpent;
        result = 31 * result + (requestedResources == null ? 0 : requestedResources.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        State other = (State) obj;
        return prosperity == other.prosperity &&
            food == other.food &&
            materials == other.materials &&
            energy == other.energy &&
            moneySpent == other.moneySpent &&
            delay == other.delay && requestedResources == other.requestedResources;
    }

    public String toString() {
        return " State { " +
            "prosperity=" + prosperity +
            ",\tfood=" + food +
            ",\tmaterials=" + materials +
            ",\tenergy=" + energy +
            ",\tmoneySpent=" + moneySpent +
                ",\tdelay=" + delay +
                ",\trequestedResources=" + requestedResources +
//                ",\n\tinitialState=" + initialState +
            '}';
    }


}
