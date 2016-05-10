package pe.com.neodark.sharedpreferences;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView nameTextView;
    Button forgetButton;
    SharedPreferences sharedPreferences;
    private static final String PREFS = "prefs";
    private static final String PREF_NAME = "name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        forgetButton = (Button) findViewById(R.id.forgetButton);
        forgetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateKeptName("");
            }
        });
        displayWelcome();
    }

    private void updateKeptName(String name) {
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putString(PREF_NAME, name);
        e.commit();
        updateCurrentName("");
    }

    private void updateCurrentName(String name) {
        if (name.length() > 0) {
            nameTextView.setText(name + " is the current name");
        } else {
            nameTextView.setText("No name kept");
        }
    }

    private void displayWelcome() {
        // welcome message to enter name and save on savedpreferences
        sharedPreferences = getSharedPreferences(PREFS, MODE_PRIVATE);
        String name = sharedPreferences.getString(PREF_NAME, "");
        if (name.length() > 0) {
            Toast.makeText(this, "Welcome back " + name + "!",
                    Toast.LENGTH_LONG).show();
            updateCurrentName(name);
        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Name Keeper");
            alert.setMessage("What is your name?");
            final EditText input = new EditText(this);
            alert.setView(input);
            alert.setPositiveButton("OK", new
                    DialogInterface.OnClickListener() {
                        public void
                        onClick(DialogInterface dialog, int whichButton) {
                            // Grab the EditText's input
                            String inputName =
                                    input.getText().toString();
                            // Put it into memory (don't forget to commit!)
                            SharedPreferences.Editor e =
                                    sharedPreferences.edit();
                            e.putString(PREF_NAME,
                                    inputName);
                            e.commit();
                            Toast.makeText(getApplicationContext(), "Welcome, " + inputName
                                    + "!", Toast.LENGTH_LONG).show();
                            updateCurrentName(inputName);
                        }
                    }
            );
            alert.setNegativeButton("Cancel", new
                    DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which)
                        {
                        }
                    }
            );
            alert.show();
        }
    }
}
