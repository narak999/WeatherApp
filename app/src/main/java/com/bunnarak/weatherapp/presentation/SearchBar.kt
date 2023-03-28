package com.bunnarak.weatherapp.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalCoroutinesApi
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "Search",
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Search,
    onSearch: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    var searchText by remember { mutableStateOf("") }
    Row(
        modifier.requiredHeight(65.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = { Text(hint) },
            textStyle = MaterialTheme.typography.body1,
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
                .clip(CircleShape)
                .onKeyEvent {keyEvent ->
                    if (keyEvent.key != Key.Enter) return@onKeyEvent false
                    if (keyEvent.type == KeyEventType.KeyUp) {
                        onSearch(searchText)
                        focusManager.clearFocus()
                    }
                    false
                },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
                imeAction = imeAction,
            ),
            keyboardActions = KeyboardActions(onDone = { onSearch(searchText) }),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.Blue,
                disabledIndicatorColor = Color.LightGray
            )
        )
        IconButton(
            modifier = Modifier.padding(top = 9.dp),
            onClick = { onSearch(searchText) }
        ) {
            Icon(Icons.Filled.Search, "Search")
        }
    }
}