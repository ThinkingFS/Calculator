package cmpt276.ca.as2_servingsizecalculator;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class to manage a collection of pots.
 */
public class PotCollection {
    private static final String PREFS_NUM_POTS = "PotCollection_numPots";
    private static final String PREFS_POT_NAME = "PotCollection_potName";
    private static final String PREFS_POT_WEIGHT = "PotCollection_potWeight";
    private List<Pot> pots = new ArrayList<>();


    public void addPot(Pot pot) {
        pots.add(pot);
    }

    public void changePot(Pot pot, int indexOfPotEditing) {
        validateIndexWithException(indexOfPotEditing);
        pots.remove(indexOfPotEditing);
        pots.add(indexOfPotEditing, pot);
    }

    public void removePot(int index) {
        validateIndexWithException(index);
        pots.remove(index);
    }

    public int countPots() {
        return pots.size();
    }
    public Pot getPot(int index) {
        validateIndexWithException(index);
        return pots.get(index);
    }

    // Useful for integrating with an ArrayAdapter
    public String[] getPotDescriptions() {
        String[] descriptions = new String[countPots()];
        for (int i = 0; i < countPots(); i++) {
            Pot pot = getPot(i);
            descriptions[i] = pot.getName() + " - " + pot.getWeightInG() + "g";
        }
        return descriptions;
    }

    public Iterator<Pot> iterator() {
        return pots.iterator();
    }

    private void validateIndexWithException(int index) {
        if (index < 0 || index >= countPots()) {
            throw new IllegalArgumentException();
        }

    }

    // Work with Shared Preferences
    public void loadFromSharedPreference(SharedPreferences prefs) {
        // Start by wiping current set:
        pots.clear();

        // Read in each pot listed in the preferences
        int numPots = prefs.getInt(PREFS_NUM_POTS, 0);
        for (int i = 0; i < numPots; i++) {
            // Retrieve info:
            String name = prefs.getString(PREFS_POT_NAME + i, "BAD");
            int weight = prefs.getInt(PREFS_POT_WEIGHT + i, -1);

            // Add it
            Pot pot = new Pot(name, weight);
            addPot(pot);
        }
    }

    public void writeToSharedPreference(SharedPreferences prefs) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(PREFS_NUM_POTS, pots.size());
        for (int i = 0; i < pots.size(); i++) {
            Pot pot = pots.get(i);
            editor.putString(PREFS_POT_NAME + i, pot.getName());
            editor.putInt(PREFS_POT_WEIGHT + i, pot.getWeightInG());
        }
        editor.commit();
    }

}
