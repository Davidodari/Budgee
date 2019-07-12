package com.odaroid.budgee.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.odaroid.budgee.data.accounts.Account
import com.odaroid.budgee.databinding.ListItemMainAccountsBinding


class AccountsAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<AccountsAdapter.AccountViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var accounts = emptyList<Account>()

    inner class AccountViewHolder(private val binding: ListItemMainAccountsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(account: Account) {
            binding.account = account
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val binding = ListItemMainAccountsBinding.inflate(inflater, parent, false)
        return AccountViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val current = accounts[position]
        holder.bind(current)
    }

    internal fun setAccounts(accounts: List<Account>) {
        this.accounts = accounts
        notifyDataSetChanged()
    }

    override fun getItemCount() = accounts.size
}
