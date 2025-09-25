package com.example.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courses.data.DataSource
import com.example.courses.model.Topic
import com.example.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TopicsGrid(topics = DataSource.topics)
                }
            }
        }
    }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = topic.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(68.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = stringResource(id = topic.nameRes),
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = topic.coursesCount.toString(),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopicsGrid(topics: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(topics) { topic ->
            TopicCard(topic = topic, modifier = Modifier.padding(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoursesTheme {
        TopicsGrid(topics = DataSource.topics)
    }
}
