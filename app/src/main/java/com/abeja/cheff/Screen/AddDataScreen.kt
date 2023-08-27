package com.abeja.cheff.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.abeja.cheff.nav.MascotaScreen


@Composable
fun AddDataScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    viewModel: LoginViewModel = hiltViewModel()
) {
    var userID: String by remember { mutableStateOf("") }
    var name: String by remember { mutableStateOf("") }
    var profession: String by remember { mutableStateOf("") }
    var age: String by remember { mutableStateOf("") }
    var ageInt: Int by remember { mutableStateOf(0) }



    var qua: String by remember { mutableStateOf("") }
    var key: String by remember { mutableStateOf("") }
    var userGues by mutableStateOf("")
    val context = LocalContext.current




    suspend fun loadWeather() {

        key = viewModel.autin()

    }

    LaunchedEffect(Unit) {

        loadWeather()
    }






    // main Layout
    Column(modifier = Modifier.fillMaxSize()) {
        // back button
        Row(
            modifier = Modifier
                .padding(start = 15.dp, top = 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back_button")
            }
        }
        // add data Layout
        Column(
            modifier = Modifier
                .padding(start = 60.dp, end = 60.dp, bottom = 50.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // userID
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = userID,
                onValueChange = {
                    userID = it
                },
                label = {
                    Text(text = "Category")
                }
            )
            // Name
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = {
                    name = it
                },
                label = {
                    Text(text = "Title")
                }
            )
            // Profession
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = profession,
                onValueChange = {
                    profession = it
                },
                label = {
                    Text(text = "Preparedness")
                }
            )
            // Age
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = age,
                onValueChange = {
                    age = it

                },

                label = {
                    Text(text = "Ingredient")
                }

            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = qua,
                onValueChange = {
                    qua = it

                },
                label = {
                    Text(text = "Quantity")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            // save Button

            Button(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth(),
                onClick = {
                    val userData = UserData(
                        userID = key,
                        name = name,
                        profession = profession,
                        age = age,
                        url = userID,
                        qua = qua
                    )

                    sharedViewModel.saveData(userData = userData, context = context)
                    navController.navigate(MascotaScreen.GetDataScreen.name)
                }
            ) {
                Text(text = "Save")
            }
        }
    }
}