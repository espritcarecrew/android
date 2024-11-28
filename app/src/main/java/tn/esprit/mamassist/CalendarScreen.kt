import android.icu.util.Calendar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import tn.esprit.mamassist.R
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CalendarScreen(navController: NavController) {
    var currentMonth by remember { mutableStateOf(Calendar.getInstance()) }
    var selectedDate by remember { mutableStateOf<Calendar?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calendar", color = Color.White, fontSize = 20.sp) },
                backgroundColor = Color(0xFFF48FB1)
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth()
                    .shadow(elevation = 10.dp, shape = RoundedCornerShape(24.dp))
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = { navController.navigate("home/pregnant") }) {
                        Icon(Icons.Default.Home, contentDescription = "Home", tint = Color(0xFFF48FB1))
                    }
                    IconButton(onClick = { navController.navigate("calendar") }) {
                        Icon(Icons.Default.CalendarToday, contentDescription = "Calendar", tint = Color(0xFFF48FB1))
                    }
                    IconButton(onClick = { /* Navigate to AI Chat screen */ }) {
                        Icon(Icons.Default.Chat, contentDescription = "AI Chat", tint = Color(0xFFF48FB1))
                    }
                    IconButton(onClick = { navController.navigate("profile") }) {
                        Icon(Icons.Default.Person, contentDescription = "Profile", tint = Color(0xFFF48FB1))
                    }
                }
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Your Calendar",
                    fontSize = 24.sp,
                    color = Color(0xFFF48FB1),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))

                // CalendarContainer with updated month navigation
                CalendarContainer(
                    currentMonth = currentMonth,
                    onPreviousMonth = {
                        currentMonth = (currentMonth.clone() as Calendar).apply {
                            add(Calendar.MONTH, -1)
                        }
                    },
                    onNextMonth = {
                        currentMonth = (currentMonth.clone() as Calendar).apply {
                            add(Calendar.MONTH, 1)
                        }
                    },
                    onDateSelected = { selectedDate = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                selectedDate?.let {
                    Text(
                        text = "Selected Date: ${it.get(Calendar.DAY_OF_MONTH)}/${it.get(Calendar.MONTH) + 1}/${it.get(Calendar.YEAR)}",
                        fontSize = 16.sp,
                        color = Color(0xFF757575)
                    )
                }
            }
        }
    )
}

@Composable
fun CalendarContainer(
    currentMonth: Calendar,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit,
    onDateSelected: (Calendar) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(4.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color(0xFFF8BBD0),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header with month and navigation
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onPreviousMonth) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Previous Month")
                }
                Text(
                    text = getMonthName(currentMonth) + " " + currentMonth.get(Calendar.YEAR),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                IconButton(onClick = onNextMonth) {
                    Icon(Icons.Default.ArrowForward, contentDescription = "Next Month")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Days and dates
            val days = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
            val dates = generateCalendarDates(currentMonth)

            // Day header
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                days.forEach { day ->
                    Text(text = day, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Date grid
            dates.chunked(7).forEach { week ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    week.forEach { date ->
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .padding(4.dp)
                                .clickable { onDateSelected(date) }
                                .background(
                                    if (date.get(Calendar.MONTH) == currentMonth.get(Calendar.MONTH)) Color.LightGray else Color.Transparent,
                                    RoundedCornerShape(50)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = date.get(Calendar.DAY_OF_MONTH).toString(),
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

// Generate the name of the month
fun getMonthName(calendar: Calendar): String {
    val monthFormat = SimpleDateFormat("MMMM", Locale.getDefault())
    return monthFormat.format(calendar.time)
}

// Generate calendar dates
fun generateCalendarDates(calendar: Calendar): List<Calendar> {
    val firstDayOfMonth = calendar.clone() as Calendar
    firstDayOfMonth.set(Calendar.DAY_OF_MONTH, 1)

    val lastDayOfMonth = calendar.clone() as Calendar
    lastDayOfMonth.set(Calendar.DAY_OF_MONTH, lastDayOfMonth.getActualMaximum(Calendar.DAY_OF_MONTH))

    val daysBefore = firstDayOfMonth.get(Calendar.DAY_OF_WEEK) - 1
    val daysAfter = 7 - lastDayOfMonth.get(Calendar.DAY_OF_WEEK)

    val allDays = mutableListOf<Calendar>()

    // Previous month
    for (i in daysBefore downTo 1) {
        firstDayOfMonth.add(Calendar.DAY_OF_MONTH, -1)
        allDays.add(firstDayOfMonth.clone() as Calendar)
    }
    firstDayOfMonth.add(Calendar.DAY_OF_MONTH, daysBefore) // Reset

    // Current month
    for (i in 1..lastDayOfMonth.get(Calendar.DAY_OF_MONTH)) {
        val date = calendar.clone() as Calendar
        date.set(Calendar.DAY_OF_MONTH, i)
        allDays.add(date)
    }

    // Next month
    for (i in 1..daysAfter) {
        lastDayOfMonth.add(Calendar.DAY_OF_MONTH, 1)
        allDays.add(lastDayOfMonth.clone() as Calendar)
    }

    return allDays
}
