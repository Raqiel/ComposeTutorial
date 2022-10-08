package com.example.composetutorial

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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

    var shoulShowOnboarding by rememberSaveable { mutableStateOf(true)}

    if (shoulShowOnboarding) {
        OnboardingScreen(onContinueClicked =  { shoulShowOnboarding = false  })
    }else{
        Greetings()
    }

}

@Composable
fun Greetings(names: List<String> =List  (1000) {"$it"} ){
    Surface(color = MaterialTheme.colors.background) {
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            LazyColumn{
                item { Text("Header") }
                items(names) { name ->
                    Greeting(name)
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    var expanded by remember {mutableStateOf(false)}
    val extraPadding by animateDpAsState(
        targetValue = if (expanded) 48.dp else 0.dp,
        animationSpec = tween(
            durationMillis = 500
        )
    )

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


@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name="DefaultPreviewDark"
)
@Preview(showBackground = true, widthDp = 400)
@Composable
fun DefaultPreview() {
    ComposeTutorialTheme {
        MyApp()
    }
}