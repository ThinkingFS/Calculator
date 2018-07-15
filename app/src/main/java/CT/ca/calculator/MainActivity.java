package cmpt276.ca.as2_servingsizecalculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Main screen of application: display known pots and allow user to select one to
 * start computing a serving size.
 */
public class MainActivity extends AppCompatActivity {
    private static final int ACTIVITY_RESULT_ADD = 101;
    private static final int ACTIVITY_RESULT_EDIT = 102;

    private PotCollection pots = new PotCollection();
    private int indexOfPotEditing = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getPreferences(0);
        pots.loadFromSharedPreference(prefs);

        setupAddPotButton();
        setupEditPotLongPress();
        setupCalculateWithPot();

        refreshPotList();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getPreferences(0);
        pots.writeToSharedPreference(prefs);
    }

    private void setupAddPotButton() {
        Button btn = (Button) findViewById(R.id.add_pot);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = EditPotActivity.makeIntentForNewPot(MainActivity.this);
                startActivityForResult(intent, ACTIVITY_RESULT_ADD);
            }
        });
    }

    private void setupEditPotLongPress() {
        ListView list = (ListView) findViewById(R.id.pot_list);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                indexOfPotEditing = position;
                Pot pot = pots.getPot(position);

                Intent intent = EditPotActivity.makeIntentForEditPot(MainActivity.this, pot);
                startActivityForResult(intent, ACTIVITY_RESULT_EDIT);
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ACTIVITY_RESULT_ADD:
                if (resultCode == Activity.RESULT_OK) {
                    Pot pot = EditPotActivity.getPotFromIntent(data);
                    pots.addPot(pot);
                    refreshPotList();
                }
                break;

            case ACTIVITY_RESULT_EDIT:
                if (resultCode == Activity.RESULT_OK) {
                    Pot pot = EditPotActivity.getPotFromIntent(data);
                    pots.changePot(pot, indexOfPotEditing);
                    refreshPotList();
                }
                break;
        }
    }

    private void setupCalculateWithPot() {
        ListView list = (ListView) findViewById(R.id.pot_list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                // Start the calculate activity
                Pot pot = pots.getPot(position);
                Intent i = CalculateActivity.makeLaunchIntent(MainActivity.this, pot);
                startActivity(i);
            }
        });
    }

    private void refreshPotList() {
        // Get array of items to show:
        String[] data = pots.getPotDescriptions();

        // Build an adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.pot_item, data);

        // Configure list view:
        ListView list = (ListView) findViewById(R.id.pot_list);
        list.setAdapter(adapter);
    }
}
