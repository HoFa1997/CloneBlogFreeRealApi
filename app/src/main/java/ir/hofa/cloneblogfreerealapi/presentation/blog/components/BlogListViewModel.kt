package ir.hofa.cloneblogfreerealapi.presentation.blog.components

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hofa.cloneblogfreerealapi.common.Resource
import ir.hofa.cloneblogfreerealapi.domain.use_case.blog.ReqGetBlog
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BlogListViewModel @Inject constructor(
    private val useCase: ReqGetBlog,
    spLocalBlog: SharedPreferences,
) : ViewModel() {

    private val _state = mutableStateOf(BlogListState())
    val state: State<BlogListState> = _state
    private val token: String? = spLocalBlog.getString("token", "tokenString")

    init {
        getBlog()
    }

    private fun getBlog() {
        useCase(token!!).onEach { result ->
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

