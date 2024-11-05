import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NameGeneratorApp()
        }
    }
}

@Composable
fun NameGeneratorApp() {
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var generatedNames by remember { mutableStateOf(listOf<String>()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF1C1C1C), Color(0xFF2E2E2E)),
                    start = Offset(0f, 0f),
                    end = Offset.Infinite
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Name Generator",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Enter Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(Color(0xFF424242), shape = MaterialTheme.shapes.medium)
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    val newName = generateName(description.text)
                    generatedNames = generatedNames + newName
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1E88E5),
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(8.dp),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text("Generate Name", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (generatedNames.isNotEmpty()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF333333)),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Generated Names",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E88E5),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        LazyColumn {
                            items(generatedNames) { name ->
                                Text(
                                    text = name,
                                    fontSize = 16.sp,
                                    color = Color.White,
                                    modifier = Modifier.padding(vertical = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// Replace with API if u want
fun generateName(description: String): String {
    val prefixes = listOf(
        "Pro", "Smart", "Eco", "Gen", "Alpha", "Ultra", "Quantum", "Nova", "Bright", "Next",
        "True", "Omni", "Prime", "Core", "Hyper", "Astra", "Peak", "Vista", "Blue", "Zen"
    )
    val suffixes = listOf(
        "Solutions", "Tech", "Hub", "Works", "Labs", "Sys", "Dynamics", "Space", "Innovations",
        "Edge", "Global", "Pulse", "Ware", "Logic", "Spark", "Flow", "Shift", "Sense", "Node", "Forge"
    )

    val prefix = prefixes[Random.nextInt(prefixes.size)]
    val suffix = suffixes[Random.nextInt(suffixes.size)]

    val core = description.split(" ")
        .filter { it.isNotBlank() }
        .joinToString("") { it.take(1).uppercase() }
        .take(8)
        .ifEmpty { "Name" }

    return "$prefix$core$suffix"
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NameGeneratorApp()
}