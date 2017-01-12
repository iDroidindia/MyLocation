package test_app.pro.mylocation.mylocation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;


//public class SplashActivity extends AppCompatActivity {
public class SplashActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

       // requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
         //       WindowManager.LayoutParams.FLAG_FULLSCREEN);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_main_activity);
  //      this.setTitle("Yu yu");

       /* Fragment Splash_Fragment = new Splash_Fragment();

        if (Splash_Fragment != null) {

            FragmentManager FM_Splash = getSupportFragmentManager();
            FragmentTransaction FT_Splash = FM_Splash.beginTransaction();
            FT_Splash.replace(R.id.fragment_main_layout, Splash_Fragment);
            FT_Splash.commit();
        }*/
        useHandler();
    }

    public void useHandler() {

        Handler mHandler;
        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, 5000);

    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {

           /* SharedPreferences  SP_Intro_Screens = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String Intro_Screens_Status  = SP_Intro_Screens.getString("Intro_Screen_Status", "Show_First_Time");

            if(Intro_Screens_Status.equalsIgnoreCase("Show_First_Time")) {


                Intent I_Show_Intro = new Intent(getApplicationContext(), Act_App_Intro.class);
                startActivity(I_Show_Intro);
                finish();
            }
            else
            { */
                Intent I_SignIn_SignUp_Screens = new Intent(getApplicationContext(), Old_MainActivity.class);
                startActivity(I_SignIn_SignUp_Screens);
                finish();
            }
//
        /*    SharedPreferences SP_SignIn_Fargment = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String SP_Data = SP_SignIn_Fargment.getString("Already_Logged_In", "No_Data");
            String SP_Intro_Screens_Status  = SP_SignIn_Fargment.getString("Intro_Screen_Status", "Show_First_Time");

            if(SP_Data.equalsIgnoreCase("No_Data")) {

                if (SP_Intro_Screens_Status.equalsIgnoreCase("Show_First_Time")) {
                    Intent I_Show_Intro = new Intent(getApplicationContext(), Act_App_Intro.class);
                    startActivity(I_Show_Intro);
                    finish();
                }
                else{
                    Fragment New_Splash_Fragment = new SignIn_Fragment();

                    if (New_Splash_Fragment != null) {

                        FragmentManager FM_SignIn = getSupportFragmentManager();
                        FragmentTransaction FT_SignIn = FM_SignIn.beginTransaction();
                        FT_SignIn.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                        FT_SignIn.replace(R.id.fragment_main_layout, New_Splash_Fragment);
                        FT_SignIn.commit();
                    }
                }

            }
            else
            {
                Fragment New_Splash_Fragment = new SignIn_Fragment();

                if (New_Splash_Fragment != null) {

                    FragmentManager FM_SignIn = getSupportFragmentManager();
                    FragmentTransaction FT_SignIn = FM_SignIn.beginTransaction();
                    FT_SignIn.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                    FT_SignIn.replace(R.id.fragment_main_layout, New_Splash_Fragment);
                    FT_SignIn.commit();
                }
            }
            /*else {

                //Intent I_Logged_In = new Intent(getApplicationContext(), MainActivity.class);
                Intent I_Logged_In = new Intent(getApplicationContext(), Act_App_Intro.class);
                startActivity(I_Logged_In);
                finish();
            }
        }*/
    };

    /*@Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }*/

}

