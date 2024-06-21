package com.daridasar.pedagang


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.daridasar.pedagang.entity.Product
import com.daridasar.pedagang.theme.AppTheme
import com.daridasar.pedagang.theme.app_theme_successful
import com.daridasar.pedagang.theme.app_theme_unsuccessful
import org.koin.androidx.compose.koinViewModel

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun App() {
    val viewModel = koinViewModel<ProductViewModel>()
    val state by remember { viewModel.state }
    val pullToRefreshState = rememberPullToRefreshState()
    if (pullToRefreshState.isRefreshing) {
        viewModel.loadProducts()
        pullToRefreshState.endRefresh()
    }
    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(
                        "SpaceX Launches",
                        style = MaterialTheme.typography.headlineLarge
                    )
                })
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .nestedScroll(pullToRefreshState.nestedScrollConnection)
                    .fillMaxSize()
                    .padding(padding)
            ) {
                if (state.isLoading) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text("Loading...", style = MaterialTheme.typography.bodyLarge)
                    }
                } else {
                    LazyColumn {
                        items(state.products) { product: Product ->
                            Column(modifier = Modifier.padding(all = 16.dp)) {
                                Text(
                                    text = "${product.name} - ${product.price}",
                                    style = MaterialTheme.typography.headlineSmall
                                )
                                Spacer(Modifier.height(8.dp))
                                Spacer(Modifier.height(8.dp))
                                val details = product.price
                                    Text(
                                        text = "${details}"
                                    )
                            }
                            HorizontalDivider()
                        }
                    }
                }

                PullToRefreshContainer(
                    state = pullToRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        }
    }
}