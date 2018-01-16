package com.cashmeoutside.mybudget.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cashmeoutside.mybudget.BR;
import com.cashmeoutside.mybudget.MyBudgetApplication;
import com.cashmeoutside.mybudget.R;
import com.cashmeoutside.mybudget.entities.Account;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.AccountViewHolder>{

    private List<Account> mAccounts = new ArrayList<>();

    public AccountsAdapter(List<Account> accounts) {
        mAccounts = accounts;
    }

    @Override
    public AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_account, parent, false);
        return new AccountViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AccountViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.account, mAccounts.get(position));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mAccounts.size();
    }

    public class AccountViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public AccountViewHolder(View view){
            super(view);
            binding = DataBindingUtil.bind(view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 1/15/2018 Remove this, it is just to show that updating and deleting can be pretty
                    Box<Account> accountBox = MyBudgetApplication.getBoxStore().boxFor(Account.class);
                    Account account = mAccounts.get(getAdapterPosition());
                    if (account.getBalance() == 0) {
                        account.setName("Updated");
                        account.setBalance(1);
                        accountBox.put(account);
                    } else {
                        accountBox.remove(account);
                    }
                }
            });
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

}
