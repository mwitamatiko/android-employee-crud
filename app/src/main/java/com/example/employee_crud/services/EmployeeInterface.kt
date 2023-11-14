package com.example.employee_crud.services

import com.example.employee_crud.model.EmployeeData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface EmployeeInterface {
    @GET("employees")
    fun getEmployeeList(): Call<List<EmployeeData>>

    @GET("employee/{id}")
    fun getEmployeeById(@Path("id") id: Int): Call<EmployeeData>

    @POST("create")
    fun addEmployee(@Body newEmployeeData: EmployeeData): Call<EmployeeData>

    @PUT("employee/update/{id}")
    @Headers("Content-Type: application/json")
    fun updateEmployee(@Path("id") id: Int, @Body employeeData: EmployeeData): Call<EmployeeData>


    @DELETE("employee/delete/{id}")
    fun deleteEmployee(@Path("id") id: Int): Call<Unit>
}