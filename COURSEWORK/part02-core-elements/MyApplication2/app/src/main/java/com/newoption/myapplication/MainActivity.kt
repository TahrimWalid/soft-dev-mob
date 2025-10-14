package com.newoption.myapplication

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { QuickLauncherScreen() }
    }
}

@Composable
fun QuickLauncherScreen() {
    val ctx = LocalContext.current

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // second screen
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { ctx.startActivity(Intent(ctx, SecondActivity::class.java)) }
            ) { Text("SECOND ACTIVITY") }

            Spacer(Modifier.height(16.dp))

            // MAPS
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    try {
                        ctx.startActivity(
                            Intent(Intent.ACTION_VIEW, "geo:0,0?q=".toUri())
                        )
                    } catch (_: ActivityNotFoundException) { // no maps app
                        try {
                            ctx.startActivity(
                                Intent(Intent.ACTION_VIEW, "https://maps.google.com".toUri())
                            )
                        } catch (_: Exception) {
                            try {
                                ctx.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        "market://details?id=com.google.android.apps.maps".toUri()
                                    )
                                )
                            } catch (_: Exception) {
                                Toast.makeText(ctx, "No app can open maps", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            ) { Text("GOOGLE MAPS") }

            Spacer(Modifier.height(16.dp))

            // EMAIL
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val mail = Intent(Intent.ACTION_SENDTO, "mailto:".toUri())
                    try {
                        ctx.startActivity(Intent.createChooser(mail, "Open email app"))
                    } catch (_: ActivityNotFoundException) {
                        try {
                            ctx.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    "market://details?id=com.google.android.gm".toUri()
                                )
                            )
                        } catch (_: Exception) {
                            try {
                                ctx.startActivity(
                                    Intent(Intent.ACTION_VIEW, "https://mail.google.com".toUri())
                                )
                            } catch (_: Exception) {
                                Toast.makeText(ctx, "No email app installed", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            ) { Text("GMAIL") }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuickLauncherPreview() { QuickLauncherScreen() }