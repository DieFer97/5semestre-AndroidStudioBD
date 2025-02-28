package com.example.agenda_app.ui.viewcontacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.agenda_app.databinding.FragmentViewContactsBinding

class ViewContactsFragment : Fragment() {

    private var _binding: FragmentViewContactsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewContactsViewModel =
            ViewModelProvider(this).get(ViewContactsViewModel::class.java)

        _binding = FragmentViewContactsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textViewContacts
        viewContactsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}