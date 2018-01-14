package com.cashmeoutside.mybudget;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.cashmeoutside.mybudget.adapters.AccountsAdapter;
import com.cashmeoutside.mybudget.entities.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountsActivity extends AppCompatActivity {

    List<Account> accounts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        accounts.add(new Account("Cabrillo CU - Checking", "19182180", 0, Account.AccountTypeEnum.CHECKING.toString()));
        accounts.add(new Account("Cabrillo CU - Savings", "19182181", 0, Account.AccountTypeEnum.SAVINGS.toString()));

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
                String result = data.getStringExtra("result");
                String[] results = result.split(",");
                Double balance = Double.parseDouble(results[2]);
                accounts.add(new Account(results[0], results[1], balance, results[3]));

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                RecyclerView rvAccounts = findViewById(R.id.rvAccounts);
                rvAccounts.setLayoutManager(layoutManager);
                RecyclerView.Adapter adapter = new AccountsAdapter(accounts);
                rvAccounts.setAdapter(adapter);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}
