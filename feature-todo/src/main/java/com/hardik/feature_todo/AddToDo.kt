package com.hardik.feature_todo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToDo(onDismiss: () -> Unit, onSubmitted: (String) -> Unit) {
    val bottomSheetState = rememberModalBottomSheetState()


    var text by remember { mutableStateOf("") }


    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = bottomSheetState,
        dragHandle = {}
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)) {
            Text(
                text = "Add Todo",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Task") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))


            Row(modifier = Modifier.fillMaxWidth()) {

                TextButton(
                    onClick = {},
                    modifier = Modifier.weight(1f)
                ) { Text("Cancel") }

                Spacer(Modifier.width(8.dp))

                Button(
                    onClick = { onSubmitted.invoke(text) },
                    modifier = Modifier.weight(1f),
                    enabled = text.isNotBlank()
                ) { Text("Add") }
            }

        }
    }
}

@Composable
fun previewAddToDo() {
    AddToDo({},{})
}