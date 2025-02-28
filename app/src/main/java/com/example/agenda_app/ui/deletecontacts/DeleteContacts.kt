package com.example.agenda_app.ui.deletecontacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agenda_app.databinding.FragmentDeleteContactsBinding
import com.example.agenda_app.model.Contact

class DeleteContactsFragment : Fragment() {

    private var _binding: FragmentDeleteContactsBinding? = null
    private val binding get() = _binding!!
    private lateinit var deleteContactsViewModel: DeleteContactsViewModel
    private lateinit var deleteContactAdapter: DeleteContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        deleteContactsViewModel =
            ViewModelProvider(this).get(DeleteContactsViewModel::class.java)

        _binding = FragmentDeleteContactsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Configurar RecyclerView
        binding.recyclerViewDeleteContacts.layoutManager = LinearLayoutManager(context)
        deleteContactAdapter = DeleteContactAdapter(emptyList()) { contact ->
            // Callback para cuando se hace clic en el botón eliminar
            deleteContactsViewModel.deleteContact(contact)
        }
        binding.recyclerViewDeleteContacts.adapter = deleteContactAdapter

        // Observar cambios en la lista de contactos
        deleteContactsViewModel.contacts.observe(viewLifecycleOwner) { contacts ->
            deleteContactAdapter.updateContacts(contacts)

            // Mostrar mensaje si no hay contactos
            if (contacts.isEmpty()) {
                binding.textEmptyDeleteContacts.visibility = View.VISIBLE
                binding.recyclerViewDeleteContacts.visibility = View.GONE
            } else {
                binding.textEmptyDeleteContacts.visibility = View.GONE
                binding.recyclerViewDeleteContacts.visibility = View.VISIBLE
            }
        }

        // Observar resultado de eliminación
        deleteContactsViewModel.deleteResult.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { result ->
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }

    override fun onResume() {
        super.onResume()

        deleteContactsViewModel.loadContacts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

