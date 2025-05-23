package com.example.fragmen_kotlin.api

import com.example.fragmen_kotlin.model.Komik
import retrofit2.http.GET
import retrofit2.http.Headers

interface SupabaseApi {

    @Headers(
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inlucnhna2J2cnBubnpjemVhYWtsIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDczMTI4NDUsImV4cCI6MjA2Mjg4ODg0NX0.q_tIHhWkJrcj5YYTMvu4DvTxCBcD1J5ShYnlPE5fAl8",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inlucnhna2J2cnBubnpjemVhYWtsIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDczMTI4NDUsImV4cCI6MjA2Mjg4ODg0NX0.q_tIHhWkJrcj5YYTMvu4DvTxCBcD1J5ShYnlPE5fAl8"
    )
    @GET("komik?select=*")
    suspend fun getKomikList(): List<Komik>
}