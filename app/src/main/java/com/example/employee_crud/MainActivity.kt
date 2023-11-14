package com.example.employee_crud

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.employee_crud.model.EmployeeData
import com.example.employee_crud.navs.MainScreen
import com.example.employee_crud.services.EmployeeInterface
import com.example.employee_crud.services.ServiceBuilder
import com.example.employee_crud.ui.theme.Employee_crudTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Employee_crudTheme {
                MainScreen()
            }
        }
    }
}

fun showToast(context: Context, message: String){
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}
fun loadEmployees(context: Context,onSuccess: (List<EmployeeData>) -> Unit){

    try{
        val employeeService = ServiceBuilder
            .buildService(EmployeeInterface::class.java)

        val requestCall = employeeService.getEmployeeList()

        requestCall.enqueue(object: Callback<List<EmployeeData>>{
            override fun onResponse(
                call: Call<List<EmployeeData>>,
                response: Response<List<EmployeeData>>
            ) {
                if (response.isSuccessful){

                    val employeeList = response.body() ?: emptyList()

                    Log.e("employeeList-->",""+employeeList)

                    // Invoke the onSuccess callback with the parsed list
                    onSuccess(employeeList)
                }else if (response.code()==401){
                    showToast(context,"your session has expired,,,please login in again")
                }else{
                    showToast(context,"Failed to retrieve items")
                }
            }

            override fun onFailure(call: Call<List<EmployeeData>>, t: Throwable) {
                showToast(context,"Failed to retrieve items")
            }

        })
    }catch (e: Exception){
        e.localizedMessage
    }

}


fun initDeleteButton(context: Context,id: Int,onDeleteSuccess: () -> Unit){
    val employeeService = ServiceBuilder.buildService(EmployeeInterface::class.java)
    val requestCall = employeeService.deleteEmployee(id)

    requestCall.enqueue(object: Callback<Unit>{
        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
            if (response.isSuccessful){
                showToast(context,"Successfully deleted item "+ id)
                onDeleteSuccess.invoke()
            }else{
                showToast(context,"Failed to delete item: "+id)
            }
        }

        override fun onFailure(call: Call<Unit>, t: Throwable) {
            showToast(context,"Failed to delete item: "+id)
        }

    })
}


