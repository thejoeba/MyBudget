package com.cashmeoutside.mybudget.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.cashmeoutside.mybudget.MyBudgetApplication;
import com.cashmeoutside.mybudget.R;
import com.cashmeoutside.mybudget.adapters.AccountsAdapter;
import com.cashmeoutside.mybudget.entities.Account;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

public class AccountsActivity extends AppCompatActivity {
    private Box<Account> mAccountBox;
    // TODO: 1/15/2018 make this private!
    List<Account> accounts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        mAccountBox = MyBudgetApplication.getBoxStore().boxFor(Account.class);
        accounts.addAll(mAccountBox.getAll());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView rvAccounts = findViewById(R.id.rvAccounts);
        rvAccounts.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new AccountsAdapter(accounts);
        rvAccounts.setAdapter(adapter);

        Button btnNewTransaction = findViewById(R.id.btnNewTransaction);
        btnNewTransaction.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivityForResult(new Intent(AccountsActivity.this,
                        NewAccountActivity.class), 1);
                            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                // TODO: 1/15/2018 This will clear all accounts from the list, and rebuild it.
                accounts.clear();
                accounts.addAll(mAccountBox.getAll());

                // TODO: 1/15/2018 You need to then tell the UI to refresh the list. You don't have an activity level adapter, so the next line doesn't currently work
//                mAdapter.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}
