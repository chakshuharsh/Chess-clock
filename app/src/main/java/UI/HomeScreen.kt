package UI

import Gray
import Saffron
import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.StringRes
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chessclock.R
import kotlinx.coroutines.launch

fun initializeMediaPlayer(context: Context, resId: Int): MediaPlayer {
    return MediaPlayer.create(context, resId)
}



enum class Screen{
HomeScreen,
    TimerControl
}


@Composable
fun HomeScreenApp(
    navController: NavHostController = rememberNavController(), viewModel: ChessTimeViewModel
) {


    // function created to solve "suspend call from non-suspend function" problem
    fun startPlayer1TimeNonSuspend() {
        viewModel.launch {
            viewModel.startPlayer1Time()
        }
    }
    // function created to solve "suspend call from non-suspend function" problem
    fun startPlayer2TimeNonSuspend() {
        viewModel.launch {
            viewModel.startPlayer2Time()
        }
    }

var isDialogVisible by remember {
    mutableStateOf(false)
}

// BELOW IS CODE FOR SOUND WHEN AUDIO BUTTON IS ENABLED
val soundOn= initializeMediaPlayer(LocalContext.current, R.raw.sound_of_speaker_on)
    val secondMediaPlayer=remember{
        soundOn
    }


//BELOW IS CODE FOR TAP SOUND OF BUTTONS OF EACH PLAYER
    val initializeMediaPlayer =
        initializeMediaPlayer(LocalContext.current, R.raw.chessclocktapsound)
    val mediaPlayer = remember {
        initializeMediaPlayer
    }

var isSoundOn by remember {
    mutableStateOf(true)
}


    var player1Color:Color by remember{
        mutableStateOf(Color.DarkGray)
    }
    var player2Color:Color by remember{
        mutableStateOf(Color.DarkGray)
    }
    var text1Color:Color by remember{
        mutableStateOf(Color.Black)
    }
    var text2Color:Color by remember{
        mutableStateOf(Color.Black)
    }

var isPaused by remember {
    mutableStateOf(false)
} // used for checking whether game is paused or not


    Column( // Column1 starts here
        modifier = Modifier
            .fillMaxSize()
//            .padding(16.dp)
            .background(color=Color.Black),
        verticalArrangement = Arrangement.SpaceBetween
    ){

//Button for player 1-> when clicked time of player 2 starts decrementing
        Button(
            colors = ButtonDefaults.outlinedButtonColors(contentColor = text1Color, containerColor = player1Color),
    onClick = {
if(isPaused){

}
     else {
    if (viewModel.currentPLayer == 1) {
        if(isSoundOn){ mediaPlayer.start()}
        player2Color=Saffron
        text2Color=Color.White
        player1Color=Color.DarkGray
        text1Color=Color.Black
        viewModel.pausePlayer1Time()
        viewModel.incrementTimeForPLayer1()//increment time = 0  where increment is not selected but function call would take place
        startPlayer2TimeNonSuspend()
       viewModel.switchPlayer()

       //player switch
        if(viewModel.move2>=1){
            viewModel.increaseMove1()
        }







    }

}
              },
            shape = customShape,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(color=Color.DarkGray)
        )
{

Column() {
    Text(
        text =viewModel.formatTime(viewModel.retrieveTimerState().player1Time) ,
        style= customTextStyle,
        modifier=Modifier.rotate(180f)

    )
    Spacer(modifier = Modifier.width(100.dp))
   Row() {
       Text(
           text = "Moves :"
       )

       Text(
           text = "${viewModel.move1}"
       )
   }
   }

        }// First Player

        Row(
    horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color=Color.Black)
){
    IconButton(
        colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.Black, contentColor = Color.Gray),
        onClick = {
               if(viewModel.retrieveTimerState().isPlayer1Running){
                   player1Color=Color.DarkGray
                   text1Color=Color.Black
                   viewModel.pausePlayer1Time()
               isPaused=true
               }
        else if(viewModel.retrieveTimerState().isPlayer2Running){
                   player2Color=Color.DarkGray
                   text2Color=Color.Black
            viewModel.pausePlayer2Time()
        isPaused=true
        }
                  else{
                      if(viewModel.currentPLayer==2){
                          player2Color=Saffron
                          text2Color=Color.White
                          startPlayer2TimeNonSuspend()
                          isPaused=false
                      }
               else if(viewModel.currentPLayer==1){
                          player1Color=Saffron
                          text1Color=Color.White
                   startPlayer1TimeNonSuspend()
               isPaused=false
               }
                  }
                  },
        modifier = Modifier
            .weight(1f)
            .background(color=Color.Black)

    ) {
        //Text(text = "Play/Pause")
        if (viewModel.retrieveTimerState().isPlayer1Running || viewModel.retrieveTimerState().isPlayer2Running) {
            Icon(
                painter = painterResource(id = R.drawable.pause), // Use a custom drawable for the pause icon
                contentDescription = "Play",
                modifier = Modifier.size(42.dp)

            )
        } else {


            Icon(
                imageVector = Icons.Default.PlayArrow, // Use the built-in play icon
                contentDescription = "Play",
                modifier = Modifier.size(42.dp)
            )
        }

    }

            Spacer(modifier = Modifier.width(1.dp))

            //RESET BUTTON
    IconButton(
        colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.Black, contentColor = Color.Gray),
        onClick = {
            viewModel.stopGame()
            isPaused=true
            isDialogVisible=true
player2Color=Color.DarkGray
            player1Color= Color.DarkGray
            text2Color= Color.Black
            text1Color=Color.Black


                  },
        modifier = Modifier.weight(1f)
    ) {
        //Text(text = "Reset")
        Icon(
            imageVector= Icons.Default.Refresh,
            contentDescription="Reset",
            modifier=Modifier.size(40.dp)
        )
    }

            if(isDialogVisible) {
                AlertDialog(
                    text = {
                        Text(text = stringResource(id = R.string.reset_button))
                    },
                    onDismissRequest = {
isDialogVisible=false
                        player2Color=Color.DarkGray
                        player1Color=Color.DarkGray
                        text1Color=Color.Black
                        text2Color=Color.Black
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                              player2Color=Color.DarkGray
                                player1Color=Color.DarkGray
                                text1Color=Color.Black
                                text2Color=Color.Black
                                viewModel.resetTimers()
                                isDialogVisible=false
                                isPaused=false

                            }
                        ) {
                            Text(text = stringResource(id = R.string.yes))
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                viewModel.stopGame()
                                isPaused=true
                                isDialogVisible=false
                                player2Color=Color.DarkGray
                                player1Color=Color.DarkGray
                                text1Color=Color.Black
                                text2Color=Color.Black

                            }
                        ) {
                            Text(text = stringResource(id = R.string.no))
                        }
                    }

                )
            }


            Spacer(modifier = Modifier.width(25.dp))


            // Settings Button
            IconButton(
                colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.Black, contentColor = Color.Gray),
                onClick = {
viewModel.stopGame()
            navController.navigate(Screen.TimerControl.name)
        },
        modifier = Modifier.weight(1f)
    ) {
        //Text(text = "Play/Pause")
        Icon(
            imageVector= Icons.Default.Settings,
            contentDescription="Settings",
            modifier=Modifier.size(40.dp)
        )
    }
    Spacer(modifier = Modifier.width(1.dp))

    IconButton(
        colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.Black, contentColor = Color.Gray),
        onClick = {
            if(!isSoundOn){
                secondMediaPlayer.start()
            }
            isSoundOn = !isSoundOn

                  },
        modifier = Modifier.weight(1f)
    ) {
       if(isSoundOn) {
           Icon(
               painter = painterResource(id = R.drawable.volumeup), // Use Icons.Default.VolumeUp for the volume icon
               contentDescription = "Sound",
               modifier = Modifier.size(35.dp)
           )
       }
        else{
            Icon(
                painter= painterResource(id = R.drawable.mute_),
                contentDescription = "mute",
                modifier=Modifier.size(35.dp)
            )
       }


    }


} // Play/Pause+Reset+Settings+Sound







        Button(
            colors = ButtonDefaults.outlinedButtonColors(contentColor = text2Color, containerColor = player2Color),
                onClick = {
if(isPaused){

}
                else {
    if (viewModel.currentPLayer == 2) {
viewModel.increaseMove2()
        if(isSoundOn){ mediaPlayer.start()}
        player1Color=Saffron
        text1Color=Color.White
        player2Color=Color.DarkGray
        text2Color=Color.Black
        viewModel.pausePlayer2Time()
       viewModel.incrementTimeForPLayer2()
        startPlayer1TimeNonSuspend()
        viewModel.switchPlayer()


    }
}




                      },
            shape = customShape,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(color=Color.DarkGray)
        )
        {

           Column() {
               Text(
                   text =viewModel.formatTime(viewModel.retrieveTimerState().player2Time) ,
                   style= customTextStyle

               )
               Spacer(modifier = Modifier.width(100.dp))
Row() {
    Text(
        text = "Moves :"
    )

    Text(
        text = "${viewModel.move2}"
    )
} //Move Row
           }// Time and Moves
        } // Second Player

   //Column1 Ends here-> Major Column
    }
// HomeScreen Function ends here
}





//@Preview
//@Composable
//fun HomeScreenPreview() {
//
//    HomeScreenApp()
//
//}