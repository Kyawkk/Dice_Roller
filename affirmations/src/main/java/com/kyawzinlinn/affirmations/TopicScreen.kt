package com.kyawzinlinn.affirmations

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kyawzinlinn.affirmations.data.DataSource
import com.kyawzinlinn.affirmations.model.Affirmation
import com.kyawzinlinn.affirmations.model.Topic

@Composable
fun TopicScreen(modifier: Modifier = Modifier, topicList: List<Topic>){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(8.dp)
    ){
        items(topicList){
            TopicGridItem(topic = it)
        }
    }
}

@Composable
fun TopicGridItem(topic: Topic, modifier: Modifier = Modifier) {
    Card (modifier){
        Row (modifier = Modifier
            .height(68.dp)
            .fillMaxWidth()){
            Image(
                painter = painterResource(id = topic.imageResourceId),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(68.dp)
                    .fillMaxHeight(),
                contentDescription = stringResource(id = topic.stringResourceId))

            Column (modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                .fillMaxSize()) {
                Text(
                    text = stringResource(id = topic.stringResourceId),
                    style = MaterialTheme.typography.bodyMedium
                )
                Row (modifier = Modifier.padding(top = 8.dp), verticalAlignment = Alignment.CenterVertically){
                    Image(painter = painterResource(id = R.drawable.ic_grain), contentDescription = null)
                    Text(
                        text = topic.count.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun TopicGridItemPreview(){
    TopicScreen(topicList = DataSource.topics)
}
