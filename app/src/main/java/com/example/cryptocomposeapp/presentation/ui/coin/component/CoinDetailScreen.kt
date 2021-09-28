package com.example.cryptocomposeapp.presentation.ui.coin.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptocomposeapp.presentation.ui.coin.CoinDetailViewModel
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    if (state.success != null) {
        Box(modifier = Modifier.fillMaxSize()) {
            state.success.let {
                LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(20.dp)) {
                    item {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "${it.rank}. ${it.name} ${it.symbol}")

                            Text(text = if (it.is_active) "active" else "inactive",
                                color = if (it.is_active) Color.Green else Color.Red,
                                fontStyle = FontStyle.Italic,
                                textAlign = TextAlign.End,
                                modifier = Modifier
                                    .weight(2f)
                                    .align(CenterVertically)
                            )
                        }

                        Spacer(modifier = Modifier.height(15.dp))

                        Text(text = "Tags",
                            style = MaterialTheme.typography.h4)
                        Spacer(modifier = Modifier.height(15.dp))
                        FlowRow(
                            mainAxisSpacing = 10.dp,
                            crossAxisSpacing = 10.dp,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            it.tags.forEach { tag ->
                                CoinTag(tag = tag.name)
                            }
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(text = "Team members",
                            style = MaterialTheme.typography.h5)

                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    items(it.team) { teamMember ->
                        TeamListItem(teamMember = teamMember, modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp))
                    }
                }
            }
        }
    }

    if (state.error?.isNotBlank() == true) {

        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Text(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
                text = state.error,
                color = MaterialTheme.colors.error)
        }
    }

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            CircularProgressIndicator(modifier = Modifier)
        }
    }
}