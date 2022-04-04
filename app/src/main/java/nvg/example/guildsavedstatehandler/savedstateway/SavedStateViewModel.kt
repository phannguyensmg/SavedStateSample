package nvg.example.guildsavedstatehandler.savedstateway

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import nvg.example.guildsavedstatehandler.state.DetailState
import nvg.example.guildsavedstatehandler.state.DetailUiState
import nvg.example.guildsavedstatehandler.state.UiStateMapper
import nvg.example.guildsavedstatehandler.util.ARG_NAME
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SavedStateViewModel
@Inject constructor(
    savedStateHandle: SavedStateHandle,
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

    init {
        val arg = savedStateHandle.get<String>(ARG_NAME) ?: "(Empty)"
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
