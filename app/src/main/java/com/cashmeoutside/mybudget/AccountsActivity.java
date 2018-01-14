package com.cashmeoutside.mybudget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cashmeoutside.mybudget.adapters.AccountsAdapter;
import com.cashmeoutside.mybudget.entities.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        RecyclerView rvAccounts = findViewById(R.id.rvAccounts);
        rvAccounts.setLayoutManager(layoutManager);
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("Cabrillo CU - Checking", "19182180", 0, Account.AccountTypeEnum.CHECKING.toString()));
        accounts.add(new Account("Cabrillo CU - Savings", "19182181", 0, Account.AccountTypeEnum.SAVINGS.toString()));
        RecyclerView.Adapter adapter = new AccountsAdapter(accounts);
        rvAccounts.setAdapter(adapter);
    }
}
