package com.abeja.cheff.Screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.abeja.cheff.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.abeja.cheff.nav.MascotaScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
){
    val scale = remember{
        androidx.compose.animation.core.Animatable(0f)
    }
    var splash: Boolean by remember { mutableStateOf(false) }

    suspend fun loather() {
        splash = viewModel.austin()

    }

    var userGues by mutableStateOf("")


    LaunchedEffect(Unit) {
        //  loviewModel.reduceQuantityByA()
        loather()
        //loviewModel.reduceQuantityByB()
    }


    suspend fun frif(){
        // splash = viewModel.austin()
        if (splash){
            navController.navigate(MascotaScreen.AddDataScreen.name)
        }else{

            navController.navigate(MascotaScreen.LoginScreen.name)
        }

    }


    // val context = LocalContext.current

    // val telStore = StoreUserTil(context)
    // get saved email
    // val savedTil = telStore.getTil.collectAsState(initial = "")

    val scope = rememberCoroutineScope()


    suspend fun loadWeather() {
        // splash = savedTil.value as Boolean
        delay(3750L)

        frif()
    }







    LaunchedEffect(key1 = true) {
        scale.animateTo(targetValue = 0.9f,
            animationSpec = tween(durationMillis = 800,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)

                }
            ),

            )
        loadWeather()

    }



    // if (iScoreRunning) navigateToSplash else navigateToEntry
    val color = Color.Cyan
    Surface(
        modifier = Modifier
            .padding(15.dp)
            .size(343.dp)
            .scale(scale.value),

        shape = CircleShape,
        color = Color.Magenta,
        border = BorderStroke(width = 2.dp, color = color)


    ) {

        Card(
            modifier = Modifier
                .width(343.dp)
                .height(343.dp)
                .padding(1.dp)
                .clickable { scope.launch {
                    // telStore.saveTil(splash)
                }  },
        )
        {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Image(painter = painterResource(id = R.drawable.cooking),
                    contentDescription = null,
                    modifier = Modifier
                        .size(199.dp)
                        .padding(16.dp)
                        .clip(CircleShape)
                )


                Text("El Hospedaje del viajero",

                    color = color.copy(alpha = 0.5f))

            }
        }


    }
}

