package ir.hofa.cloneblogfreerealapi.presentation.blog

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hofa.cloneblogfreerealapi.common.Resource
import ir.hofa.cloneblogfreerealapi.domain.use_case.ReqGetBlog
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BlogListViewModel @Inject constructor(

    private val useCase: ReqGetBlog

) : ViewModel() {

    private val _state = mutableStateOf(BlogListState())
    val state: State<BlogListState> = _state

    init {
        getBlog()
    }

    private fun getBlog() {
        useCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = BlogListState(blog = result.data?.blogs ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = BlogListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = BlogListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}

