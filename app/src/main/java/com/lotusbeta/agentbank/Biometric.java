package com.lotusbeta.agentbank;

import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class  Biometric extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biometric);

        Button nextButton = (Button)findViewById(R.id.submitRegDetails);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Biometric.this, menu.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_biometric, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveSig(View view) {

        try {

            GestureOverlayView gestureView = (GestureOverlayView) findViewById(R.id.signaturePad);

            gestureView.setDrawingCacheEnabled(true);

            Bitmap bm = Bitmap.createBitmap(gestureView.getDrawingCache());

            //code to pass image to intent
            /*ByteArrayOutputStream bstream = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100, bstream);
            byte[] byteArray = bstream.toByteArray();
            Intent signIntent = new Intent();
            signIntent.setClass(Biometric.this, Register.class);
            signIntent.putExtra("Bitmap", byteArray);
            startActivity(signIntent);
            finish();*/
            //end of code


            File f = new File(Environment.getExternalStorageDirectory()

                    + File.separator + "signature.png");

            f.createNewFile();

            FileOutputStream os;

            os = new FileOutputStream(f);

            //compress to specified format (PNG), quality - which is ignored for PNG, and out stream

            bm.compress(Bitmap.CompressFormat.PNG, 100, os);


            Intent signIntent = new Intent();
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG,50,bs);
            signIntent.setClass(Biometric.this, Register.class);
            signIntent.putExtra("Bitmap", bs.toByteArray());
            startActivity(signIntent);

            os.close();
            //ImageView signing = (ImageView)findViewById(R.id.signature);
            //signing.setImageBitmap(bm);








        } catch (Exception e) {

            Log.w("Gestures", e.getMessage());

            e.printStackTrace();

        }

    }








}
