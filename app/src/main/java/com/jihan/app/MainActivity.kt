package com.jihan.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jihan.app.lib.ShimmerListItem
import com.jihan.app.lib.shimmerEffects
import com.jihan.app.ui.theme.AppTheme
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()





        setContent {
            AppTheme {
                var isLoading by remember { mutableStateOf(true) }
                LazyColumn(Modifier.fillMaxSize()) {
                    items(20){
                      ShimmerListItem(isLoading = isLoading,Modifier.fillMaxWidth()) {
                          Row (Modifier.fillMaxWidth()){
                              Image(painter = painterResource(R.drawable.ic_launcher_foreground),null,Modifier.size(100.dp))
                              Spacer(Modifier.width(16.dp))
                              Column(modifier = Modifier.weight(1f)) {
                                  Text("Text 1",Modifier.fillMaxWidth().height(20.dp))
                                  Spacer(Modifier.height(16.dp))
                                  Text("Text 2",Modifier.fillMaxWidth().height(20.dp))
                              }

                          }
                      }

                        Spacer(Modifier.height(16.dp))
                    }
                }

                LaunchedEffect(Unit) {
                    // mocking network call
                    delay(2500)
                    isLoading = false
                }
            }
        }
    }

}



