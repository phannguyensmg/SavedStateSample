package nvg.example.guildsavedstatehandler.state

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UiStateMapper @Inject constructor() {

    fun mapUiState(data: DetailState) = when (data) {
        DetailState.Empty -> DetailUiState.EMPTY
        DetailState.Loading -> DetailUiState(true, "Loading . . .")
        is DetailState.Result -> DetailUiState(false, data.str)
    }

}