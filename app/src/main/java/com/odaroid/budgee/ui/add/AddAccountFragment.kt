package com.odaroid.budgee.ui.add


import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.odaroid.budgee.R
import com.odaroid.budgee.databinding.FragmentAddAccountBinding
import com.odaroid.budgee.ui.ViewModelsFactory
import com.odaroid.budgee.utilities.TextWatcherImpl

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
        val viewModel: AddAccountViewModel by viewModels { ViewModelsFactory(application)}
        binding.addAccountViewModel = viewModel
        binding.lifecycleOwner = this
        binding
            .targetAmountEditText
            .addTextChangedListener(object : TextWatcherImpl {
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s.toString().isNotEmpty() && s.toString().toLong() > 0) {
                        viewModel.hasAccountTarget(binding.targetAmountEditText.text.toString().toLong())
                        viewModel.changeButtonState()
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    super.afterTextChanged(s)
                    if (s.toString().isEmpty()) {
                        viewModel.hasNoAccountTarget()
                        viewModel.changeButtonState()
                    }
                }
            })
        binding
            .accountNameEditText
            .addTextChangedListener(object : TextWatcherImpl {
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s.toString().isNotEmpty()) {
                        viewModel.hasAccountName(binding.accountNameEditText.text.toString())
                        viewModel.changeButtonState()
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    super.afterTextChanged(s)
                    if (s.toString().isEmpty()) {
                        viewModel.hasNoAccountName()
                        viewModel.changeButtonState()
                    }
                }
            })
        return binding.root
    }
}
