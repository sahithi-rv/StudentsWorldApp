package com.prgguru.example;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;

/**
 * Created by animesh on 5/10/15.
 */
public class EditProfileActivity extends Activity {

    // Progress Dialog Object
    ProgressDialog prgDialog;
    // Error Msg TextView Object
    TextView errorMsg;
    // First Name Edit View Object
    EditText firstnameET;
    // Last Name Edit View Object
    EditText lastnameET;
    // Start Degree Edit View Object
    EditText startdegreeET;
    // End Degree Edit View Object
    EditText enddegreeET;
    // Address Edit View Object
    EditText addressline1ET;
    // Address2 Edit View Object
    EditText addressline2ET;
    // College Edit View Object
    EditText collegeET;
    // DOB Edit View Object
    EditText dobET;
    // Mobile Edit View Object
    EditText mobileEt;
    // Degree Edit View Object
    EditText degreeET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);
        // Find Error Msg Text View control by ID
        errorMsg = (TextView)findViewById(R.id.register_error);
        // Find Name Edit View control by ID
        firstnameET = (EditText)findViewById(R.id.first_name);
        // Find Email Edit View control by ID
        lastnameET = (EditText)findViewById(R.id.last_name);
        // Find Password Edit View control by ID
        startdegreeET = (EditText)findViewById(R.id.start_degree);
        // Instantiate Progress Dialog object
        enddegreeET = (EditText)findViewById(R.id.end_degree);
        // Instantiate Progress Dialog object
        addressline1ET = (EditText)findViewById(R.id.address_line1);
        // Instantiate Progress Dialog object
        addressline2ET = (EditText)findViewById(R.id.address_line2);
        // Instantiate Progress Dialog object
        dobET = (EditText)findViewById(R.id.dob);
        // Instantiate Progress Dialog object
        collegeET = (EditText)findViewById(R.id.college_name);
        // Find Mobile edit View Control by ID
        mobileEt = (EditText)findViewById(R.id.mobile_no);
        // Find Degree edit View Control by ID
        degreeET = (EditText)findViewById(R.id.degree);
        // Instantiate Progress Dialog object
        prgDialog = new ProgressDialog(this);
        // Set Progress Dialog Text
        prgDialog.setMessage("Please wait...");
        // Set Cancelable as False
        prgDialog.setCancelable(false);
    }


    public void registerUser(View view){
        // Get NAme ET control value
        String firstname = firstnameET.getText().toString();
        // Get Email ET control value
        String lastname = lastnameET.getText().toString();
        // Get Password ET control value
        String startdeg = startdegreeET.getText().toString();
        // Get Password ET control value
        String enddeg = enddegreeET.getText().toString();
        // Get Password ET control value
        String dob = dobET.getText().toString();
        // Get Password ET control value
        String college = collegeET.getText().toString();
        // Get Password ET control value
        String addressline1 = addressline1ET.getText().toString();
        // Get Password ET control value
        String addressline2 = addressline2ET.getText().toString();
        // Get Mobile Number
        String mobile_no = mobileEt.getText().toString();
        // Get Degree
        String degree = degreeET.getText().toString();
        // Instantiate Http Request Param Object
        RequestParams params = new RequestParams();
        // When Name Edit View, Email Edit View and Password Edit View have values other than Null
        if(Utility.isNotNull(firstname) && Utility.isNotNull(lastname) && Utility.isNotNull(degree) && Utility.isNotNull(addressline1) && Utility.isNotNull(startdeg) && Utility.isNotNull(enddeg)){
            // When Email entered is Valid
            /*if(Utility.validate(email)){
                // Put Http parameter name with value of Name Edit View control
                params.put("name", name);
                // Put Http parameter username with value of Email Edit View control
                params.put("username", email);
                // Put Http parameter password with value of Password Edit View control
                params.put("password", password);
                // Invoke RESTful Web Service with Http parameters
                invokeWS(params);
            }
            // When Email is invalid
            else{
                Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_LONG).show();
            }*/
            // Put Http parameter first name with value of First Name Edit View Control
            params.put("first_name",firstname);
            // Put Http parameter last name with value of Last Name Edit View Control
            params.put("last_name",lastname);
            // Put Http parameter degree with value of Degree Edit View Control
            params.put("degree",degree);
            // Put Http parameter start degree with value of Start Degree Edit View Control
            params.put("start_degree",startdeg);
            // Put Http parameter end degree with value of End Degree Edit View Control
            params.put("end_degree",enddeg);
            // Put Http parameter dob with value of Last DOB View Control
            params.put("dob",dob);
            // Put Http parameter address line1 with value of Address Line1 Edit View Control
            params.put("address_line1",addressline1);
            // Put Http parameter address line2 with value of Address Line2 Edit View Control
            params.put("address_line2",addressline2);
            // Put Http parameter Mobile with value of Mobile Edit View Control
            params.put("address_line1",addressline1);
            //inVokeWs(params);
        }
        // When any of the Edit View control left blank
        else{
            Toast.makeText(getApplicationContext(), "Please fill the form, don't leave any field blank", Toast.LENGTH_LONG).show();
        }

    }


}
