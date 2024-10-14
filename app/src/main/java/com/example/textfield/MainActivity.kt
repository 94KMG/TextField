
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.textfield.ui.theme.TextFieldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TextFieldTheme {
               DynamicTextFieldScreen()
            }
        }
    }
}

@Composable
fun DynamicTextFieldScreen() {
    val notes = remember { mutableStateListOf("") } // 첫 번째 빈 필드 생성
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        items(notes.size) { index ->
            TextField(
                value = notes[index],
                onValueChange = { newValue ->
                    notes[index] = newValue

                    // 새로운 텍스트 필드를 동적으로 추가
                    if (newValue.isNotEmpty() && index == notes.size - 1) {
                        notes.add("") // 새로운 빈 필드 추가
                    }

                    // 텍스트가 지워지면 필드를 제거
                    if (newValue.isEmpty()) {
                        notes.removeAt(index)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}