package com.example.app_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.ByteArrayOutputStream;


public class Control extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private View drawerView;
    private MqttAndroidClient mqttAndroidClient;
    private IMqttToken token;
    private EditText ET_User;
    private ImageView mqtt_image;

    NotificationManager manager;
    NotificationCompat.Builder builder;


    private static String CHANNEL_ID = "channel1";
    private static String CHANEL_NAME = "Channel1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mqttAndroidClient = null;
        setContentView(R.layout.activity_control);
        Button btnopen = (Button) findViewById(R.id.btn_open);
        TextView pwapp = (TextView) findViewById(R.id.pw_app);
        TextView set_user = (TextView) findViewById(R.id.set_user);

        drawerLayout = (DrawerLayout) findViewById(R.id.menu);
        drawerView = (View) findViewById(R.id.drawerView);
        drawerLayout.setDrawerListener(listener);

        ET_User = findViewById(R.id.ET_User);
        mqtt_image = findViewById(R.id.mqtt_img);
        token = null;
        mqttAndroidClient = new MqttAndroidClient(this,  "tcp://" + "54.185.18.26" + ":1883", MqttClient.generateClientId());

        // 2?????? ???????????? : ???????????? ip ?????? , 3?????? ???????????? : client ??? id??? ????????? ???????????? paho ??? ???????????? id??? ??????????????????
        try {
            token = mqttAndroidClient.connect(getMqttConnectionOption());    //mqtttoken ??????????????? ????????? connect option??? ?????????
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    mqttAndroidClient.setBufferOpts(getDisconnectedBufferOptions());    //????????? ???????????????

                    Log.e("Connect_success", "Success");
                    try {
                        mqttAndroidClient.subscribe("common", 0 );   //????????? ???????????? common ?????? ???????????? subscribe???
                        mqttAndroidClient.subscribe("picture",0);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {   //????????? ???????????????
                    Log.e("connect_fail", "Failure " + exception.toString());
                }
            });
        } catch (MqttException e)
        {
            e.printStackTrace();
        }

/*
        try {
            mqttAndroidClient.subscribe("common", 0, new IMqttMessageListener() {
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
*/
        mqttAndroidClient.setCallback(new MqttCallback() {  //?????????????????? ????????? ??????????????????

            @Override
            public void connectionLost(Throwable cause) {
            }

            @Override

            public void messageArrived(String topic, MqttMessage message) throws Exception {    //?????? ???????????? ?????? Callback method
                if (topic.equals("common")){     //topic ?????? ?????????????????? ????????? ?????????????????????
                    String msg = new String(message.getPayload());
                    ET_User.requestFocus();
                    ET_User.setText(msg);
                    Log.e("arrive message : ", msg);
                }
                else if(topic.equals("picture")){
                    byte[] $byteArray = message.getPayload();
                    Bitmap bitmap = BitmapFactory.decodeByteArray( $byteArray, 0, $byteArray.length ) ;
                    mqtt_image.setImageBitmap(bitmap);
                }
            }
            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });

        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        // 2?????? ???????????? : ???????????? ip ?????? , 3?????? ???????????? : client ??? id??? ????????? ???????????? paho ??? ???????????? id??? ??????????????????
        try {
            token = mqttAndroidClient.connect(getMqttConnectionOption());    //mqtttoken ??????????????? ????????? connect option??? ?????????
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    mqttAndroidClient.setBufferOpts(getDisconnectedBufferOptions());    //????????? ???????????????

                    Log.e("Connect_success", "Success");
                    try {
                        mqttAndroidClient.subscribe("common", 0 );   //????????? ???????????? common ?????? ???????????? subscribe???
                        mqttAndroidClient.subscribe("picture",0);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {   //????????? ???????????????
                    Log.e("connect_fail", "Failure " + exception.toString());
                }
            });
        } catch (MqttException e)
        {
            e.printStackTrace();
        }
/*
        try {
            mqttAndroidClient.subscribe("common", 0, new IMqttMessageListener() {
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
*/
        mqttAndroidClient.setCallback(new MqttCallback() {  //?????????????????? ????????? ??????????????????

            @Override
            public void connectionLost(Throwable cause) {
            }

            @Override

            public void messageArrived(String topic, MqttMessage message) throws Exception {    //?????? ???????????? ?????? Callback method
                if (topic.equals("common")){     //topic ?????? ?????????????????? ????????? ?????????????????????
                    String msg = new String(message.getPayload());
                    ET_User.requestFocus();
                    ET_User.setText(msg);
                    Log.e("arrive message : ", msg);
                }
                else if(topic.equals("picture")){
                    showNoti();
                    byte[] $byteArray = message.getPayload();
                    Bitmap bitmap = BitmapFactory.decodeByteArray( $byteArray, 0, $byteArray.length ) ;
                    mqtt_image.setImageBitmap(bitmap);
                }
            }
            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });
        btnopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btn_open:
                        drawerLayout.openDrawer(drawerView);
                }
            }
        });

        set_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent set_user_intent = new Intent(Control.this, User_seting.class);
                startActivity(set_user_intent);
                }
            });



           pwapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Control.this, appLock.class);
                intent.putExtra(app_lock_const.type,app_lock_const.CHANGE_PASSLOCK);
                Control.this.startActivityForResult(intent,app_lock_const.CHANGE_PASSLOCK);
            }
        });
    }


    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {
            drawerLayout.openDrawer(drawerView);
        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {
        }

        @Override
        public void onDrawerStateChanged(int newState) {
        }
    };


    private DisconnectedBufferOptions getDisconnectedBufferOptions() {

        DisconnectedBufferOptions disconnectedBufferOptions = new DisconnectedBufferOptions();

        disconnectedBufferOptions.setBufferEnabled(true);

        disconnectedBufferOptions.setBufferSize(100);

        disconnectedBufferOptions.setPersistBuffer(true);

        disconnectedBufferOptions.setDeleteOldestMessages(false);

        return disconnectedBufferOptions;

    }



    private MqttConnectOptions getMqttConnectionOption() {

        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();

        mqttConnectOptions.setCleanSession(false);

        mqttConnectOptions.setAutomaticReconnect(true);

        mqttConnectOptions.setWill("aaa", "I am going offline".getBytes(), 1, true);

        return mqttConnectOptions;

    }


    public void showNoti(){ builder = null; manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        builder = null;
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            manager.createNotificationChannel(
                    new NotificationChannel(CHANNEL_ID, CHANEL_NAME, NotificationManager.IMPORTANCE_DEFAULT) );
        builder = new NotificationCompat.Builder(this,CHANNEL_ID);
        }else{
            builder = new NotificationCompat.Builder(this);
        }
        builder.setContentTitle("?????? ?????? ??????!");

        builder.setContentText("?????? ????????? ?????? ????????? ?????????????????????.");

        builder.setSmallIcon(R.drawable.notification_icon);

        Notification notification = builder.build();

        manager.notify(1,notification);

    }

    public byte[] bitmapToByteArray( Bitmap bitmap ) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream() ;
        bitmap.compress( Bitmap.CompressFormat.JPEG, 100, stream) ;
        byte[] byteArray = stream.toByteArray() ;
        return byteArray ;
    }


}

