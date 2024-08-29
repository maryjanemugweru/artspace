package com.example.artspace

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtspaceTheme
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ArtspaceTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    ArtGalleryWithState()
                }
            }
        }
    }
}

@Composable
fun ArtGalleryWithState(modifier: Modifier = Modifier) {

    val images = listOf(
        Pair(R.drawable.yoel_winkler_zqgjz7h5qky_unsplash, "Rose" to "Red (2021)"),
        Pair(R.drawable.usha_kiran_lw0qpaligda_unsplash, "Tulip" to "pink (2022)"),
        Pair(R.drawable.yoel_winkler_zqgjz7h5qky_unsplash, "Lily" to "Yellow (2023)")
    )


    var currentIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(400.dp)
                    .padding(16.dp),
                painter = painterResource(id = images[currentIndex].first),
                contentDescription = "image"
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.inversePrimary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(
                    start = 24.dp,
                    top = 16.dp,
                    end = 24.dp,
                ),
                text = images[currentIndex].second.first,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.padding(
                    start = 24.dp,
                    end = 24.dp,
                    bottom = 5.dp
                ),
                text = images[currentIndex].second.second,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 24.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    currentIndex = if (currentIndex > 0) {
                        currentIndex - 1
                    } else {
                        images.size - 1
                    }
                }
            ) {
                Text(text = "Previous")
            }

            Spacer(modifier = Modifier.width(20.dp))

            Button(
                onClick = {
                    currentIndex = (currentIndex + 1) % images.size
                }
            ) {
                Text(text = "Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtGalleryPreview() {
    ArtspaceTheme {
        ArtGalleryWithState()
    }
}