package UI

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


private val initialTimeInMinutes: Long = 10
private val initialTimeInSeconds: Long = initialTimeInMinutes * 60

class ChessTimeViewModel :ViewModel(), CoroutineScope {
    private val job= Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main+job


   private suspend fun delayOneSecond(){
       delay(1000)
   }

    private val initialTimeInMinutes: Long = 10
    private val initialTimeInSeconds: Long = initialTimeInMinutes * 60
   private var currentPLayer=1

    data class TimerState(
        val player1Time: Long = initialTimeInSeconds, // Time remaining for player 1 in seconds
        val player2Time: Long = initialTimeInSeconds, // Time remaining for player 2 in seconds
        val isPlayer1Running: Boolean=false, // Is player 1's timer running?
        val isPlayer2Running: Boolean=false // Is player 2's timer running?
    )

    private var timerState by mutableStateOf(TimerState())
    fun retrieveTimerState(): TimerState {
        return timerState
    }

fun retrievecurrentPlayer():Int{
    return currentPLayer
}
fun formatTime(totalSeconds: Long): String{
    val minutes=totalSeconds/60
    val seconds =totalSeconds%60
    return String.format("%02d:%02d", minutes,seconds)
}
fun resetTimer(){
    timerState=timerState.copy()
}

    suspend fun startPlayer1Time(){
    timerState=timerState.copy(isPlayer1Running=true)

    coroutineScope { launch { updateTimers(1) } }
}
fun pausePlayer1Time(){
    timerState=timerState.copy(isPlayer1Running = false, isPlayer2Running = false)
}
    suspend fun startPlayer2Time(){
        timerState=timerState.copy(isPlayer2Running=true, isPlayer1Running = false)

        coroutineScope { launch { updateTimers(2) } }
    }
    fun pausePlayer2Time(){
        timerState=timerState.copy(isPlayer2Running = false)
    }
fun stopGame(){
    pausePlayer2Time()
    pausePlayer1Time()
}
   fun switchPlayer(){
       currentPLayer=3-currentPLayer
   }
    private suspend fun updateTimers(currentPlayer: Int) {
        while ((currentPlayer == 1 && timerState.isPlayer1Running) || (currentPlayer == 2 && timerState.isPlayer2Running)) {
            delayOneSecond()
            if (currentPlayer == 1 && timerState.isPlayer1Running) {
                // Delay for 1 second before decrementing player 1's time

                if (timerState.player1Time > 0) {
                    timerState = timerState.copy(player1Time = timerState.player1Time - 1)
                } else {
                    timerState = timerState.copy(isPlayer1Running = false)
                }
            }

            if (currentPlayer == 2 && timerState.isPlayer2Running) {
                // Delay for 1 second before decrementing player 2's time

                if (timerState.player2Time > 0) {
                    timerState = timerState.copy(player2Time = timerState.player2Time - 1)
                } else {
                    timerState = timerState.copy(isPlayer2Running = false)
                }
            }
        }
    }


    fun resetTimers(){
    timerState=TimerState(
        player1Time = initialTimeInSeconds,
        player2Time = initialTimeInSeconds,
        isPlayer1Running = false,
        isPlayer2Running = false
    )
}

}