package me.ashutoshkk.blogapp.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import me.ashutoshkk.blogapp.R
import me.ashutoshkk.blogapp.domain.model.Blog
import me.ashutoshkk.blogapp.ui.theme.Poppins

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BlogItem(blog: Blog) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val (image, column) = createRefs()

        GlideImage(
            model = blog.imageUrl,
            contentDescription = stringResource(id = R.string.thumbnail),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .aspectRatio(16.toFloat() / 9)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
        )
        Column(
            modifier = Modifier
                .constrainAs(column) {
                    top.linkTo(image.top)
                    start.linkTo(image.end)
                    end.linkTo(parent.end)
                    bottom.linkTo(image.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = blog.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontFamily = Poppins,
                fontWeight = FontWeight(600),
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text = blog.date,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontFamily = Poppins,
                fontSize = 12.sp,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewBlogItem() {
    BlogItem(
        Blog(
        id = 1,
        title = "Title",
        imageUrl = "https://images.unsplash.com/photo-1632836926809-4b9b9b5b9b0f?ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwzNnx8fGVufDB8fHx8&ixlib=rb-1.2.1",
        url = "https://google.com",
        date = "2021-09-28T12:00:00"
    )
    )
}