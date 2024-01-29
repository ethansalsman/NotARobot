package com.example.notarobot

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotARobot(darkTheme = true)
        }
    }
}

@Composable
fun NotARobot(darkTheme: Boolean) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // header for finding the cat
        Text(
            text = "Find The Cat!",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )
        // lazy column for displaying images
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            items(generateAnimalList()) { animal ->
                AnimalItem(animal)
            }
        }
    }
}


@Composable
fun AnimalItem(animal: Animal) {
    val context = LocalContext.current
    Column(
        // center and house the click event which will display message to user
        modifier = Modifier
            .padding(8.dp)
            .clickable { HandleAnimalClick(animal, context) }
    ) {
        Image(
            painter = painterResource(id = animal.imageRes),
            contentDescription = if (animal.isCat) "Cat" else "Dog",
            modifier = Modifier
                .size(120.dp)
                .clip(MaterialTheme.shapes.medium)
        )
    }
}

fun HandleAnimalClick(animal: Animal, context: Context) {
    val message = if (animal.isCat) {
        // hold messages for when the user clicks
        "Hurray, you are not a robot!"
    } else {
        "Oops, that is not a cat!"
    }
    val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
    toast.show()
}



data class Animal(val imageRes: Int, val isCat: Boolean)


fun generateAnimalList(): List<Animal> {
    // assign index numbers to each images to help display them
    val catImages = (1..3).map { resourceIdForCat(it) }
    val dogImages = (1..6).map { resourceIdForDog(it) }

    val animals = mutableListOf<Animal>()
    // assign random index
    repeat(5) {
        val randomIndex = if (it == 0) 0 else (1..6).random()
        val isCat = it == 0
        val imageRes = if (isCat) catImages.random() else dogImages[randomIndex]
        animals.add(Animal(imageRes, isCat))
    }

    return animals
}
// index for cats
fun resourceIdForCat(index: Int): Int {
    return when (index) {
        1 -> R.drawable.cat1
        2 -> R.drawable.cat2
        3 -> R.drawable.cat3
        else -> throw IllegalArgumentException("Invalid cat index: $index")
    }
}
// index for dogs
fun resourceIdForDog(index: Int): Int {
    return when (index) {
        1 -> R.drawable.dog1
        2 -> R.drawable.dog2
        3 -> R.drawable.dog3
        4 -> R.drawable.dog4
        5 -> R.drawable.dog5
        6 -> R.drawable.dog6
        else -> throw IllegalArgumentException("Invalid dog index: $index")
    }
}







