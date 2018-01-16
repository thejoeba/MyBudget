package com.cashmeoutside.mybudget.activities;

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
    private DataSubscriptionList mAccountsSubscription = new DataSubscriptionList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        mAccountBox = MyBudgetApplication.getBoxStore().boxFor(Account.class);

        Query<Account> accountsQuery = mAccountBox.query().build();
        accountsQuery.subscribe(mAccountsSubscription).on(AndroidScheduler.mainThread()).observer(new DataObserver<List<Account>>() {
            @Override
            public void onData(List<Account> accounts) {
                for (int i = 0; i < mAccounts.size(); i++) {
                    Account oldAccount = mAccounts.get(i);
                    boolean match = false;
                    for (Account newAccount : accounts) {
                        if (oldAccount.getId() == newAccount.getId()) {
                            match = true;
                            if (oldAccount.equals(newAccount)) {
                                mAccounts.set(i, newAccount);
                                mAdapter.notifyItemChanged(i);
                            }
                            break;
                        }
                    }
                    if (!match) {
                        mAccounts.remove(i);
                        mAdapter.notifyItemRemoved(i);
                    }
                }

                for (int i = 0; i < accounts.size(); i++) {
                    Account newAccount = accounts.get(i);
                    boolean match = false;
                    for (Account oldAccount : mAccounts) {
                        if (oldAccount.getId() == newAccount.getId()) {
                            match = true;
                        }
                    }
                    if (!match) {
                        mAccounts.add(i, newAccount);
                        mAdapter.notifyItemInserted(i);
                    }
                }
//                refreshAccountsList();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView rvAccounts = findViewById(R.id.rvAccounts);
        rvAccounts.setLayoutManager(layoutManager);
        mAdapter = new AccountsAdapter(mAccounts);
        rvAccounts.setAdapter(mAdapter);

        refreshAccountsList();

        Button btnNewTransaction = findViewById(R.id.btnNewTransaction);
        btnNewTransaction.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: 1/15/2018 Remove this, it is just to show adding is pretty
                mAccountBox.put(new Account(0, "Test " + mAccounts.size(), "", 0, ""));
//                startActivity(new Intent(AccountsActivity.this, NewAccountActivity.class));
                            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAccountsSubscription != null) {
            mAccountsSubscription.cancel();
        }
    }

    private void refreshAccountsList() {
        mAccounts.clear();
        mAccounts.addAll(mAccountBox.getAll());
        mAdapter.notifyDataSetChanged();
    }
}
