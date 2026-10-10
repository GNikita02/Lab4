package com.example.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab4.ui.theme.Lab4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab4Theme {
        Greeting("Android")
    }
}

fun checkN(text: String): Int? {
    val n = text.trim().toIntOrNull()
    if (n == null || n <= 0) {
        return null
    }
    return n
}

fun checkA(text: String): Double? {
    val a = text.trim().replace(',', '.').toDoubleOrNull()
    if (a == null) {
        return null
    }
    return a
}

fun calc(n: Int, a: Double): Double? {
    var denom = a
    if (denom == 0.0) {
        return null
    }
    var sum = 1.0 / denom
    for (k in 1..n) {
        denom = denom * (a + k)
        if (denom == 0.0) {
            return null
        }
        sum = sum + 1.0 / denom
    }
    return sum
}
