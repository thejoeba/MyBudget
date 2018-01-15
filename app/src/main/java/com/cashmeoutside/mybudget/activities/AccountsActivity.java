package com.cashmeoutside.mybudget.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import io.objectbox.android.AndroidScheduler;
import io.objectbox.query.Query;
import io.objectbox.reactive.DataObserver;
import io.objectbox.reactive.DataSubscriptionList;

public class AccountsActivity extends AppCompatActivity {
    private Box<Account> mAccountBox;
    private List<Account> mAccounts = new ArrayList<>();
    RecyclerView.Adapter mAdapter;
//    private DataSubscriptionList mAccountsSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        mAccountBox = MyBudgetApplication.getBoxStore().boxFor(Account.class);

        // TODO: 1/15/2018 listen for changes to accounts via Observer instead of only getting changes from activity result
//        Query<Account> accountsQuery = mAccountBox.query().build();
//        accountsQuery.subscribe(mAccountsSubscription).on(AndroidScheduler.mainThread()).observer(new DataObserver<List<Account>>() {
//            @Override
//            public void onData(List<Account> accounts) {
//                refreshAccountsList();
//            }
//        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView rvAccounts = findViewById(R.id.rvAccounts);
        rvAccounts.setLayoutManager(layoutManager);
        mAdapter = new AccountsAdapter(mAccounts);
        rvAccounts.setAdapter(mAdapter);

        refreshAccountsList();

        Button btnNewTransaction = findViewById(R.id.btnNewTransaction);
        btnNewTransaction.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivityForResult(new Intent(AccountsActivity.this,
                        NewAccountActivity.class), 1);
                            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // TODO: 1/15/2018 if using subscription method, be sure to remove listner when activity closed
//        mAccountsSubscription.cancel();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                refreshAccountsList();
            }
            if (resultCode == Activity.RESULT_CANCELED) {}
        }
    }

    private void refreshAccountsList() {
        // TODO: 1/15/2018 This will clear all mAccounts from the list, and rebuild it.
        mAccounts.clear();
        mAccounts.addAll(mAccountBox.getAll());
        mAdapter.notifyDataSetChanged();
    }
}
