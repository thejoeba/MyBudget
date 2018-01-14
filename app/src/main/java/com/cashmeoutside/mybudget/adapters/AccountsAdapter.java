package com.cashmeoutside.mybudget.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cashmeoutside.mybudget.BR;
import com.cashmeoutside.mybudget.R;
import com.cashmeoutside.mybudget.entities.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.AccountHolder>{

    private List<Account> mAccounts = new ArrayList<>();

    public AccountsAdapter(List<Account> accounts) {
        mAccounts = accounts;
    }

    @Override
    public AccountHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_account, parent, false);
        return new AccountHolder(v);
    }

    @Override
    public void onBindViewHolder(AccountHolder holder, int position) {
        holder.getBinding().setVariable(BR.account, mAccounts.get(position));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mAccounts.size();
    }

    public class AccountHolder extends RecyclerView.ViewHolder{

        private ViewDataBinding binding;

        public AccountHolder(View view){
            super(view);
            binding = DataBindingUtil.bind(view);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

}
