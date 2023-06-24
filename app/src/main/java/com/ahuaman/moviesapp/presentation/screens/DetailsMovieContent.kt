package com.ahuaman.moviesapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ahuaman.moviesapp.R
import com.ahuaman.moviesapp.domain.GenreDomain
import com.ahuaman.moviesapp.ui.theme.Green40

@Composable
fun DetailsMovieContent(
    onClickBack: () -> Unit,
    onClickFavorite: () -> Unit,
    title: String,
    description: String,
    imageBackdrop: String,
    imagePoster: String,
    genres: List<GenreDomain>,
    releaseDate: String,
    voteAverage: String,
    runtime: String,
) {
    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically

        ) {

            Icon(
                modifier = Modifier.clickable {
                    onClickBack()
                },
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = null,
                tint = Color.Black,
            )
            //title
            Text(
                text = "Detalles",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                fontFamily = FontFamily(Font(R.font.googlesans_regular, FontWeight.Normal)),
                modifier = Modifier.padding(start = 24.dp)
            )

            Icon(
                modifier = Modifier.clickable {
                    onClickFavorite()
                },
                painter = painterResource(id = R.drawable.ic_unfavorite),
                contentDescription = null,
                tint = Color.Black,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomEnd = 20.dp,
                    bottomStart = 20.dp
                ),
            ) {
                Box() {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(210.dp),
                        model = imageBackdrop,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                    )

                    androidx.compose.material3.Card(
                        modifier = Modifier
                            .offset(x = 310.dp, y = 178.dp)
                            .width(54.dp)
                            .height(24.dp)
                            .background(
                                color = Color(0x52252836),
                                shape = RoundedCornerShape(size = 8.dp)
                            )
                            .padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = androidx.compose.ui.graphics.Color.Transparent),
                    ) {
                        Row() {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_favorite),
                                contentDescription = null,
                                tint = Green40,
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = voteAverage,
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.googlesans_regular)),
                                fontWeight = FontWeight(600),
                                color = Green40
                            )
                        }
                    }
                }
            }

            androidx.compose.material3.Card(
                modifier = Modifier
                    .offset(x = 29.dp, y = 150.dp)
                    .width(95.dp)
                    .height(120.dp),
                colors = CardDefaults.cardColors(
                    containerColor = androidx.compose.ui.graphics.Color.Gray),
                shape = RoundedCornerShape (16.dp),
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp),
                    model = imagePoster,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                )
            }

            //Title
            Text(
                modifier = Modifier
                    .width(210.dp)
                    .height(48.dp)
                    .offset(x = 140.dp, y = 220.dp),
                text = title,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.googlesans_regular, FontWeight.Normal)),
                fontWeight = FontWeight(600),
            )

        }

        Spacer(modifier = Modifier.height(75.dp))

        HorizontalThreeOptions(yearRelease = releaseDate, duration = runtime , genre = if(genres.firstOrNull() == null) "" else genres.firstOrNull()?.name.toString())

        Spacer(modifier = Modifier.height(24.dp))

        //Description Title
        Text(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            text = "Description",
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.googlesans_regular, FontWeight.Normal)),
            fontWeight = FontWeight(600),
        )

        Spacer(modifier = Modifier.height(12.dp))

        //Description
        Text(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            text = description,
            textAlign = TextAlign.Justify,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.googlesans_regular, FontWeight.Normal)),
            fontWeight = FontWeight(400),
        )

    }
}

@Composable
fun HorizontalThreeOptions(
    yearRelease : String ,
    duration : String ,
    genre : String ,
) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.Center,
    ) {

        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(id = R.drawable.ic_calendar),
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(4.dp))

        //Year
        Text(
            text = yearRelease,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            fontFamily = FontFamily(Font(R.font.googlesans_regular, FontWeight.Normal)),
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_line),
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_clock),
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(4.dp))

        //Duration
        Text(
            text = duration,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            fontFamily = FontFamily(Font(R.font.googlesans_regular, FontWeight.Normal)),
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_line),
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_ticket),
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(4.dp))

        //Genre
        Text(
            text = genre,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            fontFamily = FontFamily(Font(R.font.googlesans_regular, FontWeight.Normal)),
        )

    }

}


@Preview
@Composable
fun DetailsMovieContentPrev() {
    DetailsMovieContent(
        title = "Spiderman No Way Home",
        description = "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.",
        imageBackdrop = "https://image.tmdb.org/t/p/w500/vViRXFnSyGJ2fzMbcc5sqTKswcd.jpg",
        imagePoster = "https://image.tmdb.org/t/p/w500/eLzStFuergouErSQlfABthuQHCJ.jpg",
        genres = listOf(GenreDomain(name = "Action")),
        releaseDate = "2021-12-15",
        voteAverage = "9.5",
        runtime = "118 minutos",
        onClickBack = {},
        onClickFavorite = {}
    )
}