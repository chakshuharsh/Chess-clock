package UI
// This is Timer control screen of app
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.*

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button


import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.chessclock.R
import com.example.chessclock.R.string.start
import kotlin.time.Duration.Companion.minutes
import kotlin.time.minutes

@OptIn(ExperimentalMaterial3Api::class)

var timeSelected:Int=10
var increment:Int=0

@Composable
fun TimeOptionButton(
    obj: TimeOption,
viewModel: ChessTimeViewModel,

    )
{
    var selected by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val textContent=if (obj.seconds == 0) {
            "${obj.minutes} min"
        } else {
            "${obj.minutes} min | ${obj.seconds} sec"
        }

        Text(
//            "${obj.minutes} min"
          text=textContent,

            modifier = Modifier.clickable {
                selected = !selected
                timeSelected=obj.minutes
increment=obj.seconds
            //                if (selected) {
//                    viewModel.setSelectedTimeInMinutes(obj.minutes)
//                }
            }
        )
       if(obj.seconds!=0&&obj.minutes<10){ Spacer(modifier = Modifier.width(185.dp))}
       else if(obj.seconds!=0){
           Spacer(modifier = Modifier.width(168.dp))
       }
       else if(obj.minutes>9){
           Spacer(modifier = Modifier.width(222.dp))
       }

       else{
            Spacer(modifier=Modifier.width(230.dp))
       }



        RadioButton(
            selected = selected,
            onClick = {
               timeSelected=obj.minutes
                selected = !selected
                increment=obj.seconds
//                if (selected) {
//                    viewModel.setSelectedTimeInMinutes(obj.minutes)
//                }
            }
        )
    }





}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerControls(navController: NavController, viewModel: ChessTimeViewModel, obj: TimeOption){// Name is not appropriate
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())// the appbar remains inplace and does not react to scrolling

//    navController: NavHostController = rememberNavController()


    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            TopAppBar(



                modifier = Modifier.background(Color.Black), // Set background color


                        title = {
                    Text(
                        "Time Controls",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },


                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Screen.HomeScreen.name) }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Returning to homescreen"
                        )
                    }
                },


                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Edit presets"
                        )
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Settings"
                        )
                    }
                          },
                scrollBehavior = scrollBehavior,
            )
        },
        bottomBar = {
            BottomAppBar(
                contentColor = Color.White, // Set your content color
            ){
                Button(
                           onClick = {
                              viewModel.setSelectedTimeInMinutes(timeSelected, increment)
                               viewModel.resetTimers()
                               navController.navigate(Screen.HomeScreen.name)

                                     },
                           shape= startShape,
                           modifier= Modifier
                               .weight(1f)
                               .height(60.dp)


                       ){
                           Text(
                               text = stringResource(id = start),
                           style= customTextStyleforstart
                           )
                       }
            }
        } // Scrolling behaviour in bottom bar is pending
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedButton(
                onClick = { }
            ) {
                Text(text = stringResource(id = R.string.newbutton)) // we need concatenation of two string "+" and " New Custom button" for different colors

            }
            Spacer(modifier = Modifier.width(1.dp))
LazyColumn{
    items(timeOption.size
    ){index->
        TimeOptionButton(timeOption[index],viewModel)
    }
}


    }
}


}


//@Preview
//@Composable
//fun ScaffoldExamplePreview() {
//
//    TimerControls(navController)
//
//}