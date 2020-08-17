package com.example.lightcontrol;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.UUID;

public class Controlling extends Activity implements BTInterface {
    private static final String TAG = "BlueTest5-Controlling";
    private int mMaxChars = 50000;//Default//change this to string..........
    private UUID mDeviceUUID;
    private BluetoothSocket mBTSocket;
    private ReadInput mReadThread = null;

    private boolean mIsUserInitiatedDisconnect = false;
    private boolean mIsBluetoothConnected = false;


    private Button mBtnDisconnect;
    private BluetoothDevice mDevice;

    final static  String str1 = "a";//strefa1
    final static  String str2 = "b";//strefa2
    final static  String str3 = "c"; //strefa3
    final static  String str4 = "d"; //strefa4
    final static  String zaw1 = "e"; //zaw1on
    final static  String zaw1off = "E"; //zaw1off
    final static  String zaw2 = "f"; //zaw2on
    final static  String zaw2off = "F"; //zaw2off
    final static  String zaw3 = "g"; //zaw3on
    final static  String zaw3off = "G"; //zaw3off
    final static  String zaw4 = "h"; //zaw4on
    final static  String zaw4off = "H"; //zaw4off
    final static  String pomp = "i"; //pompaon
    final static  String pompoff = "I"; //pompaoff

    private ProgressDialog progressDialog;
    Button btnstr1,btnstr2, btnstr3, btnstr4 ;
    Switch swZaw1, swZaw2, swZaw3, swZaw4, swPomp;
    private Handler handler;
    private TextView dataTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controlling);
        handler = new Handler();
        ActivityHelper.initialize(this);
        // mBtnDisconnect = (Button) findViewById(R.id.btnDisconnect);
        btnstr1=(Button)findViewById(R.id.str1);
        btnstr2=(Button)findViewById(R.id.str2);
        btnstr3 =(Button)findViewById(R.id.str3);
        btnstr4 = (Button)findViewById(R.id.str4);
        swZaw1 = (Switch)findViewById(R.id.zaw1);
        swZaw2 = (Switch)findViewById(R.id.zaw2);
        swZaw3 = (Switch)findViewById(R.id.zaw3);
        swZaw4 = (Switch)findViewById(R.id.zaw4);
        swPomp = (Switch)findViewById(R.id.pomp);
        dataTV = (TextView) findViewById(R.id.datasTextView);




        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        mDevice = b.getParcelable(MainActivity.DEVICE_EXTRA);
        mDeviceUUID = UUID.fromString(b.getString(MainActivity.DEVICE_UUID));
        mMaxChars = b.getInt(MainActivity.BUFFER_SIZE);

        Log.d(TAG, "Ready");

        swZaw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (swZaw1.isChecked()){
                    try {
                        mBTSocket.getOutputStream().write(zaw1.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        mBTSocket.getOutputStream().write(zaw1off.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        swZaw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (swZaw2.isChecked()){
                    try {
                        mBTSocket.getOutputStream().write(zaw2.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        mBTSocket.getOutputStream().write(zaw2off.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        swZaw3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (swZaw3.isChecked()){
                    try {
                        mBTSocket.getOutputStream().write(zaw3.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        mBTSocket.getOutputStream().write(zaw3off.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        swZaw4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (swZaw4.isChecked()){
                    try {
                        mBTSocket.getOutputStream().write(zaw4.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        mBTSocket.getOutputStream().write(zaw4off.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        swPomp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (swPomp.isChecked()){
                    try {
                        mBTSocket.getOutputStream().write(pomp.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        mBTSocket.getOutputStream().write(pompoff.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        btnstr1.setOnClickListener(new View.OnClickListener()
        {


            public void onClick(View v) {
// TODO Auto-generated method stub



                try {
                    mBTSocket.getOutputStream().write(str1.getBytes());

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }});

        btnstr2.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub



                try {
                    mBTSocket.getOutputStream().write(str2.getBytes());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }});

        btnstr3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mBTSocket.getOutputStream().write(str3.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnstr4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mBTSocket.getOutputStream().write(str4.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void receivedStr(final String strInput) {
        if (handler!=null)
        {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    dataTV.setText(strInput);
                }
            });
        }
    }

    private class ReadInput implements Runnable {

        private final BTInterface o;
        private boolean bStop = false;
        private Thread t;

        public ReadInput(BTInterface aInterface) {
            o = aInterface;
            t = new Thread(this, "Input Thread");
            t.start();
        }

        public boolean isRunning() {
            return t.isAlive();
        }

        @Override
        public void run() {
            InputStream inputStream;

            try {
                inputStream = mBTSocket.getInputStream();
                while (!bStop) {
                    byte[] buffer = new byte[256];
                    if (inputStream.available() > 0) {
                        inputStream.read(buffer);
                        int i = 0;
                        /*
                         * This is needed because new String(buffer) is taking the entire buffer i.e. 256 chars on Android 2.3.4 http://stackoverflow.com/a/8843462/1287554
                         */
                        for (i = 0; i < buffer.length && buffer[i] != 0; i++) {
                        }
                        final String strInput = new String(buffer, 0, i);
                        if (o!=null) o.receivedStr(strInput);

                        /*
                         * If checked then receive text, better design would probably be to stop thread if unchecked and free resources, but this is a quick fix
                         */



                    }
                    Thread.sleep(500);
                }
            } catch (IOException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InterruptedException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        public void stop() {
            bStop = true;
        }

    }

    private class DisConnectBT extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Void doInBackground(Void... params) {//cant inderstand these dotss

            if (mReadThread != null) {
                mReadThread.stop();
                while (mReadThread.isRunning())
                    ; // Wait until it stops
                mReadThread = null;

            }

            try {
                mBTSocket.close();
            } catch (IOException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            mIsBluetoothConnected = false;
            if (mIsUserInitiatedDisconnect) {
                finish();
            }
        }

    }

    private void msg(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        if (mBTSocket != null && mIsBluetoothConnected) {
            new DisConnectBT().execute();
        }
        Log.d(TAG, "Paused");
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (mBTSocket == null || !mIsBluetoothConnected) {
            new ConnectBT().execute();
        }
        Log.d(TAG, "Resumed");
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "Stopped");
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
// TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
    }

    private class ConnectBT extends AsyncTask<Void, Void, Void> {
        private boolean mConnectSuccessful = true;

        @Override
        protected void onPreExecute() {

            progressDialog = ProgressDialog.show(Controlling.this, "Hold on", "Connecting");// http://stackoverflow.com/a/11130220/1287554

        }

        @Override
        protected Void doInBackground(Void... devices) {

            try {
                if (mBTSocket == null || !mIsBluetoothConnected) {
                    mBTSocket = mDevice.createInsecureRfcommSocketToServiceRecord(mDeviceUUID);
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    mBTSocket.connect();
                }
            } catch (IOException e) {
// Unable to connect to device`
                // e.printStackTrace();
                mConnectSuccessful = false;



            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (!mConnectSuccessful) {
                Toast.makeText(getApplicationContext(), "Could not connect to device.Please turn on your Hardware", Toast.LENGTH_LONG).show();
                finish();
            } else {
                msg("Connected to device");
                mIsBluetoothConnected = true;
                mReadThread = new ReadInput(Controlling.this); // Kick off input reader
            }

            progressDialog.dismiss();
        }

    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}