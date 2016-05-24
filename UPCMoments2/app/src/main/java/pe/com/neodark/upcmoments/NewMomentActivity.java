package pe.com.neodark.upcmoments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewMomentActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences prefs;
    Button btnKeep;
    Button btnCancel;
    EditText aboutEditText;
    EditText placeEditText;
    public static String timeNow = "";
    private final String APP = "NewMoment";
    private final String ABOUT = "About";
    private final String PLACE = "Place";

    private final String IMAGE = "Image";

    private final String DATE = "Date";

    private Uri fileUri;
    private static final int MEDIA_TYPE_IMAGE = 1;

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    private Uri lastOutputMediaFileUri = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_moment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        prefs = getSharedPreferences(APP, Context.MODE_PRIVATE);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        btnKeep = (Button) findViewById(R.id.keepButton);
        aboutEditText = (EditText) findViewById(R.id.aboutEditTextV);
        placeEditText = (EditText) findViewById(R.id.placeEditTextV);
        btnKeep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();

                String about = aboutEditText.getText().toString();

                String place = placeEditText.getText().toString();

                if(!TextUtils.isEmpty(about) && !TextUtils.isEmpty(place) && lastOutputMediaFileUri!=null) {
                    editor.putString(ABOUT, about);
                    editor.putString(PLACE, place);
                    editor.putString(DATE, timeNow);
                    if (lastOutputMediaFileUri != null) {
                        editor.putString(IMAGE, lastOutputMediaFileUri.toString());
                    }
                    editor.commit();
                    startActivity(new Intent(NewMomentActivity.this, LastMomentActivity.class));
                }else{

                    Toast.makeText(NewMomentActivity.this, "Ingrese todas los datos por favor", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.fab){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            lastOutputMediaFileUri = fileUri;
        }
    }


    protected void onActivityResult(int requestCode, int
            resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE)
        {
            if (resultCode == RESULT_OK) {
                Log.d("Camerify", "ResultCode: RESULT_OK");
                String fileName = data != null ?
                        data.getData().getPath() : lastOutputMediaFileUri.getPath();
                Toast.makeText(this, "La foto fue tomada con exito", Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                // Intent canceled;
                // Intent canceled;
                Log.d("Camerify", "ResultCode: RESULT_CANCELED");
            } else {
                // Intent error
                Log.d("Camerify", "ResultCode: " + Integer.toString(resultCode));
            }
        }

    }

    private Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {
        File mediaStorageDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (! mediaStorageDir.exists()) {
            if(! mediaStorageDir.mkdirs()) {
                Log.d("Camerify", "failed to create directory");
                return null;
            }
        } else {
            Log.d("Camerify", "Directory found");
        }
        String timeStamp = new
                SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        timeNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        File mediaFile;
        if(type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() +
                    File.separator + "IMG_"+ timeStamp+".jpg");
        } else {
            return null;
        }
        try {
            Log.d("Camerify", mediaFile.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mediaFile;
    }
}
