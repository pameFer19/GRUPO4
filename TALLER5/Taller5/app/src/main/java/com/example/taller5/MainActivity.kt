package com.example.taller5

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taller5.ui.theme.Taller5Theme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.taller5.data.Person
import com.example.taller5.data.people

// Actividad principal de la aplicación
class MainActivity : ComponentActivity() {

    //se ejecuta cuando se inicia la app
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Define la interfaz usando Jetpack Compose
        setContent {

            // Aplica el tema general de la app
            Taller5Theme() {

                // Llama al composable principal y ocupa toda la pantalla
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    // Estado que controla si se muestra la pantalla de bienvenida
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }


    // Surface actúa como contenedor principal de la app
    Surface(modifier) {

        // Decide qué pantalla mostrar según el estado
        if (shouldShowOnboarding) {

            // Muestra la pantalla de onboarding
            // Al presionar "Continue", cambia el estado a false
            OnboardingScreen(
                onContinueClicked = { shouldShowOnboarding = false }
            )

        } else {

            // Muestra la pantalla principal con la lista de saludos
            Greetings()
        }
    }
}

@Composable
fun OnboardingScreen(
    // Función que se ejecuta al presionar el botón "Continue"
    onContinueClicked: () -> Unit,
    //Para modificaciones externas
    modifier: Modifier = Modifier
) {

    // Column organiza los elementos de forma vertical
    Column(
        // Ocupa toda la pantalla
        modifier = modifier.fillMaxSize(),

        // Centra el contenido verticalmente
        verticalArrangement = Arrangement.Center,

        // Centra el contenido horizontalmente
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Texto de bienvenida
        Text("Bienvenidos a Basics Codelab!")

        // Botón para continuar a la siguiente pantalla
        Button(
            // Espacio vertical alrededor del botón
            modifier = Modifier.padding(vertical = 24.dp),

            // Al hacer clic, se ejecuta la función recibida
            onClick = onContinueClicked
        ) {
            Text("Continuar")
        }
    }
}


@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    peopleList: List<Person> = people
) {
    LazyColumn(
        // Espacio vertical entre elementos
        modifier = modifier.padding(vertical = 4.dp)
    ) {
        // Itera con índice y persona
        itemsIndexed(peopleList) { index, person ->
            Greeting(
                // Cada elemento se muestra con Greeting, agregando el número delante del nombre
                name = "${index + 1}. ${person.name}",
                description = person.description
            )
        }
    }
}
@Composable
fun Greeting(name: String,description: String, modifier: Modifier = Modifier) {
    // Card de Material3 que actúa como tarjeta para cada saludo
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary // Color de fondo de la tarjeta
        ),
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp) // Espaciado exterior
    ) {
        // Contenido de la tarjeta con animación y expansión
        CardContent(name, description)
    }
}

@Composable
private fun CardContent(name: String, description: String) {
    // Estado que controla si la tarjeta está expandida
    var expanded by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(12.dp) // Espaciado interno
            .animateContentSize( // Animación fluida al expandir o colapsar
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f) // Ocupa el espacio disponible
                .padding(12.dp)
        ) {
            // Texto fijo
            //Text(text = "Hello, ")

            // Texto con el nombre, en estilo destacado
            Text(
                text = name,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )

            // Texto adicional que se muestra solo si la tarjeta está expandida
            if (expanded) {
                Text(
                    text = description,
                )
            }
        }

        // Botón de icono para expandir o colapsar la tarjeta
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less) // Texto accesible para lectores de pantalla
                } else {
                    stringResource(R.string.show_more)
                }
            )
        }
    }
}


@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "GreetingPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    Taller5Theme{
        Greetings()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    Taller5Theme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Preview
@Composable
fun MyAppPreview() {
    Taller5Theme{
        MyApp(Modifier.fillMaxSize())
    }
}
