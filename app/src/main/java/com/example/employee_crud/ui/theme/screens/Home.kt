package com.example.employee_crud.ui.theme.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.employee_crud.R
import com.example.employee_crud.navs.routes.NavRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        Text(
            text = "Employee Crud Operation",
            fontSize = 35.sp ,
            modifier = Modifier.padding(start = 20.dp, top = 20.dp),
            color = Color.Red,
            fontWeight = FontWeight.Bold,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Card (
                    modifier = Modifier
                        .width(165.dp)
                        .height(200.dp)
                    ,
                    elevation = CardDefaults
                        .cardElevation(defaultElevation = 10.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Magenta
                    ),
                    onClick = {
                        navController.navigate(NavRoutes.CreateEmployee.route)

                    }

                ){
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column (
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(130.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ){

                            Icon(
                                painter = painterResource(id = R.drawable.baseline_accessibility_new_24),
                                contentDescription = "My icon",
                                tint = Color.White,
                                modifier = Modifier.size(50.dp)
                            )
                        }
                        Column(
                            modifier = Modifier
                                .background(Color.White)
                                .fillMaxWidth()
                                .height(70.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(onClick = {
                                navController.navigate(NavRoutes.CreateEmployee.route)

                            }, shape = CutCornerShape(10),
                                border = BorderStroke(1.dp, Color.Magenta),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)) {
                                Text(text = "New Employee",
                                    color = Color.Black,
                                    fontFamily = FontFamily.Monospace)
                            }
                        }
                    }

                }
                Card (
                    modifier = Modifier
                        .width(165.dp)
                        .height(200.dp)
                    ,
                    elevation = CardDefaults
                        .cardElevation(defaultElevation = 10.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Blue
                    ),
                    onClick = {
                        navController.navigate(NavRoutes.ViewEmployees.route)
                    }


                ){
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column (
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(130.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ){

                            Icon(
                                painter = painterResource(id = R.drawable.baseline_contact_page_24),
                                contentDescription = "My icon",
                                tint = Color.White,
                                modifier = Modifier.size(50.dp)
                            )
                        }
                        Column(
                            modifier = Modifier
                                .background(Color.White)
                                .fillMaxWidth()
                                .height(70.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(onClick = {
                                navController.navigate(NavRoutes.ViewEmployees.route)
                            }, shape = CutCornerShape(10),
                                border = BorderStroke(1.dp, Color.Blue),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)) {
                                Text(text = "Employee List",
                                    color = Color.Black,
                                    fontFamily = FontFamily.Monospace)
                            }
                        }
                    }

                }

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {


            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    Home(rememberNavController())
}