package pe.com.neodark.upcmoments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LastMomentActivity extends AppCompatActivity {

    SharedPreferences prefs;
    TextView aboutTextView;
    TextView placeTextView;


    ImageView fotoImageView;

    TextView fechaTextView;

    private final String APP = "NewMoment";
    private final String ABOUT = "About";
    private final String PLACE = "Place";

    private final String IMAGE = "Image";

    private final String DATE = "Date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_moment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        prefs = getSharedPreferences(APP, Context.MODE_PRIVATE);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        aboutTextView = (TextView) findViewById(R.id.aboutTextView);
        placeTextView = (TextView) findViewById(R.id.placeTextView);
        fechaTextView = (TextView) findViewById(R.id.fechaTextView);

        fotoImageView = (ImageView) findViewById(R.id.fotoImageView);

        String about =  prefs.getString(ABOUT,"");

        String place =  prefs.getString(PLACE,"");

        String fecha =  prefs.getString(DATE,"");


        String image =  prefs.getString(IMAGE,"");

        if(about!="") {
            aboutTextView.setText(about);
        }
        if(fecha!=""){
            fechaTextView.setText(fecha);
        }

        fotoImageView.setImageURI(Uri.parse(image));

        placeTextView.setText(place);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LastMomentActivity.this,NewMomentActivity.class));
            }
        });
    }

}
