package com.odaroid.budgee.ui.add


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.odaroid.budgee.R
import com.odaroid.budgee.data.accounts.Account
import com.odaroid.budgee.databinding.FragmentAddAccountBinding
import timber.log.Timber

/**
 * Handles Add Account View Logic in MVVM Stack
 */
class AddAccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAddAccountBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_account, container, false)
        val viewModel: AddAccountViewModel by viewModels()
        binding.addAccountViewModel = viewModel
        binding.targetAmountEditText.addTextChangedListener(object : TextWatcherImpl {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty() && s.toString().toLong() > 0) {
                    viewModel.enableSaveButton()
                    Timber.d("Button Color Should Change")
                }
            }
            override fun afterTextChanged(s: Editable?) {
                super.afterTextChanged(s)
                if (s.toString().isEmpty()) {
                    viewModel.disableSaveButton()
                    Timber.d("Button Color Should Change")
                }
            }
        })
        viewModel.isReadyToSave.observe(this) { canSave ->
            binding.addButton.isEnabled = canSave
        }
        binding.addButton.setOnClickListener {
            val name = binding.accountNameEditText.text.toString()
            val amount = binding.targetAmountEditText.text.toString().toLong()
            viewModel.saveAccountInfo(Account(name, amount))
        }
        return binding.root
    }
}

interface TextWatcherImpl : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
    }
}


