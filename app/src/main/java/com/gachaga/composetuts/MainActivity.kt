package com.gachaga.composetuts

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.foundation.lazy.items
 import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutsTheme {
                // A surface container using the 'background' color from the theme
              Conversation(SampleData.conversationSample)

            }
        }
    }
}
@Composable
fun Conversation(messages:List<Message>){
  LazyColumn{
    items(messages){message->
      Welcome(message)
    }
  }

}

data class Message(val author:String, val body:String)

@Preview
@Composable
fun PreviewConversation(){
  ComposeTutsTheme() {
    Conversation(SampleData.conversationSample)

  }
}

@Composable
fun Welcome(data: Message) {
  Row(modifier = Modifier.padding(all = 8.dp)) {
    Image(
      painter = painterResource(R.drawable.ic_launcher_background),
      contentDescription = null,
      modifier = Modifier
        .size(40.dp)
        .clip(CircleShape)
        .border(1.5.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
    )
    Spacer(modifier = Modifier.width(8.dp))

    // We keep track if the message is expanded or not in this
    // variable
    var isExpanded: Boolean by remember { mutableStateOf(false) }
    val surfaceColor by animateColorAsState(
      if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
    )

    Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
      Text(
        text = data.author,
        color = MaterialTheme.colors.secondaryVariant,
        style = MaterialTheme.typography.subtitle2
      )

      Spacer(modifier = Modifier.height(4.dp))

      Surface(
        shape = MaterialTheme.shapes.medium,
        elevation = 1.dp,
         // surfaceColor color will be changing gradually from primary to surface
        color = surfaceColor,
        // animateContentSize will change the Surface size gradually
        modifier = Modifier.animateContentSize().padding(1.dp)

      ) {
        Text(
          text = data.body,
          modifier = Modifier.padding(all = 4.dp),
          // If the message is expanded, we display all its content
          // otherwise we only display the first line
          maxLines = if (isExpanded) Int.MAX_VALUE else 1,
          style = MaterialTheme.typography.body2
        )
      }
    }
  }
}

