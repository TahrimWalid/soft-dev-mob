package com.listapp.myapplication

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val index = intent.getIntExtra("ITEM_INDEX", -1)
        setContent {
            DetailScreen(index, this)
        }
    }
}

@Composable
fun DetailScreen(itemIndex: Int, activity: DetailActivity) {
    val (itemName, drawableId) = when (itemIndex) {
        0 -> "Peach" to R.drawable.peach
        1 -> "Tomato" to R.drawable.tomato
        2 -> "Squash" to R.drawable.squash
        else -> "Unknown Item" to 0
    }

    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(drawableId) {
        if (drawableId != 0) {
            bitmap = scaledBitmapFromDrawable(activity, drawableId)
        }
    }

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = itemName,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            if (bitmap != null) {
                Image(
                    bitmap = bitmap!!.asImageBitmap(),
                    contentDescription = itemName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { activity.finish() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Go Back")
            }
        }
    }
}

fun scaledBitmapFromDrawable(activity: DetailActivity, drawableId: Int): Bitmap {
    val windowManager = activity.getSystemService(android.content.Context.WINDOW_SERVICE) as WindowManager
    val screenWidth = windowManager.defaultDisplay.width

    val drawable = activity.getDrawable(drawableId)
    val bitmap = drawableToBitmap(drawable!!)

    val scaleFactor = bitmap.width / screenWidth
    val newWidth = if (scaleFactor > 1) bitmap.width / scaleFactor else bitmap.width
    val newHeight = if (scaleFactor > 1) bitmap.height / scaleFactor else bitmap.height

    return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)
}

fun drawableToBitmap(drawable: Drawable): Bitmap {
    val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}
