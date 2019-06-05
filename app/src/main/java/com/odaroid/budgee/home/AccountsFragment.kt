package com.odaroid.budgee.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.odaroid.budgee.R
import com.odaroid.budgee.databinding.FragmentAccountsBinding

class AccountsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAccountsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_accounts, container, false)
        binding.addAccountFloatingActionButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_accountsFragment_to_addAccountFragment)
        }
        return binding.root
    }
}


