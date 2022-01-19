package com.learn.todoapp.data.repositoryImpl

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.learn.todoapp.data.api.TodoApi
import com.learn.todoapp.data.api.models.ErrorResponse
import com.learn.todoapp.data.api.models.User
import com.learn.todoapp.domain.models.LoginResponse
import com.learn.todoapp.domain.repositories.LoginRepository
import retrofit2.Response

class LoginRepositoryImpl(private val todoApi: TodoApi) : LoginRepository {
    override suspend fun login(email: String, password: String): LoginResponse {
        val response = todoApi.login(User(email, password))
        var errorMessage : String? = null
        if(!response.isSuccessful) {
            errorMessage = getErrorMsg(response) // TODO need to handle in common
        }
        return LoginResponse(response.isSuccessful, response.body()?.token, errorMessage)
    }

    private fun getErrorMsg(
        response: Response<com.learn.todoapp.data.api.models.LoginResponse?>
    ): String? {
        val type = object : TypeToken<ErrorResponse>() {}.type
        val errorResponse: ErrorResponse? =
            Gson().fromJson(response.errorBody()?.charStream(), type)
        return errorResponse?.error
    }
}