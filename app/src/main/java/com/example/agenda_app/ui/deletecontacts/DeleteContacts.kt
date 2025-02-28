package com.example.agenda_app.ui.deletecontacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.agenda_app.databinding.FragmentDeleteContactsBinding

class DeleteContactsFragment : Fragment() {

    private var _binding: FragmentDeleteContactsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val deleteContactsViewModel =
            ViewModelProvider(this).get(DeleteContactsViewModel::class.java)

        _binding = FragmentDeleteContactsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDeleteContacts
        deleteContactsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}