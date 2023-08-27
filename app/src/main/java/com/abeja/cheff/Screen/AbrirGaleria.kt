package com.abeja.cheff.Screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AbrirGaleria(
    openGallery: ()->Unit
){
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(64.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(onClick = openGallery){
            Text(text = "Abrir Galeria",
                fontSize = 18.sp
            )
        }
    }
}