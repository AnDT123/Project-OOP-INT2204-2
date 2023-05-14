package com.oop.iotapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.slider.Slider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.ArrayList;
import java.util.List;

public class CurtainsActivity extends AppCompatActivity{

    private ArrayAdapter<String> adapter = null;
    private List<String> listDevices = new ArrayList<>();
    private DatabaseReference myRef;

    private AutoCompleteTextView actv_devices;
    private MaterialButton bt_addDevice, bt_deleteDevice, bt_statusOn, bt_statusOff, bt_percentConfirm, bt_autoOn, bt_autoOff;
    private MaterialCardView cv_back;
    private Slider sl_percent;

    private MqttHandler mqttHandler;

    private String uid, port = "192.168.1.96", uri = "1883";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curtains);

        uid = getIntent().getStringExtra("uid");
        port = getIntent().getStringExtra("port");
        uri = getIntent().getStringExtra("uri");

        try {
            mqttHandler  = new MqttHandler();
            mqttHandler.connect("tcp://"+uri+":"+port, "curtains", this.getApplicationContext());
        } catch (Exception e){
            e.printStackTrace();
        }

        initViews();


        myRef = FirebaseDatabase.getInstance("https://ntiot-741e0-default-rtdb.asia-southeast1.firebasedatabase.app").getReference(uid+"/Curtains");

//        Toast.makeText(this, uid, Toast.LENGTH_SHORT).show();

        getListCurtains();

        curtainsTasks();
    }

    private void curtainsTasks() {

        Log.e(TAG, "curtainsTasks: Start");

        Toast.makeText(this, "Vui lòng chọn thiết bị", Toast.LENGTH_SHORT).show();

        cv_back.setOnClickListener(e -> {
            Intent intentBack = new Intent(this, MainActivity.class);
            startActivity(intentBack);
            this.finish();
        });

        bt_addDevice.setOnClickListener(e -> {
            addDevice();
        });

        bt_deleteDevice.setOnClickListener(e -> {
            deleteDevice();
        });

        bt_statusOn.setOnClickListener(e -> {
            statusOn();
        });

        bt_statusOff.setOnClickListener(e -> {
            statusOff();
        });

        bt_percentConfirm.setOnClickListener(e -> {
            setPercent();
        });

        bt_autoOn.setOnClickListener(e -> {
            autoOn();
        });

        bt_autoOff.setOnClickListener(e -> {
            autoOff();
        });
    }

    private void setPercent() {
        Log.e(TAG, "setTemperature: Start");
        String selected = actv_devices.getText().toString();
        Long percent = (long) sl_percent.getValue();
        myRef.child(selected).child("percent").setValue(percent);
        mqttHandler.publish("curtains/"+selected+"/percent", Long.toString(percent));
    }

    private void autoOff() {
        Log.e(TAG, "bt_autoOff: Start");
        String selected = actv_devices.getText().toString();
        if (!selected.equals("")) {
            myRef.child(selected).child("autoMode").setValue(0L);
        }
        mqttHandler.publish("curtains/"+selected+"/auto_mode", "0");
    }

    private void autoOn() {
        Log.e(TAG, "bt_autoOn: Start");
        String selected = actv_devices.getText().toString();
        if (!selected.equals("")) {
            myRef.child(selected).child("autoMode").setValue(1L);
            mqttHandler.publish("curtains/"+selected+"/auto_mode", "1");
        }
    }

    private void statusOff() {
        Log.e(TAG, "statusOff: Start");
        String selected = actv_devices.getText().toString();
        if (!selected.equals("")) {
            myRef.child(selected).child("status").setValue(0L);
            mqttHandler.publish("curtains/"+selected+"/status", "0");
        }
    }

    private void statusOn() {
        Log.e(TAG, "statusOn: Start");
        String selected = actv_devices.getText().toString();
        if (!selected.equals("")) {
            myRef.child(selected).child("status").setValue(1L);
            mqttHandler.publish("curtains/"+selected+"/status", "1");
        }
    }

    private void deleteDevice() {
        Log.e(TAG, "deleteDevice: Start");
        String selected = actv_devices.getText().toString();
        if (!selected.equals("")) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(CurtainsActivity.this);
            dialog.setTitle("XÓA THIẾT BỊ");
            dialog.setMessage("Bạn có chắc muốn xóa " + selected + " không?");
            dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(CurtainsActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    myRef.child(selected).removeValue();
                    finish();
                    startActivity(getIntent());
                }
            });
            dialog.show();
        }

    }

    private void addDevice() {
        Log.e(TAG, "addDevice: Start");
        AlertDialog.Builder dialog = new AlertDialog.Builder(CurtainsActivity.this);
        dialog.setTitle("THÊM THIẾT BỊ");

        View view = getLayoutInflater().inflate(R.layout.layout_add_device, null);

        EditText et_deviceName = view.findViewById(R.id.edittext_name_add);
        Button bt_addDevice = view.findViewById(R.id.button_add_add);
        bt_addDevice.setOnClickListener(e -> {
            String newDevice = et_deviceName.getText().toString();
            CurtainsData curtainsData = new CurtainsData(newDevice, 0L, 0L, 0L);
            myRef.child(newDevice).setValue(curtainsData);
            Toast.makeText(CurtainsActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(getIntent());
        });

        dialog.setView(view);

        dialog.show();
    }

    private void getListCurtains() {
        Log.e(TAG, "getListCurtains: Start");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String curtainsData = dataSnapshot.getKey();
                    listDevices.add(curtainsData);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CurtainsActivity.this, "Không thể lấy dữ liệu từ server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        Log.e(TAG, "initViews: Start");

        actv_devices = findViewById(R.id.autocompletetextview_devices_curtains);
        bt_addDevice = findViewById(R.id.button_add_device_curtains);
        bt_deleteDevice = findViewById(R.id.button_delete_device_curtains);
        bt_statusOn = findViewById(R.id.button_status_on_curtains);
        bt_statusOff = findViewById(R.id.button_status_off_curtains);
        bt_percentConfirm = findViewById(R.id.button_percent_confirm_curtains);
        bt_autoOn = findViewById(R.id.button_autoMode_on_curtains);
        bt_autoOff = findViewById(R.id.button_autoMode_off_curtains);
        cv_back = findViewById(R.id.cardview_back_curtains);
        sl_percent = findViewById(R.id.slider_percent_curtains);

        adapter = new ArrayAdapter<>(this, R.layout.dropdown_item, listDevices);
        actv_devices.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mqttHandler.disconnect();
    }
}