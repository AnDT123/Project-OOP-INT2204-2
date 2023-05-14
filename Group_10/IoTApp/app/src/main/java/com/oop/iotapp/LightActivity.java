package com.oop.iotapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Telephony;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.slider.Slider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LightActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter = null;
    private List<String> listDevices = new ArrayList<>();

    private AutoCompleteTextView actv_devices;
    private MaterialButton bt_addDevice, bt_deleteDevice, bt_statusOn, bt_statusOff,
            bt_autoOn, bt_autoOff, bt_timerSetStart, bt_timerSetStop, bt_timerOn, bt_timerOff, bt_confirmLightIntensity;
    private Slider  sl_lightIntensity;
    private MaterialCardView cv_back;
    private TextView tv_timerSetStart, tv_timerSetStop;

    private DatabaseReference myRef;

    private String uid, port = "192.168.1.96", uri = "1883";

    private MqttHandler mqttHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        uid = getIntent().getStringExtra("uid");
        port = getIntent().getStringExtra("port");
        uri = getIntent().getStringExtra("uri");

        try {
            mqttHandler  = new MqttHandler();
            mqttHandler.connect("tcp://"+uri+":"+port, "light", this.getApplicationContext());
        } catch (Exception e){
            e.printStackTrace();
        }

        initViews();

        myRef = FirebaseDatabase.getInstance("https://ntiot-741e0-default-rtdb.asia-southeast1.firebasedatabase.app").getReference(uid+"/Light");

        getListLight();

        lightTasks();
    }

    private void lightTasks() {
        Log.e(TAG, "lightTasks: Start");

        Toast.makeText(this, "Vui lòng chọn thiết bị", Toast.LENGTH_SHORT).show();

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

        bt_autoOn.setOnClickListener(e -> {
            bt_autoOn();
        });

        bt_autoOff.setOnClickListener(e -> {
            bt_autoOff();
        });

        bt_confirmLightIntensity.setOnClickListener(e -> {
            setLightIntensity();
        });

        bt_timerOn.setOnClickListener(e -> {
            timerOn();
        });

        bt_timerOff.setOnClickListener(e -> {
            timerOff();
        });

        bt_timerSetStop.setOnClickListener(e -> {
            setStop();
        });

        bt_timerSetStart.setOnClickListener(e -> {
            setStart();
        });

        cv_back.setOnClickListener(e -> {
            Intent intentBack = new Intent(LightActivity.this, MainActivity.class);
            startActivity(intentBack);
        });

    }

    private void timerOff() {
        Log.e(TAG, "timerOff: Start");
        String selected = actv_devices.getText().toString();
        if (!selected.equals("")) {
            myRef.child(selected).child("timerMode").setValue(0L);
            mqttHandler.publish("light/"+selected+"/timer_mode", "0");
        }
//        bt_timerOff.setBackgroundColor(ContextCompat.getColor(this ,R.color.second_light));
//        bt_timerOff.setClickable(false);
//
//        bt_timerOn.setBackgroundColor(ContextCompat.getColor(this ,R.color.primary_light));
//        bt_timerOn.setClickable(true);
    }

    private void timerOn() {
        Log.e(TAG, "timerOn: Start");
        String selected = actv_devices.getText().toString();
        if (!selected.equals("")) {
            myRef.child(selected).child("timerMode").setValue(1L);
            mqttHandler.publish("light/"+selected+"/timer_mode", "1");
        }
//        bt_timerOn.setBackgroundColor(ContextCompat.getColor(this ,R.color.second_light));
//        bt_timerOn.setClickable(false);
//
//        bt_timerOff.setBackgroundColor(ContextCompat.getColor(this ,R.color.primary_light));
//        bt_timerOff.setClickable(true);
    }

    private void setLightIntensity() {
        Log.e(TAG, "setLightIntensity: Start");
        String selected = actv_devices.getText().toString();
        Long lightIntensity = (long) sl_lightIntensity.getValue();
        myRef.child(selected).child("lightIntensity").setValue(lightIntensity);
        mqttHandler.publish("light/"+selected+"/light_intensity", Long.toString(lightIntensity));
    }

    private void bt_autoOff() {
        Log.e(TAG, "bt_autoOff: Start");
        String selected = actv_devices.getText().toString();
        if (!selected.equals("")) {
            myRef.child(selected).child("autoMode").setValue(0L);
            mqttHandler.publish("light/"+selected+"/status", "0");
        }
//        bt_autoOff.setBackgroundColor(ContextCompat.getColor(this ,R.color.second_light));
//        bt_autoOff.setClickable(false);
//
//        bt_autoOn.setBackgroundColor(ContextCompat.getColor(this ,R.color.primary_light));
//        bt_autoOn.setClickable(true);
    }

    private void bt_autoOn() {
        Log.e(TAG, "bt_autoOn: Start");
        String selected = actv_devices.getText().toString();
        if (!selected.equals("")) {
            myRef.child(selected).child("autoMode").setValue(1L);
            mqttHandler.publish("light/"+selected+"/auto_mode", "1");

        }
//        bt_autoOn.setBackgroundColor(ContextCompat.getColor(this ,R.color.second_light));
//        bt_autoOn.setClickable(false);
//
//        bt_autoOff.setBackgroundColor(ContextCompat.getColor(this ,R.color.primary_light));
//        bt_autoOff.setClickable(true);
    }

    private void setStop() {
        Log.e(TAG, "setStop: Start");
        String selected = actv_devices.getText().toString();
        final int[] hour = new int[1];
        final int[] minute = new int[1];
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int selectedMinute) {
                hour[0] = hourOfDay;
                minute[0] = selectedMinute;
                tv_timerSetStop.setText(String.format(Locale.getDefault(), "%02d:%02d", hour[0], minute[0]));
                myRef.child(selected).child("timerStop").setValue(String.format(Locale.getDefault(), "%02d:%02d", hour[0], minute[0]));
                mqttHandler.publish("light/"+selected+"/time_stop", String.format(Locale.getDefault(), "%02d:%02d", hour[0], minute[0]));
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour[0], minute[0], true);
        timePickerDialog.setTitle("Chọn thời gian bắt đầu");
        timePickerDialog.show();
    }

    private void setStart() {
        Log.e(TAG, "setStart: Start");
        String selected = actv_devices.getText().toString();
        final int[] hour = new int[1];
        final int[] minute = new int[1];
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int selectedMinute) {
                hour[0] = hourOfDay;
                minute[0] = selectedMinute;
                tv_timerSetStart.setText(String.format(Locale.getDefault(), "%02d:%02d", hour[0], minute[0]));
                myRef.child(selected).child("timerStart").setValue(String.format(Locale.getDefault(), "%02d:%02d", hour[0], minute[0]));
                mqttHandler.publish("light/"+selected+"/time_start", String.format(Locale.getDefault(), "%02d:%02d", hour[0], minute[0]));
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour[0], minute[0], true);
        timePickerDialog.setTitle("Chọn thời gian bắt đầu");
        timePickerDialog.show();
    }

    private void statusOff() {
        Log.e(TAG, "statusOff: Start");
        String selected = actv_devices.getText().toString();
        if (!selected.equals("")) {
            myRef.child(selected).child("status").setValue(0L);
            mqttHandler.publish("light/"+selected+"/status", "0");
        }
//        bt_statusOff.setBackgroundColor(ContextCompat.getColor(this ,R.color.second_light));
//        bt_statusOff.setClickable(false);
//
//        bt_statusOn.setBackgroundColor(ContextCompat.getColor(this ,R.color.primary_light));
//        bt_statusOn.setClickable(true);
    }

    private void statusOn() {
        Log.e(TAG, "statusOn: Start");
        String selected = actv_devices.getText().toString();
        if (!selected.equals("")) {
            myRef.child(selected).child("status").setValue(1L);
            mqttHandler.publish("light/"+selected+"/status", "1");
        }

    }

    private void deleteDevice() {
        Log.e(TAG, "deleteDevice: Start");
        String selected = actv_devices.getText().toString();
        if (!selected.equals("")) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(LightActivity.this);
            dialog.setTitle("XÓA THIẾT BỊ");
            dialog.setMessage("Bạn có chắc muốn xóa " + selected + " không?");
            dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(LightActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(this, "Oke ?", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder dialog = new AlertDialog.Builder(LightActivity.this);
        dialog.setTitle("THÊM THIẾT BỊ");

        View view = getLayoutInflater().inflate(R.layout.layout_add_device, null);

        EditText et_deviceName = view.findViewById(R.id.edittext_name_add);
        Button bt_addDevice = view.findViewById(R.id.button_add_add);
        bt_addDevice.setOnClickListener(e -> {
            String newDevice = et_deviceName.getText().toString();
            LightData lightData = new LightData(newDevice,
                    0L, 0L, 0L, 0L, "00:00", "00:00");
            myRef.child(newDevice).setValue(lightData);
            Toast.makeText(LightActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(getIntent());
        });

        dialog.setView(view);

        dialog.show();
    }

    private void getListLight() {
        Log.e(TAG, "getListLight: Start");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String lightData = dataSnapshot.getKey();
                    listDevices.add(lightData);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LightActivity.this, "Không thể lấy dữ liệu từ server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        Log.e(TAG, "initViews: Start");

        bt_addDevice = findViewById(R.id.button_add_device_light);
        bt_deleteDevice = findViewById(R.id.button_delete_device_light);
        bt_statusOn = findViewById(R.id.button_status_on_light);
        bt_statusOff = findViewById(R.id.button_status_off_light);
        bt_autoOn = findViewById(R.id.button_auto_on_light);
        bt_autoOff = findViewById(R.id.button_auto_off_light);
        bt_timerSetStart = findViewById(R.id.button_timer_start_light);
        bt_timerSetStop = findViewById(R.id.button_timer_stop_light);
        bt_timerOn = findViewById(R.id.button_timer_on_light);
        bt_timerOff = findViewById(R.id.button_timer_off_light);
        bt_confirmLightIntensity = findViewById(R.id.button_confirm_lightIntensity_light);

        sl_lightIntensity = findViewById(R.id.slider_lightIntensity_light);

        cv_back = findViewById(R.id.cardview_back_light);

        tv_timerSetStart = findViewById(R.id.textview_timer_start_light);
        tv_timerSetStop = findViewById(R.id.textview_timer_stop_light);

        actv_devices = findViewById(R.id.autocompletetextview_devices_light);
        adapter = new ArrayAdapter<>(this, R.layout.dropdown_item, listDevices);
        actv_devices.setAdapter(adapter);
    }
}