package com.example.employee_crud.navs.routes

sealed class NavRoutes(val route: String){
    object Home: NavRoutes("home")
    object CreateEmployee: NavRoutes("new")
    object ViewEmployees: NavRoutes("employees")
    object ViewEmployee: NavRoutes("employee")
}

