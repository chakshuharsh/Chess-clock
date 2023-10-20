import UI.ChessTimeViewModel
import UI.customShape
import UI.customTextStyle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chessclock.ui.theme.ChessClockTheme
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import com.example.chessclock.R
import kotlinx.coroutines.launch


@Composable
fun HomeScreenApp(){
    val viewModel= remember {
        ChessTimeViewModel()
    }
    fun startPlayer1TimeNonSuspend() {
        viewModel.launch {
            viewModel.startPlayer1Time()
        }
    }

    fun startPlayer2TimeNonSuspend() {
        viewModel.launch {
            viewModel.startPlayer2Time()
        }
    }

    Column( // Column1 starts here
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ){


        Button(
    onClick = {

        if(viewModel.retrievecurrentPlayer()==1) {
            viewModel.switchPlayer()
            startPlayer2TimeNonSuspend()
            viewModel.pausePlayer1Time()

        }

              },
            shape = customShape,
    modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)

)
{
  Text(
      text =viewModel.formatTime(viewModel.retrieveTimerState().player1Time) ,
  style=customTextStyle
  )
}

        Row(
    horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
){
    IconButton(
        onClick = {  },
        modifier = Modifier.weight(1f)
    ) {
        //Text(text = "Play/Pause")
        Icon(
            imageVector= Icons.Default.PlayArrow,
            contentDescription="Play",
            modifier=Modifier.size(42.dp)
        )
    }
    Spacer(modifier = Modifier.width(1.dp))
    IconButton(
        onClick = { /* Handle button click here */ },
        modifier = Modifier.weight(1f)
    ) {
        //Text(text = "Reset")
        Icon(
            imageVector= Icons.Default.Refresh,
            contentDescription="Reset",
            modifier=Modifier.size(40.dp)
        )
    }
    Spacer(modifier = Modifier.width(25.dp))
    IconButton(
        onClick = { /* Handle button click here */ },
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
        onClick = { /* Handle button click here */ },
        modifier = Modifier.weight(1f)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.volumeup), // Use Icons.Default.VolumeUp for the volume icon
            contentDescription = "Sound",
            modifier = Modifier.size(35.dp)
        )
    }


}
        Button(
            onClick = {

                if(viewModel.retrievecurrentPlayer()==2) {
                    viewModel.switchPlayer()
                startPlayer1TimeNonSuspend()
                viewModel.pausePlayer2Time()
            }
                                                    },
            shape = customShape,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        )
        {

            Text(
                text =viewModel.formatTime(viewModel.retrieveTimerState().player2Time) ,
                style=customTextStyle
            )
        }

   //Column1 Ends here
    }
// HomeScreen Function ends here
}

@Preview
@Composable
fun HomeScreenPreview() {
    ChessClockTheme {
        HomeScreenApp()
    }
}