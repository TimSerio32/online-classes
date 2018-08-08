package serio.tim.android.com.camerademo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import stanford.androidlib.SimpleActivity;

public class PhotoActivity extends SimpleActivity {

    private static final int REQ_CODE_TAKE_PICTURE = 30210;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
    }

    public void takePictureClick(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, REQ_CODE_TAKE_PICTURE);
        }

        // Stanford Android Library
        // requestPermission("android.permission.CAMERA");

        Intent picIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(picIntent, REQ_CODE_TAKE_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == REQ_CODE_TAKE_PICTURE) {
            Bitmap bmp = (Bitmap) intent.getExtras().get("data");
            ImageView img = (ImageView) findViewById(R.id.photo);
            img.setImageBitmap(bmp);
        }
    }
}
