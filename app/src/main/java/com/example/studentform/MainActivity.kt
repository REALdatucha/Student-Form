package com.example.studentform

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar


private val BgDeep = Color(0xFF0A0A0F)
private val BgCard = Color(0xFF12121A)
private val BgField = Color(0xFF1A1A26)
private val NeonCyan = Color(0xFF00F5FF)
private val NeonPurple = Color(0xFFBF5FFF)
private val NeonPink = Color(0xFFFF2D78)
private val NeonGreen = Color(0xFF39FF14)
private val TextPrimary = Color(0xFFE8E8FF)
private val TextSecondary = Color(0xFF8888AA)
private val BorderIdle = Color(0xFF2A2A40)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                StudentFormScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentFormScreen() {
    val context = LocalContext.current

    var nameState by remember { mutableStateOf("") }
    var surnameState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var dateState by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf("") }
    var isAgreed by remember { mutableStateOf(false) }

    val directions = listOf("Android", "iOS", "Web", "Flutter", "Backend")

    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, day ->
            dateState = "%02d/%02d/%04d".format(day, month + 1, year)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDeep)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 32.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            listOf(Color(0xFF1A003A), Color(0xFF003A3A))
                        )
                    )
                    .padding(horizontal = 24.dp, vertical = 26.dp)
            ) {
                Column {
                    Text(
                        text = "// FORM_v2.0",
                        color = NeonCyan,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 3.sp
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "Student\nProfile",
                        color = TextPrimary,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Black,
                        lineHeight = 40.sp,
                        letterSpacing = (-1).sp
                    )
                    Spacer(Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .width(60.dp)
                            .height(3.dp)
                            .background(
                                Brush.horizontalGradient(listOf(NeonCyan, NeonPurple))
                            )
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "შეავსეთ ყველა ველი სწორად",
                        color = TextSecondary,
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(BgCard)
                    .border(
                        width = 1.dp,
                        brush = Brush.verticalGradient(listOf(NeonPurple.copy(0.4f), Color.Transparent)),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                CyberTextField(
                    value = nameState,
                    onValueChange = { nameState = it },
                    label = "სახელი",
                    placeholder = "შეიყვანეთ სახელი",
                    accentColor = NeonCyan
                )

                CyberTextField(
                    value = surnameState,
                    onValueChange = { surnameState = it },
                    label = "გვარი",
                    placeholder = "შეიყვანეთ გვარი",
                    accentColor = NeonPurple
                )

                CyberTextField(
                    value = emailState,
                    onValueChange = { emailState = it },
                    label = "ელ-ფოსტა",
                    placeholder = "example@mail.com",
                    accentColor = NeonPink
                )

                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    SectionLabel(text = "დაბადების თარიღი", color = NeonGreen)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(BgField)
                            .border(
                                width = 1.dp,
                                color = if (dateState.isEmpty()) BorderIdle else NeonGreen,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable { datePickerDialog.show() }
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (dateState.isEmpty()) "DD/MM/YYYY" else dateState,
                                color = if (dateState.isEmpty()) TextSecondary else NeonGreen,
                                fontSize = 15.sp,
                                fontWeight = if (dateState.isEmpty()) FontWeight.Normal else FontWeight.Bold
                            )
                            Text(text = "📅", fontSize = 18.sp)
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(
                            Brush.horizontalGradient(
                                listOf(Color.Transparent, NeonPurple.copy(0.5f), Color.Transparent)
                            )
                        )
                )

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    SectionLabel(text = "ფავორიტი მიმართულება", color = NeonPurple)
                    directions.forEach { direction ->
                        CyberRadioRow(
                            text = direction,
                            selected = selectedOption == direction,
                            onSelect = { selectedOption = direction }
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(
                            Brush.horizontalGradient(
                                listOf(Color.Transparent, NeonPink.copy(0.5f), Color.Transparent)
                            )
                        )
                )

                CyberSwitchRow(
                    text = "ვეთანხმები წესებს და პირობებს",
                    checked = isAgreed,
                    onCheckedChange = { isAgreed = it }
                )
            }

            Spacer(Modifier.height(28.dp))

            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(58.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Brush.horizontalGradient(listOf(NeonPurple, NeonCyan))
                    )
                    .clickable {
                        val valid = nameState.isNotBlank()
                                && surnameState.isNotBlank()
                                && emailState.isNotBlank()
                                && dateState.isNotBlank()
                                && selectedOption.isNotBlank()
                                && isAgreed

                        if (valid) {
                            Toast.makeText(context, "მონაცემები გაიგზავნა!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "შეავსეთ ყველა ველი!", Toast.LENGTH_SHORT).show()
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "SUBMIT →",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 3.sp
                )
            }
        }
    }
}


@Composable
fun SectionLabel(text: String, color: Color) {
    Text(
        text = text,
        color = color,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 2.sp
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CyberTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    accentColor: Color
) {
    val isFocused = value.isNotEmpty()
    val borderColor by animateColorAsState(
        targetValue = if (isFocused) accentColor else BorderIdle,
        animationSpec = tween(300),
        label = "border"
    )

    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        SectionLabel(text = label, color = accentColor)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    color = TextSecondary,
                    fontSize = 14.sp
                )
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = accentColor,
                unfocusedBorderColor = borderColor,
                focusedContainerColor = BgField,
                unfocusedContainerColor = BgField,
                cursorColor = accentColor,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary
            ),
            textStyle = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Medium)
        )
    }
}

@Composable
fun CyberRadioRow(text: String, selected: Boolean, onSelect: () -> Unit) {
    val bgColor by animateColorAsState(
        targetValue = if (selected) NeonPurple.copy(alpha = 0.15f) else Color.Transparent,
        animationSpec = tween(200),
        label = "radioBg"
    )
    val borderColor by animateColorAsState(
        targetValue = if (selected) NeonPurple else BorderIdle,
        animationSpec = tween(200),
        label = "radioBorder"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(bgColor)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { onSelect() }
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(RoundedCornerShape(50))
                .border(
                    width = 2.dp,
                    color = if (selected) NeonPurple else TextSecondary,
                    shape = RoundedCornerShape(50)
                ),
            contentAlignment = Alignment.Center
        ) {
            if (selected) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(RoundedCornerShape(50))
                        .background(NeonPurple)
                )
            }
        }
        Text(
            text = text,
            color = if (selected) TextPrimary else TextSecondary,
            fontSize = 14.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}

@Composable
fun CyberSwitchRow(text: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            color = if (checked) TextPrimary else TextSecondary,
            fontSize = 13.sp,
            fontWeight = if (checked) FontWeight.SemiBold else FontWeight.Normal,
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Black,
                checkedTrackColor = NeonGreen,
                uncheckedThumbColor = TextSecondary,
                uncheckedTrackColor = BorderIdle,
                uncheckedBorderColor = BorderIdle
            )
        )
    }
}