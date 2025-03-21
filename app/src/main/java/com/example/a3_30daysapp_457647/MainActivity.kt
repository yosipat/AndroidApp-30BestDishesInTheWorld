package com.example.a3_30daysapp_457647

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a3_30daysapp_457647.data.DataSource
import com.example.a3_30daysapp_457647.model.info
import com.example.a3_30daysapp_457647.ui.theme.A3_30DaysApp_457647Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A3_30DaysApp_457647Theme {

                Day30App(

                )

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
//                Image(
//                    painter = painterResource(R.drawable.ic_woof_logo),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(dimensionResource(R.dimen.img_size))
//                        .padding(
//                            dimensionResource(R.dimen.padding_small)
//                        )
//                )
                Text(
//                    text = stringResource(R.string.app_name),
                    text = "30 Best Dishes in the World",
                    style = MaterialTheme.typography.displayMedium
                )
            }
        }
    )

}


@Composable
fun Day30App() {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = { TopAppBar() }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Day30List(infoList = DataSource().loadInfo())
        }
    }


    //Day30List(infoList = DataSource().loadInfo())
}

@Composable
fun Day30Card(info: info, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.tertiary
        else MaterialTheme.colorScheme.primary
    )
    Card(modifier=modifier) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                .background(color = color)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = info.indexResourceId.toString(),
                        modifier = Modifier.padding(16.dp),
                        color=MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.displayLarge
                    )
                    Column {
                        Text(
                            text = stringResource(info.stringResourceId),
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                            color=MaterialTheme.colorScheme.onPrimary
                        )
                        Text(
                            text = stringResource(info.countryResourceId),
                            modifier = Modifier.padding(start = 16.dp, top = 0.dp, bottom = 16.dp),
                            color = MaterialTheme.colorScheme.inversePrimary
                        )
                    }

                }

                ItemButton(expanded = expanded, onClick = { expanded = !expanded })
            }

            if (expanded) {
                Image(
                    painter = painterResource(info.imageResourceID),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(194.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = stringResource(info.infoResourceId),
                    modifier = Modifier.padding(16.dp),
                    color=MaterialTheme.colorScheme.onPrimary
                )
            }

        }
    }
}

@Composable
fun Day30List(infoList: List<info>) {
    LazyColumn() {
        items(items = infoList) { info ->
            Day30Card(
                info = info,
                modifier = Modifier.padding(10.dp).clip(MaterialTheme.shapes.medium)
            )
        }

    }
}

@Composable
fun ItemButton(
    expanded: Boolean,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp
            else Icons.Filled.KeyboardArrowDown,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onError
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    A3_30DaysApp_457647Theme {
        Day30App()
    }
}