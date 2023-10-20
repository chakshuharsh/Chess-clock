package UI

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val customShape: CornerBasedShape = RoundedCornerShape(
    topStart=10.dp,
    topEnd = 10.dp,
    bottomStart =10.dp,
    bottomEnd =10.dp,
    )
val customTextStyle = TextStyle(
    fontSize = 100.sp,
    fontWeight = FontWeight.Bold
)