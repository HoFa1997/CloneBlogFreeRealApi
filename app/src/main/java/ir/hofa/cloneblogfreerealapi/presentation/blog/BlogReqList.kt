package ir.hofa.cloneblogfreerealapi.presentation.blog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.hofa.cloneblogfreerealapi.domain.model.blog.BlogItem

@Composable
fun BlogReqList(blog: BlogItem) {
    Column {
        Text(text = blog.id)
        Text(text = blog._id)
        Text(text = blog.image)
        Text(text = blog.title)
        Text(text = blog.createdAt)
        Text(text = blog.updatedAt)
        Spacer(modifier = Modifier.height(50.dp))
    }
}