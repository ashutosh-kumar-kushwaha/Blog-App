package me.ashutoshkk.blogapp.presentation.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import me.ashutoshkk.blogapp.common.ConnectivityObserver
import me.ashutoshkk.blogapp.common.NetworkConnectivityObserver
import me.ashutoshkk.blogapp.domain.model.Blog
import me.ashutoshkk.blogapp.presentation.home.components.BlogItem
import me.ashutoshkk.blogapp.presentation.viewModels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onItemClick: (String) -> Unit = {}
) {
    val blogs = viewModel.blogs.collectAsLazyPagingItems()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val connectivityObserver =
        NetworkConnectivityObserver(LocalContext.current)
    var showConnectedSnackbar by remember {
        mutableStateOf(false)
    }

    val connectivityStatus = connectivityObserver.observe()
        .collectAsStateWithLifecycle(ConnectivityObserver.Status.Unavailable)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Blog App",
                        color = Color.Black,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                    )
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White)
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        HomeScreenContent(blogs, it) { url ->
            onItemClick(url)
        }
    }

    LaunchedEffect(connectivityStatus.value) {
        when (connectivityStatus.value) {
            ConnectivityObserver.Status.Available -> {
                if(showConnectedSnackbar){
                    snackbarHostState.showSnackbar(
                        message = "Internet Connected",
                        duration = SnackbarDuration.Short
                    )
                }
            }

            ConnectivityObserver.Status.Losing -> {
                snackbarHostState.showSnackbar(
                    message = "Internet Losing",
                    duration = SnackbarDuration.Short
                )
            }

            ConnectivityObserver.Status.Lost -> {
                showConnectedSnackbar = true
                snackbarHostState.showSnackbar(
                    message = "Internet Lost",
                    duration = SnackbarDuration.Indefinite
                )
            }

            ConnectivityObserver.Status.Unavailable -> {
                snackbarHostState.showSnackbar(
                    message = "Internet Unavailable",
                    duration = SnackbarDuration.Indefinite
                )
            }
        }
    }
}

@Composable
fun HomeScreenContent(
    blogs: LazyPagingItems<Blog>,
    paddingValues: PaddingValues,
    onItemClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(paddingValues)
            .padding(horizontal = 24.dp),
        contentPadding = PaddingValues(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(blogs.itemCount) {
            BlogItem(
                blogs[it]!!
            ) { url ->
                onItemClick(url)
            }
        }
        when (val state = blogs.loadState.refresh) {
            is LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier.fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            is LoadState.Error -> {
                item {
                    Column(
                        modifier = Modifier.fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        state.error.localizedMessage?.let {
                            Text(
                                text = it,
                                color = Color.Black,
                                fontSize = 16.sp,
                            )
                        }
                    }
                }
            }

            else -> {}
        }

        when (val state = blogs.loadState.append) {
            is LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            is LoadState.Error -> {
                item {
                    Column(
                        modifier = Modifier.fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        state.error.localizedMessage?.let {
                            Text(
                                text = it,
                                color = Color.Black,
                                fontSize = 16.sp,
                            )
                        }
                    }
                }
            }

            else -> {}
        }
    }
}