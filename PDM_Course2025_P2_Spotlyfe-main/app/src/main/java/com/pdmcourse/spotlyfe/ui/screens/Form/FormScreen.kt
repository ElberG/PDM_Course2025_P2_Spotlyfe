package com.pdmcourse.spotlyfe.ui.screens.Form

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pdmcourse.spotlyfe.ui.components.SelectLocationMap
import androidx.compose.material3.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(viewModel: FormViewModel, onBack: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var remark by remember { mutableStateOf("") }
    val location by viewModel.location.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("New Place") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "Back",
                            modifier = Modifier.size(48.dp))
                    }
                }
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") }
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = remark,
                onValueChange = { remark = it },
                label = { Text("Description") }
            )
            Spacer(Modifier.height(8.dp))
            SelectLocationMap(
                initialPosition = location,
                onLocationChanged = { viewModel.updateLocation(it) }
            )
            Spacer(Modifier.height(2.dp))
            Button(
                onClick = {
                    if (name.isNotBlank()) {
                        viewModel.savePlace(name, remark, onBack)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save")
            }
        }
    }
}
