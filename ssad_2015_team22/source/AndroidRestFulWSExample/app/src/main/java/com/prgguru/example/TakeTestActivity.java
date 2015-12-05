package com.prgguru.example;

import java.util.List;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import org.json.JSONArray;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


/**
 * Created by animesh on 8/10/15.
 */
public class TakeTestActivity extends Activity{
    ProgressDialog prgDialog;
    // Error Msg TextView Object
    TextView errorMsg;
    // Otp Edit View Object


    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.taketest);
        prgDialog = new ProgressDialog(this);
        // Set Progress Dialog Text
        prgDialog.setMessage("Please wait...");
        // Set Cancelable as False
        prgDialog.setCancelable(false);
        JSONObject obj = null;
        errorMsg = (TextView) findViewById(R.id.login_error);
        try {
            obj = new JSONObject(getIntent().getStringExtra("test"));
        } catch (JSONException e) {
            System.out.println("error");
        }
        // System.out.println(obj.getClass().getName());
        //try {
        //    System.out.println(obj.getJSONArray("list"));
        //}catch (JSONException e){
        //    System
        //}
        Spinner dropdown = (Spinner) findViewById(R.id.spinner1);
        //String[] items = new String[5];
        String a;
        //JSONObject testlist;
        JSONArray list;
        HashMap<String, String> testlist = new HashMap<String, String>();
        List<String> items = new ArrayList<String>();
        List<String> items1 = new ArrayList<String>();
        List<String> items2 = new ArrayList<String>();
        //String[] items = new String[]{"1", "2", "three"};
        try {
            list = obj.getJSONArray("list");
            //items = {"1","2","3"};
            //System.out.println(list);
            //testlist = list;
            //.get(0) ; //.get("subject");
            //testlist = obj.getJSONObject("list");
            for (int i = 0; i < list.length(); i++) {
                JSONObject actor = list.getJSONObject(i);
                String subject = actor.getString("subject");
                String length = actor.getString("length"); //length will be used in R2
                String test_id = actor.getString("test_id");
                items.add(subject);
                testlist.put(subject, test_id);
                //items1.add(length);
                //items2.add(test_id);
            }
        } catch (JSONException e) {
            System.out.println("not parsing json");
        }
        //System.out.println("printing this time" + items);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


    //public void getTestId(View view) {
      //  String testsubject = spinner.getSelectedItem().toString();
        //if (!testsubject.equals("")) {
            //String testid =
            //RequestParams params
        //}
        // String testid = spinner.getSelectedItem().toString();
        //if(!testid.equals("")){
        //    RequestParams params = new RequestParams();
        //    params.put("test_id",testid);pass
        //    sendTestId(params);
        //}
        //}
    }


    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {


        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
            Object subject = parent.getItemAtPosition(pos);
            System.out.println(subject);
            //sendTestId();
        }

        public void onNothingSelected(AdapterView<?> parent) {
            System.out.println("Please Choose Something");
        }
    }

    public void sendTestId(RequestParams params) {
        // Show Progress Dialog
        prgDialog.show();
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://10.0.2.2:8081/useraccount/", params, new AsyncHttpResponseHandler() {
            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(String response) {
                // Hide Progress Dialog
                prgDialog.hide();
                try {
                    // JSON Object
                    JSONObject obj = new JSONObject(response);

                    // When the JSON response has status boolean value assigned with true
                    if (obj.getBoolean("status")) {
                        //if (true){
                        // Set Default Values for Edit View controls
                        //setDefaultValues();
                        // Display successfully registered message using Toast
                        Toast.makeText(getApplicationContext(), "You are successfully registered!", Toast.LENGTH_LONG).show();
                        //navigatetoOtpActivity();
                        //navigatetoQuestionActivity();
                    }
                    // Else display error message
                    else {
                        errorMsg.setText(obj.getString("error_msg"));
                        Toast.makeText(getApplicationContext(), obj.getString("error_msg"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    //navigatetoOtpActivity();
                    Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }

            // When the response returned by REST has Http response code other than '200'
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

    public void navigatetoQuestionActivity(View view){
        Intent questionIntent = new Intent(getApplicationContext(),QuestionActivty.class);
        // Clears History of Activity
        questionIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(questionIntent);
    }
}
