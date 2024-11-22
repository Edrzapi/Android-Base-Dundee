package uk.co.devfoundry.androidbaseappdemo.model


data class Pokemon(
    val name: String,
    val url: String
)

data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)