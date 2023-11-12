package code;

import java.util.concurrent.TimeUnit;

//import code.Problem.Operators;

public class Town {

    static int food;
    static int materials;
    static int energy;
    static int prosperity;
//    static int money;
    
    /// food Amount is the same as food              ,
//    static int foodAmount;
//    static int materialsAmount;
//    static int energyAmount;
    
    static int moneySpent;
    
    static int unitPriceFood;
    static int unitPriceMaterials;
    static int unitPriceEnergy;
    
    static int amountRequestFood;
    static int delayRequestFood;

    static int amountRequestMaterials;
    static int delayRequestMaterials;

    static int amountRequestEnergy;
    static int delayRequestEnergy;

    static int priceBUILD1;
    static int foodUseBUILD1;
    static int materialsUseBUILD1;
    static int energyUseBUILD1;
    static int prosperityBUILD1;

    static int priceBUILD2;
    static int foodUseBUILD2;
    static int materialsUseBUILD2;
    static int energyUseBUILD2;
    static int prosperityBUILD2;
    
//    static boolean requestFood ; 
//    static boolean requestMaterials ; 
//    static boolean requestEnergy ; 
    
    
    static InitialStateParser initialState;
   
//    static int delay;
    static int depth ; 
    

    public Town(InitialStateParser initialState) {
    	
        this.food = initialState.initialFood;
        this.materials = initialState.initialMaterials;
        this.energy = initialState.initialMaterials;
        this.prosperity = initialState.initialProsperity;
//        this.money = money;
        this.initialState = initialState;
        
        
        this.unitPriceFood = initialState.unitPriceFood;
        this.unitPriceMaterials = initialState.unitPriceMaterials;
        this.unitPriceEnergy = initialState.unitPriceEnergy;
        
        
        this.amountRequestFood=initialState.amountRequestFood; 
        this.delayRequestFood=initialState.delayRequestFood; 
        
        this.amountRequestMaterials=initialState.amountRequestMaterials;
        this.delayRequestMaterials=initialState.delayRequestMaterials;
        
        this.amountRequestEnergy=initialState.amountRequestEnergy; 
        this.delayRequestEnergy= initialState.delayRequestEnergy; 
        
        this.priceBUILD1=initialState.priceBUILD1;
        this.foodUseBUILD1=initialState.foodUseBUILD1;
        this.materialsUseBUILD1=initialState.materialsUseBUILD1;
        this.energyUseBUILD1=initialState.energyUseBUILD1;
        this.prosperityBUILD1=initialState.prosperityBUILD1;

        this.priceBUILD2=initialState.priceBUILD2;
        this.foodUseBUILD2=initialState.foodUseBUILD2;
        this.materialsUseBUILD2=initialState.materialsUseBUILD2;
        this.energyUseBUILD2=initialState.energyUseBUILD2;
        this.prosperityBUILD2=initialState.prosperityBUILD2;

        moneySpent = 0;
    }

    public static Node RequestFood(Node parentNode) {

        if (food == 0 || moneySpent > 100000 || food > 50) {
            return null;
        }
           
        food = parentNode.state.food; 
        materials = parentNode.state.materials; 
        energy = parentNode.state.energy; 
        moneySpent = parentNode.state.moneySpent ;
        prosperity=parentNode.state.prosperity;
        depth = parentNode.depth;
        

        food -= 1;
        materials -= 1;
        energy -= 1;
        moneySpent += unitPriceFood + unitPriceMaterials + unitPriceEnergy;
        depth +=1 ; 
       
        
        if (delayRequestFood==0) {
       	 food += amountRequestFood;
       }
       
       
        State newChildState = new State(prosperity, food, energy, materials, moneySpent, initialState);	
        Node childNode = new Node(newChildState, parentNode, Operators.REQUEST_FOOD, depth , moneySpent);
        childNode.foodDelay= delayRequestFood ;
        
    
        return childNode;
//    	System.out.println("request food node  :"+food + " , "+ money +" , " + foodAmount + newChildState);
    }

    
    public static Node RequestMaterials(Node parentNode) {
//    	System.out.println("RequestMaterials : "+materials + " , "+ food +" , " + energy);
        if (materials == 0 || moneySpent > 100000|| materials>50) {
            return null;
        }
        food = parentNode.state.food; 
        materials = parentNode.state.materials; 
        energy = parentNode.state.energy; 
        moneySpent = parentNode.state.moneySpent ;
        prosperity=parentNode.state.prosperity;
        depth= parentNode.depth;
        
        food -= 1;
        materials -= 1;
        energy -= 1;        
        moneySpent += unitPriceFood + unitPriceMaterials + unitPriceEnergy;
        depth+=1; 
        
        
        if (delayRequestMaterials==0) {
        	materials += amountRequestMaterials;
        }
 

//    	System.out.println("RequestMaterialsAfter : "+materials + " , "+ food +" , " + energy);

        State newChildState = new State(prosperity, food, energy, materials, moneySpent, initialState);
        Node childNode = new Node(newChildState, parentNode, Operators.REQUEST_MATERIALS, depth, moneySpent);
        childNode.materialsDelay=delayRequestMaterials; 

        return childNode;
    }

    public static Node RequestEnergy(Node parentNode) {
    	
        if (energy == 0 ||moneySpent > 100000|| energy>50) {
            return null;
        }
        
        food = parentNode.state.food; 
        materials = parentNode.state.materials; 
        energy = parentNode.state.energy; 
        moneySpent = parentNode.state.moneySpent ;
        prosperity=parentNode.state.prosperity;
        
        food -= 1;
        materials -= 1;
        energy -= 1;
        moneySpent += unitPriceFood + unitPriceMaterials + unitPriceEnergy;
    
        if (delayRequestEnergy==0) {
        	 energy += amountRequestEnergy;
        }

        State newChildState = new State(prosperity, food, energy, materials, moneySpent, initialState);
        Node childNode = new Node(newChildState, parentNode, Operators.REQUEST_ENERGY, depth, moneySpent);
        childNode.energyDelay = delayRequestEnergy; 
        return childNode;

    }

    public static Node Wait(Node parentNode) {
//        if (parentNode.operator == Operators.REQUEST_FOOD || parentNode.operator == Operators.REQUEST_ENERGY ||
//                parentNode.operator == Operators.REQUEST_MATERIALS || parentNode.operator == Operators.WAIT) {
//            delay -= 1;
//        }
    	 food = parentNode.state.food; 
         materials = parentNode.state.materials; 
         energy = parentNode.state.energy; 
         moneySpent = parentNode.state.moneySpent ;
         depth = parentNode.depth;
         prosperity = parentNode.state.prosperity; 

         food -= 1;
         materials -= 1;
         energy -= 1;
         depth+=1 ; 
         
        moneySpent += unitPriceFood + unitPriceMaterials + unitPriceEnergy;
        
        State newChildState = new State(prosperity, food, energy, materials, moneySpent, initialState);   
        Node childNode = new Node(newChildState, parentNode, Operators.WAIT , parentNode.depth+1 , moneySpent);
        
        if (parentNode.foodDelay > 0 ) {
        	childNode.foodDelay=parentNode.foodDelay-1 ; 
        	if (childNode.foodDelay ==0 ) {
        		 childNode.state.food+=amountRequestFood ; 
        	}
        }else if (parentNode.energyDelay>0) {
        	childNode.energyDelay=parentNode.energyDelay-1;
        	if (childNode.energyDelay ==0 ) {
        		childNode.state.energy+=amountRequestEnergy ; 
        	}
        }else if (parentNode.materialsDelay>0) {
        	childNode.energyDelay=parentNode.materialsDelay-1;
        	if (childNode.materialsDelay ==0 ) {
        		childNode.state.materials+=amountRequestMaterials ; 
        	}
        	
        }
       
        return childNode;
    }

    
    public static Node Build1(Node parentNode) {

        food = parentNode.state.food; 
        materials = parentNode.state.materials; 
        energy = parentNode.state.energy; 
        prosperity=parentNode.state.prosperity;
        moneySpent = parentNode.state.moneySpent ;
        
        if (food < foodUseBUILD1 || materials < materialsUseBUILD1 || energy < energyUseBUILD1 || moneySpent>100000) {
            return null ;
        }
        else {
        	
        food -= foodUseBUILD1;
        materials -= materialsUseBUILD1;
        energy -= energyUseBUILD1;
        prosperity += prosperityBUILD1;
        moneySpent += priceBUILD1 +((foodUseBUILD1*unitPriceFood)+(materialsUseBUILD1*unitPriceMaterials)+(energyUseBUILD1*unitPriceEnergy));
        
        State newChildState = new State(prosperity, food, energy, materials, moneySpent, initialState);   
        Node childNode = new Node(newChildState, parentNode, Operators.BUILD1 , parentNode.depth+1 , moneySpent);
        
        if (parentNode.foodDelay > 0 ) {
        	childNode.foodDelay=parentNode.foodDelay-1 ; 
        	if (childNode.foodDelay ==0 ) {
        		 childNode.state.food+=amountRequestFood ; 
        	}
        }else if (parentNode.energyDelay>0) {
        	childNode.energyDelay=parentNode.energyDelay-1;
        	if (childNode.energyDelay ==0 ) {
        		childNode.state.energy+=amountRequestEnergy ; 
        	}
        }else if (parentNode.materialsDelay>0) {
        	childNode.energyDelay=parentNode.materialsDelay-1;
        	if (childNode.materialsDelay ==0 ) {
        		childNode.state.materials+=amountRequestMaterials ; 
        	}
        	
        }

        return childNode ; 
        }
        
//      System.out.println("build 1 " + childNode);
    }

    public static Node Build2(Node parentNode) {
 
        food = parentNode.state.food; 
        materials = parentNode.state.materials; 
        energy = parentNode.state.energy; 
        prosperity=parentNode.state.prosperity;
        moneySpent = parentNode.state.moneySpent ;

        if (food < foodUseBUILD2 || materials < materialsUseBUILD2 || energy < energyUseBUILD2 || moneySpent>100000) {
            return null ;
        }
        else {
        food -= foodUseBUILD2;
        materials -= materialsUseBUILD2;
        energy -= energyUseBUILD2;
//        money -= priceBUILD2;
        prosperity += prosperityBUILD2;
        moneySpent += priceBUILD2+((foodUseBUILD2*unitPriceFood)+(materialsUseBUILD2*unitPriceMaterials)+(energyUseBUILD2*unitPriceEnergy));

        State newChildState = new State(prosperity, food, energy, materials, moneySpent, initialState);
        Node childNode = new Node(newChildState, parentNode, Operators.BUILD2, parentNode.depth+1, moneySpent);
        
        if (parentNode.foodDelay > 0 ) {
        	childNode.foodDelay=parentNode.foodDelay-1 ; 
        	if (childNode.foodDelay ==0 ) {
        		 childNode.state.food+=amountRequestFood ; 
        	}
        }else if (parentNode.energyDelay>0) {
        	childNode.energyDelay=parentNode.energyDelay-1;
        	if (childNode.energyDelay ==0 ) {
        		childNode.state.energy+=amountRequestEnergy ; 
        	}
        }else if (parentNode.materialsDelay>0) {
        	childNode.energyDelay=parentNode.materialsDelay-1;
        	if (childNode.materialsDelay ==0 ) {
        		childNode.state.materials+=amountRequestMaterials ; 
        	}
        	
        }
        
        return childNode;
        }
    }
    
}
