package com.yuanshenbin;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import com.yuanshenbin.basic.util.Utils;
import com.yuanshenbin.test.R;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * author : yuanshenbin
 * time   : 2023/10/19
 * desc   :
 */
public  class TestActivity  extends AppCompatActivity {

    private static final String ACTION_USB_PERMISSION = "com.yuanshenbin.test.USB_PERMISSION";

    PendingIntent mPermissionIntent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_mac).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer.parseInt("-");
                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String macAddress = wifiInfo.getMacAddress();
                ((TextView) findViewById(R.id.tv_mac)).setText(macAddress);
            }
        });
        findViewById(R.id.btn_ssid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ssid = wifiInfo.getSSID();
                ((TextView) findViewById(R.id.tv_ssid)).setText(ssid);
            }
        });
        findViewById(R.id.btn_bssid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ssid = wifiInfo.getBSSID();
                ((TextView) findViewById(R.id.tv_bssid)).setText(ssid);
            }
        });
        initUsbAccessoryWidget();
    }

    private void initUsbAccessoryWidget() {

        mPermissionIntent = PendingIntent.getBroadcast(this, 0,
                new Intent(ACTION_USB_PERMISSION), 0);
        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        filter.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
        filter.addAction(UsbManager.ACTION_USB_ACCESSORY_ATTACHED);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);

        registerReceiver(mUsbReceiver, filter);

    }

    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
                boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
                if (usbCharge) {

                    Toast.makeText(context, "USB连接", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(context, "USB断开", Toast.LENGTH_SHORT).show();
                }
            }

            if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
                UsbDevice device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                if (device != null) {
                    int usbClass = device.getDeviceClass();
                    if (usbClass == UsbConstants.USB_CLASS_PER_INTERFACE) {
                           Toast.makeText(context,  "USB连接用于传输数据", Toast.LENGTH_SHORT).show();
                    } else if (usbClass == UsbConstants.USB_CLASS_COMM) {
                           Toast.makeText(context,  "USB连接用于串行通信", Toast.LENGTH_SHORT).show();
                    } else if (usbClass == UsbConstants.USB_CLASS_HUB) {
                           Toast.makeText(context,  "USB连接用于充电", Toast.LENGTH_SHORT).show();
                    } else {
                           Toast.makeText(context,  "其他类型的USB连接", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    };

}
