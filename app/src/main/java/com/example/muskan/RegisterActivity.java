package com.example.muskan;

import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.util.Map;

public class RegisterActivity extends AppCompatActivity
{
    public static FirebaseFirestore g_firestore;
    FirebaseAuth mAuth;
    EditText emailId,pass,confirm_pass,firstName,lastName,addLine1,city,state,pinCode,country,contactNo;
    String emailIdStr,passStr,confirm_passStr,firstNameStr,lastNameStr,addLine1Str,cityStr,stateStr,pinCodeStr,countryStr,contactNoStr;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        ActionBar actionbar = getSupportActionBar();
//        actionbar.hide();
        g_firestore=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        test();
    }
    
    /*
     * This is a Test method to test UI feature functionality.
     * It must be removed during actual feature implementation and should not
     * be considered as actual code for the feature to work.
     */





    private void test ()
    {

        emailId=findViewById(R.id.email);
        pass=findViewById(R.id.password);
        confirm_pass=findViewById(R.id.confirm_password);

        firstName=findViewById(R.id.first_name);
        lastName=findViewById(R.id.last_name);
        contactNo=findViewById(R.id.contact_no);

        addLine1=findViewById(R.id.add_line1);
        city=findViewById(R.id.city);
        state=findViewById(R.id.state);
        country=findViewById(R.id.country);
        pinCode=findViewById(R.id.pin_code);


        LinearLayout cred = findViewById(R.id.credentials);
        LinearLayout det = findViewById(R.id.personalDetails);
        LinearLayout add = findViewById(R.id.address);
    
        cred.setVisibility(View.VISIBLE);
        det.setVisibility(View.INVISIBLE);
        add.setVisibility(View.INVISIBLE);
    
        ImageView proceed1 = findViewById(R.id.proceed1);
        proceed1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view) {
                if (validate1()) {
                    if (cred.isShown()) {
                        cred.setVisibility(View.INVISIBLE);
                        det.setVisibility(View.VISIBLE);
                    }

                }
            }
        });
    
        ImageView proceed2 = findViewById(R.id.proceed2);
        proceed2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view) {
                if (validate2()) {
                    if (det.isShown()) {
                        det.setVisibility(View.INVISIBLE);
                        add.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        ImageView proceed3 = findViewById(R.id.proceed3);
        proceed3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate3()){
                    signupNewUser();
                }
            }
        });
    }
    private boolean validate1(){
        emailIdStr=emailId.getText().toString().trim();
        passStr=pass.getText().toString().trim();
        confirm_passStr=confirm_pass.getText().toString().trim();
        if (emailIdStr.isEmpty()){
            emailId.setError("Enter Your Email");
            return false;
        }
        if (passStr.isEmpty()){
            pass.setError("Enter Password");
            return false;
        }
        if (confirm_passStr.isEmpty()){
            confirm_pass.setError("Confirm Your Password");
            return false;
        }
        if (passStr.compareTo(confirm_passStr)!=0){
            Toast.makeText(RegisterActivity.this,"Password and Confirm Password must be same",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private boolean validate2(){
        firstNameStr=firstName.getText().toString().trim();
        lastNameStr=lastName.getText().toString().trim();
        contactNoStr=contactNo.getText().toString().trim();
        if (firstNameStr.isEmpty()){
            firstName.setError("Enter Your FirstName");
            return false;
        }
        if (lastNameStr.isEmpty()){
            lastName.setError("Enter Your LastName");
            return false;
        }
        if(contactNoStr.isEmpty()){
            contactNo.setError("Enter Your Contact No");
            return false;
        }
        return true;
    }
    private boolean validate3(){
        addLine1Str=addLine1.getText().toString().trim();
        pinCodeStr=pinCode.getText().toString().trim();
        cityStr=city.getText().toString().trim();
        stateStr=state.getText().toString().trim();
        countryStr=country.getText().toString().trim();
        if (addLine1Str.isEmpty()){
            addLine1.setError("Enter Your Address");
            return false;
        }
        if (pinCodeStr.isEmpty()){
            pinCode.setError("Enter PinCode");
            return false;
        }
        if (cityStr.isEmpty()){
            city.setError("Enter City");
            return false;
        }
        if (stateStr.isEmpty()){
            state.setError("Enter State");
            return false;
        }
        if (countryStr.isEmpty()){
            country.setError("Enter Country");
            return false;
        }
        return true;
    }
    private void signupNewUser(){
        mAuth.createUserWithEmailAndPassword(emailIdStr, passStr)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {

                        // Sign in success, update UI with the signed-in user's information
                        createUserData(emailIdStr, firstNameStr, new CompleteListener() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(RegisterActivity.this,"Sign Up Successfull",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(RegisterActivity.this,firstPage.class);
                                startActivity(intent);
                                RegisterActivity.this.finish();
                            }

                            @Override
                            public void onFailure() {
                                Toast.makeText(RegisterActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

    }
    public static void createUserData(String email, String name,CompleteListener completeListener) {
        Map<String, Object> userData = new ArrayMap<>();
        userData.put("EMAIL_ID", email);
        userData.put("NAME", name);

        DocumentReference userDoc = g_firestore.collection("USERS").document(FirebaseAuth.getInstance().getCurrentUser().getUid());

        WriteBatch batch = g_firestore.batch();
        batch.set(userDoc, userData);
        batch.commit()
                .addOnSuccessListener(unused -> completeListener.onSuccess())
                .addOnFailureListener(e -> completeListener.onFailure());


    }

}