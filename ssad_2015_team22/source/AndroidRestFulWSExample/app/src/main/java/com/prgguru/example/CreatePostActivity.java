package com.prgguru.example;

/**
 * Created by pratyusha on 7/11/15.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

public class CreatePostActivity extends Activity {

    // Progress Dialog Object
    ProgressDialog prgDialog;
    // Error Msg TextView Object
    TextView errorMsg;
    // Name Edit View Object
    EditText queryET;
    // Email Edit View Object
    EditText descriptionET;
    // Passwprd Edit View Object
    EditText tagsET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createpost);
        // Find Error Msg Text View control by ID
        errorMsg = (TextView) findViewById(R.id.post_error);
        // Find Name Edit View control by ID
        queryET = (EditText) findViewById(R.id.queryName);
        // Find Email Edit View control by ID
        descriptionET = (EditText) findViewById(R.id.descriptionName);
        // Find Password Edit View control by ID
        //tagsET = (EditText)findViewById(R.id.tags_name);
        // Instantiate Progress Dialog object
        prgDialog = new ProgressDialog(this);
        // Set Progress Dialog Text
        prgDialog.setMessage("Please wait...");
        // Set Cancelable as False
        prgDialog.setCancelable(false);

    }
    public void createPost(View view) {
        // Get query ET control value
        String question = queryET.getText().toString();
        // Get description ET control value
        String description = descriptionET.getText().toString();
        // Get tags ET control value
        //String tags= tagsET.getText().toString();
        String emailid = "praty@ya.com";
        // Instantiate Http Request Param Object
        RequestParams params = new RequestParams();
        // When values are other than Null
        if (Utility.isNotNull(question) && Utility.isNotNull(description)) {

            params.put("email", emailid);
            // Put Http parameter name with value of query post control
            params.put("title", question);
            // Put Http parameter username with value of description post control
            params.put("description", description);
            // Put Http parameter password with value of tags Post control
            //params.put("tags", tags);

            // Invoke RESTful Web Service with Http parameters
            invokeWS(params);
        }


        // When any of the post View control left blank
        else {
            Toast.makeText(getApplicationContext(), "Please fill the form, don't leave any field blank", Toast.LENGTH_LONG).show();
        }

    }


    public void invokeWS(RequestParams params) {
        // Show Progress Dialog
        prgDialog.show();
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://10.0.2.2:8081/useraccount/post/create_post", params, new AsyncHttpResponseHandler() {
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
                        setDefaultValues();
                        // Display successfully registered message using Toast
                        Toast.makeText(getApplicationContext(), "Your question is posted!", Toast.LENGTH_LONG).show();

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

            public void setDefaultValues(){
                queryET.setText("");
                descriptionET.setText("");
                tagsET.setText("");
            }

}
