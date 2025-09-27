package com.example.bobsburger.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.rememberAsyncImagePainter
import com.example.bobsburger.data.response.BobBurgerResponse
import com.example.bobsburger.ui.theme.BobBurgersViewModel
import com.example.bobsburger.util.BobBurgerState


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
                            burgerChar = state.data.get(index),
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
        Card(modifier = Modifier.fillMaxWidth().align(Alignment.CenterVertically)) {
            Image(
                painter = rememberAsyncImagePainter(burgerChar.image),
                contentDescription = burgerChar.name,
                alignment = Alignment.Center,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth()
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = burgerChar.name!!,
                modifier = Modifier
                    .padding(12.dp).align(Alignment.CenterHorizontally),
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
