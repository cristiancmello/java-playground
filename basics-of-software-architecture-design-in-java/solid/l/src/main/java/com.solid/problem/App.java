package com.solid.problem;

/**
 * L  ->  LISKOV SUBSTITUTION PRINCIPLE
 * <p>
 * ----------------------------------------------
 * <p>
 * What is the motivation of Liskov principle?
 * We usually create class hierarchies during the application development
 * For example: we extend some classes creating some derived classes !!!
 * <p>
 * <p>
 * It would be great if the new derived classed would work as well
 * without replacing the functionality of the classes
 * <p>
 * Otherwise the new classes can produce undesired effects when they are
 * used in existing program modules.
 * <p>
 * --------------------------------------------------
 * <p>
 * - child classes should never break the parent class type definition
 * <p>
 * - Let q(x) be a property provable about objects x of type T. Then q(y) should be provable for objects
 * y of type S where S is a subtype of T
 * <p>
 * - subtypes must be substitutable for their base types
 * ( derived types must be completely substitutable for their base types )
 * <p>
 * We have to make sure there will be no problems using the subtype or
 * the original class
 * Do not break functionality !!! we can call the methods anyway
 * <p>
 * - we can solve it with the help of Template Pattern
 * - it is not independent from Open Close Principle + interface segregation principle
 * The violation of Liskov principle is a latent violation of Open Closed Principle !!!
 */


public class App {

    public static void main(String[] args) {
        Vehicle car = new Car();
        if (car instanceof ElectricCar) System.out.println("Unable to add fuel to electric car...");
        else car.addFuel();

        Vehicle car2 = new ElectricCar();
        if (car2 instanceof ElectricCar) System.out.println("Unable to add fuel to electric car...");
        else car2.addFuel();
    }
}
