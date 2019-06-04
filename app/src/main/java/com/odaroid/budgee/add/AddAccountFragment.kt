package com.odaroid.budgee.add


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.odaroid.budgee.R
import com.odaroid.budgee.databinding.FragmentAddAccountBinding

class AddAccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAddAccountBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_account, container, false)
        return binding.root
    }
}
