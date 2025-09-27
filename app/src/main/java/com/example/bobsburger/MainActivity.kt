package com.example.bobsburger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bobsburger.screen.BurgerScreen
import com.example.bobsburger.ui.theme.BobsburgerTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BobsburgerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BurgerScreen(
                        modifier = Modifier.padding(innerPadding),
                        onBurgerSelected = {

                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
/*    BurgerScreen(
        modifier = modifier,
        onBurgerSelected = {}
    )*/
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BobsburgerTheme {
        Greeting("Android")
    }
}