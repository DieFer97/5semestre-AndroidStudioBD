package com.example.agenda_app.ui.addcontacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.agenda_app.databinding.FragmentAddContactsBinding

class AddContactsFragment : Fragment() {

    private var _binding: FragmentAddContactsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val addContactsViewModel =
            ViewModelProvider(this).get(AddContactsViewModel::class.java)

        _binding = FragmentAddContactsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textAddContacts
        addContactsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}