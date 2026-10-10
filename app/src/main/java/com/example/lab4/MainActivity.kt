package com.example.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab4.ui.theme.Lab4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalcScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CalcScreen(modifier: Modifier = Modifier) {
    var nText by remember { mutableStateOf("") }
    var aText by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<Double?>(null) }

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = nText,
            onValueChange = {
                nText = it
                error = ""
                result = null
            },
            label = { Text("n") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = aText,
            onValueChange = {
                aText = it
                error = ""
                result = null
            },
            label = { Text("a") },
            isError = error.isNotEmpty(),
            supportingText = {
                if (error.isNotEmpty()) {
                    Text(error)
                }
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {
            val n = checkN(nText)
            val a = checkA(aText)
            if (n == null) {
                error = "Нужно натуральное n"
                result = null
            } else if (a == null) {
                error = "Нужно число a"
                result = null
            } else {
                val value = calc(n, a)
                if (value == null) {
                    error = "Деление на ноль"
                    result = null
                } else {
                    error = ""
                    result = value
                }
            }
        }) {
            Text("Вычислить")
        }
        val ready = result
        if (ready != null) {
            Text("Результат: ${"%.4f".format(ready)}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalcScreenPreview() {
    Lab4Theme {
        CalcScreen()
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
