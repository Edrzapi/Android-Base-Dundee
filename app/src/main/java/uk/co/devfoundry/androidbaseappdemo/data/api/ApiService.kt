package uk.co.devfoundry.androidbaseappdemo.data.api

import retrofit2.http.GET
import uk.co.devfoundry.androidbaseappdemo.model.PokemonResponse

//CRUD methods.. get,post,update, etc..
interface ApiService {

    //    Getting pokemon
    @GET("pokemon")
    suspend fun getPokemonList(): PokemonResponse


}