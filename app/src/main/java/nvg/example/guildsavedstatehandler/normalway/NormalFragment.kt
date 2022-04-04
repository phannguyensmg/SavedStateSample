package nvg.example.guildsavedstatehandler.normalway

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
import nvg.example.guildsavedstatehandler.util.ARG_NAME

@AndroidEntryPoint
class NormalFragment : Fragment() {

    private var _binding: FragmentNormalWayBinding? = null
    private val viewModel by viewModels<NormalViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNormalWayBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUiChanges()
        setupActions()
        loadUiData()
    }

    private fun subscribeUiChanges() {
        viewModel.uiState
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .mapLatest { ui -> _binding?.ui = ui }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setupActions() {
        _binding?.btnOpenTemp?.setOnClickListener {
            findNavController().navigate(R.id.action_normal_to_temp)
        }
    }

    private fun loadUiData() {
        val arg = arguments?.getString(ARG_NAME) ?: "(Empty)"
        viewModel.fetchData(arg)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}