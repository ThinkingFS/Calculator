package cmpt276.ca.as2_servingsizecalculator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Activity to actually calculate how much food is in a serving size.
 */
public class CalculateActivity extends AppCompatActivity {
    private static final String EXTRA_POT_NAME = "CalculatorActivity.potName";
    private static final String EXTRA_POT_WEIGHT = "CalculatorActivity.potWeight";
    private Pot pot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        // Get pot:
        String potName = getIntent().getStringExtra(EXTRA_POT_NAME);
        int potWeight = getIntent().getIntExtra(EXTRA_POT_WEIGHT, -1);
        pot = new Pot(potName, potWeight);
        updatePotInfoDisplay();
        calculate();

        // On any input, setup re-calculate
        recalculateOnChange(R.id.weight_combined);
        recalculateOnChange(R.id.num_servings);

        setupBackButton();
    }

    private void setupBackButton() {
        Button btn = (Button) findViewById(R.id.back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void updatePotInfoDisplay() {
        // Name:
        TextView tvName = (TextView) findViewById(R.id.pot_name);
        tvName.setText(pot.getName());

        // Weight
        TextView tvWeight = (TextView) findViewById(R.id.weight_empty);
        tvWeight.setText("" + pot.getWeightInG());
    }

    private void recalculateOnChange(int textFieldID) {
        TextView tv = (TextView) findViewById(textFieldID);
        tv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                calculate();
            }
        });
    }


    private void calculate() {
        // Get Combined Weight
        int combinedWeightInG = getNumberFromEditTextOrNegForFail(R.id.weight_combined);
        int potWeightInG = pot.getWeightInG();
        int foodWeightInG = combinedWeightInG - potWeightInG;

        // Compute Serving Size
        int numServings = getNumberFromEditTextOrNegForFail(R.id.num_servings);
        int weightPerServingInG = -1;
        if (numServings > 0) {
            weightPerServingInG = foodWeightInG / numServings;
        }

        // Display Results:
        displayNumberIfPositive(R.id.weight_food, foodWeightInG);
        displayNumberIfPositive(R.id.serving_weight, weightPerServingInG);
    }

    private int getNumberFromEditTextOrNegForFail(int id) {
        EditText combinedWeightEditView = (EditText) findViewById(id);
        String text = combinedWeightEditView.getText().toString();
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }
    private void displayNumberIfPositive(int id, int data) {
        TextView tv = (TextView) findViewById(id);
        if (data >= 0 ) {
            tv.setText("" + data);
        } else {
            tv.setText("");
        }
    }


    public static Intent makeLaunchIntent(Context packageContext, Pot pot) {
        Intent intent = new Intent(packageContext, CalculateActivity.class);
        intent.putExtra(EXTRA_POT_NAME, pot.getName());
        intent.putExtra(EXTRA_POT_WEIGHT, pot.getWeightInG());
        return intent;
    }
}
