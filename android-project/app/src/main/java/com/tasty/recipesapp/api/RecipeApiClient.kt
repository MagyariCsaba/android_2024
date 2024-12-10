package com.tasty.recipesapp.api

import RecipeService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeApiClient {
    companion object {
        private const val BASE_URL =
            "https://recipe-appservice-cthjbdfafnhfdtes.germanywestcentral-01.azurewebsites.net/"

        private const val AUTH_TOKEN =
            "eyJhbGciOiJSUzI1NiIsImtpZCI6IjFkYzBmMTcyZThkNmVmMzgyZDZkM2EyMzFmNmMxOTdkZDY4Y2U1ZWYiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiIxNjI1ODkxMzM3NDgtcWpndWZzNnJ2NDRmY3J0NHE4ZHN0cmU2djFlbG80Y3MuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiIxNjI1ODkxMzM3NDgtcWpndWZzNnJ2NDRmY3J0NHE4ZHN0cmU2djFlbG80Y3MuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMTMxNTI0MTc0ODM3NTIxNDM2NDkiLCJlbWFpbCI6ImNvZGVzcHJpbmdtZW50b3JAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImF0X2hhc2giOiJPSEhFdHdYX19iZ01QNEJIUmVHTjdRIiwibmFtZSI6Ik1lbnRvciBDb2Rlc3ByaW5nIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FDZzhvY0tTZEZOT2F6UTkyRWFBY3pqR2NCdXpmVEYyaHFqN2lNNEllcWFEQXBmSkVVdjNLdz1zOTYtYyIsImdpdmVuX25hbWUiOiJNZW50b3IiLCJmYW1pbHlfbmFtZSI6IkNvZGVzcHJpbmciLCJpYXQiOjE3MzE4NzgzNjIsImV4cCI6MTczMTg4MTk2Mn0.yAkPDj2yLc6T53aVQQ9W33JZtz2A6jIOjr26wRGa8JWkheN3N8f4vGOrePCnlaCsfq_Rm_VWnPQpciv_JktuakODwoPrzyiL1-DE6CrsoRxqQmdn3NoLo0j_Mn_DuXwCbuLuZoBHbFJ99joYtbUTGM-LlnZSmctAL3g31RhVMixLkong1xT3uNtKWbusSYshfOIUy89hunfux0O6W35-KA-iRzB8S9sucCPGtOXKX-gNawH_qlDfKYMblh0iMGaFmv_ErrH5mJqCWwhhYLbiJpEM1zBQMrcW6nsJ9zkQpoEOD9_kZz_2UQWMgJP3LCxvlvR06Jc9wU3woEiEmzJR0w"
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(AUTH_TOKEN))
        .build()

    val recipeService: RecipeService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        recipeService = retrofit.create(RecipeService::class.java)
    }
}