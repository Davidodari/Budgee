package com.odaroid.budgee.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.odaroid.budgee.R
import com.odaroid.budgee.data.accounts.Account


class AccountsAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<AccountsAdapter.AccountViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var accounts = emptyList<Account>()

    inner class AccountViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val accountName: TextView = itemView.findViewById(R.id.account_name_text_view)
        val accountTarget: TextView = itemView.findViewById(R.id.account_target_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val itemView = inflater.inflate(R.layout.list_item_main_accounts, parent, false)
        return AccountViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val current = accounts[position]
        holder.accountName.text = current.name
        holder.accountTarget.text = current.target.toString()
    }

    internal fun setAccounts(accounts: List<Account>) {
        this.accounts = accounts
        notifyDataSetChanged()
    }

    override fun getItemCount() = accounts.size
}
