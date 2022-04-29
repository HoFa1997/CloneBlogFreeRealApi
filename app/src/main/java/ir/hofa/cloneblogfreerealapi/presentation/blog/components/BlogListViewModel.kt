package ir.hofa.cloneblogfreerealapi.presentation.blog.components

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hofa.cloneblogfreerealapi.common.Resource
import ir.hofa.cloneblogfreerealapi.domain.model.blog.newblog.NewBlog
import ir.hofa.cloneblogfreerealapi.domain.use_case.blog.ReqGetBlog
import ir.hofa.cloneblogfreerealapi.domain.use_case.blog.newblog.ReqNewBlog
import ir.hofa.cloneblogfreerealapi.presentation.blog.newblog.components.NewBlogState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BlogListViewModel @Inject constructor(
    private val useCaseGetNewBlog: ReqNewBlog,
    private val useCaseGetBlog: ReqGetBlog,
    spLocalBlog: SharedPreferences,
) : ViewModel() {

    private val _stateGetBlog = mutableStateOf(BlogListState())
    val stateGetBlog: State<BlogListState> = _stateGetBlog
    private val token: String? = spLocalBlog.getString("token", "tokenString")


    private val _stateNewBlog = mutableStateOf(NewBlogState())
    val stateNewBlog: State<NewBlogState> = _stateNewBlog


    init {
        getBlog()
    }

    private fun getBlog() {
        useCaseGetBlog(token!!).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateGetBlog.value = BlogListState(blog = result.data?.blogs ?: emptyList())
                }
                is Resource.Error -> {
                    _stateGetBlog.value = BlogListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _stateGetBlog.value = BlogListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getNewBlog(body: NewBlog) {
        useCaseGetNewBlog(body).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateNewBlog.value = NewBlogState(blog = result.data)
                }
                is Resource.Error -> {
                    _stateNewBlog.value = NewBlogState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _stateNewBlog.value = NewBlogState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}

