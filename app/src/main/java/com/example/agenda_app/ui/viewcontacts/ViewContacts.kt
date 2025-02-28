package com.example.agenda_app.ui.viewcontacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agenda_app.databinding.FragmentViewContactsBinding

class ViewContactsFragment : Fragment() {

    private var _binding: FragmentViewContactsBinding? = null
    private val binding get() = _binding!!
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var viewContactsViewModel: ViewContactsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewContactsViewModel = ViewModelProvider(this).get(ViewContactsViewModel::class.java)

        _binding = FragmentViewContactsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Configurar RecyclerView
        binding.recyclerViewContacts.layoutManager = LinearLayoutManager(context)
        contactAdapter = ContactAdapter(emptyList())
        binding.recyclerViewContacts.adapter = contactAdapter

        // Observar cambios en la lista de contactos
        viewContactsViewModel.contacts.observe(viewLifecycleOwner) { contacts ->
            contactAdapter.updateContacts(contacts)

            // Mostrar mensaje si no hay contactos
            if (contacts.isEmpty()) {
                binding.textEmptyContacts.visibility = View.VISIBLE
                binding.recyclerViewContacts.visibility = View.GONE
            } else {
                binding.textEmptyContacts.visibility = View.GONE
                binding.recyclerViewContacts.visibility = View.VISIBLE
            }
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        // Recargar contactos cuando el fragmento se reanuda
        viewContactsViewModel.loadContacts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}