package de.wittenbu.magni.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.wittenbu.magni.R
import de.wittenbu.magni.models.ExerciseDefinition
import de.wittenbu.magni.ui.theme.MagniTheme
import de.wittenbu.magni.util.FakeData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseCard(
    exercise: ExerciseDefinition,
    onNavigateToExercise: (ExerciseDefinition) -> Unit,
    onNavigateToEditExercise: (ExerciseDefinition) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    ElevatedCard(
        onClick = { onNavigateToExercise(exercise) },
        modifier = Modifier.height(170.dp)
    ) {
        Column(modifier = Modifier.padding(PaddingValues(20.dp))) {
            Row (modifier = Modifier.height(20.dp) ) {
                Image(
                    painter = painterResource(id = R.drawable.exercises),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    contentDescription = null,
                    modifier = Modifier
                        .width(20.dp)
                        .clip(CircleShape))
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = exercise.name,
                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier)
                Spacer(modifier = Modifier.weight(1f))
                Box(modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.TopEnd)) {
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = null)
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Edit") },
                            onClick = { onNavigateToEditExercise(exercise) })
                        DropdownMenuItem(
                            text = { Text("Delete") },
                            onClick = { /* TODO */ }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            val chips: MutableSet<String> = mutableSetOf()
            chips.add(exercise.trainingType.toString())
            chips.addAll(exercise.weightType.map { it.toString() })
            chips.addAll(exercise.movementTypes.map { it.toString() })
            chips.addAll(exercise.muscles.map { it.toString() })

            ChipGrid(
                spacing = 6.dp,
                moreItemsView = { SuggestionChip(onClick = { }, enabled = false, label = { Text(text = "+ $it") }) },
                modifier = Modifier.fillMaxWidth()
            ) {
                chips.forEach {
                    SuggestionChip(
                        onClick = { },
                        enabled = false,
                        label = { Text(text = it, overflow = TextOverflow.Ellipsis, maxLines = 1) }
                    )
                }
            }
        }
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = false)
@Preview(name = "Light Mode")
@Composable
fun ExerciseCardPreview() {
    MagniTheme {
        ExerciseCard(exercise = FakeData.Exercise.pushUp, onNavigateToExercise = {  }, onNavigateToEditExercise = { })
    }
}