package com.animereview.app

import android.os.Bundle
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AnimeListScreen() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeListScreen() {
    val context = LocalContext.current
    var allReviews by remember { mutableStateOf(getSampleReviews()) }
    var search by remember { mutableStateOf("") }
    var sort by remember { mutableStateOf("rating") }

    // Filter reviews based on search text
    val filtered = mutableListOf<AnimeReview>()
    for (review in allReviews) {
        if (review.title.contains(search, ignoreCase = true)) {
            filtered.add(review)
        }
    }

    // Sort the filtered list
    val sorted = if (sort == "rating") {
        filtered.sortedByDescending { it.rating }
    } else {
        filtered.sortedBy { it.title }
    }

    MaterialTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = {
                    Text(
                        "Anime Reviews",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6A1B9A)
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Search bar and sort
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
            ) {
                // Search box for filtering anime
                OutlinedTextField(
                    value = search,
                    onValueChange = { search = it },
                    label = { Text("Search anime...") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Sort by rating button
                    FilterChip(
                        selected = sort == "rating",
                        onClick = { sort = "rating" },
                        label = { Text("⭐ Top Rated") }
                    )
                    // Sort by title button
                    FilterChip(
                        selected = sort == "title",
                        onClick = { sort = "title" },
                        label = { Text("A-Z Title") }
                    )
                }
            }

            // Show reviews list if there are any
            if (sorted.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No anime reviews yet. Add one!", fontSize = 16.sp)
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    itemsIndexed(sorted) { index, review ->
                        AnimeReviewCard(review) {
                            val intent = Intent(context, DetailActivity::class.java)
                            intent.putExtra("REVIEW_ID", review.id)
                            context.startActivity(intent)
                        }
                    }
                }
            }
        }

        // FAB button to add new review
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
            FloatingActionButton(
                onClick = {
                    val intent = Intent(context, AddReviewActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier.padding(16.dp),
                containerColor = Color(0xFFFF7043)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Review", tint = Color.White)
            }
        }
    }
}

@Composable
fun AnimeReviewCard(review: AnimeReview, onCardClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCardClick() }
            .clip(RoundedCornerShape(12.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Poster image
            Box(
                modifier = Modifier
                    .size(80.dp, 120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFEEEEEE)),
                contentAlignment = Alignment.Center
            ) {
                if (review.posterImageId != 0) {
                    Image(
                        painter = painterResource(id = review.posterImageId),
                        contentDescription = review.title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text("📺", fontSize = 32.sp)
                }
            }

            // Review info
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            review.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.weight(1f)
                        )
                        // Show heart if favorite
                        if (review.isFavorite) {
                            Text("❤️", fontSize = 14.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(review.genre, fontSize = 12.sp, color = Color.Gray)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ShowStars(review.rating)
                    Text("${review.rating}/5", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

// Function to display star rating
@Composable
fun ShowStars(num: Int) {
    Row {
        for (i in 1..5) {
            if (i <= num) {
                Text("⭐", fontSize = 12.sp)
            } else {
                Text("☆", fontSize = 12.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAnimeListScreen() {
    AnimeListScreen()
}
