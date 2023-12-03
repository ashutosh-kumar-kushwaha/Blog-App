package me.ashutoshkk.blogapp.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import me.ashutoshkk.blogapp.R
import me.ashutoshkk.blogapp.domain.model.Blog
import me.ashutoshkk.blogapp.ui.theme.Poppins

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BlogItem() {
    val blog = Blog(
        title = "This is a blog title",
        imageUrl = "https://images.unsplash.com/photo-1634179617417-8b9b8b5b9b0f?ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwzNnx8fGVufDB8fHx8&ixlib=rb-1.2.1&w=1000&q=80",
        url = "https://google.com",
        date = "2 days ago",
        id = 0
    )
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val (image, title, date) = createRefs()

        GlideImage(
            model = blog.imageUrl,
            contentDescription = stringResource(id = R.string.thumbnail),
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .aspectRatio((4 / 3).toFloat())
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )
        Column(
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(image.top)
                    start.linkTo(image.end)
                    end.linkTo(parent.end)
                    bottom.linkTo(image.bottom)
                },
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = blog.title,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                fontFamily = Poppins,
                fontWeight = FontWeight(600),
                fontSize = 20.sp
            )
            Text(
                text = blog.date,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontFamily = Poppins,
                fontSize = 12.sp
            )
        }
    }
}

@Preview
@Composable
fun PreviewBlogItem() {
    BlogItem()
}