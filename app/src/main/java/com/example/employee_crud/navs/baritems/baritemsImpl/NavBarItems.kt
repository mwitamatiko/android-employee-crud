package com.example.employee_crud.navs.baritems.baritemsImpl

import com.example.employee_crud.R
import com.example.employee_crud.navs.baritems.BarItem
import com.example.employee_crud.navs.routes.NavRoutes

object NavBarItems {

    val BarItems = listOf(
        BarItem(
            title = "Home",
            image = R.drawable.baseline_home_24,
            route = NavRoutes.Home.route
        ),
        BarItem(
            title = "New Employee",
            image = R.drawable.baseline_calculate_24,
            route = NavRoutes.CreateEmployee.route
        ),
        BarItem(
            title = "Employee List",
            image = R.drawable.baseline_contact_page_24,
            route = NavRoutes.ViewEmployees.route
        )
//        BarItem(
//            title = "Employee",
//            image = R.drawable.baseline_contact_page_24,
//            route = NavRoutes.ViewEmployee.route
//        ),
    )
}