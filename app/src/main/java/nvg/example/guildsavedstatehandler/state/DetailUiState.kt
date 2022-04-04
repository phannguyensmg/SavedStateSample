package nvg.example.guildsavedstatehandler.state

data class DetailUiState(
    val isLoading: Boolean,
    val result: String
) {
    companion object {
        val EMPTY = DetailUiState(false, "Empty state")
    }
}