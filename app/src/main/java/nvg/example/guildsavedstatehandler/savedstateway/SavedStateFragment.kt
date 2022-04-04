package nvg.example.guildsavedstatehandler.savedstateway

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import nvg.example.guildsavedstatehandler.R
import nvg.example.guildsavedstatehandler.databinding.FragmentNormalWayBinding

@AndroidEntryPoint
class SavedStateFragment : Fragment() {

    private var _binding: FragmentNormalWayBinding? = null
    private val viewModel by viewModels<SavedStateViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNormalWayBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActions()
        subscribeUiChanges()
    }

    private fun setupActions() {
        _binding?.btnOpenTemp?.setOnClickListener {
            findNavController().navigate(R.id.action_saved_state_to_temp)
        }
    }

    private fun subscribeUiChanges() {
        viewModel.uiState
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .mapLatest { ui -> _binding?.ui = ui }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}