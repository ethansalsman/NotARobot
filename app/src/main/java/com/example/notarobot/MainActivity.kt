package com.example.notarobot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.notarobot.ui.theme.NotARobotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotARobotTheme {
                // A surface container using the 'background' color from the them
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}
data class Animal(val imageRes: Int, val isCat: Boolean)
@Composable
fun NotARobot() {
    val animals = remember { generateAnimalList }
    var feedbackMessage by remember { mutableStateOf<String?>(null) }


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotARobotTheme {
        Greeting("Android")
    }
}