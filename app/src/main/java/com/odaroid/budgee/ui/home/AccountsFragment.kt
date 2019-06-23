package com.odaroid.budgee.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.odaroid.budgee.R
import com.odaroid.budgee.databinding.FragmentAccountsBinding
import com.odaroid.budgee.ui.ViewModelsFactory
import com.odaroid.budgee.utilities.ListDividerDecoration

class AccountsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAccountsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_accounts, container, false)
        val application = requireNotNull(this.activity).application
        val viewModel: AccountsViewModel by viewModels { ViewModelsFactory(application) }
        val recyclerView = binding.accountsRecyclerView
        val accountsAdapter = AccountsAdapter(context!!)
        recyclerView.adapter = accountsAdapter
        val layoutManager = LinearLayoutManager(this.activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(ListDividerDecoration(context!!, R.drawable.divider))
        viewModel.accounts.observe(this) { accounts ->
            accounts.run { accountsAdapter.setAccounts(accounts) }
        }
        binding.addAccountFloatingActionButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_accountsFragment_to_addAccountFragment)
        }
        return binding.root
    }
}
