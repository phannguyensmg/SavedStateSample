package nvg.example.guildsavedstatehandler.tempscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import nvg.example.guildsavedstatehandler.databinding.FragmentTempScreenBinding

class TempScreenFragment : Fragment() {

    private var _binding: FragmentTempScreenBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTempScreenBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}