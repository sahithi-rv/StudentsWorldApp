package com.prgguru.example;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
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

import junit.framework.Test;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by animesh on 8/10/15.
 */
public class QuestionActivty extends Activity {

    // Progress Dialog Object
    ProgressDialog prgDialog;
    // Error Msg TextView Object
    TextView errorMsg;
    // Passwprd Edit View Object
    EditText questionET;
    TextView question ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        prgDialog = new ProgressDialog(this);
        // Set Progress Dialog Text
        prgDialog.setMessage("Please wait...");
        // Set Cancelable as False
        prgDialog.setCancelable(false);
        errorMsg = (TextView)findViewById(R.id.login_error);
        // Find Name Edit View control by ID
        question = (TextView)findViewById(R.id.question);
        question.setText("What is size of int in C");
    }


    public void navigatetoEvaluationActivity(View view){
        Intent evaluationIntent = new Intent(getApplicationContext(),EvaluateActivity.class);
        // Clears History of Activity
        evaluationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(evaluationIntent);
    }

}
