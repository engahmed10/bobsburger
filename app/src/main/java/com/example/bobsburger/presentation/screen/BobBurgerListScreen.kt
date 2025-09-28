package com.example.bobsburger.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.rememberAsyncImagePainter
import com.example.bobsburger.data.response.BobBurgerResponse
import com.example.bobsburger.presentation.BobBurgersViewModel
import com.example.bobsburger.presentation.util.BobBurgerState


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun BurgerScreen(
    modifier: Modifier,
    onBurgerSelected: () -> Unit,
    viewmodel: BobBurgersViewModel = hiltViewModel()
) {
    val bobBurgersStateList = viewmodel.bobBurgerCharsStateFlow.collectAsState()

    LaunchedEffect(
        Unit
    ) {
        viewmodel.getBobBurgersChars()
    }

    Column(modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        when (val state = bobBurgersStateList.value) {
            is BobBurgerState.Loading -> {
                Text(text = "Loading ..,...")
            }
            is BobBurgerState.Error -> {
                Text(text = "Error")
            }
            is BobBurgerState.Success -> {
                LazyColumn {
                    items(state.data.size,
                        ) { index ->
                        BurgerItem(
                            burgerChar = state.data[index],
                            onBurgerSelected = onBurgerSelected
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BurgerItem(burgerChar: BobBurgerResponse, onBurgerSelected: () -> Unit) {

    Row(
        modifier = Modifier
            .padding(12.dp)
            .clickable { onBurgerSelected() }
    ) {
        var degree by remember { mutableStateOf(0f) }
        var degreeX by remember { mutableStateOf(0f) }
        Card(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterVertically)) {
            Text("\uD83D\uDD3D", Modifier.clickable {
                degreeX -= 10
            }.align(
                alignment = Alignment.CenterHorizontally
            ).padding(top= 10.dp))
            Image(
                painter = rememberAsyncImagePainter(burgerChar.image),
                contentDescription = burgerChar.name,
                alignment = Alignment.Center,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .height(150.dp)
                    .graphicsLayer(
                        rotationY = degree, cameraDistance = 8f,
                                rotationX = degreeX
                    )

            )
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center){
                Text("\uD83D\uDD04", Modifier.clickable {
                    degree += 10
                }.align(Alignment.TopStart))
                Text("\uD83D\uDD3C", Modifier.clickable {
                    degreeX += 10
                }.align(Alignment.Center))
                Text("\uD83D\uDD01", Modifier.clickable {
                    degree -= 10
                }.align(Alignment.CenterEnd))
            }

            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = burgerChar.name ?: "",
                modifier = Modifier
                    .padding(12.dp)
                    .align(Alignment.CenterHorizontally),
                style = TextStyle(
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF121221),
                ),
                fontWeight = FontWeight(400),
                color = Color(0xFF121221),

            )
            Text(text = "First Episode: ",Modifier.align(Alignment.CenterHorizontally))
            Text(
                text = burgerChar.firstEpisode!!,
                style = TextStyle(
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Default
                ),
               modifier =  Modifier.align(Alignment.CenterHorizontally)
            )
            Text(text = "Voiced By: ${burgerChar.voicedBy} ",
                Modifier.align(Alignment.CenterHorizontally))
        }
    }
}
