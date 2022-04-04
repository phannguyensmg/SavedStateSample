package nvg.example.guildsavedstatehandler.normalway

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import nvg.example.guildsavedstatehandler.state.DetailState
import nvg.example.guildsavedstatehandler.state.DetailUiState
import nvg.example.guildsavedstatehandler.state.UiStateMapper
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NormalViewModel
@Inject constructor(
    private val uiMapper: UiStateMapper
) : ViewModel() {

    private val _loadedData = MutableStateFlow<DetailState>(DetailState.Empty)

    val uiState: StateFlow<DetailUiState>
        get() = _loadedData
            .mapLatest { data -> uiMapper.mapUiState(data) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(500),
                initialValue = DetailUiState.EMPTY
            )

    fun fetchData(arg: String) {
        simulateFetchingData(arg)
    }

    private fun simulateFetchingData(arg: String) {
        viewModelScope.launch {
            _loadedData.emit(DetailState.Loading)

            delay(2000)
            val t = SimpleDateFormat("hh:mm:ss", Locale.getDefault()).format(System.currentTimeMillis())
            _loadedData.emit(DetailState.Result("Last update for $arg at $t"))
        }
    }

}
