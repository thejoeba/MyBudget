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
    }

    public void onSave(View v) {
        Box<Account> accountBox = MyBudgetApplication.getBoxStore().boxFor(Account.class);
        accountBox.put(mAccount);

        setResult(Activity.RESULT_OK);
        finish();
    }

    public void onCancel(View v) {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

}

