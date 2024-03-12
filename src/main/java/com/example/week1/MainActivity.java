package com.example.week1;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner sourceUnitSpinner, destinationUnitSpinner;
    private EditText editTextValue;
    private TextView textViewResult;
    private Button buttonConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sourceUnitSpinner = findViewById(R.id.sourceUnitSpinner);
        destinationUnitSpinner = findViewById(R.id.destinationUnitSpinner);
        editTextValue = findViewById(R.id.editTextValue);
        textViewResult = findViewById(R.id.textViewResult);
        buttonConvert = findViewById(R.id.buttonConvert);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.weight_units_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sourceUnitSpinner.setAdapter(adapter);
        destinationUnitSpinner.setAdapter(adapter);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double inputValue = Double.parseDouble(editTextValue.getText().toString());
                String sourceUnit = sourceUnitSpinner.getSelectedItem().toString();
                String destinationUnit = destinationUnitSpinner.getSelectedItem().toString();

                double convertedValue = convertWeight(sourceUnit, destinationUnit, inputValue);

                textViewResult.setText(String.valueOf(convertedValue));
            }
        });
    }

    private double convertWeight(String sourceUnit, String destinationUnit, double inputValue) {
        double conversionFactor = 1.0;

        if (sourceUnit.equals("Pound") && destinationUnit.equals("Kilogram")) {
            conversionFactor = 0.453592;
        } else if (sourceUnit.equals("Pound") && destinationUnit.equals("Gram")) {
            conversionFactor = 453.592;
        } else if (sourceUnit.equals("Kilogram") && destinationUnit.equals("Pound")) {
            conversionFactor = 2.20462;
        } else if (sourceUnit.equals("Kilogram") && destinationUnit.equals("Gram")) {
            conversionFactor = 1000.0;
        } else if (sourceUnit.equals("Gram") && destinationUnit.equals("Pound")) {
            conversionFactor = 0.00220462;
        } else if (sourceUnit.equals("Gram") && destinationUnit.equals("Kilogram")) {
            conversionFactor = 0.001;
        }

        return inputValue * conversionFactor;
    }
}