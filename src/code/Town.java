package code;

import java.util.concurrent.TimeUnit;
import code.Constants.*;

//import code.Problem.Operators;

public class Town {

	static int food;
	static int materials;
	static int energy;
	static int prosperity;

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

	static InitialStateParser initialState;

	static int depth;
	static int delay2;
	static Resource requestedResource;
	static int resourceAmount;

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

		this.amountRequestFood = initialState.amountRequestFood;
		this.delayRequestFood = initialState.delayRequestFood;

		this.amountRequestMaterials = initialState.amountRequestMaterials;
		this.delayRequestMaterials = initialState.delayRequestMaterials;

		this.amountRequestEnergy = initialState.amountRequestEnergy;
		this.delayRequestEnergy = initialState.delayRequestEnergy;

		this.priceBUILD1 = initialState.priceBUILD1;
		this.foodUseBUILD1 = initialState.foodUseBUILD1;
		this.materialsUseBUILD1 = initialState.materialsUseBUILD1;
		this.energyUseBUILD1 = initialState.energyUseBUILD1;
		this.prosperityBUILD1 = initialState.prosperityBUILD1;

		this.priceBUILD2 = initialState.priceBUILD2;
		this.foodUseBUILD2 = initialState.foodUseBUILD2;
		this.materialsUseBUILD2 = initialState.materialsUseBUILD2;
		this.energyUseBUILD2 = initialState.energyUseBUILD2;
		this.prosperityBUILD2 = initialState.prosperityBUILD2;
		this.delay2 = 0;
		moneySpent = 0;
	}

	public static Node RequestFood(Node parentNode) {

		int total = parentNode.state.moneySpent + unitPriceFood + unitPriceMaterials + unitPriceEnergy;

		if (food <= 0 || materials <= 0 || energy <= 0 || moneySpent > 100000 || food == 50
				|| (food - 1 + amountRequestFood) >= 50 || 100000 - total < 0) {
			return null;
		}

		food = parentNode.state.food - 1;
		materials = parentNode.state.materials - 1;
		energy = parentNode.state.energy - 1;
		moneySpent = parentNode.state.moneySpent + unitPriceFood + unitPriceMaterials + unitPriceEnergy;

		if (parentNode.state.delay==-1 &&
				(parentNode.state.requestedResources==Resource.FOOD||
						parentNode.state.requestedResources==Resource.MATERIALS||
						parentNode.state.requestedResources==Resource.ENERGY)){
			resourceAmount = (parentNode.state.requestedResources == Resource.FOOD) ? Town.amountRequestFood
					: parentNode.state.requestedResources == Resource.ENERGY ? Town.amountRequestEnergy
					: Town.amountRequestMaterials;

			switch (parentNode.state.requestedResources) {
				case FOOD:
					food += resourceAmount;
					break;
				case MATERIALS:
					materials += resourceAmount;
					break;
				case ENERGY:
					energy += resourceAmount;
					break;
				default:
					break;
			}

		}

		delay2 = delayRequestFood;

		State newChildState = new State(parentNode.state.prosperity, Math.min(food, 50), Math.min(energy, 50),
				Math.min(materials, 50), moneySpent, initialState, delay2, Resource.FOOD);

		Node childNode = new Node(newChildState, parentNode, Actions.REQUESTFOOD, parentNode.depth+1, moneySpent);
		childNode.foodDelay = delayRequestFood;

		return childNode;
//    	System.out.println("request food node  :"+food + " , "+ money +" , " + foodAmount + newChildState);
	}

	public static Node RequestMaterials(Node parentNode) {
//    	System.out.println("RequestMaterials : "+materials + " , "+ food +" , " + energy);

		moneySpent = parentNode.state.moneySpent;
		int total = moneySpent + unitPriceFood + unitPriceMaterials + unitPriceEnergy;

		if (food <= 0 || materials <= 0 || energy <= 0 || moneySpent > 100000 || materials == 50
				|| (materials - 1 + amountRequestMaterials) >= 50 || 100000 - total < 0) {
			return null;
		}
		food = parentNode.state.food - 1;
		materials = parentNode.state.materials - 1;
		energy = parentNode.state.energy - 1;
		moneySpent = parentNode.state.moneySpent + unitPriceFood + unitPriceMaterials + unitPriceEnergy;

		prosperity = parentNode.state.prosperity;
		if (parentNode.state.delay==0 &&
				(parentNode.state.requestedResources==Resource.FOOD||
						parentNode.state.requestedResources==Resource.MATERIALS||
						parentNode.state.requestedResources==Resource.ENERGY)){
			resourceAmount = (parentNode.state.requestedResources == Resource.FOOD) ? Town.amountRequestFood
					: parentNode.state.requestedResources == Resource.ENERGY ? Town.amountRequestEnergy
					: Town.amountRequestMaterials;

			switch (parentNode.state.requestedResources) {
				case FOOD:
					food += resourceAmount;
					break;
				case MATERIALS:
					materials += resourceAmount;
					break;
				case ENERGY:
					energy += resourceAmount;
					break;
				default:
					break;
			}

		}

		delay2 = delayRequestMaterials;

//    	System.out.println("RequestMaterialsAfter : "+materials + " , "+ food +" , " + energy);

		State newChildState = new State(prosperity, Math.min(food, Constants.RESOURCE_LIMIT),
				Math.min(energy, Constants.RESOURCE_LIMIT), Math.min(materials, Constants.RESOURCE_LIMIT), moneySpent,
				initialState, delay2, Resource.MATERIALS);
		Node childNode = new Node(newChildState, parentNode, Actions.REQUESTMATERIALS, parentNode.depth + 1,
				moneySpent);
		childNode.materialsDelay = delayRequestMaterials;

		return childNode;
	}

	public static Node RequestEnergy(Node parentNode) {
		moneySpent = parentNode.state.moneySpent;
		int total = moneySpent + unitPriceFood + unitPriceMaterials + unitPriceEnergy;
		if (food <= 0 || materials <= 0 || energy <= 0 || moneySpent > 100000 || energy == 50
				|| (energy - 1 + amountRequestEnergy) >= 50 || 100000 - total < 0) {
			return null;
		}

		food = parentNode.state.food - 1;
		materials = parentNode.state.materials - 1;
		energy = parentNode.state.energy - 1;
		moneySpent = parentNode.state.moneySpent + unitPriceFood + unitPriceMaterials + unitPriceEnergy;
		prosperity = parentNode.state.prosperity;


		if (parentNode.state.delay==0 &&
				(parentNode.state.requestedResources==Resource.FOOD||
						parentNode.state.requestedResources==Resource.MATERIALS||
						parentNode.state.requestedResources==Resource.ENERGY)){
			resourceAmount = (parentNode.state.requestedResources == Resource.FOOD) ? Town.amountRequestFood
					: parentNode.state.requestedResources == Resource.ENERGY ? Town.amountRequestEnergy
					: Town.amountRequestMaterials;

			switch (parentNode.state.requestedResources) {
				case FOOD:
					food += resourceAmount;
					break;
				case MATERIALS:
					materials += resourceAmount;
					break;
				case ENERGY:
					energy += resourceAmount;
					break;
				default:
					break;
			}

		}


		delay2 = delayRequestEnergy;

		State newChildState = new State(prosperity, Math.min(food, Constants.RESOURCE_LIMIT),
				Math.min(energy, Constants.RESOURCE_LIMIT), Math.min(materials, Constants.RESOURCE_LIMIT), moneySpent,
				initialState, delay2, Resource.ENERGY);

//		System.out.print("delay--" + delay2 + "delayrequestenergy-- " + delayRequestEnergy);

		Node childNode = new Node(newChildState, parentNode, Actions.REQUESTENERGY, parentNode.depth + 1, moneySpent);
		childNode.energyDelay = delayRequestEnergy;
		return childNode;

	}

	public static Node Wait(Node parentNode) {

		moneySpent = parentNode.state.moneySpent;
		int total = moneySpent + unitPriceFood + unitPriceMaterials + unitPriceEnergy;
		if (food <= 0 || materials <= 0 || energy <= 0 || moneySpent > 100000 || 100000 - total < 0) {
			return null;
		}
		food = parentNode.state.food - 1;
		materials = parentNode.state.materials - 1;
		energy = parentNode.state.energy - 1;

		prosperity = parentNode.state.prosperity;

		moneySpent = parentNode.state.moneySpent + unitPriceFood + unitPriceMaterials + unitPriceEnergy;

		if (parentNode.state.delay > 0) {
			delay2 = parentNode.state.delay - 1;
			resourceAmount = delay2 > 0 ? 0
					: parentNode.state.requestedResources == Resource.FOOD ? Town.amountRequestFood
					: parentNode.state.requestedResources == Resource.ENERGY ? Town.amountRequestEnergy
					: Town.amountRequestMaterials;

			switch (parentNode.state.requestedResources) {
				case FOOD:
					food += resourceAmount;
					break;
				case MATERIALS:
					materials += resourceAmount;
					break;
				case ENERGY:
					energy += resourceAmount;
					break;
				default:
					break;
			}
			requestedResource = (delay2 > 0 || ((parentNode.state.requestedResources==Resource.FOOD||
					parentNode.state.requestedResources==Resource.MATERIALS||
					parentNode.state.requestedResources==Resource.ENERGY)))? parentNode.state.requestedResources : null;

		} else {
			delay2 = 0;
			requestedResource = null;
		}

		State newChildState = new State(prosperity, Math.min(food, 50),
				Math.min(energy, 50), Math.min(materials,50), moneySpent,
				initialState, delay2, requestedResource);

		Node childNode = new Node(newChildState, parentNode, Actions.WAIT, parentNode.depth+1, moneySpent);

		return childNode;
	}

	public static Node Build1(Node parentNode) {

		food = parentNode.state.food;
		materials = parentNode.state.materials;
		energy = parentNode.state.energy;
		prosperity = parentNode.state.prosperity;
		moneySpent = parentNode.state.moneySpent;

		int total = moneySpent + priceBUILD1 + ((foodUseBUILD1 * unitPriceFood)
				+ (materialsUseBUILD1 * unitPriceMaterials) + (energyUseBUILD1 * unitPriceEnergy));

//		System.out.println("  food-- " + food + "  fooduse-- " + foodUseBUILD1 + "  materials--" + materials
//				+ "  materialsuse--- " + materialsUseBUILD1 + "  energy-- " + energy + "  energyUse--  "
//				+ energyUseBUILD1 + "  moneyspent-- " + moneySpent + "  total-- " + total);

		if (food < foodUseBUILD1 || materials < materialsUseBUILD1 || energy < energyUseBUILD1 || moneySpent > 100000
				|| 100000 - total < 0) {
			return null;
		}

		else {

			food -= foodUseBUILD1;
			materials -= materialsUseBUILD1;
			energy -= energyUseBUILD1;
			prosperity += prosperityBUILD1;
			moneySpent += priceBUILD1 + ((foodUseBUILD1 * unitPriceFood) + (materialsUseBUILD1 * unitPriceMaterials)
					+ (energyUseBUILD1 * unitPriceEnergy));

			if (parentNode.state.delay > 0) {
				delay2 = parentNode.state.delay -1;

				resourceAmount = delay2 > 0 ? 0
						: parentNode.state.requestedResources == Resource.FOOD ? Town.amountRequestFood
						: parentNode.state.requestedResources == Resource.ENERGY ? Town.amountRequestEnergy
						: Town.amountRequestMaterials;

				switch (parentNode.state.requestedResources) {
					case FOOD:
						food += resourceAmount;
						break;
					case MATERIALS:
						materials += resourceAmount;
						break;
					case ENERGY:
						energy += resourceAmount;
						break;
					default:
						break;
				}
				requestedResource = (delay2 > 0|| ((parentNode.state.requestedResources==Resource.FOOD||
						parentNode.state.requestedResources==Resource.MATERIALS||
						parentNode.state.requestedResources==Resource.ENERGY)))? parentNode.state.requestedResources : null;

			} else {
				delay2 = 0;
				requestedResource = null;
			}

			State newChildState = new State(prosperity, Math.min(food, Constants.RESOURCE_LIMIT),
					Math.min(energy, Constants.RESOURCE_LIMIT), Math.min(materials, Constants.RESOURCE_LIMIT),
					moneySpent, initialState, delay2, requestedResource);
			Node childNode = new Node(newChildState, parentNode, Actions.BUILD1, parentNode.depth + 1, moneySpent);

			return childNode;
		}

//      System.out.println("build 1 " + childNode);
	}

	public static Node Build2(Node parentNode) {

		food = parentNode.state.food;
		materials = parentNode.state.materials;
		energy = parentNode.state.energy;
		prosperity = parentNode.state.prosperity;
		moneySpent = parentNode.state.moneySpent;
		int total = moneySpent + priceBUILD2 + ((foodUseBUILD2 * unitPriceFood)
				+ (materialsUseBUILD2 * unitPriceMaterials) + (energyUseBUILD2 * unitPriceEnergy));

		if (food < foodUseBUILD2 || materials < materialsUseBUILD2 || energy < energyUseBUILD2 || moneySpent > 100000
				|| 100000 - total < 0) {
			return null;
		} else {
			food -= foodUseBUILD2;
			materials -= materialsUseBUILD2;
			energy -= energyUseBUILD2;
//        money -= priceBUILD2;
			prosperity += prosperityBUILD2;
			moneySpent += priceBUILD2 + ((foodUseBUILD2 * unitPriceFood) + (materialsUseBUILD2 * unitPriceMaterials)
					+ (energyUseBUILD2 * unitPriceEnergy));

			if (parentNode.state.delay > 0) {
				delay2 = parentNode.state.delay - 1;
				resourceAmount = delay2 > 0 ? 0
						: parentNode.state.requestedResources == Resource.FOOD ? Town.amountRequestFood
						: parentNode.state.requestedResources == Resource.ENERGY ? Town.amountRequestEnergy
						: Town.amountRequestMaterials;

				switch (parentNode.state.requestedResources) {
					case FOOD:
						food += resourceAmount;
						break;
					case MATERIALS:
						materials += resourceAmount;
						break;
					case ENERGY:
						energy += resourceAmount;
						break;
					default:
						break;
				}
				requestedResource = (delay2 > 0 || ((parentNode.state.requestedResources==Resource.FOOD||
						parentNode.state.requestedResources==Resource.MATERIALS||
						parentNode.state.requestedResources==Resource.ENERGY)))? parentNode.state.requestedResources : null;

			} else {
				delay2 = 0;
				requestedResource = null;
			}

			State newChildState = new State(prosperity, Math.min(food, Constants.RESOURCE_LIMIT),
					Math.min(energy, Constants.RESOURCE_LIMIT), Math.min(materials, Constants.RESOURCE_LIMIT),
					moneySpent, initialState, delay2, requestedResource);

			Node childNode = new Node(newChildState, parentNode, Actions.BUILD2, parentNode.depth + 1, moneySpent);

			return childNode;
		}
	}

}
