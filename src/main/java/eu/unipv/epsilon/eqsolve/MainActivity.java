package eu.unipv.epsilon.eqsolve;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import eu.unipv.epsilon.eqsolve.equation.*;
import eu.unipv.epsilon.eqsolve.solver.*;

import java.util.Arrays;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_MESSAGE = "eu.unipv.epsilon.eqsolve.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.solverSelector);
        populateSpinner(spinner);
        spinner.setOnItemSelectedListener(this);
    }

    private void populateSpinner(Spinner spinner) {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.solver_names, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.i("EQSOLVE-DEBUG", "spinner selected " + position + " @ " + (String) parent.getItemAtPosition(position));

        if (position == 0) { // Linear solver selection
            findViewById(R.id.parCLabel).setVisibility(View.GONE);
            findViewById(R.id.parCField).setVisibility(View.GONE);
        }
        else if (position == 1) { // Quadratic solver selection
            findViewById(R.id.parCLabel).setVisibility(View.VISIBLE);
            findViewById(R.id.parCField).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.i("EQSOLVE-DEBUG", "spinner, nothing selected");
    }

    public void onSolveClick(View view) {
        Log.i("EQSOLVE-DEBUG", "solve");

        Equation equation;
        Solver solver;

        try {
            Spinner spinner = (Spinner) findViewById(R.id.solverSelector);
            switch (spinner.getSelectedItemPosition()) {
                case 0: // Linear solver selection
                    equation = new LinearEquation();
                    solver = new LinearSolver();
                    break;
                case 1:
                    equation = new QuadraticEquation();
                    solver = new QuadraticSolver();
                    break;
                default:
                    throw new RuntimeException("Solver not selected");
            }

            String par1 = ((EditText)findViewById(R.id.parAField)).getText().toString();
            equation.setParam(0, Double.parseDouble(par1));

            String par2 = ((EditText)findViewById(R.id.parBField)).getText().toString();
            equation.setParam(1, Double.parseDouble(par2));

            if (equation instanceof QuadraticEquation) {
                String par3 = ((EditText)findViewById(R.id.parCField)).getText().toString();
                equation.setParam(2, Double.parseDouble(par3));
            }

            String solutions = Arrays.toString(solver.solve(equation));
            ((TextView) findViewById(R.id.statusLabel)).setText(solutions);
        }
        catch (RuntimeException e) {
            Intent intent = new Intent(this, ErrorActivity.class);
            intent.putExtra(EXTRA_MESSAGE, e.getMessage());
            startActivity(intent);

            //((TextView) findViewById(R.id.textView4)).setText(e.getMessage());
        }

    }

}
