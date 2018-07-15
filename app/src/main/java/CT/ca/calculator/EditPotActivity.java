package cmpt276.ca.as2_servingsizecalculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Activity to allow user to add/edit a pot.
 */
public class EditPotActivity extends AppCompatActivity {
    private static final String EXTRA_NAME = "EditPotActivity.name";
    private static final String EXTRA_WEIGHT = "EditPotActivity.weight";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pot);

        populateUIFromIntent();
        setupOKButton();
        setupCancelButton();
    }

    private void setupOKButton() {
        Button btn = (Button) findViewById(R.id.edit_ok);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Get values from UI:
                    EditText etName = (EditText) findViewById(R.id.edit_pot_name);
                    String name = etName.getText().toString();

                    if (name.length() == 0) {
                        Toast.makeText(EditPotActivity.this, "Please enter a pot name.", Toast.LENGTH_SHORT)
                                .show();
                        return;
                    }

                    EditText etWeight = (EditText) findViewById(R.id.edit_pot_weight);
                    int weight = Integer.parseInt(etWeight.getText().toString());

                    // Pass back the data in an intent
                    Intent intent = new Intent();
                    intent.putExtra(EXTRA_NAME, name);
                    intent.putExtra(EXTRA_WEIGHT, weight);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } catch (NumberFormatException ex) {
                    Toast.makeText(EditPotActivity.this, "Please enter a pot weight.", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    private void setupCancelButton() {
        Button btn = (Button) findViewById(R.id.edit_cancel);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

    }

    private void populateUIFromIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_NAME)) {
            Pot pot = getPotFromIntent(intent);

            EditText etName = (EditText) findViewById(R.id.edit_pot_name);
            etName.setText(pot.getName());

            EditText etWeight = (EditText) findViewById(R.id.edit_pot_weight);
            etWeight.setText("" + pot.getWeightInG());
        }
    }


    // Create a launch intent
    public static Intent makeIntentForNewPot(Context packageContext) {
        return new Intent(packageContext, EditPotActivity.class);
    }
    public static Intent makeIntentForEditPot(Context packageContext, Pot pot) {
        Intent intent = makeIntentForNewPot(packageContext);
        intent.putExtra(EXTRA_NAME, pot.getName());
        intent.putExtra(EXTRA_WEIGHT, pot.getWeightInG());
        return intent;
    }

    public static Pot getPotFromIntent(Intent data) {
        String name = data.getStringExtra(EXTRA_NAME);
        int weight = data.getIntExtra(EXTRA_WEIGHT, -1);
        return new Pot(name, weight);
    }
}
