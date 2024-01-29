package com.example.notarobot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTargetMarker
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.notarobot.ui.theme.NotARobotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotARobot()

                }
            }
        }


data class Animal(val imageRes: Int, val isCat: Boolean)
@Composable
fun NotARobot() {
    val animals = remember { generateAnimalList }
    var feedbackMessage by remember { mutableStateOf<String?>(null) }


    Scaffold(
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(animal) { animal ->
                    CatDogImage(animal) { clickedAnimal ->
                        feedbackMessage = if (clickedAnimal.isCat) {
                            "Hurray, you are not a robot!"
                        } else {
                            "Oops, that is not a cat!"
                        }
                    }
                }
            }
            feedbackMessage?.let {
                FeedbackMessage(message = it) {
                    feedbackMessage = null
                }
            }
        }
    )
}



@Composable
fun CatDogImage(animal: Animal,OnClick: (Animal) -> (Unit) {
    Image(
        painter = painterResource(id = animal.imageRes),
        contentDescription = if (animal.isCat) "Cat" else "Dog",
        Modifier = Modifier
            .size(120.dp)
            .padding(8.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .clickable { onClick(animal) }
    )
    }
@Composable
fun FeedbackMessage(message: String, onDismiss: () -> Unit) {
    Snackbar(
        modifier = Modifier
            .padding(16.dp)
            .align(Alignment.BottomCenter),
        backgroundColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Text(message, modifier = Modifier.padding(8.dp))
        IconButton(
            onClick = onDismiss,
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "Dismiss")
        }
    }
}