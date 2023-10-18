package UI

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val customShape: CornerBasedShape = RoundedCornerShape(
    topStart=0.dp,
    topEnd = 0.dp,
    bottomStart =0.dp,
    bottomEnd =0.dp,
    )
val customTextStyle = TextStyle(
    fontSize = 100.sp,
    fontWeight = FontWeight.Bold
)