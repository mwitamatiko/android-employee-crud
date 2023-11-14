package com.example.employee_crud.ui.theme.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.employee_crud.model.EmployeeData
import com.example.employee_crud.navs.routes.NavRoutes
import com.example.employee_crud.services.EmployeeInterface
import com.example.employee_crud.services.ServiceBuilder
import com.example.employee_crud.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeScreen(navController: NavHostController,id: Int,first_name: String, last_name: String,email: String){
    val id1 by remember { mutableStateOf(id) }
    var firstName by remember { mutableStateOf(first_name) }
    var lastName by remember { mutableStateOf(last_name) }
    var email1 by remember { mutableStateOf(email) }

    val context = LocalContext.current

    Log.i("id1:",""+id1)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Row {
            Text(
                text="View and/or Update Employee",
                fontSize = 25.sp
            )
        }


        Spacer(modifier = Modifier.height(50.dp))

        OutlinedTextField(
            value = firstName,
            onValueChange = {firstName = it },
//            label = { Text(text = "$first_name",fontSize = 20.sp) },
            modifier = Modifier.fillMaxWidth(0.9f),
            //shape = RoundedCornerShape(20.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )
        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            value = lastName,
            onValueChange = {lastName = it },
//            label = { Text(text = "$last_name",fontSize = 20.sp) },
            modifier = Modifier.fillMaxWidth(0.9f),
            //shape = RoundedCornerShape(20.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = email1,
            onValueChange = {email1 = it },
//            label = { Text(text = "$email",fontSize = 20.sp) },
            modifier = Modifier.fillMaxWidth(0.9f),
            //shape = RoundedCornerShape(20.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            )
        )
        Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = {
                    val updated_first_name = firstName
                    val updated_last_name = lastName
                    val updated_email = email1

                    val employeeData = EmployeeData(id,updated_first_name,updated_last_name,updated_email)

                    val employeeService = ServiceBuilder
                        .buildService(EmployeeInterface::class.java)

                    val requestCall = employeeService.updateEmployee(id1, employeeData)

                    requestCall.enqueue(object: Callback<EmployeeData>{
                        override fun onResponse(
                            call: Call<EmployeeData>,
                            response: Response<EmployeeData>
                        ) {
                            if (response.isSuccessful){
                                val updatedEmployee = response.body()
                                showToast(context,"Successfully updated user "+ updatedEmployee)

                            }else{
                                showToast(context,"Failed to update user: "+id1)
                            }
                        }

                        override fun onFailure(call: Call<EmployeeData>, t: Throwable) {
                            showToast(context,"Failed to update item: "+id1)
                        }

                    } )
                },
                border = BorderStroke(1.dp, Color.Red),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            {
                Text(
                    text = "Update Employee",
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            OutlinedButton(
                onClick = {
                    navController.navigate(NavRoutes.ViewEmployees.route)
                },
                border = BorderStroke(1.dp, Color.Red),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            {
                Text(
                    text = "View Changes",
                    fontSize = 20.sp
                )
            }

    }


}

@Preview(showBackground = true)
@Composable
fun EmployeePreview(){
    EmployeeScreen(rememberNavController(),1,"Joe","Doe","joedoe@gmail.com")
}