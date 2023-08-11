package com.kyawzinlinn.diceroller

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ArtSpace(){
    val artworks = listOf(
        ArtWork(R.drawable.dice_1,"One","Artist One","2021"),
        ArtWork(R.drawable.dice_2,"Two","Artist Two","2022"),
        ArtWork(R.drawable.dice_3,"Three","Artist Three","2023"),
        ArtWork(R.drawable.dice_4,"Four","Artist Four","2024"),
        ArtWork(R.drawable.dice_5,"Five","Artist Five","2025"),
        ArtWork(R.drawable.dice_6,"Six","Artist Six","2026")
    )

    var currentIndex by remember{ mutableStateOf(0) }

    val artWork = artworks.get(currentIndex)

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(modifier = Modifier
            .padding(32.dp)
            .shadow(3.dp, spotColor = Color.Gray)
            .fillMaxSize()
            .weight(1f),painter = painterResource(id = artWork.artworkWallId), contentDescription = artWork.artworkTitle,)

        ArtworkTitle(artWork)
        Spacer(modifier = Modifier.height(32.dp))
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            Button(onClick = { if (currentIndex > 0) currentIndex-- }, modifier = Modifier
                .padding(8.dp)
                .weight(1f)) {
                Text(text = "Previous")
            }

            Button(onClick = { if (currentIndex != artworks.size - 1) currentIndex++}, modifier = Modifier
                .padding(8.dp)
                .weight(1f)) {
                Text(text = "Next")
            }
        }
    }
}


@Composable
fun ArtworkTitle(artWork: ArtWork) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = artWork.artworkTitle,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${artWork.artist} (${artWork.year})"
        )
    }
}

data class ArtWork(
    @DrawableRes val artworkWallId: Int,
    val artworkTitle: String,
    val artist: String,
    val year: String
)