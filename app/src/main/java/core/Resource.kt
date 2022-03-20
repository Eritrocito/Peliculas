package core
//Esto retorna tres estados que representan la llamada al server: en espera, éxito y fracaso

sealed class Resource<out T> { //Es un tipo de clase que se usa para retornar estados
    class Loading<out T>: Resource<T>() //Es el estado de carga
    data class Succes<out T>(val data:T): Resource<T>()
    data class Failure(val exception: Exception): Resource<Nothing>()

}