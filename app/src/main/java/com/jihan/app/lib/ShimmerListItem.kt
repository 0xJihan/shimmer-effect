package com.jihan.app.lib

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerListItem(
    isLoading:Boolean,
    modifier: Modifier = Modifier,
    contentAfterLoading: @Composable () -> Unit
) {


    if (isLoading){

        Row (modifier){
            Box(Modifier.size(100.dp).shimmerEffects())
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Box(Modifier.fillMaxWidth().height(20.dp).shimmerEffects())
                Spacer(Modifier.height(16.dp))
                Box(Modifier.fillMaxWidth().height(20.dp).shimmerEffects())
            }

        }

    }else{
        contentAfterLoading()
    }


}

fun Modifier.shimmerEffects(): Modifier = composed{
    var size by remember { mutableStateOf(IntSize.Zero) }

    val transition = rememberInfiniteTransition(label = "Shimmer Transition")

    val startOfSetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(tween(1000)),
        label = "Shimmer Animation"
    )

    background(brush = Brush.linearGradient(
        colors = listOf(
            Color(0xFFB8B5B5),
            Color(0xFF8F8B8B),
            Color(0xFFB8B5B5),
        ),
        start = Offset(startOfSetX, 0f),
        end = Offset(startOfSetX + size.width.toFloat(), size.height.toFloat())
    )).onGloballyPositioned {
        size=it.size
    }


}