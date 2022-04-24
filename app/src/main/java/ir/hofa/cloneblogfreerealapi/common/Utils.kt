package ir.hofa.cloneblogfreerealapi.common

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.format.DateTimeFormatter

class Utils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertStringToDate(text: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val firstDate = text.subSequence(0, 10).toString()
        val date = formatter.parse(firstDate)
        return DateTimeFormatter.ofPattern("dd, MMM yyyy").format(date)
    }
}