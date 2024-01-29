package com.example.notarobot

import android.content.res.Resources.Theme
import android.os.Bundle
import android.text.Layout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.notarobot.ui.theme.NotARobotTheme
import kotlin.random.Random
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
fun NotARobot(darkTheme: Boolean){
    // call lazy column
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        items(generateAnimalList()) { animal ->
            AnimalItem(animal)
        }
    }
}



@Composable
fun ClickMe(){
    val context = LocalContext.current

    var name: String by remember { mutableStateOf("") }

    Column (modifier = Modifier.padding(10.dp)){
        for (i in 1..5) {
            val imageName = ""
            val imageId = context.resources.getIdentifier((imageName, "drawable", context.packageName)

            Image(
                painter = painterResource(id = imageId),
                contentDescription =  null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    .clickable {
                        // toast message
                        val toast = Toast.makeText(context, "", Toast.LENGTH_LONG)
                        toast.show()
                    }
            )

            Spacer(modifier = Modifier.width(8.dp))
        }
        Spacer(modifier = Modifier.height(3.dp))


    }
}
@Composable
fun Animals(){

}

//data class Animal(val imageRes: Int, val isCat: Boolean)
//
//@Composable
//fun NotARobot() {
//    CatDogCaptchaApp()
//}
//
//@Composable
//fun CatDogCaptchaApp() {
//    val animals = remember { generateAnimalList() }
//    var feedbackMessage by remember { mutableStateOf<String?>(null) }
//
//
//        content = {
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(16.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                items(animals) { animal ->
//                    CatDogImage(animal) { clickedAnimal ->
//                        feedbackMessage = if (clickedAnimal.isCat) {
//                            "Hurray, you are not a robot!"
//                        } else {
//                            "Oops, that's not a cat!"
//                        }
//                    }
//                }
//            }
//
//            feedbackMessage?.let {
//                FeedbackMessage(message = it) {
//                    feedbackMessage = null
//                }
//            }
//        }
//
//}
//
//@Composable
//fun CatDogImage(animal: Animal, onClick: (Animal) -> Unit) {
//    Image(
//        painter = painterResource(id = animal.imageRes),
//        contentDescription = if (animal.isCat) "Cat" else "Dog",
//        modifier = Modifier
//            .size(120.dp)
//            .padding(8.dp)
//            .clip(shape = MaterialTheme.shapes.medium)
//            .clickable { onClick(animal) }
//    )
//}
//
//@Composable
//fun FeedbackMessage(message: String, onDismiss: () -> Unit) {
//    Snackbar(
//        modifier = Modifier
//            .padding(16.dp)
//            .align(Alignment.BottomCenter),
//        backgroundColor = MaterialTheme.colorScheme.primary,
//        contentColor = MaterialTheme.colorScheme.onPrimary
//    ) {
//        Text(message, modifier = Modifier.padding(8.dp))
//        IconButton(
//            onClick = onDismiss,
//            modifier = Modifier.align(Layout.Alignment.CenterVertically)
//        ) {
//            Icon(imageVector = Icons.Default.Close, contentDescription = "Dismiss")
//        }
//    }
//}
//
//fun generateAnimalList(): List<Animal> {
//    val dogImages = (1..4).map { R.drawable.dog_$it }
//    val catImage = R.drawable.cat
//
//    val animals = mutableListOf<Animal>()
//
//    repeat(5) {
//        val randomIndex = Random.nextInt(dogImages.size)
//        val isCat = it == 0 // The first image is always a cat
//        val imageRes = if (isCat) catImage else dogImages[randomIndex]
//        animals.add(Animal(imageRes, isCat))
//    }
//
//    return animals
//}


