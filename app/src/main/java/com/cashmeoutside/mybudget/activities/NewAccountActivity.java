package com.cashmeoutside.mybudget.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.cashmeoutside.mybudget.MyBudgetApplication;
import com.cashmeoutside.mybudget.R;
import com.cashmeoutside.mybudget.databinding.NewAccountActivityBinding;
import com.cashmeoutside.mybudget.entities.Account;

import io.objectbox.Box;

public class NewAccountActivity extends AppCompatActivity {
    private Account mAccount;
    private NewAccountActivityBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.new_account_activity);
        mBinding = DataBindingUtil.setContentView(this, R.layout.new_account_activity);
        mAccount = new Account(0, "", "", 0d, "");
        mBinding.setAccount(mAccount);
        mBinding.executePendingBindings();


        Spinner spinAccountType = findViewById(R.id.spinAccountType);
        spinAccountType.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Account.AccountTypeEnum.values()));

        Button btnSaveAddNewAccount = findViewById(R.id.btnSaveAddNewAccount);
        btnSaveAddNewAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onSave();
            }
        });

        Button btnCancelAddNewAccount = findViewById(R.id.btnCancelAddNewAccount);
        btnCancelAddNewAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });

    }

    public void onSave() {
        //Save to objectBox
        Box accountBox = MyBudgetApplication.getBoxStore().boxFor(Account.class);

        accountBox.put(mAccount);

        setResult(Activity.RESULT_OK);
        finish();
    }

}

