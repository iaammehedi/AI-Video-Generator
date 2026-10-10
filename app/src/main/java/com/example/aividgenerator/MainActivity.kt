package com.example.aividgenerator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AIVideoGeneratorScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AIVideoGeneratorScreen() {
    var promptText by remember { mutableStateOf("") }
    var scriptText by remember { mutableStateOf("") }
    var selectedRatio by remember { mutableStateOf("16:9 (YouTube)") }
    var isGenerating by remember { mutableStateOf(false) }

    val aspectRatios = listOf("16:9 (YouTube)", "9:16 (Reels/TikTok)", "1:1 (Square)")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "AI Video Generator",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Prompt Input Field
        OutlinedTextField(
            value = promptText,
            onValueChange = { promptText = it },
            label = { Text("Enter Video Scene Prompt") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Script & Dialogue Input Field
        OutlinedTextField(
            value = scriptText,
            onValueChange = { scriptText = it },
            label = { Text("Character Dialogue / Script") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 4
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Aspect Ratio Selection
        Text(text = "Select Aspect Ratio:", style = MaterialTheme.typography.titleMedium)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            aspectRatios.forEach { ratio ->
                FilterChip(
                    selected = (selectedRatio == ratio),
                    onClick = { selectedRatio = ratio },
                    label = { Text(ratio) }
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Upload Character Button (UI Prototype)
        OutlinedButton(
            onClick = { /* Image picker will be attached here */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Upload Character Image (100% Face Lock)")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Generate Video Button
        Button(
            onClick = { isGenerating = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            enabled = !isGenerating
        ) {
            if (isGenerating) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Generating High Quality Video...")
            } else {
                Text("Generate AI Video")
            }
        }
    }
}
