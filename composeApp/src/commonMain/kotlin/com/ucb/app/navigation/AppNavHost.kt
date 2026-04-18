package com.ucb.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ucb.app.github.presentation.screen.GithubScreen
import com.ucb.app.movie.presentation.screen.MovieScreen

@Composable
fun AppNavHost() {


    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = NavRoute.Github) {
        composable<NavRoute.Profile> {


        }

        composable<NavRoute.ProfileEdit> {


        }
        composable<NavRoute.Github> {
            GithubScreen()
        }

        composable<NavRoute.Movies> {
            MovieScreen()
        }
    }
}
