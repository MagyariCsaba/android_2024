import com.tasty.recipesapp.data.dto.RecipeDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeService {
    @GET("api/recipes")
    suspend fun getRecipes(): List<RecipeDTO>

    @GET("api/recipes/{id}")
    suspend fun getRecipeDetails(
        @Path("Id") id: String,
    ): RecipeDTO
}
