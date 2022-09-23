package com.gachaga.composetuts

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gachaga.composetuts.ui.theme.ComposeTutsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Welcome(MyOwn("Android", "2022-09-23"))
                }
            }
        }
    }
}

data class MyOwn(val name:String, val date:String)

@Composable
fun Welcome(data: MyOwn) {
  Row(modifier = Modifier.padding(all = 8.dp)){
    Image(
      painter = painterResource(id = R.drawable.ic_launcher_background),
      contentDescription = "Image",
      modifier = Modifier
      // Set image size to 40 dp
      .size(40.dp)
      // Clip image to be shaped as a circle
      .clip(CircleShape)
      // Add a horizontal space between the image and the column
      .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)


    )
    Spacer(modifier = Modifier.width(8.dp))
    Column() {
      Text(text = data.name,
        color = MaterialTheme.colors.secondaryVariant,
        style = MaterialTheme.typography.subtitle2

      )
      Spacer(modifier = Modifier.height(4.dp))
      Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {

        Text(
          text = data.date,
          style = MaterialTheme.typography.body2

        )
      }
     }
  }
}


@Preview(name = "Light Mode")
@Preview(
  uiMode = Configuration.UI_MODE_NIGHT_YES,
  showBackground = true,
  name = "Dark Mode"
)@Composable
fun DefaultPreview() {
    ComposeTutsTheme {
        Welcome(MyOwn("Pius","My Own Book"))
    }
}
