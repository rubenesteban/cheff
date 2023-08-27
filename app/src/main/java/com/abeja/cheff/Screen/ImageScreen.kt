package com.abeja.cheff.Screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*


import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.abeja.cheff.R
import com.abeja.cheff.nav.MascotaScreen
import com.abeja.cheff.util.Constants.ALL_IMAGES
import kotlinx.coroutines.launch



@Composable
fun ImageScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    viewModel: ImageViewModel = hiltViewModel(),
    loginviewModel: LoginViewModel = hiltViewModel(),
    modifier: Modifier = Modifier

){
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    var userID: String by remember { mutableStateOf("") }
    var name: String by remember { mutableStateOf("") }
    var profession: String by remember { mutableStateOf("") }
    var age: String by remember { mutableStateOf("") }
    var ageInt: Int by remember { mutableStateOf(0) }
    var key: String by remember { mutableStateOf("") }
    var image: String by remember { mutableStateOf("") }


    var qua: String by remember { mutableStateOf("") }


    var userGues by mutableStateOf("")

    val context = LocalContext.current
    fun please(h:String): String {
        h.replace("\n","\n-" )
        return h

    }

    suspend fun loadWeather() {
        userID = please(age)
        key = loginviewModel.autin()

    }

    LaunchedEffect(Unit) {

        loadWeather()
    }



    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ){imageUri->
        imageUri?.let{
            viewModel.addImageToStorage(imageUri)
        }

    }

    Scaffold(
        content = { padding->

            Column(
                modifier = Modifier.fillMaxSize().padding(padding)
                    .verticalScroll(rememberScrollState())
                    .statusBarsPadding()
            ) {
                AbrirGaleria(
                    openGallery = {
                        galleryLauncher.launch(ALL_IMAGES)
                    }
                )
                GetImageFromDatabase(
                    createImageContent = { imageUrl->
                        ImageContent(imageUrl)
                    }
                )


                CourseDescriptionBody( name, profession, qua, age, image)
            }


        },

        scaffoldState = scaffoldState
    )
    AddImageToStorage(
        addImageToDatabase = {downloadUrl->
            viewModel.addImageToDatabase(downloadUrl)
        }
    )

    fun showSnackBar() = coroutineScope.launch {
        val result = scaffoldState.snackbarHostState.showSnackbar(
            message = "Imagen correctamente agregada",
            actionLabel = "Mostrar"
        )
        if (result == SnackbarResult.ActionPerformed){
            viewModel.getImageFromDatabase()
            sharedViewModel.retrieveData(
                userID = key,
                context = context
            ) { data ->
                name = data.name
                profession = data.profession
                age = data.age
                qua = data.qua
                image = data.url

            }
        }
    }


    AddImageToDatabase(
        showSnackBar = { isImageAddedToDatabase->
            if (isImageAddedToDatabase){
                showSnackBar()
            }

        }
    )


}





@Composable
private fun CourseDescriptionBody(name: String, profession:String, qua:String, age: String ,image : String ) {


    Text(
        text = image,
        color = MaterialTheme.colors.primary,
        style = MaterialTheme.typography.body2,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                top = 36.dp,
                end = 16.dp,
                bottom = 16.dp
            )
    )
    Text(
        text = name,
        style = MaterialTheme.typography.h4,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
    Spacer(modifier = Modifier.height(16.dp))
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(
            text = profession,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
    Divider(modifier = Modifier.padding(16.dp))
    Text(
        text = stringResource(id = R.string.what_you_ll_need),
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(
            text = age,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 32.dp
                )
        )

        Text(
            text = "__ / $qua",
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 32.dp
                )
        )
    }
}



