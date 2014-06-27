package abstractfactory;

public interface PizzaIngredientFactory {

    Dough createDough();
    Sauce createSauce();
    Cheese createCheese();
    Clams createClam();
    Pepperoni createPepperoni();
    Veggies[] createVeggies();

}
