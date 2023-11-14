package com.example.employee_crud.ui.theme.screens

import android.util.Log
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.employee_crud.initDeleteButton
import com.example.employee_crud.loadEmployees
import com.example.employee_crud.model.EmployeeData
import com.example.employee_crud.navs.routes.NavRoutes

@Composable
fun EmployeeListScreen(navController: NavHostController){

    val context = LocalContext.current

    // State variable to hold the list of employees
    var employeeList by remember { mutableStateOf<List<EmployeeData>>(emptyList()) }

    // State variable to represent whether data is being loaded
    var isLoading by remember { mutableStateOf(true) }


    // Call the function to load employees
    DisposableEffect(Unit) {
            // Execute code when the composable is first displayed
            loadEmployees(context) {
                employeeList = it

                // Set isLoading to false once data is loaded
                isLoading = false
            }

            // Return a cleanup lambda inside onDispose
            onDispose {
                // Cleanup or cancel any ongoing work if needed
            }
        }

    Log.e("employeeList: ",""+employeeList)


    // Show a progress bar while data is being retrieved
    if (isLoading) {

        val progressValue = 0.75f
        val infiniteTransition = rememberInfiniteTransition()

        val progressAnimationValue by infiniteTransition.animateFloat(
            initialValue = 0.0f,
            targetValue = progressValue,animationSpec = infiniteRepeatable(
                animation = tween(900)
            )
        )


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(200.dp),
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 4.dp,
                progress = progressAnimationValue

            )

            Text(
                text = "Loading...",
                fontSize=20.sp,
                modifier = Modifier.padding(top = 8.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    } else {
        LazyColumn {
            items(employeeList) { model ->
                MyList(
                    employeeData = model,
                    onClickItem = {
//                        onListItemClick
                    },
                    onDeleteClick = {id ->
                        initDeleteButton(context, id){
                            // Execute code when the composable is first displayed
                            loadEmployees(context) {
                                employeeList = it

                                // Set isLoading to false once data is loaded
                                isLoading = false
                            }

                        }
                    },
                    onEyeClick = { id, firstName, lastName, email ->
                        navController.navigate(NavRoutes.ViewEmployee.route+"/$id/$firstName/$lastName/$email")
                    }
                )
            }
        }


    }
}

@Composable
fun MyList(
    employeeData: EmployeeData,
    onClickItem: () -> Unit,
    onDeleteClick: (Int) -> Unit,
    onEyeClick: (Int, String, String, String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(10.dp),
//        onClick = { onClickItem(employeeData.id) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Content
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(1.dp)
                ) {
                    Text("Id:", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    Text("${employeeData.id}", modifier = Modifier.weight(1f))
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(1.dp)
                ) {
                    Text("First Name:", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    Text(employeeData.first_name, modifier = Modifier.weight(1f))
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(1.dp)
                ) {
                    Text("Last Name:", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    Text(employeeData.last_name, modifier = Modifier.weight(1f))
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(1.dp)
                ) {
                    Text("Email:", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    Text(employeeData.email, modifier = Modifier.weight(1f))
                }
            }

            // View icon
            IconButton(
                // Provide the function to handle the eye icon click
                onClick = { onEyeClick(
                    employeeData.id,
                    employeeData.first_name,
                    employeeData.last_name,
                    employeeData.email)
                },
                modifier = Modifier
                    .size(54.dp)
                    .padding(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Visibility,
                    contentDescription = "Eye Icon",
                    tint = Color.Blue // Set your desired color here
                )
            }

            // Delete icon
            IconButton(
                onClick = { onDeleteClick(employeeData.id) },
                modifier = Modifier
                    .size(54.dp)
                    .padding(4.dp)

            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.Red
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun EmployeeListPreview(){
    EmployeeListScreen(rememberNavController())
}