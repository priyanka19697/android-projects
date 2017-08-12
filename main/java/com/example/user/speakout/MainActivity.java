package com.example.user.speakout;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView=(TextView)findViewById(R.id.textView);
        Button bt_spk=(Button)findViewById(R.id.bt_spk);

        }
    public void OnButtonClick(View v){
        if(v.getId()==R.id.bt_spk){
            promptSpeechInput();
        }

    }
    public void promptSpeechInput()
    {
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"speak out");
        try{
            startActivityForResult(intent,100);
        }catch(ActivityNotFoundException a) {
            Toast.makeText(MainActivity.this, "sorry your device doesnt support this", Toast.LENGTH_SHORT).show();

        }

    }
    public void onActivityResult(int request_code,int result_code,Intent intent)
    {
        super.onActivityResult(request_code,result_code,intent);
    switch (request_code){
        case 100:if (result_code==RESULT_OK||intent!=null)
        {
            TextView resultt=(TextView)findViewById(R.id.tv_result);
            ArrayList<String> result =intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            resultt.setText(result.get(0));
        }
    }
    }

}
