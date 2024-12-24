package gaur.himanshu.august.moviedetails.data

import com.example.test23.test.data.Movie

data class MovieResponse(
    val Response: String,
    val Search: List<Movie>,
    val totalResults: String
)