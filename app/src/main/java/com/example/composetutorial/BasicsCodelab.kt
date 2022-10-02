package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetutorial.ui.theme.ComposeTutorialTheme

class BasicsCodelab : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                MyApp()
                // A surface container using the 'background' color from the theme

            }
        }
    }
}


@Composable
fun MyApp(names: List<String> = listOf("World", "Compose")){
    Surface(color = MaterialTheme.colors.background) {
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            for (name in names) {
                Greeting(name)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    var expanded = remember {mutableStateOf(false)}
    Surface(color = MaterialTheme.colors.primary,
    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {

       Row (modifier = Modifier.padding(24.dp)){
           Column(
               modifier = Modifier.weight(1f)) {
               Text(text = "Hello,")
               Text(text = name)
           }
           OutlinedButton(onClick = { expanded.value = !expanded.value}) {
               Text(if (expanded.value) "Show Less" else "Show more")
           }
       }
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun DefaultPreview() {
    ComposeTutorialTheme {
        MyApp()
    }
}