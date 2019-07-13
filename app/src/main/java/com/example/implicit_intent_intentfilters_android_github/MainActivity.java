package com.example.implicit_intent_intentfilters_android_github;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String COORDINATES = "51.5298,0.1722";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openMap(View view){
        Uri uri = Uri.parse("geo:"+COORDINATES);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

    public void sendText(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.putExtra("IMPLICIT_TEXT","Hello, Thank you!");
        intent.setType("text/plain");
        if(intent.resolveActivity(getPackageManager())!= null){
            startActivity(intent);
            Log.i("Main","if method called");
        }
        else{
            Toast.makeText(getApplicationContext(),"No other application to handle this intent",Toast.LENGTH_SHORT).show();
//            ShareCompat.IntentBuilder
//                    .from(this)
//                    .setType("text/plain")
//                    .setChooserTitle("IMPLICIT_TEXT")
//                    .setText("Hello, Thank you!")
//                    .startChooser();

            Log.i("Main","else method called");
        }

    }
}
