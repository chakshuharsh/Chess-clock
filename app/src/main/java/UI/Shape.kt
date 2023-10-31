package UI

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val customShape: CornerBasedShape = RoundedCornerShape(// for timer button -> HomeScreen
    topStart=0.dp,
    topEnd = 0.dp,
    bottomStart =0.dp,
    bottomEnd =0.dp,
    )
val customTextStyle = TextStyle(// for timer -> HomeScreen
    fontSize = 100.sp,
    fontWeight = FontWeight.Bold
)

val startShape:CornerBasedShape=RoundedCornerShape(// for start button -> TimerControl at bottom bar
    topStart = 8.dp,
    topEnd = 8.dp,
    bottomStart = 8.dp,
    bottomEnd = 8.dp,
)

val customTextStyleforstart = TextStyle(// for Start button -> TimerControl at bottom bar
    fontSize = 30.sp,
    fontWeight = FontWeight.Bold
)
