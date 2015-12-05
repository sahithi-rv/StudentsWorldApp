package com.prgguru.example;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by animesh on 8/10/15.
 */
public class EvaluateActivity extends Activity {

    ProgressDialog prgDialog;
    // Error Msg TextView Object
    TextView errorMsg;
    // Otp Edit View Object
    EditText otpEt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluate);
        prgDialog = new ProgressDialog(this);
        // Set Progress Dialog Text
        prgDialog.setMessage("Please wait...");
        // Set Cancelable as False
        prgDialog.setCancelable(false);
        errorMsg = (TextView)findViewById(R.id.login_error);
        // Find Name Edit View control by ID
        System.out.println("created");
    }
}
