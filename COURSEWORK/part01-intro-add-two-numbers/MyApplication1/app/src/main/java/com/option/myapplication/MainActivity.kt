package com.option.myapplication

import androidx.compose.foundation.text.KeyboardOptions
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MyFirstApp() }
    }
}

@Composable
fun MyFirstApp() {
    var first by remember { mutableStateOf("") }
    var second by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("My First App", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = first,
                onValueChange = { first = it.filter { c -> c.isDigit() || c == '-' } },
                label = { Text("First number") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = second,
                onValueChange = { second = it.filter { c -> c.isDigit() || c == '-' } },
                label = { Text("Second number") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))

            Button(
                onClick = {
                    val a = first.toIntOrNull()
                    val b = second.toIntOrNull()
                    result = if (a != null && b != null) (a + b).toString()
                    else "Enter valid numbers"
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("ADD") }

            Spacer(Modifier.height(32.dp))
            Text(text = result ?: "", fontSize = 48.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyFirstApp() = MyFirstApp()