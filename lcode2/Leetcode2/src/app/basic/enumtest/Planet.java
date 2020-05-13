package app.basic.enumtest;

public enum Planet {
    // like fixed instances
    MARS, EARTH(1.414), JUPITER, MERCURY, NEPTUNE, VENUS, SATURN, URANUS;

    private double mass;
    private Planet() {}

    private Planet(double mass) {
        this.mass = mass;
    }

    public double getMass() {
        return mass;
    }

    public static void main(String[] args) {
        System.out.println(EARTH.ordinal());    
    }
}

