package com.oop.iotapp;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class MqttHandler {

    private MqttClient client;

    public void connect(String brokerURl, String clientId, Context context){
        try{

            MemoryPersistence persistence = new MemoryPersistence();

            client = new MqttClient(brokerURl, clientId, persistence);

            MqttConnectOptions connectOptions = new MqttConnectOptions();

            connectOptions.setCleanSession(true);

            client.connect(connectOptions);

        } catch (MqttException e){
            e.printStackTrace();
            Toast.makeText(context, "Không thể kết nối tới broker", Toast.LENGTH_SHORT).show();
        }
    }

    public void disconnect(){
        try {
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(String topic, String message){
        try {
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            client.publish(topic, mqttMessage);
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(String topic){
        try {
            client.subscribe(topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
