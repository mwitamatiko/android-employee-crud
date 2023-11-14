package com.example.employee_crud.navs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.employee_crud.navs.baritems.baritemsImpl.NavBarItems
import com.example.employee_crud.navs.routes.NavRoutes
import com.example.employee_crud.ui.theme.screens.EmployeeListScreen
import com.example.employee_crud.ui.theme.screens.EmployeeScreen
import com.example.employee_crud.ui.theme.screens.Home
import com.example.employee_crud.ui.theme.screens.NewEmployeeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    val navController = rememberNavController()

    val  context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(

                title = {
                    Text(text = "Employee Demo")
                },
                colors = TopAppBarDefaults
                    .smallTopAppBarColors(
                        containerColor = Color.Magenta,
                        titleContentColor = Color.White
                    ),
            )
        },
        content = { padding -> 
        
            Column(Modifier.padding(padding)) {
                NavigationHost(navController = navController)
            }    

        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    )
}

@Composable
fun NavigationHost(navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route
    ){
        composable(NavRoutes.Home.route){
            Home(navController=navController)
        }

        composable(NavRoutes.CreateEmployee.route){
            NewEmployeeScreen()
        }
        composable(NavRoutes.ViewEmployees.route){
            EmployeeListScreen(navController=navController)
        }
        composable(
            NavRoutes.ViewEmployee.route+"/{id}/{first_name}/{last_name}/{email}",
                arguments = listOf(
                    navArgument(name = "id"){
                        type= NavType.IntType
                        defaultValue=0
                    },
                    navArgument(name = "first_name"){
                        type= NavType.StringType
                        nullable=false
                    },
                    navArgument(name = "last_name"){
                        type= NavType.StringType
                        nullable=false
                    },
                    navArgument(name = "email"){
                        type= NavType.StringType
                        nullable=false
                    },
                )

            ){ backStackEntry ->
            EmployeeScreen(
                navController=navController,
                id = backStackEntry.arguments?.getInt("id")!!,
                first_name = backStackEntry.arguments?.getString("first_name")!!,
                last_name = backStackEntry.arguments?.getString("last_name")!!,
                email = backStackEntry.arguments?.getString("email")!!
            )
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoutes = backStackEntry?.destination?.route

    NavigationBar {
        NavBarItems.BarItems.forEach { navItem ->

            NavigationBarItem(
                selected = currentRoutes == navItem.route,
                onClick = {
                    navController.navigate(navItem.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState=true
                        }
                        launchSingleTop=true
                        restoreState=true
                    }
                },
                icon = {
                    // Convert the resource ID to an ImageVector
                    val imageVector = ImageVector.vectorResource(id = navItem.image)
                    Icon(
                        imageVector = imageVector,
                        contentDescription = navItem.title,
                        modifier = Modifier
                            .size(30.dp)
                    )
                },
                label = {
                    Text(text = navItem.title, fontSize = 18.sp)
                }
            )

        }

    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen()
}