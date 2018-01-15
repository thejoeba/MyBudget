package com.cashmeoutside.mybudget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class NewAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_account_activity);

//        Button btnSaveAddNewAccount = findViewById(R.id.btnSaveAddNewAccount);
//        btnSaveAddNewAccount.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {


//                EditText etAccountName = findViewById(R.id.etAccountName);
//                EditText etAccountNumber = findViewById(R.id.etAccountNumber);
//                EditText etInitialBalance = findViewById(R.id.etInitialBalance);
//                Spinner spinAccountType = findViewById(R.id.spinAccountType);

//                String accountName = etAccountName.getText().toString();
//                String accountNumber = etAccountNumber.getText().toString();
//                String accountBalance = etInitialBalance.getText().toString();
//                String accountType = spinAccountType.toString();

//                String result = accountName + "," + accountNumber + "," +  accountBalance + "," +  accountType;

//                Intent returnIntent = new Intent();
//                returnIntent.putExtra("result", result);
//                setResult(Activity.RESULT_OK, returnIntent);
//                finish();
//            }
//        });

//        Button btnCancelAddNewAccount = findViewById(R.id.btnCancelAddNewAccount);
//        btnCancelAddNewAccount.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent returnIntent = new Intent();
//                setResult(Activity.RESULT_CANCELED, returnIntent);
//                finish();
//            }
//        });

    }

}

