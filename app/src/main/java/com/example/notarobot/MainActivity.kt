package com.example.notarobot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.notarobot.ui.theme.NotARobotTheme
import kotlin.random.Random


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
    CatDogCaptchaApp()
}

@Composable
fun CatDogCaptchaApp() {
    val animals = remember { generateAnimalList() }
    var feedbackMessage by remember { mutableStateOf<String?>(null) }

    Scaffold(
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(animals) { animal ->
                    CatDogImage(animal) { clickedAnimal ->
                        feedbackMessage = if (clickedAnimal.isCat) {
                            "Hurray, you are not a robot!"
                        } else {
                            "Oops, that's not a cat!"
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
fun CatDogImage(animal: Animal, onClick: (Animal) -> Unit) {
    Image(
        painter = painterResource(id = animal.imageRes),
        contentDescription = if (animal.isCat) "Cat" else "Dog",
        modifier = Modifier
            .size(120.dp)
            .padding(8.dp)
            .clip(shape = CircleShape)
            .clickable { onClick(animal) }
    )
}

@Composable
fun FeedbackMessage(message: String, onDismiss: () -> Unit) {
    Snackbar(
        modifier = Modifier
            .padding(16.dp),
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

fun generateAnimalList(): List<Animal> {
    val dogImages = (1..5).map { R.drawable.dog_$it }
    val catImage = R.drawable.cat

    val animals = mutableListOf<Animal>()

    repeat(10) {
        val randomIndex = Random.nextInt(dogImages.size)
        val isCat = it == 0 // The first image is always a cat
        val imageRes = if (isCat) catImage else dogImages[randomIndex]
        animals.add(Animal(imageRes, isCat))
    }

    return animals
}

@Preview(showBackground = true)
@Composable
fun PreviewCatDogCaptchaApp() {
    CatDogCaptchaApp()
}
