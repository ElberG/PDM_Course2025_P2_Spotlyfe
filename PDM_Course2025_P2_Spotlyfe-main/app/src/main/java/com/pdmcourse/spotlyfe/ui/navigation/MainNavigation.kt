package com.pdmcourse.spotlyfe.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pdmcourse.spotlyfe.ui.screens.Form.FormScreen
import com.pdmcourse.spotlyfe.ui.screens.Form.FormViewModel
import com.pdmcourse.spotlyfe.ui.screens.SavedPlaces.SavedPlacesScreen
import com.pdmcourse.spotlyfe.ui.screens.SavedPlaces.SavedPlacesViewModel

sealed class Screen(val route: String) {
  object SavedPlaces : Screen("saved_places")
  object Form : Screen("form")
}

@Composable
fun MainNavigation(navController: NavHostController) {
  NavHost(navController, startDestination = Screen.SavedPlaces.route) {
    composable(Screen.SavedPlaces.route) {
      val viewModel = hiltViewModel<SavedPlacesViewModel>()
      SavedPlacesScreen(viewModel) { navController.navigate(Screen.Form.route) }
    }
    composable(Screen.Form.route) {
      val viewModel = hiltViewModel<FormViewModel>()
      FormScreen(viewModel) { navController.popBackStack() }
    }
  }
}

