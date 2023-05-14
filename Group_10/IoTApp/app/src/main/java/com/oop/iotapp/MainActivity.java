package com.oop.iotapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private static final String TAG = "MainActivity";
    private MaterialCardView cvUser, cvLight, cvHeater, cvTemperature, cvCurtain;
    private TextView tvWelcome;

    private String user = "";

    private String uid;

    private String port, uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent loginIntent = getIntent();
        uid = loginIntent.getStringExtra("uid");
//        Log.d("EMAIL", email);
//        Toast.makeText(this, uid, Toast.LENGTH_SHORT).show();

        initViews();

        clickCardViews();
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_user_setting:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("THÊM THIẾT BỊ");

                View view = getLayoutInflater().inflate(R.layout.layout_setting, null);

                EditText et_uri = view.findViewById(R.id.edittext_uri);
                EditText et_port = view.findViewById(R.id.edittext_port);
                Button bt_setting = view.findViewById(R.id.button_setting_confirm);
                bt_setting.setOnClickListener(e -> {
                    port = et_port.getText().toString();
                    uri = et_uri.getText().toString();
                    Toast.makeText(this, "Đã xác nhận cài đặt", Toast.LENGTH_SHORT).show();
                });

                dialog.setView(view);

                dialog.show();
                return true;
            case R.id.menu_user_logout:
                FirebaseAuth.getInstance().signOut();
                Intent intentUser = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentUser);
                return true;
            default:
                return false;
        }
    }

    private void clickCardViews() {
        Log.d(TAG, "clickCardViews: Stared");

        cvUser.setOnClickListener(e -> {

            PopupMenu popupMenu = new PopupMenu(this, findViewById(R.id.cardview_user));
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.inflate(R.menu.menu_user);
            popupMenu.show();
        });

        cvLight.setOnClickListener(e -> {
            Intent intentLight = new Intent(MainActivity.this, LightActivity.class);
            intentLight.putExtra("uid", uid);
            intentLight.putExtra("port", port);
            intentLight.putExtra("uri", uri);
            startActivity(intentLight);
        });

        cvHeater.setOnClickListener(e -> {
            Intent intentHeater = new Intent(MainActivity.this, HeaterActivity.class);
            intentHeater.putExtra("uid", uid);
            intentHeater.putExtra("port", port);
            intentHeater.putExtra("uri", uri);
            startActivity(intentHeater);
        });

        cvTemperature.setOnClickListener(e -> {
            Intent intentTemperature = new Intent(MainActivity.this, TemperatureActivity.class);
            intentTemperature.putExtra("uid", uid);
            intentTemperature.putExtra("port", port);
            intentTemperature.putExtra("uri", uri);
            startActivity(intentTemperature);
        });


        cvCurtain.setOnClickListener(e -> {
            Intent intentCurtain = new Intent(MainActivity.this, CurtainsActivity.class);
            intentCurtain.putExtra("uid", uid);
            intentCurtain.putExtra("port", port);
            intentCurtain.putExtra("uri", uri);
            startActivity(intentCurtain);
        });

    }

    private void initViews() {
        Log.d(TAG, "initViews: Started");

        cvUser = findViewById(R.id.cardview_user);
        cvLight = findViewById(R.id.cardview_light);
        cvHeater = findViewById(R.id.cardview_heater);
        cvTemperature = findViewById(R.id.cardview_temperature);
        cvCurtain = findViewById(R.id.cardview_curtain);

        tvWelcome = findViewById(R.id.textview_welcome);
        tvWelcome.setText("Welcome " + user);
    }

}