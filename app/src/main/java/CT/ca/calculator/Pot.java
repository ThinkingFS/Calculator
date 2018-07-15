package cmpt276.ca.as2_servingsizecalculator;

/**
 * Store information about a single pot/pan
 */

public class Pot {
    private String name;
    private int weightInG;

    // Set member data based on parameters.
    public Pot(String name, int weightInG) {
        setName(name);
        setWeightInG(weightInG);
    }

    // Return the weight
    public int getWeightInG() {
        return weightInG;
    }

    // Set the weight. Throws IllegalArgumentException if weight is less than 0.
    public void setWeightInG(int weightInG) {
        if (weightInG < 0) {
            throw new IllegalArgumentException("Weight must be 0 or greater.");
        }
        this.weightInG = weightInG;
    }

    // Return the name.
    public String getName() {
        return name;
    }

    // Set the name. Throws IllegalArgumentException if name is an empty string (length 0),
    // or if name is a null-reference.
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name must not be null.");
        }
        if (name.length() == 0) {
            throw new IllegalArgumentException("Name must not be empty.");
        }
        this.name = name;
    }
}
