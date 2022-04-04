package nvg.example.guildsavedstatehandler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import nvg.example.guildsavedstatehandler.databinding.FragmentMainBinding
import nvg.example.guildsavedstatehandler.util.ARG_NAME

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.btnNormalWay?.setOnClickListener {
            view.findNavController().navigate(R.id.open_normal_way, bundleOf(ARG_NAME to "Ronin1"))
        }
        _binding?.btnSavedStateHandler?.setOnClickListener {
            view.findNavController().navigate(R.id.open_saved_state_way, bundleOf(ARG_NAME to "Ronin2"))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}