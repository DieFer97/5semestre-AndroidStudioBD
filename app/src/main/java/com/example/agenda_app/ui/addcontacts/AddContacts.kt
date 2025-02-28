package com.example.agenda_app.ui.addcontacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.agenda_app.databinding.FragmentAddContactsBinding

class AddContactsFragment : Fragment() {

    private var _binding: FragmentAddContactsBinding? = null
    private val binding get() = _binding!!
    private lateinit var addContactsViewModel: AddContactsViewModel
    private val handler = android.os.Handler(android.os.Looper.getMainLooper())
    private var statusResetRunnable: Runnable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addContactsViewModel =
            ViewModelProvider(this).get(AddContactsViewModel::class.java)

        _binding = FragmentAddContactsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Configurar botón de guardar
        binding.buttonSave.setOnClickListener {
            val name = binding.editName.text.toString()
            val phone = binding.editPhone.text.toString()
            val email = binding.editEmail.text.toString()

            addContactsViewModel.saveContact(name, phone, email)
        }

        // Observar resultado de guardar
        addContactsViewModel.saveStatus.observe(viewLifecycleOwner) { result ->
            result?.let {
                binding.textStatus.visibility = View.VISIBLE
                binding.textStatus.text = it.message

                if (it.success) {
                    binding.editName.text?.clear()
                    binding.editPhone.text?.clear()
                    binding.editEmail.text?.clear()

                    // Mostrar Toast
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()

                    if (statusResetRunnable != null) {
                        handler.removeCallbacks(statusResetRunnable!!)
                    }

                    statusResetRunnable = Runnable {
                        if (_binding != null) {
                            binding.textStatus.visibility = View.GONE
                            addContactsViewModel.resetStatus()
                        }
                    }
                    handler.postDelayed(statusResetRunnable!!, 3000)
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        // Cancel any pending callbacks
        if (statusResetRunnable != null) {
            handler.removeCallbacks(statusResetRunnable!!)
            statusResetRunnable = null
        }

        super.onDestroyView()
        _binding = null
    }
}