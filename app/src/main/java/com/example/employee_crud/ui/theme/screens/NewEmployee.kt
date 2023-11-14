package com.example.employee_crud.ui.theme.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.employee_crud.model.EmployeeData
import com.example.employee_crud.services.EmployeeInterface
import com.example.employee_crud.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewEmployeeScreen(){
    val context = LocalContext.current
    var first_name by remember {
        mutableStateOf("")
    }
    var last_name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Row {
            Text(
                text="Create New Employee",
                fontSize = 30.sp
            )
        }


        Spacer(modifier = Modifier.height(50.dp))

        OutlinedTextField(
            value = first_name,
            onValueChange = {first_name = it },
            label = { Text(text = "first name",fontSize = 20.sp)},
            modifier = Modifier.fillMaxWidth(0.9f),
            //shape = RoundedCornerShape(20.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )
        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            value = last_name,
            onValueChange = {last_name = it },
            label = { Text(text = "last name",fontSize = 20.sp)},
            modifier = Modifier.fillMaxWidth(0.9f),
            //shape = RoundedCornerShape(20.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {email = it },
            label = { Text(text = "example@gmail.com",fontSize = 20.sp)},
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

                val newEmployee = EmployeeData(0,"","","")
                newEmployee.first_name = first_name
                newEmployee.last_name = last_name
                newEmployee.email = email

                val employeeService = ServiceBuilder.buildService(EmployeeInterface::class.java)
                val requestCall = employeeService.addEmployee(newEmployee)

                requestCall.enqueue(object: Callback<EmployeeData>{
                    override fun onResponse(
                        call: Call<EmployeeData>,
                        response: Response<EmployeeData>
                    ) {
                        if(response.isSuccessful){
                            var newlyCreatedEmployee = response.body()
                            Log.i("created employee: ",""+newlyCreatedEmployee)
                            Toast.makeText(context,"added employee"+newEmployee,Toast.LENGTH_SHORT).show()

                        }else{
                            Toast.makeText(context,"Failed to add employee"+newEmployee,Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<EmployeeData>, t: Throwable) {
                        Toast.makeText(context,"Failed to add employee"+newEmployee,Toast.LENGTH_SHORT).show()
                    }

                })

                // Clear text fields after button click
                first_name = ""
                last_name = ""
                email = ""


            },
            border = BorderStroke(1.dp, Color.Red),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red),
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
                .size(50.dp)
        )
        {
            Text(
                text = "Create Employee",
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun NewEmployeePreview(){
    NewEmployeeScreen()
}