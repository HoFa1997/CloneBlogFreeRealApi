package ir.hofa.cloneblogfreerealapi.presentation.blog.components

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hofa.cloneblogfreerealapi.common.Resource
import ir.hofa.cloneblogfreerealapi.domain.model.blog.newblog.NewBlog
import ir.hofa.cloneblogfreerealapi.domain.use_case.blog.ReqDelBlog
import ir.hofa.cloneblogfreerealapi.domain.use_case.blog.ReqGetBlog
import ir.hofa.cloneblogfreerealapi.domain.use_case.blog.ReqNewBlog
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BlogListViewModel @Inject constructor(
    private val useCaseGetNewBlog: ReqNewBlog,
    private val useCaseGetBlog: ReqGetBlog,
    private val useCaseDelBlog: ReqDelBlog,
    spLocalBlog: SharedPreferences,
) : ViewModel() {

    private val _stateGetBlog = mutableStateOf(BlogListState())
    val stateGetBlog: State<BlogListState> = _stateGetBlog
    private val token: String? = spLocalBlog.getString("token", "tokenString")


    private val _stateNewBlog = mutableStateOf(NewBlogState())
    val stateNewBlog: State<NewBlogState> = _stateNewBlog

    private val _stateDelBlog = mutableStateOf(DeleteBlogState())
    val stateDelNewBlog: State<DeleteBlogState> = _stateDelBlog


    init {
        getBlog()
    }

    fun getBlog() {
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

    fun reqNewBlog(body: NewBlog) {
        useCaseGetNewBlog(token!!, body).onEach { result ->
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

    fun reqDelBlog(blogId: String) {
        useCaseDelBlog(token!!, blogId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateDelBlog.value = DeleteBlogState(blog = result.data)
                }
                is Resource.Error -> {
                    _stateDelBlog.value = DeleteBlogState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _stateDelBlog.value = DeleteBlogState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}

