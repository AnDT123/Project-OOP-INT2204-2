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

public class TemperatureActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter = null;
    private List<String> listDevices = new ArrayList<>();

    private AutoCompleteTextView actv_devices;
    private MaterialButton bt_addDevice, bt_deleteDevice, bt_statusOn, bt_statusOff,
            bt_autoOn, bt_autoOff, bt_timerSetStart, bt_timerSetStop, bt_timerOn, bt_timerOff, bt_confirmTemperature, bt_confirmFanSpeed;
    private Slider sl_temperature, sl_fanSpeed;
    private MaterialCardView cv_back;
    private TextView tv_timerSetStart, tv_timerSetStop;

    private DatabaseReference myRef;

    private String uid, port = "192.168.1.96", uri = "1883";

    private MqttHandler mqttHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        uid = getIntent().getStringExtra("uid");
        port = getIntent().getStringExtra("port");
        uri = getIntent().getStringExtra("uri");

        try {
            mqttHandler  = new MqttHandler();
            mqttHandler.connect("tcp://"+uri+":"+port, "temperature", this.getApplicationContext());
        } catch (Exception e){
            e.printStackTrace();
        }

        initViews();


        myRef = FirebaseDatabase.getInstance("https://ntiot-741e0-default-rtdb.asia-southeast1.firebasedatabase.app").getReference(uid+"/Temperature");

        getListTemperature();

        temperatureTasks();
    }

    private void temperatureTasks() {
        Log.e(TAG, "temperatureTasks: Start");

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

        bt_confirmTemperature.setOnClickListener(e -> {
            setTemperature();
        });

        bt_confirmFanSpeed.setOnClickListener(e -> {
            setFanSpeed();
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
            Intent intentBack = new Intent(TemperatureActivity.this, MainActivity.class);
            startActivity(intentBack);
            this.finish();
        });

    }

    private void timerOff() {
        Log.e(TAG, "timerOff: Start");
        String selected = actv_devices.getText().toString();
        if (!selected.equals("")) {
            myRef.child(selected).child("timerMode").setValue(0L);
            mqttHandler.publish("temperature/"+selected+"/timer_mode", "0");
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
            mqttHandler.publish("temperature/"+selected+"/timer_mode", "1");
        }
//        bt_timerOn.setBackgroundColor(ContextCompat.getColor(this ,R.color.second_light));
//        bt_timerOn.setClickable(false);
//
//        bt_timerOff.setBackgroundColor(ContextCompat.getColor(this ,R.color.primary_light));
//        bt_timerOff.setClickable(true);
    }

    private void setFanSpeed() {
        Log.e(TAG, "setFanSpeed: Start");
        String selected = actv_devices.getText().toString();
        Long fanSpeed = (long) sl_fanSpeed.getValue();
        myRef.child(selected).child("fanSpeed").setValue(fanSpeed);
        mqttHandler.publish("temperature/"+selected+"/fan_speed", Long.toString(fanSpeed));
    }

    private void setTemperature() {
        Log.e(TAG, "setTemperature: Start");
        String selected = actv_devices.getText().toString();
        Long temperature = (long) sl_temperature.getValue();
        myRef.child(selected).child("temperature").setValue(temperature);
        mqttHandler.publish("temperature/"+selected+"/temperature", Long.toString(temperature));
    }

    private void bt_autoOff() {
        Log.e(TAG, "bt_autoOff: Start");
        String selected = actv_devices.getText().toString();
        if (!selected.equals("")) {
            myRef.child(selected).child("autoMode").setValue(0L);
            mqttHandler.publish("temperature/"+selected+"/auto_mode", "0");
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
            mqttHandler.publish("temperature/"+selected+"/auto_mode", "1");
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
                mqttHandler.publish("temperature/"+selected+"/timer_stop", String.format(Locale.getDefault(), "%02d:%02d", hour[0], minute[0]));
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
                mqttHandler.publish("temperature/"+selected+"/timer_start", String.format(Locale.getDefault(), "%02d:%02d", hour[0], minute[0]));
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
            mqttHandler.publish("temperature/"+selected+"/status", "0");
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
            mqttHandler.publish("temperature/"+selected+"/status", "1");
        }

    }

    private void deleteDevice() {
        Log.e(TAG, "deleteDevice: Start");
        String selected = actv_devices.getText().toString();
        if (!selected.equals("")) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(TemperatureActivity.this);
            dialog.setTitle("XÓA THIẾT BỊ");
            dialog.setMessage("Bạn có chắc muốn xóa " + selected + " không?");
            dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(TemperatureActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
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
        AlertDialog.Builder dialog = new AlertDialog.Builder(TemperatureActivity.this);
        dialog.setTitle("THÊM THIẾT BỊ");

        View view = getLayoutInflater().inflate(R.layout.layout_add_device, null);

        EditText et_deviceName = view.findViewById(R.id.edittext_name_add);

        Button bt_addDevice = view.findViewById(R.id.button_add_add);
        bt_addDevice.setOnClickListener(e -> {
            String newDevice = et_deviceName.getText().toString();
            TemperatureData temperatureData = new TemperatureData(newDevice,
                    0L, 0L, 0L, 0L, 0L, "00:00", "00:00");
            myRef.child(newDevice).setValue(temperatureData);
            Toast.makeText(TemperatureActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(getIntent());
        });

        dialog.setView(view);

        dialog.show();
    }

    private void getListTemperature() {
        Log.e(TAG, "getListCurtains: Start");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String temperatureData = dataSnapshot.getKey();
                    listDevices.add(temperatureData);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TemperatureActivity.this, "Không thể lấy dữ liệu từ server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        Log.e(TAG, "initViews: Start");

        bt_addDevice = findViewById(R.id.button_add_device_temperature);
        bt_deleteDevice = findViewById(R.id.button_delete_device_temperature);
        bt_statusOn = findViewById(R.id.button_status_on_temperature);
        bt_statusOff = findViewById(R.id.button_status_off_temperature);
        bt_autoOn = findViewById(R.id.button_auto_on_temperature);
        bt_autoOff = findViewById(R.id.button_auto_off_temperature);
        bt_timerSetStart = findViewById(R.id.button_timer_start_temperature);
        bt_timerSetStop = findViewById(R.id.button_timer_stop_temperature);
        bt_timerOn = findViewById(R.id.button_timer_on_temperature);
        bt_timerOff = findViewById(R.id.button_timer_off_temperature);
        bt_confirmTemperature = findViewById(R.id.button_confirm_temperature_temperature);
        bt_confirmFanSpeed = findViewById(R.id.button_confirm_fanSpeed_temperature);

        sl_temperature = findViewById(R.id.slider_temperture_temperature);
        sl_fanSpeed = findViewById(R.id.slider_fanSpeed_temperature);

        cv_back = findViewById(R.id.cardview_back_temperature);

        tv_timerSetStart = findViewById(R.id.textview_timer_start_temperature);
        tv_timerSetStop = findViewById(R.id.textview_timer_stop_temperature);

        actv_devices = findViewById(R.id.autocompletetextview_devices_temperature);
        adapter = new ArrayAdapter<>(this, R.layout.dropdown_item, listDevices);
        actv_devices.setAdapter(adapter);
    }
}