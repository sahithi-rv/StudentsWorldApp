package com.prgguru.example;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class TestActivity extends Activity {

    ProgressDialog prgDialog;
    String myJSON;
    private static final String TAG_OPTION_A="option_a";
    private static final String TAG_OPTION_B="option_b";
    private static final String TAG_OPTION_C= "option_c";
    private static final String TAG_OPTION_D ="option_d";
    private static final String TAG_QUESTION="question";
    private static final String TAG_QUESTION_ID="question_id";
    TextView errorMsg;
    JSONArray questions = null;
    ArrayList<HashMap<String, String>> questoinsList;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        list = (ListView) findViewById(R.id.list_item);
        //questionsList = new ArrayList<HashMap<String,String>>();
        //getData();
        prgDialog = new ProgressDialog(this);
        // Set Progress Dialog Text
        prgDialog.setMessage("Please wait...");
        // Set Cancelable as False
        prgDialog.setCancelable(false);
        errorMsg = (TextView)findViewById(R.id.login_error);
        System.out.println("came here");
    }

    public void sendRequestForTest(View view){
        prgDialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://10.0.2.2:8081/useraccount/test/list", new AsyncHttpResponseHandler() {
            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(String response) {
                // Hide Progress Dialog
                prgDialog.hide();
                try {
                    // JSON Object
                    JSONObject obj = new JSONObject(response);
                    navigatetoTakeTestActivity(obj);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    //navigatetoOtpActivity();
                    Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                // Hide Progress Dialog
                prgDialog.hide();
                // When Http response code is '404'
                if (statusCode == 404) {
                    Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                }
                // When Http response code is '500'
                else if (statusCode == 500) {
                    Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                }
                // When Http response code other than 404, 500
                else {
                    Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void navigatetoTakeTestActivity(JSONObject obj){
        System.out.println("going to takestestactivity" + obj);
        Intent taketestIntent = new Intent(getApplicationContext(),TakeTestActivity.class);
        taketestIntent.putExtra("test",obj.toString());
        taketestIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(taketestIntent);
    }


    public void navigatetocreatePost(View view){
        Intent loginIntent = new Intent(getApplicationContext(),CreatePostActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
    }

}
