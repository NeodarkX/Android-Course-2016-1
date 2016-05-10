package pe.com.neodark.sharecontent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ShareActionProvider shareActionProvider;
    TextView shareTextView;
    EditText shareEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        shareTextView = (TextView) findViewById(R.id.shareTextView);

        shareTextView = (TextView) findViewById(R.id.shareEditText);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onShareEditTextChanged();
                Snackbar.make(view, "Updated content to share",
                        Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                setShareIntent();
            }
        });
    }

    private void setShareIntent() {
        if (shareActionProvider != null) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "ContentSharing App");
                    shareIntent.putExtra(Intent.EXTRA_TEXT,
                            shareTextView.getText().toString());
            shareActionProvider.setShareIntent(shareIntent);
        }
    }

    private void onShareEditTextChanged(){
        shareTextView.setText("I want to share that " + shareEditText.getText().toString());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar i it is present.
                getMenuInflater().inflate(R.menu.menu_main, menu);
        //we add the item for sharing content
        MenuItem shareItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider)
                MenuItemCompat.getActionProvider(shareItem);
        setShareIntent();
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
