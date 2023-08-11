package com.kyawzinlinn.superheroes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kyawzinlinn.superheroes.model.Hero

@Composable
fun HeroItem(
    modifier: Modifier = Modifier,
    hero: Hero
){
    Card (
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .clip(MaterialTheme.shapes.medium)
    ) {
        Row (
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .sizeIn(minHeight = 72.dp)){
            HeroInfo(hero.nameRes,hero.descriptionRes, modifier = Modifier.weight(1f))
            HeroImage(hero.imageRes)
        }
    }
}

@Composable
fun HeroImage(imageRes: Int, modifier: Modifier = Modifier) {
    Box (modifier = modifier.size(72.dp)){
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.size(72.dp).clip(MaterialTheme.shapes.small)
        )
    }
}

@Composable
fun HeroInfo(nameRes: Int, descriptionRes: Int, modifier: Modifier = Modifier) {
    Column (modifier = modifier.padding(end = 16.dp)){
        Text(
            text = stringResource(id = nameRes),
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = stringResource(id = descriptionRes),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
