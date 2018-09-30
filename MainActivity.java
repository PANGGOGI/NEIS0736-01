package com.etrangroup.etranprom;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Context context;


    ImageView imageHighBeam;
    ImageView imageDashboard;
    ImageView imageRangeScale;
    ImageView imageTurnsignal;

    Button btnRideMode;

    private PopupMenu mPopupMenu;

    AlphaAnimation animation1;
    AlphaAnimation animation2;
    AlphaAnimation animation3;
    int speedCurrent;
    int speedPrevious;
    int speedMode = 0;
    int loopTurnsignallight = 0;
    int signallight = 0;
    int signallight_left = 10;
    int signallight_right = 20;
    int signallight_hazard = 30;

    boolean enableHighbeam = false;

    CountDownTimer cc;

    boolean enableGPS = false;
    boolean isChangeMode = false;

    int RIDEMODE = 0;
    int RIDEMODE_ECO = 0;
    int RIDEMODE_SPORT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        enableGPS = true;
        RIDEMODE = RIDEMODE_ECO;

//        TextView txtSpeed = (TextView) findViewById(R.id.txtSpeed);
        TextView txtSpeedUnit = (TextView) findViewById(R.id.txtSpeedUnit);
        imageHighBeam = (ImageView)findViewById(R.id.imageHighBeam);
        imageDashboard = (ImageView)findViewById(R.id.imageDashboard);
        imageRangeScale = (ImageView)findViewById(R.id.imageRangeScale);
        imageTurnsignal = (ImageView)findViewById(R.id.imageTurnsignal);
        imageDashboard.setImageResource(R.drawable.img_dash_guage_green);

        if(speedMode == 0) {
            txtSpeedUnit.setText("km/h");
            imageRangeScale.setImageResource(R.drawable.img_dash_guage_range);
        } else {
            txtSpeedUnit.setText("MPH");
            imageRangeScale.setImageResource(R.drawable.img_dash_guage_range_mile);
        }

        ImageButton imageButton = (ImageButton) findViewById(R.id.btnMenu);
        mPopupMenu = new PopupMenu(this, imageButton);
        MenuInflater menuInflater = mPopupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.mainmenu, mPopupMenu.getMenu());
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupMenu.show();
            }
        });


        cc = new CountDownTimer(500,1000) {

            @Override
            public void onTick(long millisUntilFinished) {}

            @Override
            public void onFinish() {
                if(loopTurnsignallight == 0) {
                    imageTurnsignal.setImageResource(0);
                    loopTurnsignallight++;
                } else {
                    if(signallight == signallight_left) {
                        imageTurnsignal.setImageResource(R.drawable.img_dash_turnsignal_left);
                    } else if(signallight == signallight_right) {
                        imageTurnsignal.setImageResource(R.drawable.img_dash_turnsignal_right);
                    }else if(signallight == signallight_hazard) {
                        imageTurnsignal.setImageResource(R.drawable.img_dash_turnsignal_hazard);
                    }
                    loopTurnsignallight = 0;
                }
                start();
            }
        };

        mPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
//                Toast.makeText(getApplicationContext(),
//                        item.getTitle(), Toast.LENGTH_SHORT).show();
                //String iid = item.getItemId();
                if(item.getItemId() == R.id.menu_turnleft)
                {
                    cc.cancel();
                    signallight = signallight_left;
                    cc.start();
                } else if(item.getItemId() == R.id.menu_turnright)
                {
                    cc.cancel();
                    signallight = signallight_right;
                    cc.start();
                }else if(item.getItemId() == R.id.menu_turnhazard)
                {
                    cc.cancel();
                    signallight = signallight_hazard;
                    cc.start();
                }else if(item.getItemId() == R.id.menu_light_highbeam)
                {
                    if(enableHighbeam) {
                        enableHighbeam = false;
                        imageHighBeam.setImageResource(R.drawable.img_dash_guage_highbeam_off);
                    } else {
                        enableHighbeam = true;
                        imageHighBeam.setImageResource(R.drawable.img_dash_guage_highbeam_on);
                    }
                }else if(item.getItemId() == R.id.menu_reset)
                {
                    cc.cancel();
                    signallight = 0;
                    imageTurnsignal.setImageResource(0);

                    enableHighbeam = false;
                    imageHighBeam.setImageResource(R.drawable.img_dash_guage_highbeam_off);
                }





                return true;
            }
        });

        LocationManager locationManager = (LocationManager) this .getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) { location.getLatitude();

                if(enableGPS) {
                    int speedKMH = (int) ((location.getSpeed() * 3600) / 1000);
                    int speedMile = (int) (location.getSpeed() * 2.2369);

                    speedPrevious = speedCurrent;
                    speedCurrent = speedKMH;

                    //Log.d("ETRAN",String.valueOf(speedCurrent));

                    TextView txtSpeed = (TextView) findViewById(R.id.txtSpeed);
                    TextView txtSpeedUnit = (TextView) findViewById(R.id.txtSpeedUnit);

                    if (speedMode == 0) {
                        txtSpeed.setText("" + speedKMH + "");
                        txtSpeedUnit.setText("km/h");
                    } else {
                        txtSpeed.setText("" + speedMile + "");
                        txtSpeedUnit.setText("MPH");
                    }

                    if(speedCurrent > speedPrevious)
                    {
                        if(speedCurrent >= 60)
                        {
                            imageDashboard.setImageResource(R.drawable.img_dash_guage_red);
                        } else if(speedCurrent >= 50)
                        {
                            if(RIDEMODE == RIDEMODE_ECO)
                            {
                                imageDashboard.setImageResource(R.drawable.img_dash_guage_blue);
                            } else {
                                imageDashboard.setImageResource(R.drawable.img_dash_guage_orange);
                            }
                        } else {

                            if(RIDEMODE == RIDEMODE_ECO)
                            {
                                imageDashboard.setImageResource(R.drawable.img_dash_guage_green);
                            } else {
                                imageDashboard.setImageResource(R.drawable.img_dash_guage_blue);
                            }

                        }
                    } else if(speedCurrent < speedPrevious)
                    {
                        if(speedCurrent >= 60)
                        {
                            imageDashboard.setImageResource(R.drawable.img_dash_guage_red);
                        } else if(speedCurrent >= 50)
                        {
                            if(RIDEMODE == RIDEMODE_ECO)
                            {
                                imageDashboard.setImageResource(R.drawable.img_dash_guage_blue);
                            } else {
                                imageDashboard.setImageResource(R.drawable.img_dash_guage_orange);
                            }
                        } else {
                            if(RIDEMODE == RIDEMODE_ECO)
                            {
                                imageDashboard.setImageResource(R.drawable.img_dash_guage_green);
                            } else {
                                imageDashboard.setImageResource(R.drawable.img_dash_guage_blue);
                            }
                        }
                    } else {
                        if(speedCurrent >= 60)
                        {
                            imageDashboard.setImageResource(R.drawable.img_dash_guage_red);
                        } else if(speedCurrent >= 50)
                        {
                            if(RIDEMODE == RIDEMODE_ECO)
                            {
                                imageDashboard.setImageResource(R.drawable.img_dash_guage_blue);
                            } else {
                                imageDashboard.setImageResource(R.drawable.img_dash_guage_orange);
                            }
                        } else {
                            if(RIDEMODE == RIDEMODE_ECO)
                            {
                                imageDashboard.setImageResource(R.drawable.img_dash_guage_green);
                            } else {
                                imageDashboard.setImageResource(R.drawable.img_dash_guage_blue);
                            }
                        }
                    }
                }
            }

            public void onStatusChanged(String provider, int status, Bundle extras) { }
            public void onProviderEnabled(String provider) { }
            public void onProviderDisabled(String provider) { }

        };

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    123);

        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        Animation myFadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fadeout);

        animation1 = new AlphaAnimation(0.0f, 1.0f);
        animation1.setDuration(2000);
        animation1.setStartOffset(500);

        animation1.setAnimationListener(new Animation.AnimationListener(){

            @Override
            public void onAnimationEnd(Animation arg0) {

            }

            @Override
            public void onAnimationRepeat(Animation arg0) {

            }

            @Override
            public void onAnimationStart(Animation arg0) {

            }

        });


        animation2 = new AlphaAnimation(0.0f, 1.0f);
        animation2.setDuration(1500);
        animation2.setStartOffset(500);

        animation2.setAnimationListener(new Animation.AnimationListener(){

            @Override
            public void onAnimationEnd(Animation arg0) {

            }

            @Override
            public void onAnimationRepeat(Animation arg0) {

            }

            @Override
            public void onAnimationStart(Animation arg0) {

            }

        });

        animation3 = new AlphaAnimation(0.0f, 1.0f);
        animation3.setDuration(1500);
        animation3.setStartOffset(1000);

        animation3.setAnimationListener(new Animation.AnimationListener(){

            @Override
            public void onAnimationEnd(Animation arg0) {

            }

            @Override
            public void onAnimationRepeat(Animation arg0) {

            }

            @Override
            public void onAnimationStart(Animation arg0) {

            }

        });


        imageHighBeam.startAnimation(animation1);
        imageDashboard.startAnimation(animation2);
        imageRangeScale.startAnimation(animation3);

        btnRideMode = (Button)findViewById(R.id.btnRideMode);
        if(RIDEMODE == RIDEMODE_ECO)
        {
            btnRideMode.setText("ECO");
        } else {
            btnRideMode.setText("SPORT");
        }
        btnRideMode.setOnClickListener( new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RIDEMODE == RIDEMODE_ECO)
                {
                    RIDEMODE = RIDEMODE_SPORT;
                    btnRideMode.setText("SPORT");
                } else {
                    RIDEMODE = RIDEMODE_ECO;
                    btnRideMode.setText("ECO");
                }
            }
        });






    }



    public class CountDown extends CountDownTimer {

        public CountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            speedPrevious = speedCurrent;
            speedCurrent = speedCurrent +1;

            TextView txtSpeed = (TextView) findViewById(R.id.txtSpeed);
            TextView txtSpeedUnit = (TextView) findViewById(R.id.txtSpeedUnit);

            if(speedMode == 0) {
                txtSpeed.setText("" + speedCurrent + "");
                txtSpeedUnit.setText("km/h");
            } else {
                txtSpeed.setText("" + speedCurrent + "");
                txtSpeedUnit.setText("MPH");
            }

            if(speedCurrent > speedPrevious)
            {
                if(speedCurrent >= 60)
                {
                    imageDashboard.setImageResource(R.drawable.img_dash_guage_red);
                } else if(speedCurrent >= 50)
                {
                    if(RIDEMODE == RIDEMODE_ECO)
                    {
                        imageDashboard.setImageResource(R.drawable.img_dash_guage_blue);
                    } else {
                        imageDashboard.setImageResource(R.drawable.img_dash_guage_orange);
                    }
                } else {

                    if(RIDEMODE == RIDEMODE_ECO)
                    {
                        imageDashboard.setImageResource(R.drawable.img_dash_guage_green);
                    } else {
                        imageDashboard.setImageResource(R.drawable.img_dash_guage_blue);
                    }

                }
            } else if(speedCurrent < speedPrevious)
            {
                if(speedCurrent < 60)
                {
                    imageDashboard.setImageResource(R.drawable.img_dash_guage_red);
                } else if(speedCurrent < 50)
                {
                    if(RIDEMODE == RIDEMODE_ECO)
                    {
                        imageDashboard.setImageResource(R.drawable.img_dash_guage_blue);
                    } else {
                        imageDashboard.setImageResource(R.drawable.img_dash_guage_orange);
                    }
                } else {
                    if(RIDEMODE == RIDEMODE_ECO)
                    {
                        imageDashboard.setImageResource(R.drawable.img_dash_guage_green);
                    } else {
                        imageDashboard.setImageResource(R.drawable.img_dash_guage_blue);
                    }
                }
            }
        }

        @Override
        public void onFinish() {

        }
    }

}
