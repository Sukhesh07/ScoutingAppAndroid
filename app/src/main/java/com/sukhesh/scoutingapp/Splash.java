    package com.sukhesh.scoutingapp;

    import android.content.Intent;
    import android.os.Bundle;
    import android.os.Handler;
    import android.view.WindowManager;

    import androidx.appcompat.app.AppCompatActivity;

    public class Splash extends AppCompatActivity {
        //Loading screen lmaoo its literally just for looks its not actually waiting for anything to load
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //hide annoying toolbar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Run the splash screen for 4 seconds and switch to main activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
}