package test_app.pro.mylocation.mylocation;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.test.mock.MockPackageManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Old_MainActivity extends Activity implements View.OnClickListener{

    ImageView image_1;
    ImageView image_2;

    Button bt1,bt2;
    Timer timer;
    Animation zoom;

    long animation_clock = 5000;
    private static final int REQUEST_CODE_PERMISSION = 2;


   String[] mPermission = {Manifest.permission.READ_CONTACTS, Manifest.permission.READ_SMS,
           Manifest.permission.ACCESS_FINE_LOCATION,
           Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main1);

        image_1 = (ImageView) findViewById(R.id.image_1);
        image_2 = (ImageView) findViewById(R.id.image_2);

        bt1= (Button)findViewById(R.id.kenburnsbt1);
        bt2= (Button)findViewById(R.id.kenburnsbt2);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission[0])
                    != MockPackageManager.PERMISSION_GRANTED ) /* ||
                 /*   ActivityCompat.checkSelfPermission(this, mPermission[1])
                            != MockPackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, mPermission[2])
                            != MockPackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, mPermission[3])
                            != MockPackageManager.PERMISSION_GRANTED) */ {

                ActivityCompat.requestPermissions(this,
                        mPermission, REQUEST_CODE_PERMISSION);

                // If any permission aboe not allowed by user, this condition will execute every tim, else your else part will work
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        zoom = AnimationUtils.loadAnimation(this, R.anim.zoom);
    }

    @Override
    public void onResume(){
        super.onResume();
        timer = new Timer();
        timer.scheduleAtFixedRate(new FadeTimerTask(), animation_clock, animation_clock);
    }

    @Override
    public void onPause(){
        super.onPause();
        timer.cancel();
        timer = null;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Old_MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void crossfade(){
        if(image_2.getVisibility() == View.VISIBLE){
            _crossfade(image_1, image_2);
        }
        else {
            _crossfade(image_2, image_1);
        }


    }

    @SuppressLint("NewApi")
    private void _crossfade(View fadeIn, final View fadeOut) {

        // Set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        Log.i("FadeIn", "FadeOut alpha: " + String.valueOf(fadeOut.getAlpha()));
        fadeIn.setAlpha(0f);
        fadeIn.setVisibility(View.VISIBLE);

        // Animate the content view to 100% opacity, and clear any animation
        // listener set on the view.
        fadeIn.animate()
                .alpha(1f)
                .setDuration(1000)
                .setListener(null).start();

        // Zoom content view

        fadeIn.animate()
                .scaleX((float) 1.10)
                .scaleY((float) 1.10)
                .setDuration(animation_clock - 50).start();

        // Animate the loading view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)
        Log.i("FadeOut", "FadeIn alpha: " + String.valueOf(fadeIn.getAlpha()));
        fadeOut.animate()
                .alpha(0f)
                .setDuration(1000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        fadeOut.setVisibility(View.GONE);
                        fadeOut.setScaleX((float)1.0);
                        fadeOut.setScaleY((float)1.0);
                    }
                }).start();
    }

    @Override
    public void onClick(View view) {

        int id=view.getId();
        switch (id) {
            case R.id.kenburnsbt1:



                    UserListDbHelper uldh = new UserListDbHelper(getApplicationContext());

                Cursor cursor = uldh.getSQLiteDBdata();

                if (cursor != null) {

                    Intent i = new Intent(getApplicationContext(), ListViewActivity.class);
                    startActivity(i);
                    // finish();
                    break;

                } else {
                    Toast.makeText(getApplicationContext(), "DB does not exsists", Toast.LENGTH_SHORT).show();
                    break;
                }




            case R.id.kenburnsbt2:

                    Intent j = new Intent(getApplicationContext(), G_Maps_Main_Activity.class);
                    startActivity(j);
                    finish();
                break;
            default:
            {

            }
        }

    }


    private class FadeTimerTask extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    crossfade();
                }
            });
        }
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("Req Code", "" + requestCode);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length == 4 &&
                    grantResults[0] == MockPackageManager.PERMISSION_GRANTED)  /*&&
                    grantResults[1] == MockPackageManager.PERMISSION_GRANTED &&
                    grantResults[2] == MockPackageManager.PERMISSION_GRANTED &&
                    grantResults[3] == MockPackageManager.PERMISSION_GRANTED) */ {

                // Success Stuff here
                Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();

            }else {

                Toast.makeText(getApplicationContext(), "Permission not granted. ", Toast.LENGTH_SHORT).show();
            }
        }

    }
    /*private boolean canAccessLocation() {
        return(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION));
    }

    private boolean hasPermission(String perm) {
        return(PackageManager.PERMISSION_GRANTED==checkSelfPermission(perm));
    }*/

}
