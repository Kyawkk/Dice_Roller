package com.kyawzinlinn.diceroller

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {

    var currentStep by remember {
        mutableStateOf(1)
    }

    var squeezeCount by remember {
        mutableStateOf(0)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            when(currentStep){
                1 -> LemonTextAndImage(
                    textLabelResourceId = R.string.lemon_tree,
                    drawableResourceId = R.drawable.lemon_tree,
                    onImageClick = {
                        currentStep = 2
                        squeezeCount = (2..4).random()
                    })

                2 -> LemonTextAndImage(
                    textLabelResourceId = R.string.lemon,
                    drawableResourceId = R.drawable.lemon_squeeze,
                    onImageClick = {
                        squeezeCount--
                        if(squeezeCount == 0){
                            currentStep = 3
                        }
                    })
                
                3 -> LemonTextAndImage(
                    textLabelResourceId = R.string.glass_of_lemon,
                    drawableResourceId = R.drawable.lemon_drink,
                    onImageClick = {
                        currentStep = 4
                    })
                
                4 -> LemonTextAndImage(
                    textLabelResourceId = R.string.empty_glass,
                    drawableResourceId = R.drawable.lemon_restart,
                    onImageClick = { currentStep = 1 })
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier){
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ){
            Button(
                shape = RoundedCornerShape(16),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
                onClick = onImageClick
            ) {
                Image(painter = painterResource(id = drawableResourceId), contentDescription = null)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = textLabelResourceId),
                fontSize = 16.sp
            )
        }
    }

}

@Preview
@Composable
fun LemonPreview() {
    MaterialTheme() {
        LemonadeApp()
    }
}