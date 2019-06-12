package com.odaroid.budgee.ui.add


import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.odaroid.budgee.R
import com.odaroid.budgee.data.AccountsRepository
import com.odaroid.budgee.utilities.TextWatcherImpl
import com.odaroid.budgee.data.BudgeeDatabase
import com.odaroid.budgee.data.accounts.Account
import com.odaroid.budgee.databinding.FragmentAddAccountBinding

/**
 * Handles Add Account View Logic in MVVM Stack
 */
class AddAccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAddAccountBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_account, container, false)
        val application = requireNotNull(this.activity).application
        val viewModelFactory = AddAccountViewModelFactory(application)
        val viewModel: AddAccountViewModel by viewModels { viewModelFactory }
        binding.addAccountViewModel = viewModel
        binding
            .targetAmountEditText
            .addTextChangedListener(object : TextWatcherImpl {
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s.toString().isNotEmpty() && s.toString().toLong() > 0) {
                        viewModel.hasAccountTarget()
                        viewModel.addButtonState()
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    super.afterTextChanged(s)
                    if (s.toString().isEmpty()) {
                        viewModel.hasNoAccountTarget()
                        viewModel.addButtonState()
                    }
                }
            })
        binding
            .accountNameEditText
            .addTextChangedListener(object : TextWatcherImpl {
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s.toString().isNotEmpty()) {
                        viewModel.hasAccountName()
                        viewModel.addButtonState()
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    super.afterTextChanged(s)
                    if (s.toString().isEmpty()) {
                        viewModel.hasNoAccountName()
                        viewModel.addButtonState()
                    }
                }
            })
        viewModel.isReadyToSave.observe(this) { canSave ->
            binding.addButton.isEnabled = canSave

        }
        onAddButtonClicked(binding, viewModel)
        return binding.root
    }

    private fun onAddButtonClicked(binding: FragmentAddAccountBinding, viewModel: AddAccountViewModel) {
        binding.addButton.setOnClickListener {
            val name = binding.accountNameEditText.text.toString()
            val amount = binding.targetAmountEditText.text.toString().toLong()
            viewModel.saveAccount(Account(name, amount))
        }
    }
}



