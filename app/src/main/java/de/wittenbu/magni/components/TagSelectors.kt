package de.wittenbu.magni.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagSelectors(
    selectedTag: String?,
    tags: List<String>,
    types: List<Pair<String, List<String>>>,
    onClickTag: (String) -> Unit
) {
    types.forEach { type ->
        AnimatedVisibility(
            visible = selectedTag == type.first,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            FlowRow(mainAxisSpacing = 8.dp) {
                type.second.forEach {
                    FilterChip(
                        selected =  tags.contains(it),
                        onClick = { onClickTag(it) },
                        label = { Text(text = it) })
                }
            }
        }
    }
}

