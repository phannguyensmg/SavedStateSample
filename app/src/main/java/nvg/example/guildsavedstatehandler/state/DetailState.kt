package nvg.example.guildsavedstatehandler.state

sealed class DetailState {
    object Empty : DetailState()
    object Loading : DetailState()
    data class Result(val str: String) : DetailState()
}