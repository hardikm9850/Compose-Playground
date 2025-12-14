package com.hardik.feature_todo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hardik.feature_todo.beans.Todo
import kotlinx.coroutines.launch

@Composable
fun ToDoScreen() {
    val todos = remember { mutableStateListOf<Todo>(
        Todo("A"),
        Todo("A"),
        Todo("A"),Todo("A"),Todo("A"),Todo("A"),Todo("A"),
        Todo("A"),Todo("A"),Todo("A"),Todo("A"),Todo("A"),
    ) }
    // The lambda does not re-run when todos changes
    // val completedTodos = remember { todos.count { it.isCompleted } }
    // Runs every time ToDoScreen() recomposes
    // val completedTodos = todos.count { it.isCompleted }

    // The block runs only when todos changes
    val completedCount by remember {
        derivedStateOf {
            todos.count { it.isCompleted }
        }
    }
    // The block runs only when todos changes
    val isEmpty by remember {
        derivedStateOf {
            todos.isEmpty()
        }
    }

    var showSheet by remember { mutableStateOf(false) }
    val lazyColumnState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    if (showSheet) {
        AddToDo({
            showSheet = false
        }, {
            todos.add(Todo(it))
            showSheet = false
            coroutineScope.launch {
                lazyColumnState.animateScrollToItem(todos.size - 1)
            }
        })
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            when(isEmpty) {
                true -> ShowEmptyState()
                false -> ShowTodoList(todos, lazyColumnState)
            }

        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp), onClick = {
                showSheet = true
            }) {
            Text("+", fontSize = 30.sp)
        }
    }
}

@Composable
fun ShowEmptyState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "No Todos",
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}

@Composable
fun ColumnScope.ShowTodoList(todos: MutableList<Todo>, lazyColumnState: LazyListState) {
    Spacer(
        modifier = Modifier.height(20.dp)
    )
    Text(
        "Add ToDo",
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(
        modifier = Modifier.height(20.dp)
    )
    Divider(
        color = Color.Gray
    )
    Spacer(
        modifier = Modifier.height(20.dp)
    )

    LazyColumn(
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth(),
        state = lazyColumnState
    ) {
        item {
            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }
        items(todos.size) { index ->
            val todo = todos[index]

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        todos[index] =
                            todos[index].copy(isCompleted = todos[index].isCompleted.not())
                    }
                    .padding(16.dp)
            ) {
                Text(
                    todo.text,
                    textDecoration = if (todo.isCompleted)
                        TextDecoration.LineThrough
                    else
                        TextDecoration.None,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview
@Composable
fun previewToDoScreen() {
    ToDoScreen()
}