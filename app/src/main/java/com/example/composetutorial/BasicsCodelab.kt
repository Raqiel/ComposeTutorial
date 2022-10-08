package com.example.composetutorial

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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

           }
                // A surface container using the 'background' color from the theme


        }
    }
}

@Composable
fun MyApp(){

    var shoulShowOnboarding by remember { mutableStateOf(true)}

    if (shoulShowOnboarding) {
        OnboardingScreen(onContinueClicked =  { shoulShowOnboarding = false  })
    }else{
        Greetings()
    }

}

@Composable
fun Greetings(names: List<String> = listOf("World", "Compose")){
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
    var expanded by remember {mutableStateOf(false)}
    val extraPadding = if (expanded) 48.dp else 0.dp
    Surface(color = MaterialTheme.colors.primary,
    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {

       Row (modifier = Modifier.padding(24.dp)){
           Column(
               modifier = Modifier
                   .weight(1f)
                    .padding(bottom = extraPadding)
           ) {
               Text(text = "Hello,")
               Text(text = name)
           }
           OutlinedButton(onClick = { expanded = !expanded}) {
               Text(if (expanded) "Show Less" else "Show more")
           }
       }
    }
}



@Composable
fun OnboardingScreen(
    onContinueClicked: ()-> Unit
) {

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick =  onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    ComposeTutorialTheme {
        OnboardingScreen(onContinueClicked ={})
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun DefaultPreview() {
    ComposeTutorialTheme {
        MyApp()
    }
}