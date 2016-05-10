package pe.com.neodark.camarify;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Uri fileUri;
    private static final int MEDIA_TYPE_IMAGE = 1;
    private static final int MEDIA_TYPE_VIDEO = 2;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;

    private Uri lastOutputMediaFileUri = null;
    Button takeAShotButton;
    Button shootAVideoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        takeAShotButton = (Button) findViewById(R.id.takeAShotButton);
        shootAVideoButton = (Button) findViewById(R.id.shootVideoButton);
        takeAShotButton.setOnClickListener(this);
        shootAVideoButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.takeAShotButton){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(intent,
                    CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            lastOutputMediaFileUri = fileUri;
        }
        if (v.getId() == R.id.shootVideoButton) {
            Intent intent = new
                    Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
// High Quality Video Recording
            startActivityForResult(intent,
                    CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
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
                Toast.makeText(this, "Image saved to: " +
                        fileName, Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                // Intent canceled;
                // Intent canceled;
                Log.d("Camerify", "ResultCode: RESULT_CANCELED");
            } else {
                // Intent error
                Log.d("Camerify", "ResultCode: " + Integer.toString(resultCode));
            }
        }
        if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE)
        {
            if (resultCode == RESULT_OK) {
                Log.d("Camerify", "ResultCode: RESULT_OK");
                String fileName = data != null ?
                        data.getData().getPath() : lastOutputMediaFileUri.getPath();
                Toast.makeText(this, "Video saved to: " +
                        fileName, Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
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
        File mediaFile;
        if(type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() +
                    File.separator + "IMG_"+ timeStamp+".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() +
                    File.separator + "VID_"+ timeStamp + ".mp4");
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
